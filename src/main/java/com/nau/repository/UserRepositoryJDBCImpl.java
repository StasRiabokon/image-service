package com.nau.repository;

import com.nau.utils.DBConnectionManager;
import com.nau.model.Image;
import com.nau.model.User;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class UserRepositoryJDBCImpl implements UserRepository {

    private Connection connection = DBConnectionManager.getConnection();

    @Override
    public void initDatabase() {
        String sqlUsers = "create table  if not exists Users" +
                "(" +
                "  id       int auto_increment" +
                "    primary key," +
                "  login    varchar(128) not null," +
                "  password varchar(128) not null," +
                "  constraint Users_login_uindex" +
                "  unique (login)" +
                ");";

        String sqlImages = "create table if not exists Images" +
                "(" +
                "  id     int auto_increment" +
                "    primary key," +
                "  data   mediumblob not null," +
                "  userId int        not null," +
                "  constraint userId" +
                "  foreign key (userId) references Users (id)" +
                "); ";


        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlUsers);
            statement.executeUpdate(sqlImages);

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
        String sql = "SELECT id, data, userId FROM ImageService.Images";
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
        String sql = "SELECT id, data, userId  FROM ImageService.Images WHERE id = ?";
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
        String sql = "INSERT INTO ImageService.Images(data, userId) VALUES(?, ?)";
        PreparedStatement statementUser = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(image.getData());
            statementUser = connection.prepareStatement(sql);
            statementUser.setBlob(1, inputStream);
            statementUser.setInt(2, image.getUserId());
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
        String sql = "SELECT id, data, userId FROM ImageService.Images WHERE userId=?";
        List<Image> list = null;
        Image image = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();

            while (resultSet.next()) {
                image = new Image();
                image.setId(resultSet.getInt("id"));
                image.setData(resultSet.getBytes("data"));
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
    public Image getImageByUser(Integer userId, Integer imageId) {
        String sql = "SELECT id, data, userId  FROM ImageService.Images WHERE id = ? AND userId = ?";
        Image image = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, imageId);
            statement.setInt(2, userId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                image = new Image();
                image.setId(resultSet.getInt("id"));
                image.setData(resultSet.getBytes("data"));
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
    public void deleteImageByUser(Integer userId, Integer imageId) {
        String sql = "DELETE FROM ImageService.Images WHERE id = ? AND userId = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, imageId);
            statement.setInt(2, userId);
            statement.executeUpdate();

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

}
