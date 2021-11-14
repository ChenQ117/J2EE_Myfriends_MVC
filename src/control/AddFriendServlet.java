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
 * @Date: 2021/11/13 on 22:32
 */
@WebServlet("/form/AddFriend")
public class AddFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userid = session.getAttribute("userid")==null?-1:(int)session.getAttribute("userid");
        try {
            //如果没有登录则跳转到登录表单页面
            if (userid == -1){
                response.sendRedirect("./loginForm.jsp");
                return;
            }
            String name = request.getParameter("name");
            if (name.equals("")){
                request.setAttribute("error","添加失败，姓名不能为空");
                request.getRequestDispatcher("./addFriendForm.jsp").forward(request,response);
                return;
            }
            String sex = request.getParameter("sex");
            String email = request.getParameter("email");
            String qq = request.getParameter("qq");
            String address = request.getParameter("address");
            String telephone = request.getParameter("telephone");
            Friend friend;
            if (request.getParameter("age").equals("")){
                friend = new Friend(userid,name,sex,qq,telephone,email,address);
            }else {
                int age = Integer.valueOf(request.getParameter("age"));
                friend = new Friend(userid,name,sex,age,qq,telephone,email,address);
            }
            FriendModel friendModel = new FriendModel();
            boolean isSuccess = friendModel.addFriend(friend);
            if (!isSuccess){
                request.setAttribute("error","添加失败，请稍后重试");
                request.getRequestDispatcher("../page/friendsInfo.jsp").forward(request,response);
                return;
            }
            //friends数据有更新，需重新从数据库中读取
            session.setAttribute("friends",null);
            response.sendRedirect("../page/friendsInfo.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
