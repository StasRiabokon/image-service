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

@WebServlet("/download-zip")
public class DownloadZipServlet extends HttpServlet {

    private static UserServiceImpl service = UserServiceImpl.getInstance();

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

    private static byte[] zipFiles(String login) throws IOException {

        List<Image> images = service.getImagesByUser(service.getUserByLogin(login).getId());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        byte bytes[] = new byte[4096];

        for (Image image : images) {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(image.getData());

                 BufferedInputStream bis = new BufferedInputStream(bais)) {

                zos.putNextEntry(new ZipEntry("image" + image.hashCode()));

                int bytesRead;
                while ((bytesRead = bis.read(bytes)) != -1) {
                    zos.write(bytes, 0, bytesRead);
                }
                zos.closeEntry();
            }
        }
        zos.flush();
        baos.flush();
        zos.close();
        baos.close();

        return baos.toByteArray();
    }
}
