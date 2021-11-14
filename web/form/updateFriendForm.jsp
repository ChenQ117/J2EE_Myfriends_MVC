<%@ page import="vo.Friend" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp"%>
<html>
<head>
    <title>修改好友</title>
</head>
<%
    String error = (String)request.getAttribute("error");
    if (error!=null){
        out.print("<center><font color=red>error:"+error+"</font></center>");
        request.setAttribute("error",null);
    }

    int userid = session.getAttribute("userid")==null?-1:(int)session.getAttribute("userid");
    Friend friend = (Friend) session.getAttribute("updatefriend");
%>
<body>
    <center>
        <form action="UpdateFriend">
            <table>
                <tr>
                    <td align="right">姓名：</td>
                    <td><input name="name" value="<%=friend.getName()%>"></td>
                </tr>
                <tr>
                    <td align="right">性别：</td>
                    <td><input name="sex" value="<%=friend.getSex()%>"></td>
                </tr>
                <tr>
                    <td align="right">年龄：</td>
                    <td><input name="age" value="<%=friend.getAge()%>"></td>
                </tr>
                <tr>
                    <td align="right">QQ：</td>
                    <td><input name="qq" value="<%=friend.getQq()%>"></td>
                </tr>
                <tr>
                    <td align="right">电话：</td>
                    <td><input name="telephone" value="<%=friend.getTelephone()%>"></td>
                </tr>
                <tr>
                    <td align="right">邮箱：</td>
                    <td><input name="email" value="<%=friend.getEmail()%>"></td>
                </tr>
                <tr>
                    <td align="right">地址：</td>
                    <td><input name="address" value="<%=friend.getAddress()%>"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="修改">
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