/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (request.getParameter("logout") == null) {

            if (session.getAttribute("usernameInfo") == null) {
                System.out.println("1");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            } else {
                System.out.println("2");
                response.sendRedirect("home");
                return;
            }

        } else {
            System.out.println("3");
            session.invalidate();
            request.setAttribute("logout", "You have successfully logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username_input");
        String password = request.getParameter("password_input");
        System.out.print(password);

        if (!username.isEmpty() && !password.isEmpty()) {
            AccountService accountService = new AccountService();
            User user = accountService.login(username, password);
            if (user == null) {
                request.setAttribute("invalidMessage", "Invalid Login");
                request.setAttribute("username_attr", username);
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("usernameInfo", user.getUsername());
                response.sendRedirect("home");
            }

        } else {
            request.setAttribute("invalidMessage", "Invalid Login");
            request.setAttribute("username_attr", username);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

    }

}
