package com.yuan.controller;

import com.yuan.mapper.StudentMapper;
import com.yuan.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/studentList")
    public List<Student> queryStudentList(){
        List<Student> list=studentMapper.queryStudentList();
        return list;
    }
}
