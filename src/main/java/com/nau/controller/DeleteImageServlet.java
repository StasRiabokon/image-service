package com.nau.controller;

import com.nau.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteImageServlet extends HttpServlet {

    private static UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String imageId = req.getParameter("id");

        if (login != null && imageId != null) {
            int id = Integer.parseInt(imageId);
            service.deleteImageByUser(service.getUserByLogin(login).getId(), id);
            HttpSession session = req.getSession(true);
            session.setAttribute("deleted", true);
            resp.sendRedirect("user-room.jsp");
        }
    }
}
