package com.nau.model;

import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

@Data
public class User {

    private int id;

    private String login;

    private String password;

    private List<Image> images;

    public void addImage(Image image) {
        images.add(image);
    }

    public Stream<Image> stream() {
        return images.stream();
    }


}
