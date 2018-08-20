<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>My JSP 'main.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${basePath }/static/script/jquery.js"></script>
	
	
  </head>
  <body>
  <form action="${basePath}/user/new.do" method="post" onsubmit="return checkForm()">
     UNAME:<input type="text" id="name" name="uname" value="" onfocus="clearName()"/><span id="nameError" style="color:red;font-size:12px;display:none">用户名不能为空</span> <br>
     UPWD:<input type="password" id="pwd" name="upwd" value="" onfocus="clearPwd()"/><span id="pwdError" style="color:red;font-size:12px;display:none">密码不能为空</span><br>
     <input type="submit" value="save"/>
  </form>
  
    <c:forEach items="${error}" var="error">
  <span style="color:red;">${error.defaultMessage}</span>
  </c:forEach>
  </body>
</html>
