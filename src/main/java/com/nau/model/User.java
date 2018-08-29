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

}
