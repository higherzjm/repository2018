<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
  </head>
  
  <body>
  阿杰springMVC+Ibatis框架测试<br>
  ${basePath}<br><br>
   <form action="${basePath}/user/login.do" method="post">
   	Name:<input type="text" name="uname"/><br/>
   	Pwd:<input type="password" name="upwd"/><br>
   	<input type="submit" value="login"/>
   </form>
  
  <c:forEach items="${error}" var="error">
  <span style="color:red;">${error.defaultMessage}</span>
  </c:forEach>
  </body>
</html>
