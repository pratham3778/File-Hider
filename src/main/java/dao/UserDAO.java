package dao;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db.MyConnection.connection;

public class UserDAO {
    public static boolean isExists(String email) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select email from users");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            String e = resultSet.getString(1);
            if (e.equals(email)) {
                return true;
            }
        }
            return false;
        }
        public static int saveUser(User user) throws SQLException {
            Connection connection1 = MyConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into users values(default, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            return ps.executeUpdate();
        }
    }
