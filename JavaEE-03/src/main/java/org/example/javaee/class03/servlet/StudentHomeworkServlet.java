package org.example.javaee.class03.servlet;

import org.example.javaee.class03.jdbc.StudentHomeworkJDBC;
import org.example.javaee.class03.model.Homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allHomework")
public class StudentHomeworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Homework> list = StudentHomeworkJDBC.showHomework();

        req.setAttribute("list",list);

        //展示给学生
        req.getRequestDispatcher("jsp/queryAllHomework.jsp").forward(req,resp);
    }
}
