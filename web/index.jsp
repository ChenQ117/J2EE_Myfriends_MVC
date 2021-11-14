<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/head.jsp"%>
<%
  int userid = session.getAttribute("userid")==null?-1:(int)session.getAttribute("userid");
  if (userid == -1){
    response.sendRedirect("form/loginForm.jsp");
    return;
  }
  session.setAttribute("friends",null);
  String error = (String)request.getAttribute("error");
  if (error!=null){
    out.print("<center><font color=red>error:"+error+"</font></center>");
    request.setAttribute("error",null);
  }
%>
<html>
<head>
  <title>我的好友录</title>
</head>
<body>
<a href="./page/friendsInfo.jsp">查询所有好友信息</a>
<br>
<a href="./page/queryByName.jsp">按姓名查询好友信息</a>
</body>
</html>
<%@ include file="include/footer.jsp"%>