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

import static java.util.stream.Collectors.toList;

@WebServlet("/images")
public class AllImagesServlet extends HttpServlet {

    private static UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idStr = req.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            byte[] content = service.getImageById(id).getData();
            resp.setContentLength(content.length);
            resp.getOutputStream().write(content);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.setAttribute("count", service.getAllImages().stream().map(Image::getId).collect(toList()));
        resp.sendRedirect("images.jsp");
    }
}
