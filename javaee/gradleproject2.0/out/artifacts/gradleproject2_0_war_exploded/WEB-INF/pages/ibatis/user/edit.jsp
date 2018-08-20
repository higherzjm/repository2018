<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../base.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'edit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  <body>
  
  <form action="${basePath}/user/edit.do" method="post">
               <input type="hidden" name="id" value="${user.id}" >
      UNAME:<input type="text" name="uname" value="${user.uname }"/> <br>
      UPWD:<input type="text" name="upwd" value="${user.upwd }"/><br>
            <input type="submit" value="save"/>
  </form>
    <c:forEach items="${error}" var="error">
  <span style="color:red;">${error.defaultMessage}</span>
  </c:forEach>
  </body>
</html>
