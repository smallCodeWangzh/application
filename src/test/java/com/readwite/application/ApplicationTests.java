package com.readwite.application;

import com.readwite.application.bean.Person;
import com.readwite.application.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private PersonService personService;

    @Test
    public void contextLoads() {
        Person person = new Person();
        person.setName("wangwu");
        person.setAge(18);
        personService.add(person);
    }

    @Test
    public void testQuery() {
        List<Person> all = personService.findAll();
        all.forEach(System.out::println);
    }


}
