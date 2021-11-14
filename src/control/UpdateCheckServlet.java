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
 * @Description: ${判断update信息是否正常选中，若未选中则不进行跳转}
 * @Author: ChenQ
 * @Date: 2021/11/14 on 1:17
 */
@WebServlet("/form/UpdateCheck")
public class UpdateCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int perPage = session.getAttribute("perPage")==null?1:((Integer) session.getAttribute("perPage"));
        if (request.getParameter("update")==null){
            request.setAttribute("error","请选择需要修改的好友");
            request.getRequestDispatcher("../page/friendsInfo.jsp?page="+perPage).forward(request,response);
            return;
        }
        int id = Integer.parseInt(request.getParameter("update"));
        session.setAttribute("updateid",id);
        FriendModel friendModel = new FriendModel();
        Friend friend = friendModel.getFriendById(id);
        if (friend!=null){
            session.setAttribute("updatefriend",friend);
            response.sendRedirect("../form/updateFriendForm.jsp");
        }else {
            request.getRequestDispatcher("../page/friendsInfo.jsp?page="+perPage).forward(request,response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
