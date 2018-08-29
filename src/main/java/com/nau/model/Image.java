package com.nau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    private int id;

    private byte[] data;

    private int userId;


}
