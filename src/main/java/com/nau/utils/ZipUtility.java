package com.nau.utils;

import com.nau.model.Image;
import com.nau.service.UserServiceImpl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtility{

    private static UserServiceImpl service = UserServiceImpl.getInstance();

    public static byte[] zipFiles(String login) throws IOException {

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
