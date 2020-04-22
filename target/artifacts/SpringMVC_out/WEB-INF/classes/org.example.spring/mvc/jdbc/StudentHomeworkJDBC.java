package org.example.spring.mvc.jdbc;

import org.example.spring.mvc.bean.Homework;
import org.example.spring.mvc.bean.Student;
import org.example.spring.mvc.bean.StudentHomework;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Configuration

public class StudentHomeworkJDBC {
    private static ApplicationContext contextSh;
    static {
        contextSh = new AnnotationConfigApplicationContext(StudentHomework.class);
    }

    private static ApplicationContext contextS;
    static {
        contextS = new AnnotationConfigApplicationContext(Student.class);
    }

    private static ApplicationContext contextH;
    static {
        contextH = new AnnotationConfigApplicationContext(Homework.class);
    }



    public static List<StudentHomework> selectAll(){


        String  sqlString = "select * from s_student_homework";

        List<StudentHomework> list = new ArrayList<>();

        try(Connection connection = DatabasePool.getHikariDataSource().getConnection()){
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    while(resultSet.next()){
                        StudentHomework sh = (StudentHomework) contextSh.getBean("studentHomework");
                        sh.setId(resultSet.getLong("id"));
                        sh.setStudentId(resultSet.getLong("student_id"));
                        sh.setHomeworkId(resultSet.getLong("homework_id"));
                        sh.setHomeworkTitle(resultSet.getString("homework_title"));
                        sh.setHomeworkContent(resultSet.getString("homework_content"));
                        list.add(sh);
                    }

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    public static List<Student> selectAllStudent(){


        String  sqlString = "select * from s_student";

        List<Student> list = new ArrayList<>();

        try(Connection connection = DatabasePool.getHikariDataSource().getConnection()){
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    while(resultSet.next()){
                        Student stu = (Student) contextS.getBean("student");
                        stu.setId(resultSet.getLong("id"));
                        stu.setName(resultSet.getString("name"));
                        list.add(stu);
                    }

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    public static List<Homework> selectAllHomework(){


        String  sqlString = "select * from s_homework";


        List<Homework> list = new ArrayList<>();

        try(Connection connection = DatabasePool.getHikariDataSource().getConnection()){
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    while(resultSet.next()){
                        Homework hw = (Homework) contextH.getBean("homework");
                        hw.setId(resultSet.getLong("id"));
                        hw.setTitle(resultSet.getString("title"));
                        hw.setContent(resultSet.getString("content"));
                        list.add(hw);
                    }

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    public boolean addHomework(Homework hw) {

        String sql = "INSERT INTO `school`.`s_homework`(`id`,`title`,`content`,`create_time`,`update_time`) VALUES (?,?,?,?,?)";


        try(Connection connection = DatabasePool.getHikariDataSource().getConnection()){
            PreparedStatement ps =  connection.prepareStatement(sql);
            ps.setLong(1,hw.getId());
            ps.setString(2,hw.getTitle());
            ps.setString(3,hw.getContent());
            int count = ps.executeUpdate();
            ps.close();
            return count > 0 ? true : false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean addStudent(Student stu) {

        String sql = "INSERT INTO `school`.`s_student`(`id`,`name`,`create_time`,`update_time`) VALUES (?,?,?,?)";


        try(Connection connection = DatabasePool.getHikariDataSource().getConnection()){
            PreparedStatement ps =  connection.prepareStatement(sql);
            ps.setLong(1,stu.getId());
            ps.setString(2,stu.getName());
            int count = ps.executeUpdate();
            ps.close();
            return count > 0 ? true : false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean submitHomework(StudentHomework studentHomework) {

        String sql = "INSERT INTO `school`.`s_student_homework`(`id`,`student_id`,`homework_id`,`homework_title`,`homework_content`,`create_time`,`update_time`) VALUES (?,?,?,?,?,?,?)";


        try(Connection connection = DatabasePool.getHikariDataSource().getConnection()){
            PreparedStatement ps =  connection.prepareStatement(sql);
            ps.setLong(1,studentHomework.getId());
            ps.setLong(2,studentHomework.getStudentId());
            ps.setLong(3,studentHomework.getHomeworkId());
            ps.setString(4,studentHomework.getHomeworkTitle());
            ps.setString(5,studentHomework.getHomeworkContent());
            int count = ps.executeUpdate();
            ps.close();
            return count > 0 ? true : false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isExistStudent(Long id){


        String sql = "SELECT * from `school`.`s_student` where id = ? ";
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabasePool.getHikariDataSource().getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            //获取执行结果
            if(resultSet.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public boolean isExistHomework(Long id){

        String sql = "SELECT * from `school`.`s_homework` where id = ? ";
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = DatabasePool.getHikariDataSource().getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            //获取执行结果
            if(resultSet.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


    public static void main(String[] args) {
        List<StudentHomework> list = selectAll();
        for(StudentHomework sh : list){
            System.out.println(sh.getHomeworkContent());
        }
    }
}