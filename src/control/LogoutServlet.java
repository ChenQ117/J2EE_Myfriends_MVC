package control;

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
 * @Date: 2021/11/13 on 14:37
 */
@WebServlet("/page/Logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        try {
            session.setAttribute("userid",null);
            session.setAttribute("friends",null);
            response.sendRedirect("../index.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }
}
