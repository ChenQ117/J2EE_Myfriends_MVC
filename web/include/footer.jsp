<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <hr color="#660">
    <%
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = sd.format(date);
    %>
    <center><font>当前时间：<%=format%></font></center>
</body>
</html>
