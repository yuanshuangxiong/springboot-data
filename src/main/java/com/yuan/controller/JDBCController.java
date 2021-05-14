package com.yuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库的所有信息
    //没有实体类，数据库中东西通过map封装
    @GetMapping("/userList")
    public List<Map<String,Object>>userList(){
        String sql="select * from student";
        List<Map<String,Object>>list_maps= jdbcTemplate.queryForList(sql);
        return list_maps;
    }

    @GetMapping("/addStudent")
    public String addStudent(){
        String sql="insert into student(id,name,password,tid) values(10,'小明','123456',1)";
        jdbcTemplate.update(sql);
        return "update-ok";
    }

    @GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable Integer id){
        String sql="update student set name=?,password=? where id="+id;

        Object[] objects=new Object[2];
        objects[0]="小明2";
        objects[1]="123123";
        jdbcTemplate.update(sql,objects);
        return "modify-ok";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deteleStudent(@PathVariable Integer id){
        String sql="delete from student where id=?";
        jdbcTemplate.update(sql,id);
        return "delete-ok";
    }
}
