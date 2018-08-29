package com.nau.controller;

import com.nau.model.Image;
import com.nau.service.UserServiceImpl;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.nau.utils.ZipUtility.zipFiles;

@WebServlet("/download-zip")
public class DownloadZipServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");

        if (login != null) {
            byte[] zip = zipFiles(login);

            ServletOutputStream sos = response.getOutputStream();
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"DATA.ZIP\"");

            sos.write(zip);
            sos.flush();
        }

    }


}
