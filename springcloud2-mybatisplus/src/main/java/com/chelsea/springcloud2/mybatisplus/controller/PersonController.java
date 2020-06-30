package com.chelsea.springcloud2.mybatisplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chelsea.springcloud2.mybatisplus.domain.Person;
import com.chelsea.springcloud2.mybatisplus.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    
    @RequestMapping("/insert")
    public void insert() {
        Person person = new Person();
        person.setAge(20);
        person.setName("aaa");
        personService.insert(person);
    }
    
    @RequestMapping("/selectPage")
    public Page<Person> selectPage() {
        Page<Person> page = new Page<>(1, 2);
        QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "aaa");
        Page<Person> result = personService.selectPage(page, queryWrapper);
        return result;
    }
    
    @RequestMapping("/update")
    public void update() {
        Person person = new Person();
        person.setName("bbb");
        QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "aaa");
        personService.update(person, queryWrapper);
    }
}
