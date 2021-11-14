<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>登录表单</title>
</head>
<%
    String error =(String) request.getAttribute("error");
    if (error!=null){
        out.print("<center><font color=red>error:"+error+"</font></center>");
        request.setAttribute("error",null);
    }
    String registerInfo = (String)request.getAttribute("registerInfo");
    if (registerInfo!=null){
        out.print("<center><font>"+registerInfo+"</font></center>");
        request.setAttribute("registerInfo",null);
    }
%>
<body>
    <font>用户登录：</font>
    <center>
        <form action="Login">
            <table>
                <tr>
                    <td align="right"><label>用户登录：</label></td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td align="right"><label>用户密码：</label></td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="登录">
                        <input type="reset" value="重置">
                        <a href="registerForm.jsp"/>注册
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
<%@ include file="../include/footer.jsp"%>