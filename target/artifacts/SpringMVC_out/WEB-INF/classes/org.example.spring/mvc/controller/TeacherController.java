package org.example.spring.mvc.controller;

import org.example.spring.mvc.bean.Homework;
import org.example.spring.mvc.bean.Student;
import org.example.spring.mvc.jdbc.StudentHomeworkJDBC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
/*@RequestMapping("/Teacher/")*/
public class TeacherController  extends HttpServlet {
    @RequestMapping("/add")
    public void AddStudentHomeworkServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");


        Homework hw = new Homework();
        hw.setId(id);
        hw.setTitle(new String(title.getBytes("ISO-8859-1"), "UTF-8"));
        hw.setContent(new String(content.getBytes("ISO-8859-1"), "UTF-8"));
        StudentHomeworkJDBC add = new StudentHomeworkJDBC();
        add.addHomework(hw);
    }

    @RequestMapping("/addStu")
    public void AddStudentServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");


        Student stu = new Student();
        stu.setId(id);
        stu.setName(new String(name.getBytes("ISO-8859-1"), "UTF-8"));
        StudentHomeworkJDBC addStu = new StudentHomeworkJDBC();
        addStu.addStudent(stu);
    }

}