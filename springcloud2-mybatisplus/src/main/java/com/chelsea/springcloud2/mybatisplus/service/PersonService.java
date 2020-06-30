package com.chelsea.springcloud2.mybatisplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chelsea.springcloud2.mybatisplus.dao.PersonDao;
import com.chelsea.springcloud2.mybatisplus.domain.Person;

@Service
public class PersonService {
    
    @Autowired
    private PersonDao personDao;

    /**
     * 单条新增
     * @param person
     */
    @Transactional
    public void insert(Person person) {
        personDao.insert(person);
    }
    
    /**
     * 分页查询（带条件）
     * @return 
     */
    @Transactional
    public Page<Person> selectPage(Page<Person> page, Wrapper<Person> wrapper) {
        return personDao.selectPage(page, wrapper);
    }
    
    /**
     * 更新数据（带条件）
     * @param person
     * @param wrapper
     */
    @Transactional
    public void update(Person person, Wrapper<Person> wrapper) {
        personDao.update(person, wrapper);
    }
    
}
