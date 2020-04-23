package org.example.spring.mvc.service;

import org.example.spring.mvc.bean.Homework;
import org.example.spring.mvc.bean.Student;
import org.example.spring.mvc.bean.StudentHomework;
import org.example.spring.mvc.jdbc.StudentHomeworkJDBC;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class JdbcService {


    /*    public void getById(Long id){
            System.out.println("getById("+ id + ")");
        }*/
    public JdbcService getJdbcService(){
        return new JdbcService();
    }

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StudentHomeworkJDBC.class);
    StudentHomeworkJDBC studentHomeworkJdbc = (StudentHomeworkJDBC)context.getBean("studentHomeworkJdbc");

    public  List<StudentHomework> selectAll(){
        return StudentHomeworkJDBC.selectAll();
    }

    public  List<Student> selectAllStudent(){
        return StudentHomeworkJDBC.selectAllStudent();
    }
    public  List<Homework> selectAllHomework(){
        return StudentHomeworkJDBC.selectAllHomework();
    }

    public boolean addHomework(Homework hw){
        return studentHomeworkJdbc.addHomework(hw);
    }

    public boolean addStudent(Student stu){
        return studentHomeworkJdbc.addStudent(stu);
    }

    public boolean submitHomework(StudentHomework studentHomework){
        return studentHomeworkJdbc.submitHomework(studentHomework);
    }

    public boolean isExistStudent(Long id){
        return studentHomeworkJdbc.isExistStudent(id);
    }

    public boolean isExistHomework(Long id){
        return studentHomeworkJdbc.isExistHomework(id);
    }
}