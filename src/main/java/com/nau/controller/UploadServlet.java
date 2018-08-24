package com.nau.controller;

import com.nau.model.Image;
import com.nau.service.UserServiceImpl;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)
public class UploadServlet extends HttpServlet {

    private static UserServiceImpl service = UserServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");

        int userId = service.getUserByLogin(login).getId();

        InputStream inputStream = null;
        Image image = null;

        Part filePart = request.getPart("photo");
        if (filePart != null) {

            inputStream = filePart.getInputStream();
            image = new Image();

            byte[] targetArray = new byte[inputStream.available()];
            inputStream.read(targetArray);
            String url = "";

            image.setData(targetArray);
            image.setUserId(userId);
            image.setUrl(url);
            HttpSession session = request.getSession(true);
            session.setAttribute("addedли?", true);

            service.saveImage(image, userId);
            response.sendRedirect("user-room.jsp");
        }else {
            response.sendRedirect("error.jsp");
        }

    }

}
