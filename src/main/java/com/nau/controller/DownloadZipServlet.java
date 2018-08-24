package com.nau.controller;

import com.nau.model.Image;
import com.nau.service.UserServiceImpl;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@WebServlet("/download-zip")
public class DownloadZipServlet extends HttpServlet {

    private static UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = "/home/stas/NetBeansProjects/imageservice/src/main/resources/data";
        File directory = new File(path);
        String[] files = directory.list();

        createImages(path);

        //check if directories have files
        if (files != null && files.length > 0) {

            //create zip stream
            byte[] zip = zipFiles(directory, files);


            // Sends the response back to the user / browser with zip content
            ServletOutputStream sos = response.getOutputStream();
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"DATA.ZIP\"");

            sos.write(zip);
            sos.flush();
        }
        deleteImages(path);
    }

    private void createImages(String path) {

        List<Image> images = service.getAllImages();

        for (int i = 0; i < images.size(); i++) {
            Thread th = new Thread(new CreateImageInThread(images.get(i), path));
            th.start();
        }

    }

    private void deleteImages(String path) {
        try {
            FileUtils.cleanDirectory(new File(path + File.separator));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] zipFiles(File directory, String[] files) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        byte bytes[] = new byte[4096];

        for (String fileName : files) {
            try (FileInputStream fis = new FileInputStream(directory.getPath()
                    + "/" + fileName);

                 BufferedInputStream bis = new BufferedInputStream(fis)) {

                zos.putNextEntry(new ZipEntry(fileName));

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
