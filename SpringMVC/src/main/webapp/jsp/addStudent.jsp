<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加学生</title>
    <style>
        input{
            border: none;
            outline: none;
            background-color: rgba(0, 0, 0, 0);
            font-family: arial;
            width: 100%;
            display: inline-table;
            height: 100%
        }
    </style>
</head>
<body>
<div align="center">
    <h2>新学生</h2>
    <form action="${pageContext.request.contextPath}/addStudent" method="post">
        <table border="1" cellspacing="0" cellpadding="0">
            <tr>
                <th>学号:</th>
                <td><input type="number" name="id" maxlength="20" style="font-family: arial" required></td>
            </tr>
            <tr>
                <th>姓名:</th>
                <td><input type="text" name="name" maxlength="20" style="font-family: arial" required></td>
            </tr>

            <tr align="center">
                <th><input type="reset" value="清空"></th>
                <td><input type="submit" value="提交"> </td>
            </tr>
        </table>
    </form>
</div>


</body>
</html>
