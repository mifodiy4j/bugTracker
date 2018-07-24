package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

/**
 * BugTracker
 * Класс для выполнения операций
 * в системе Bug Tracker.
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public class BugTracker {
    private static final Logger Log = LoggerFactory.getLogger(BugTracker.class);

    Connection conn = null;

    /**
     * Ввод новых записей в базу данных
     *
     * @param project добавляемый проект
     * @param user добавляемый пользователь
     * @param issue добавляемая пробема
     */
    public void addNew(Project project, User user, Issue issue) {
        try {
            conn = SqlConnect.INSTANCE.getConnection();
            PreparedStatement st;
            int idUser = checkUserInDb(user);
            if (idUser == 0) {
                st = conn.prepareStatement("insert into users(name) values(?);");
                st.setString(1, user.getName());
                st.executeUpdate();
                ResultSet keyUser = st.getGeneratedKeys();
                idUser = keyUser.getInt(1);
                keyUser.close();
                st.close();
            }

            st = conn.prepareStatement("insert into issues(description) values(?);");
            st.setString(1, issue.getDescription());
            st.executeUpdate();
            ResultSet keyIssue = st.getGeneratedKeys();
            int idIssue = keyIssue.getInt(1);
            keyIssue.close();
            st.close();

            st = conn.prepareStatement("insert into projects(name, user_id, issue_id) values(?, ?, ?);");
            st.setString(1, project.getName());
            st.setInt(2, idUser);
            st.setInt(3, idIssue);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Метод для полученя id пользователя
     *
     * @param user
     * @return возвращает id пользователя,
     * <code>0</code> если пользователя с данным именем нет в базе данных
     */
    public int checkUserInDb(User user) {
        int id = 0;
        try {
            conn = SqlConnect.INSTANCE.getConnection();
            PreparedStatement st = conn.prepareStatement("select id from users where name=?;");
            st.setString(1, user.getName());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return id;
    }

    /**
     * Метод для вывода в консоль списка названия всех проектов
     */
    public void showAllProjects() {
        try {
            conn = SqlConnect.INSTANCE.getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM projects as pr group by name;");
            ResultSet rs = st.executeQuery();
            System.out.println(String.format("================================"));
            System.out.println(String.format("|%30s|", "Projects"));
            System.out.println(String.format("================================"));
            while (rs.next()) {
                String name = rs.getString("name");
                String subName = name.length() > 30 ? name.substring(0, 30) : name;
                System.out.println(String.format("|%30s|", subName));
            }
            System.out.println(String.format("================================"));
            rs.close();
            st.close();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Метод для вывода в консоль списка имен всех пользователей
     */
    public void showAllUsers() {
        try {
            conn = SqlConnect.INSTANCE.getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM users;");
            ResultSet rs = st.executeQuery();
            System.out.println(String.format("================================"));
            System.out.println(String.format("|%30s|", "Users"));
            System.out.println(String.format("================================"));
            while (rs.next()) {
                String name = rs.getString("name");
                String subName = name.length() > 30 ? name.substring(0, 30) : name;
                System.out.println(String.format("|%30s|", subName));
            }
            System.out.println(String.format("================================"));
            rs.close();
            st.close();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Метод для вывода в консоль списка описания всех проблем
     */
    public void showAllIssues() {
        try {
            conn = SqlConnect.INSTANCE.getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM issues");
            ResultSet rs = st.executeQuery();
            System.out.println(String.format("================================"));
            System.out.println(String.format("|%30s|", "Description of issues"));
            System.out.println(String.format("================================"));
            while (rs.next()) {
                String desc = rs.getString("description");
                String subDesc = desc.length() > 30 ? desc.substring(0, 30) : desc;
                System.out.println(String.format("|%30s|", subDesc));
            }
            System.out.println(String.format("================================"));
            rs.close();
            st.close();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Метод для вывода в консоль списка проблем для конкретного проекта и пользователя
     *
     * @param nameProject название проекта
     * @param nameUser имя пользователя
     */
    public void showAllIssuesForTheProjectByTheUser(String nameProject, String nameUser) {
        try {
            conn = SqlConnect.INSTANCE.getConnection();
            PreparedStatement st = conn.prepareStatement("select iss.description from projects as pro " +
                    "left join users as us ON pro.user_id=us.id " +
                    "left join issues as iss ON pro.issue_id=iss.id " +
                    "where pro.name=? and us.name=?;");
            st.setString(1, nameProject);
            st.setString(2, nameUser);
            ResultSet rs = st.executeQuery();
            System.out.println(String.format("================================"));
            System.out.println(String.format("|%30s|", "Description of issues"));
            System.out.println(String.format("================================"));
            while (rs.next()) {
                String desc = rs.getString("description");
                String subDesc = desc.length() > 30 ? desc.substring(0, 30) : desc;
                System.out.println(String.format("|%30s|", subDesc));
            }
            System.out.println(String.format("================================"));
            rs.close();
            st.close();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Метод для закрытия соединения с базой данных
     */
    public void closeConnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
        }
    }
}