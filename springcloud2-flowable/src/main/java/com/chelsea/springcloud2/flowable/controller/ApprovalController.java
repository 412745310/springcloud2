package com.chelsea.springcloud2.flowable.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 审批controller
 *
 */
@RestController
@RequestMapping("/approval")
public class ApprovalController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;
    
    /**
     * 开启工作流
     * 
     * @param key
     * @param serviceName
     * @return
     */
    @RequestMapping("/startProcess")
    public String startProcess(String key) {
        Map<String, Object> map = new HashMap<>();
        map.put("variable", "全局变量");
        String processId = "工单主键id";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, processId, map);
        return "工作开启成功，流程实例ID为：" + processInstance.getProcessInstanceId();
    }
    
    /**
     * 获取指定用户组流程任务列表
     *
     * @param group
     * @return
     */
    @RequestMapping("/listByGroup")
    public Object listByGroup(String group) {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(group).list();
        return tasks.toString();
    }
    
    /**
     * 获取指定用户流程任务列表
     *
     * @param group
     * @return
     */
    @RequestMapping("/listByUser")
    public Object listByUser(String user) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(user).list();
        for (Task task : tasks) {
            ProcessInstance pi =
                    runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId())
                            .singleResult();
            Map<String, Object> map = new HashMap<>();
            String businessKey = pi.getBusinessKey();
            String taskId = task.getId();
            map.put("processId", task.getProcessInstanceId());
            map.put("taskId", taskId);
            map.put("businessKey", businessKey);
            list.add(map);
        }
        return list.toString();
    }
    
    /**
     * 执行审批
     * 
     * @param taskId
     * @param approved
     * @return
     */
    @RequestMapping("/approved")
    public String approved(String taskId, String approved) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return "节点不存在";
        }
        Map<String, Object> variables = new HashMap<>();
        Boolean result = approved.equals("1") ? true : false;
        variables.put("approved", result);
        taskService.complete(taskId, variables);
        return "审批是否通过：" + result;
    }
    
    
    /**
     * 查询流程图
     *
     * @param httpServletResponse
     * @param processId
     * @throws Exception
     */
    @RequestMapping(value = "/processDiagram")
    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();

        //流程走完的不显示图
        if (pi == null) {
            return;
        }
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        String InstanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService
                .createExecutionQuery()
                .processInstanceId(InstanceId)
                .list();

        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        // InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0, true);
        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, "宋体", "宋体", "宋体", engconf.getClassLoader(), 1.0, true);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = httpServletResponse.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
