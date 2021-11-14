<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>按姓名查询</title>
</head>
<%
    int userid = session.getAttribute("userid")==null?-1:(int)session.getAttribute("userid");
    if (userid == -1){
        response.sendRedirect("form/loginForm.jsp");
        return;
    }
%>
<body>
    <center>
        <form action="QueryByName">
            <input type="text" name="query">
            <input type="submit" value="查询"></input>
            <a href="../index.jsp">返回主页</a>
            <a href="Logout">注销</a>
        </form>
    </center>
</body>
</html>
<%@ include file="../include/footer.jsp"%>