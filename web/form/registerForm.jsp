<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp"%>
<html>
<head>
    <title>注册表单</title>
</head>
<%
    String error = (String)request.getAttribute("error");
    if (error != null){
        out.print("<center><font color=red>error:"+error+"</font></center>");
        request.setAttribute("error",null);
    }
%>
<body>
    <label>用户注册：</label>
    <center>
        <form action="Register">
            <table>
                <tr>
                    <td align="right"><label>登录名称：</label></td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td align="right"><label>登录密码：</label></td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td align="right"><label>密码确认：</label></td>
                    <td><input type="password" name="passwordConfirm"></td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
                        <input type="submit" value="注册">
                        <input type="reset" value="重置">
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
<%@ include file="../include/footer.jsp"%>
