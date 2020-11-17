package com.ab;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @classname: School
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/17、21:48
 */
@Data
@Component
public class School implements ISchool {
    // Resource
    @Autowired(required = true) //primary
    Klass class1;
    @Resource(name = "student100")
    Student student100;

    @Override
    public void ding(){
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
    }

}
