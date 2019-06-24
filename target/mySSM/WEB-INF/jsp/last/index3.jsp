<%--
  Created by IntelliJ IDEA.
  User: wangjing
  Date: 2019/6/1
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <%--application/javascript是服务器端处理js文件的mime类型，text/javascript是浏览器处理js的mime类型,现在浏览器对后者支持更好--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
</head>
<body>


<form action="${pageContext.request.contextPath}/userInfo/checkLogin" method="post">
    <table>
        <tr>
            <td>用户名:</td>
            <td><input id="username" name="username" type="text"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input id="password" name="password" type="password"></td>
            <td><input id="test" name="test" type="text"></td>
        </tr>
        <tr >
            <td><input type="submit" value="${pageContext.request.getAttribute("1")}" ></td>
            <td><input type="button" value="注册" onclick="window.location.href='/userInfo/regist'"> </td>
            <td><input type="button" id="mytestButton" value="test" onclick="loadTest()"> </td>
        </tr>

    </table>
</form>
<script>
    function loadTest(){
        console.log(${pageContext.request.contextPath});
        $.ajax({
            async:true,//异步加载
            timeout:1000,
            type:"GET",
            //dataType表示我需要的数据类型
            //dataType:"",
            //contentType表示我要发的数据类型
            //contentType:"application/json",这句不能加，加了后台会接收不到值。
            url:"/userInfo/index3",
            success:function (data) {
            },
            error:function (data) {
            }
        });
    }
</script>
</body>
</html>

