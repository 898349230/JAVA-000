package com.ab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @classname: Student
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/17、21:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable {

    private int id;
    private String name;

    public void init(){
        System.out.println("hello...........");
    }

    public Student create(){
        return new Student(101,"KK101");
    }
}