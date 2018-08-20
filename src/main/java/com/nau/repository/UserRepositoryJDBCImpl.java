package com.nau.repository;

import com.nau.connection.DBConnectionManager;
import com.nau.model.Image;
import com.nau.model.User;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserRepositoryJDBCImpl implements UserRepository {

    private Connection connection = DBConnectionManager.getConnection();

    @Override
    public void dropTableIfExist(String name) {
        String sql = "DROP TABLE IF EXISTS " + name;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User getUserByLogin(String login) {
        String sql = "SELECT id, login, password FROM ImageService.Users WHERE login = ?";
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return user;
    }

    @Override
    public User saveUser(User user) {
        String sql = "INSERT INTO ImageService.Users(login, password) VALUES(?, ?)";
        PreparedStatement statementUser = null;
        try {
            statementUser = connection.prepareStatement(sql);
            statementUser.setString(1, user.getLogin());
            statementUser.setString(2, user.getPassword());
            statementUser.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statementUser != null) statementUser.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return user;
    }

    @Override
    public List<Image> getAllImages() {
        String sql = "SELECT id, url, data, userId FROM ImageService.Images";
        List<Image> list = null;
        Image image = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();

            while (resultSet.next()) {

                image = new Image();
                image.setId(resultSet.getInt("id"));
                image.setData(resultSet.getBytes("data"));

                image.setUrl("data:image/png;base64," + resultSet.getBytes("data"));

                image.setUserId(resultSet.getInt("userId"));
                list.add(image);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return list;
    }

    @Override
    public Image getImageById(Integer id) {
        String sql = "SELECT id, url, data, userId  FROM ImageService.Images WHERE id = ?";
        Image image = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                image = new Image();
                image.setId(resultSet.getInt("id"));
                image.setData(resultSet.getBytes("data"));

                image.setUrl("data:image/png;base64," + resultSet.getBytes("data"));

                image.setUserId(resultSet.getInt("userId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return image;
    }

    @Override
    public Image saveImage(Image image, Integer userId) {
        String sql = "INSERT INTO ImageService.Images(url, data, userId) VALUES(?, ?, ?)";
        PreparedStatement statementUser = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(image.getData());
            statementUser = connection.prepareStatement(sql);
            statementUser.setString(1, image.getUrl());
            statementUser.setBlob(2, inputStream);
            statementUser.setInt(3, image.getUserId());
            statementUser.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statementUser != null) statementUser.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<Image> getImagesByUser(Integer userId) {
        return null;
    }

    @Override
    public User getUserById(Integer userId) {
        return null;
    }

    @Override
    public Image getImageByUser(Integer userId, Integer imageId) {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }

    @Override
    public void deleteImageByUser(Integer userId, Integer imageId) {

    }

}
