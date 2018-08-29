package com.nau.controller;

import com.nau.model.Image;
import com.nau.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@WebServlet("/personal")
public class PersonalImagesServlet extends HttpServlet {

    private static UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String login = req.getParameter("login");

        if (login != null) {
            int userId = service.getUserByLogin(login).getId();
            if (idStr != null) {

                int id = Integer.parseInt(idStr);
                byte[] content = service.getImageByUser(userId, id).getData();
                resp.setContentLength(content.length);
                resp.getOutputStream().write(content);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        if (login != null) {
            int userId = service.getUserByLogin(login).getId();
            HttpSession session = req.getSession(true);
            session.setAttribute("login", login);
            session.setAttribute("personal_count", service.getImagesByUser(userId).stream().map(Image::getId).collect(toList()));
            resp.sendRedirect("personal-images.jsp");
        }
    }
}
