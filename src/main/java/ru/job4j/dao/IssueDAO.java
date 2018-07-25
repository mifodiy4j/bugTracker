package ru.job4j.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.Issue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IssueDAO implements IIssueDAO {
    private static final Logger Log = LoggerFactory.getLogger(IssueDAO.class);
    private Factory factory = Factory.getInstance();

    @Override
    public int insert(Issue issue) {
        Connection conn = null;
        int idIssue = 0;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("insert into issues(description) values(?);")
        ) {
            st.setString(1, issue.getDescription());

            st.executeUpdate();
            ResultSet keyIssue = st.getGeneratedKeys();
            idIssue = keyIssue.getInt(1);
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
        return idIssue;
    }

    @Override
    public List<Issue> getAll() {
        List<Issue> listIssues = new ArrayList<>();
        Issue issue;
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("SELECT description FROM issues")
        ) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String description = rs.getString("description");
                issue = new Issue(description);
                listIssues.add(issue);
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
        return listIssues;
    }

    public List<Issue> showAllIssuesForThisProjectByThisUser(String nameProject, String nameUser) {
        List<Issue> listIssues = new ArrayList<>();
        Issue issue;
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("select iss.description from projects as pro " +
                "left join users as us ON pro.user_id=us.id " +
                "left join issues as iss ON pro.issue_id=iss.id " +
                "where pro.name=? and us.name=?;")
        ) {
            st.setString(1, nameProject);
            st.setString(2, nameUser);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String description = rs.getString("description");
                issue = new Issue(description);
                listIssues.add(issue);
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
        return listIssues;
    }
}