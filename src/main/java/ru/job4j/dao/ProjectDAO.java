package ru.job4j.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO implements IProjectDAO {
    private static final Logger Log = LoggerFactory.getLogger(ProjectDAO.class);
    private Factory factory = Factory.getInstance();

    @Override
    public int insert(Project project) {
        Connection conn = null;
        int idProject = 0;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("insert into projects(name, user_id, issue_id) values(?, ?, ?);")
        ) {
            st.setString(1, project.getName());
            st.setInt(2, project.getUser_id());
            st.setInt(3, project.getIssue_id());

            st.executeUpdate();
            ResultSet keyProject = st.getGeneratedKeys();
            idProject = keyProject.getInt(1);
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
        return idProject;
    }

    @Override
    public List<Project> getAll() {
        List<Project> listProjects = new ArrayList<>();
        Project project;
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("SELECT name FROM projects as pr group by name;")
        ) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                project = new Project(name, 0, 0);
                listProjects.add(project);
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
        return listProjects;
    }
}