package control;

import model.server.FriendModel;
import vo.Friend;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: ${NAME}
 * @Description: ${DESCRIPTION}
 * @Author: ChenQ
 * @Date: 2021/11/13 on 19:21
 */
@WebServlet("/page/FriendInfoServlet")
public class FriendInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int userid = session.getAttribute("userid")==null?-1:(int)session.getAttribute("userid");
        try {
            //如果没有登录则跳转到登录表单页面
            if (userid == -1){
                response.sendRedirect("form/loginForm.jsp");
                return;
            }
            //如果已经获得过friends的信息，则不需要再去访问数据库重新获取了
            if (session.getAttribute("friends") == null){
                FriendModel friendModel = new FriendModel();
                List<Friend> friends = friendModel.getFriends(userid);
                if (friends == null){
                    request.setAttribute("error","好友列表查询失败,请稍后重试");
                    request.getRequestDispatcher("../index.jsp").forward(request,response);
                    return;
                }
                session.setAttribute("friends",friends);
            }
            response.sendRedirect("./friendsInfo.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }
}
