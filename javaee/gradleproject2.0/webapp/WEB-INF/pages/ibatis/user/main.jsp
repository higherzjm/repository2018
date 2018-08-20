<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../base.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ibatis的分页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
            当前用户：<span style="color:red">${USER.uname }</span><br><br>
     <a href="${basePath}/user/new.do">添加</a>
     <table cellspacing="1" cellpadding="0" border="0" bgcolor="green" width="50%">
         <tr bgcolor="white">
            <td>编号</td>
            <td>用户名</td>
            <td>密码</td>
            <td>操作</td>
         </tr>
         <c:forEach items="${pages.pageList}" var="user">
         <tr bgcolor="white">
            <td>${user.id }</td>
            <td>${user.uname}</td>
            <td>${user.upwd}</td>
            <td>
            <a href="${basePath}/user/del.do?id=${user.id}">删除</a>
            <a href="${basePath}/user/edit.do?id=${user.id}">编辑</a></td>
         </tr>
         </c:forEach>
     </table>    
 <span style="font-size:15px;color:green;">共有${pages.totalCount }条数据  每页显示${pages.pageSize }条数据 共分${pages.totalPages }页 当前是第${pages.currentPageNo }页</span>
    <c:choose>
    	<c:when test="${pages.currentPageNo == 1}">
    		<span style="font-size:13px;">首页</span>
    	</c:when>
    	<c:otherwise>
    		 <a href="${basePath}/user/list/1" style="font-size:13px;">首页</a>
    	</c:otherwise>
    </c:choose>
    
   	<c:choose>
   		<c:when test="${pages.hasPavPage}">
   			<a href="${basePath}/user/list/${pages.currentPageNo -1 }" style="font-size:13px;">上一页</a>
   		</c:when>
   		<c:otherwise>
   			<span style="font-size:13px;">上一页</span>
   		</c:otherwise>
   	</c:choose>
    
    <c:choose>
    	<c:when test="${pages.hasNextPage}">
    		<a href="${basePath}/user/list/${pages.currentPageNo + 1 }" style="font-size:13px;">下一页</a>
    	</c:when>
    	<c:otherwise>
   			<span style="font-size:13px;">下一页</span>
   		</c:otherwise>
    </c:choose>
    
    <c:choose>
    	<c:when test="${pages.currentPageNo == pages.totalPages}">
    		<span style="font-size:13px;">尾页</span>
    	</c:when>
    	<c:otherwise>
    		 <a href="${basePath}/user/list/${pages.totalPages }" style="font-size:13px;">尾页</a>
    	</c:otherwise>
    </c:choose>   
  </body>
</html>
