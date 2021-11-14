package control;

import model.server.FriendModel;

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
 * @Date: 2021/11/13 on 23:34
 */
@WebServlet("/page/DeleteFriend")
public class DeleteFriendServlet extends HttpServlet {
    String url = "../page/friendsInfo.jsp?page=";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int perPage = session.getAttribute("perPage")==null?1:((Integer) session.getAttribute("perPage"));
        int perCount = session.getAttribute("perCount")==null?1:((Integer) session.getAttribute("perCount"));
        String[] dels = request.getParameterValues("del");
        if (dels==null){
            request.setAttribute("error","请选择需要删除的好友");
            request.getRequestDispatcher(url+perPage).forward(request,response);
            return;
        }
        FriendModel friendModel = new FriendModel();
        friendModel.deleteFriends(dels);
        if (dels.length==perCount && perPage>1){
            perPage = perPage-1;
        }
        session.setAttribute("perPage",perPage);
        //friends数据有更新，需重新从数据库中读取
        session.setAttribute("friends",null);
        System.out.println("41:perPage"+perPage);
        response.sendRedirect(url+perPage);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
