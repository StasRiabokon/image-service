package com.nau.repository;

import com.nau.model.Image;
import com.nau.model.User;

import java.awt.color.ICC_Profile;
import java.util.List;

public interface UserRepository {

    void initDatabase();

    void dropTableIfExist(String name);

    List<User> getAllUsers();

    List<Image> getAllImages();

    List<Image> getImagesByUser(Integer userId);

    User getUserByLogin(String login);

    Image getImageByUser(Integer userId, Integer imageId);

    User saveUser(User user);

    Image saveImage(Image image, Integer userId);

    void deleteImageByUser(Integer userId, Integer imageId);

    Image getImageById(Integer id);
}
