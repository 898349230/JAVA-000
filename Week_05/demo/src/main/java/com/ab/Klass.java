package com.ab;

import lombok.Data;

import java.util.List;

/**
 * @classname: Xlass
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/17、21:46
 */
@Data
public class Klass {

    List<Student> students;

    public void dong(){
        System.out.println(this.getStudents());
    }

}
