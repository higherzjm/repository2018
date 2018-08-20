<%--
  Created by IntelliJ IDEA.
  User: zjm
  Date: 2017/11/21
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"+request.getServerName() + ":" + request.getServerPort() + path + "/";
  System.out.println("basePath:"+basePath);
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>gradle工程</title>
    <script type="text/javascript">
        window.location.href="<%=basePath%>MainController/mainmethod.do";
    </script>
  </head>
  <body style="text-align: center;margin-top: 100px">
  <input type="text"/><br>
  <input type="password"/><br>
  <input type="button" value="登入" onclick=""/><br>
  </body>
</html>
