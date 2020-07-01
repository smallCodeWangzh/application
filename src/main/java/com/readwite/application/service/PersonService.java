package com.readwite.application.service;

import com.readwite.application.bean.Person;
import com.readwite.application.config.Read;
import com.readwite.application.config.Write;
import com.readwite.application.dao.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonMapper personMapper;
    /**
     * 代表该方法对数据库的操作是一个写操作
     * @param person
     */
    @Write
    public void add(Person person) {
        personMapper.insert(person);
    }


    /**
     * 代表该方法对数据库的操作是一个读操作
     */
    @Read
    public List<Person> findAll() {
        return personMapper.selectAll();
    }
}
