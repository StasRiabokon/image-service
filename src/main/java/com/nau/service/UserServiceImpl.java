package com.nau.service;

import com.nau.model.Image;
import com.nau.model.User;
import com.nau.repository.UserRepository;
import com.nau.repository.UserRepositoryJDBCImpl;

import java.util.List;

public class UserServiceImpl implements UserRepository {

    private UserRepository repository = new UserRepositoryJDBCImpl();

    public static volatile UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        UserServiceImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (UserServiceImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserServiceImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void initDatabase() {
        repository.initDatabase();
    }

    @Override
    public void dropTableIfExist(String name) {
        repository.dropTableIfExist(name);
    }

    @Override
    public User saveUser(User user) {
        return repository.saveUser(user);
    }

    @Override
    public List<Image> getAllImages() {
        return repository.getAllImages();
    }

    @Override
    public Image saveImage(Image image, Integer userId) {
        return repository.saveImage(image, userId);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @Override
    public List<Image> getImagesByUser(Integer userId) {
        return repository.getImagesByUser(userId);
    }


    @Override
    public User getUserByLogin(String login) {
        return repository.getUserByLogin(login);
    }

    @Override
    public Image getImageByUser(Integer userId, Integer imageId) {
        return repository.getImageByUser(userId, imageId);
    }


    @Override
    public void deleteImageByUser(Integer userId, Integer imageId) {
        repository.deleteImageByUser(userId, imageId);
    }

    @Override
    public Image getImageById(Integer id) {
        return repository.getImageById(id);
    }
}
