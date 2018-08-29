package com.nau.controller;

import com.nau.utils.EmailUtility;
import com.nau.utils.PropertiesLoader;
import com.nau.utils.ZipUtility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/send")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,   // 2MB
        maxFileSize = 1024 * 1024 * 10,         // 10MB
        maxRequestSize = 1024 * 1024 * 50)      // 50MB
public class SendMailAttachServlet extends HttpServlet {

    private PropertiesLoader props = PropertiesLoader.getInstance();

    private String host;
    private String port;
    private String user;
    private String pass;

    public void init() {
        host = props.getHost();
        port = props.getPort();
        user = props.getUser();
        pass = props.getPass();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");

        byte[] uploadedFiles = ZipUtility.zipFiles(login);


        String recipient = request.getParameter("recipient");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        String resultMessage = "";

        try {
            EmailUtility.sendEmailWithAttachment(host, port, user, pass,
                    recipient, subject, content, uploadedFiles);

            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("message", resultMessage);
            response.sendRedirect("personal-images.jsp");
        }
    }

}
