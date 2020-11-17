package com.ab;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @classname: StudentConfigure 自动配置 Klass和 Student
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/17、21:50
 */
@Configuration
public class SchoolAutoConfiguration {

    /**
     * @description: 自动配置  class1
     * @param
     * @return com.ab.Klass
     * @author sunxinbo
     * @time 2020/11/17 21:56
     */
    @Bean(name = "class1")
    public Klass getKlass(){
        Klass klass = new Klass();
        List<Student> students = new ArrayList<>();
        Student student = new Student(1,"sunxin");
        students.add(student);
        klass.setStudents(students);
        System.out.println("自动装配 class1");
        return klass;
    }

    /**
     * @description 自动配置 student100
     * @param
     * @return com.ab.Student
     * @author sunxinbo
     * @time 2020/11/17 21:59
     */
    @Bean(name = "student100")
    public Student getStudent(){
        Student student = new Student();
        System.out.println("自动装配 student100");
        return student.create();
    }
}
