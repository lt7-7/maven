package org.example.spring.mvc.controller;

import org.example.spring.mvc.jdbc.StudentHomeworkJDBC;
import org.example.spring.mvc.bean.StudentHomework;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.io.UnsupportedEncodingException;

@Controller
/*@RequestMapping("/Student/")*/
public class StudentController extends HttpServlet {

    /**
     * @RequestMapping(value = "submitHomework",method = RequestMethod.GET)等价于
     *    @GetMapping("submitHomework")
     */

    @ResponseBody
    public Object testMapping(@RequestBody Object request, @RequestParam(required = true) String id){
        //HttpServletRequest 获取Body ------> request

        //Object ------> HttpServletResponse
        return new Object();
    }


    @RequestMapping("/submitHomework")  /**会映射到 StudentController.SubmitHomework()*/
    public void SubmitHomework(@RequestParam(value = "id")Long id,
                               @RequestParam(value = "studentId")Long studentId,
                               @RequestParam(value = "homeworkId")Long homeworkId,
                               @RequestParam(value = "homeworkTitle")String homeworkTitle,
                               @RequestParam(value = "homeworkContent")String homeworkContent) throws UnsupportedEncodingException {
  /*      Long id = Long.parseLong(req.getParameter("id"));
        Long studentId = Long.parseLong(req.getParameter("studentId"));
        Long homeworkId = Long.parseLong(req.getParameter("homeworkId"));
        String homeworkTitle = req.getParameter("homeworkTitle");
        String homeworkContent = req.getParameter("homeworkContent");
*/
        StudentHomeworkJDBC submitHw = new StudentHomeworkJDBC();
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
