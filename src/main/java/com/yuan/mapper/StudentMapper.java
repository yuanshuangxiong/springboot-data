package com.yuan.mapper;

import com.yuan.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //dao
@Mapper//这个注解表示这是一个mapper类
public interface StudentMapper {

    List<Student>queryStudentList();

    Student queryStudentById(int id);

    int addStudent (Student student);

    int updateStudent(Student student);

    int deleteStudent(int id);

}
