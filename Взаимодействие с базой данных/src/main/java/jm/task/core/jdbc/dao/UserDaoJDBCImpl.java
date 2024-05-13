package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

    try (Connection connection = Util.getConnection();
    Statement statement = connection.createStatement()) {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "last_name VARCHAR(50) NOT NULL," +
                "age TINYINT NOT NULL," +
                "PRIMARY KEY (id)" +
                ")";
        statement.executeUpdate(sql);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void dropUsersTable() {
    try (Connection connection = Util.getConnection();
    Statement statement = connection.createStatement()) {
        String sql = "DROP TABLE IF EXISTS users";
        statement.executeUpdate(sql);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void saveUser(String name, String lastName, byte age) {
    try (Connection connection = Util.getConnection();
    PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)")) {
        statement.setString(1,name);
        statement.setString(2,lastName);
        statement.setByte(3,age);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void removeUserById(long id) {
    try (Connection connection = Util.getConnection();
    PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
        statement.setLong(1,id);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")
                );
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
    try (Connection connection = Util.getConnection();
    Statement statement = connection.createStatement()) {
        String sql = "TRUNCATE TABLE users";
        statement.executeUpdate(sql);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}
