<%@ page import="java.util.List" %>
<%@ page import="vo.Friend" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp"%>
<html>
<head>
    <title>查询所有好友信息</title>
</head>
<%
    int userid = session.getAttribute("userid")==null?-1:(int)session.getAttribute("userid");
    if (userid == -1){
        response.sendRedirect("../form/loginForm.jsp");
        return;
    }
    String error = (String)request.getAttribute("error");
    if (error!=null){
        out.print("<center><font color=red>error:"+error+"</font></center>");
        request.setAttribute("error",null);
    }
    List<Friend> friendsList = null;
    if (session.getAttribute("friends") == null){
        response.sendRedirect("FriendInfoServlet");
        return;
    }else {
        friendsList = (List<Friend>) session.getAttribute("friends");
    }
%>
<body>
<%--title--%>
<%--    <h1>我的好友录</h1>--%>
    <a href="../form/addFriendForm.jsp">添加新好友</a>
    <a href="#" onclick="update()">修改选中好友</a>
    <a href="#" onclick="myDelete()">删除选中好友</a>
    <a href="../index.jsp">返回主页</a>
    <a href="Logout">注销</a>
<%--    <hr color="#660">--%>
<%--内容--%>
    <center>
        <form action="#" name="myForm">
            <table width="100%">
                <%--        表头行--%>
                <tr>
                    <th>修改</th>
                    <th>删除</th>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>QQ</th>
                    <th>电话</th>
                    <th>EMail</th>
                    <th>地址</th>
                </tr>
                <%
                    if (session.getAttribute("pageCount")==null){
                        session.setAttribute("pageCount",10);
                    }
                    int pageCount = (int)session.getAttribute("pageCount");
                    int perPage = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
                    session.setAttribute("perPage",perPage);
                    int lastPage = friendsList==null?1: (int)Math.ceil(friendsList.size()/Double.valueOf(pageCount));
                    int k = 0;
                    for(int i=0;i<pageCount;i++){
                        k=i+(perPage-1)*pageCount;
                        if (k<friendsList.size()){
                            Friend friend = friendsList.get(k);
                            out.print("<tr align=center>");
                            out.print("<td><input type=radio name=update value="+friend.getId()+"></td>");
                            out.print("<td><input type=checkbox name=del value="+friend.getId()+"></td>");
                            out.print("<td>"+(k+1)+"</td>");
                            out.print("<td><label name=name>"+friend.getName()+"</label></td>");
                            out.print("<td><label name=sex>"+friend.getSex()+"</label></td>");
                            out.print("<td><label name=age>"+friend.getAge()+"</label></td>");
                            out.print("<td><label name=qq>"+friend.getQq()+"</label></td>");
                            out.print("<td><label name=telephone>"+friend.getTelephone()+"</label></td>");
                            out.print("<td><label name=email>"+friend.getEmail()+"</label></td>");
                            out.print("<td><label name=address>"+friend.getAddress()+"</label></td>");
                            out.print("</tr>");
                        }else {
                            break;
                        }
                    }
                    session.setAttribute("perCount",k+1);
                %>
            </table>
        </form>

        <a href="friendsInfo.jsp?page=1">|<<1</a>
        <a href="friendsInfo.jsp?page=<%=(perPage-1)<1?1:(perPage-1)%>"><<</a>
        <%
            for (int i=0;i<4;i++){
                int index = perPage+i;
                if (index<=lastPage&&index>0){
                    out.println("<a href=friendsInfo.jsp?page="+index+">"+index+"</a>");
                }else {
                    break;
                }
            }
            if (perPage!=lastPage && lastPage>4){
                out.println("...");
            }
        %>
        <a href="friendsInfo.jsp?page=<%=(perPage+1)>lastPage?lastPage:(perPage+1)%>">>></a>
        <a href="friendsInfo.jsp?page=<%=lastPage%>"><%=lastPage==0?1:lastPage%>>>|</a>
    </center>
</body>
<script>
    function update() {
        document.myForm.action = "../form/UpdateCheck";
        document.myForm.submit();
    }
    function myDelete() {
        document.myForm.action = "../page/DeleteFriend";
        document.myForm.submit();
    }
</script>
</html>
<%@ include file="../include/footer.jsp"%>
