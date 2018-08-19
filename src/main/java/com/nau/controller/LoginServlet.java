package com.nau.controller;

import com.nau.model.User;
import com.nau.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static UserServiceImpl service = UserServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        User checkUser = service.getUserByLogin(login);

        if (checkUser != null) {//TODO: alert wrong password or username
            if (checkUser.getPassword().equals(user.getPassword())) {
                session.setAttribute("login", login);
                resp.sendRedirect("user-room.jsp");
                return;
            }
        }
        resp.sendRedirect("login.jsp");


    }
}
