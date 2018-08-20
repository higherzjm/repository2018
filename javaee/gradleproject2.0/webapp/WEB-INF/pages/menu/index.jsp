<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"+request.getServerName() + ":" + request.getServerPort() + path + "/style/";
System.out.println("basePath:"+basePath);
String basePath2 = request.getScheme() + "://"+request.getServerName() + ":" + request.getServerPort() + path + "/";
System.out.println("basePath2:"+basePath2);
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
   <meta charset="utf-8">
    <title>test</title>
	
    <link rel="stylesheet" type="text/css" href="css/nav.css">
    <link rel="stylesheet" type="text/css" href="font/iconfont.css">
	
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/nav.js"></script>
    <style type="text/css">

    </style>
</head>
<body>
  <div style="height: 60px;background-color: rebeccapurple;text-align: center;font-family: Aharoni;font-weight: bolder;
  font-size: 40px;line-height: 60px">
      test
  </div>
    <div  class="nav" align="left" style="width: 200px">
        <ul>
            <li class="nav-item">
                <a href="javascript:;"><i class="my-icon nav-icon icon_1"></i><span>j2ee</span><i class="my-icon nav-more"></i></a>
                <ul>
                    <li><a href="<%=basePath2%>MainController/springmvcmain.do" TARGET="contentiframe"><span>springmvc</span></a></li>
                    <li><a href="<%=basePath2%>springjdbccontrol/springjdbcmain.do" TARGET="contentiframe"><span>springjdbc</span></a></li>
                    <li><a href="<%=basePath2%>user/index.do" TARGET="contentiframe"><span>springmvcibatis</span></a></li>
                    <li><a href="<%=basePath2%>static/jsp/downfiles.jsp" TARGET="contentiframe"><span>文件下载</span></a></li>
                    <li><a href="<%=basePath2%>static/jsp/servlet.jsp" TARGET="contentiframe"><span>servlet</span></a></li>
                    <li><a href="<%=basePath2%>static/jsp/springwebsocket.jsp" TARGET="contentiframe"><span>springwebsocket</span></a></li>
                    <li><a href="<%=basePath2%>static/jsp/velocity.jsp" TARGET="contentiframe"><span>velocity</span></a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a href="javascript:;"><i class="my-icon nav-icon icon_2"></i><span>技术前沿</span><i class="my-icon nav-more"></i></a>
                <ul>
                    <li><a href="<%=basePath2%>static/jsp/redis.jsp" TARGET="contentiframe"><span>redis</span></a></li>
                    <li><a href="<%=basePath2%>static/jsp/websocket.jsp" TARGET="contentiframe"><span>websocket</span></a></li>
                    <li><a href="<%=basePath2%>static/jsp/websocekt_tomcatexample.xhtml" TARGET="contentiframe"><span>websocekt_tomcatexample</span></a></li>
                    <li><a href="<%=basePath2%>static/jsp/jms.jsp" TARGET="contentiframe"><span>jms</span></a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a href="javascript:;"><i class="my-icon nav-icon icon_3"></i><span>前端</span><i class="my-icon nav-more"></i></a>
                <ul>
                    <li><a href="javascript:;"><span>javascript</span></a></li>
                    <li><a href="javascript:;"><span>jquery</span></a></li>
                    <li><a href="javascript:;"><span>extjs</span></a></li>
                    <li><a href="javascript:;"><span>其他</span></a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a href="javascript:;"><i class="my-icon nav-icon icon_3"></i><span>关系型数据库</span><i class="my-icon nav-more"></i></a>
                <ul>
                    <li><a href="javascript:;"><span>oracle</span></a></li>
                    <li><a href="javascript:;"><span>mysql</span></a></li>
                    <li><a href="javascript:;"><span>其他</span></a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a href="javascript:;"><i class="my-icon nav-icon icon_3"></i><span>非关系型数据库</span><i class="my-icon nav-more"></i></a>
                <ul>
                    <li><a href="javascript:;"><span>mongodb</span></a></li>
                    <li><a href="javascript:;"><span>redis</span></a></li>
                    <li><a href="javascript:;"><span>其他</span></a></li>
                </ul>
            </li>
        </ul>
    </div>
  <div style="text-align:center;background-color: black;height: 60px;font-family: Aharoni;font-weight: bolder;
    font-size: 40px;line-height: 60px;margin: 0px">
      <p1>更多源码：<a href="http://www.mycodes.net/" target="_blank" style="display: none">源码之家</a></p1>
  </div>
    <div align="left" style="height:100%;width: 100%;left:200px;top: 60px;position: absolute;">
     <iframe name="contentiframe" name="content" style="background-color: ghostwhite;height:100%;width: 100%;text-align: center"/>
    </div>

</body>
</html>


