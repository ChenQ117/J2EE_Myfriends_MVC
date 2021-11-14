package control;

import model.server.UserModel;
import utils.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @version v1.0
 * @ClassName: ${NAME}
 * @Description: ${DESCRIPTION}
 * @Author: ChenQ
 * @Date: 2021/11/13 on 14:37
 */
@WebServlet("/form/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        UserModel userModel = new UserModel();
        int userid = userModel.loginCheck(username, password);
        try {
            if (userid == -1){
                request.setAttribute("error","账号密码错误");
                request.getRequestDispatcher("loginForm.jsp").forward(request,response);
            }else {
                session.setAttribute("userid",userid);
                request.setAttribute("error",null);
//            request.getRequestDispatcher("../../index.jsp").forward(request,response);
                response.sendRedirect("../index.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }
}
