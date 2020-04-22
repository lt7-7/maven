package org.example.spring.mvc.controller;

import org.example.spring.mvc.bean.StudentHomework;
import org.example.spring.mvc.bean.bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller

public class StudentController extends HttpServlet{
    @RequestMapping("/submitHomework")
    public void SubmitHomeworkServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Long studentId = Long.parseLong(req.getParameter("studentId"));
        Long homeworkId = Long.parseLong(req.getParameter("homeworkId"));
        String homeworkTitle = req.getParameter("homeworkTitle");
        String homeworkContent = req.getParameter("homeworkContent");

        bean submitHw = new bean();
        if (submitHw.isExistStudent(studentId)) {
            if (submitHw.isExistHomework(homeworkId)) {
                StudentHomework sh = new StudentHomework();
                sh.setId(id);
                sh.setStudentId(studentId);
                sh.setHomeworkId(homeworkId);
                sh.setHomeworkTitle(new String(homeworkTitle.getBytes("ISO-8859-1"), "UTF-8"));
                sh.setHomeworkContent(new String(homeworkContent.getBytes("ISO-8859-1"), "UTF-8"));
                submitHw.submitHomework(sh);
            }

        }
    }

}
