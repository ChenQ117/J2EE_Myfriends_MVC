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
import java.util.List;

/**
 * @version v1.0
 * @ClassName: ${NAME}
 * @Description: ${DESCRIPTION}
 * @Author: ChenQ
 * @Date: 2021/11/13 on 20:11
 */
@WebServlet("/page/QueryByName")
public class QueryByNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        session.setAttribute("friends",null);
        int userid = session.getAttribute("userid")==null?-1:(int)session.getAttribute("userid");
        String likeQuery = request.getParameter("query");
        FriendModel friendModel = new FriendModel();
        List<Friend> likeFriends = friendModel.getLikeFriends(userid, likeQuery);
        System.out.println(likeFriends.size());
        if (likeFriends == null){
            request.setAttribute("error","好友列表查询失败,请稍后重试");
            request.getRequestDispatcher("../index.jsp").forward(request,response);
            return;
        }
        session.setAttribute("friends",likeFriends);
        response.sendRedirect("./friendsInfo.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
