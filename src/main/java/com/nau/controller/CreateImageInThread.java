package com.nau.controller;

import com.nau.model.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CreateImageInThread implements Runnable {

    private Image image;
    private String path;

    public CreateImageInThread(Image image, String path) {
        this.image = image;
        this.path = path;
    }

    @Override
    public void run() {
        try {
            InputStream in = new ByteArrayInputStream(image.getData());
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "jpg", new File(path + File.separator + "image" + image.hashCode()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}