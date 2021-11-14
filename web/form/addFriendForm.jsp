<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp"%>
<html>
<head>
    <title>添加好友</title>
</head>
<%
    String error = (String)request.getAttribute("error");
    if (error!=null){
        out.print("<center><font color=red>error:"+error+"</font></center>");
        request.setAttribute("error",null);
    }
%>
<body>
    <center>
        <form action="AddFriend">
            <table>
                <tr>
                    <td align="right">姓名：</td>
                    <td><input name="name"></td>
                </tr>
                <tr>
                    <td align="right">性别：</td>
                    <td><input name="sex"></td>
                </tr>
                <tr>
                    <td align="right">年龄：</td>
                    <td><input name="age"></td>
                </tr>
                <tr>
                    <td align="right">QQ：</td>
                    <td><input name="qq"></td>
                </tr>
                <tr>
                    <td align="right">电话：</td>
                    <td><input name="telephone"></td>
                </tr>
                <tr>
                    <td align="right">邮箱：</td>
                    <td><input name="email"></td>
                </tr>
                <tr>
                    <td align="right">地址：</td>
                    <td><input name="address"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="添加">
                        <input type="reset" value="重置">
                        <input type="button" name="cancel" onsubmit="cancel()" value="取消">
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
<script>
    function cancel() {
        window.history.back();//返回上一页
        // window.navigate("../page/friendsInfo.jsp");
    }
</script>
</html>
<%@ include file="../include/footer.jsp"%>