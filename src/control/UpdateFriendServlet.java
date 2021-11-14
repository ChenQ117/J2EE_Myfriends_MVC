package control;

import model.server.FriendModel;
import vo.Friend;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @version v1.0
 * @ClassName: ${NAME}
 * @Description: ${DESCRIPTION}
 * @Author: ChenQ
 * @Date: 2021/11/13 on 23:28
 */
@WebServlet("/form/UpdateFriend")
public class UpdateFriendServlet extends HttpServlet {
    String url = "./updateFriendForm.jsp?update=";
    String url2 = "../page/friendsInfo.jsp?page=";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int perPage = session.getAttribute("perPage")==null?1:((Integer) session.getAttribute("perPage"));

        int id = (int)session.getAttribute("updateid");
        int userid = session.getAttribute("userid")==null?-1:(int)session.getAttribute("userid");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        String qq = request.getParameter("qq");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        int age=request.getParameter("age").equals("")?0:Integer.valueOf(request.getParameter("age"));
        if (name.equals("")){
            request.setAttribute("error","修改失败，姓名不能为空");
            request.getRequestDispatcher(url+id+"&name="+name+"&sex="+sex+"&age="+age
                    +"&email="+email+"&qq="+qq+"&address="+address+"&telephone="+telephone)
                    .forward(request,response);
            return;
        }
        Friend friend = new Friend(userid,name,sex,age,qq,telephone,email,address);
        friend.setId(id);
        FriendModel friendModel = new FriendModel();
        boolean isSuccess = friendModel.updateFriend(friend);
        if (!isSuccess){
            request.setAttribute("error","修改失败，请稍后重试");
            request.getRequestDispatcher(url+id+"&name="+name+"&sex="+sex+"&age="+age
                    +"&email="+email+"&qq="+qq+"&address="+address+"&telephone="+telephone)
                    .forward(request,response);
            return;
        }
        //friends数据有更新，需重新从数据库中读取
        session.setAttribute("friends",null);
        response.sendRedirect(url2+perPage);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
