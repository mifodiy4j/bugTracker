package ru.job4j.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private static final Logger Log = LoggerFactory.getLogger(UserDAO.class);
    private Factory factory = Factory.getInstance();

    @Override
    public int insert(User user) {

        Connection conn = null;
        int idUser = 0;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("insert into users(name) values(?);")
        ) {
            st.setString(1, user.getName());

            st.executeUpdate();
            ResultSet keyUser = st.getGeneratedKeys();
            idUser = keyUser.getInt(1);
            conn.commit();

            factory.close();

        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
        return idUser;
    }

    @Override
    public List<User> getAll() {
        List<User> listUsers = new ArrayList<>();
        User user;
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("SELECT name FROM users;")
        ) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                user = new User(name);
                listUsers.add(user);
            }
            conn.commit();
            Factory.getInstance().close();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
        return listUsers;
    }

    /**
     * Метод для полученя id пользователя
     *
     * @param user объект, id которого необходимо найти
     * @return возвращает id пользователя,
     * <code>0</code> если пользователя с данным именем нет в базе данных
     */
    public int getIdByOtherParameter(User user) {
        int id = 0;

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("select id from users where name=?;")
        ) {

            st.setString(1, user.getName());

            try (ResultSet rs = st.executeQuery()
            ) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
            conn.commit();
            factory.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
        return id;
    }
}