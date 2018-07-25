package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dao.IssueDAO;
import ru.job4j.dao.ProjectDAO;
import ru.job4j.dao.UserDAO;

import java.util.List;

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

    /**
     * Ввод новых записей в базу данных
     *
     * @param nameProject имя добавляемого проекта
     * @param nameUser имя добавляемого пользователя
     * @param descIssue описание добавляемой пробемы
     */
    public void addNew(String nameProject, String nameUser, String descIssue) {
        if (nameProject != null && !nameProject.isEmpty() &&
                nameUser != null && !nameUser.isEmpty() &&
                descIssue != null && !descIssue.isEmpty()) {
            UserDAO userDAO = new UserDAO();
            IssueDAO issueDAO = new IssueDAO();
            ProjectDAO projectDAO = new ProjectDAO();

            User user = new User(nameUser);
            int idUser = userDAO.getIdByOtherParameter(user);
            if (idUser == 0) {
                idUser = userDAO.insert(user);
            }
            projectDAO.insert(new Project(nameProject, idUser, issueDAO.insert(new Issue(descIssue))));
        } else {
            throw new NullFieldException("Out of menu rage.");
        }
    }

    /**
     * Метод для вывода в консоль списка названия всех проектов
     */
    public void showAllProjects() {
        ProjectDAO projectDAO = new ProjectDAO();
        List<Project> listProjects = projectDAO.getAll();
        if (!listProjects.isEmpty()) {
            System.out.println(String.format("================================"));
            System.out.println(String.format("|%30s|", "Projects"));
            System.out.println(String.format("================================"));
            for (Project project : listProjects) {
                String name = project.getName();
                String subName = name.length() > 30 ? name.substring(0, 30) : name;
                System.out.println(String.format("|%30s|", subName));
            }
            System.out.println(String.format("================================"));
        } else {
            System.out.println("No elements");
        }
    }

    /**
     * Метод для вывода в консоль списка имен всех пользователей
     */
    public void showAllUsers() {
        UserDAO userDAO = new UserDAO();
        List<User> listUsers = userDAO.getAll();
        if (!listUsers.isEmpty()) {
            System.out.println(String.format("================================"));
            System.out.println(String.format("|%30s|", "Users"));
            System.out.println(String.format("================================"));
            for (User user : listUsers) {
                String name = user.getName();
                String subName = name.length() > 30 ? name.substring(0, 30) : name;
                System.out.println(String.format("|%30s|", subName));
            }
            System.out.println(String.format("================================"));
        } else {
            System.out.println("No elements");
        }
    }

    /**
     * Метод для вывода в консоль списка описания всех проблем
     */
    public void showAllIssues() {
        IssueDAO issueDAO = new IssueDAO();
        List<Issue> listIssue = issueDAO.getAll();
        if (!listIssue.isEmpty()) {
            System.out.println(String.format("================================"));
            System.out.println(String.format("|%30s|", "Description of issues"));
            System.out.println(String.format("================================"));
            for (Issue issue : listIssue) {
                String desc = issue.getDescription();
                String subDesc = desc.length() > 30 ? desc.substring(0, 30) : desc;
                System.out.println(String.format("|%30s|", subDesc));
            }
            System.out.println(String.format("================================"));
        } else {
            System.out.println("No elements");
        }
    }

    /**
     * Метод для вывода в консоль списка проблем для конкретного проекта и пользователя
     *
     * @param nameProject название проекта
     * @param nameUser    имя пользователя
     */
    public void showAllIssuesForTheProjectByTheUser(String nameProject, String nameUser) {
        if (nameProject != null && !nameProject.isEmpty() &&
                nameUser != null && !nameUser.isEmpty()) {
            IssueDAO issueDAO = new IssueDAO();
            List<Issue> listIssue = issueDAO.showAllIssuesForThisProjectByThisUser(nameProject, nameUser);
            if (!listIssue.isEmpty()) {
                System.out.println(String.format("================================"));
                System.out.println(String.format("|%30s|", "Description of issues"));
                System.out.println(String.format("================================"));
                for (Issue issue : listIssue) {
                    String desc = issue.getDescription();
                    String subDesc = desc.length() > 30 ? desc.substring(0, 30) : desc;
                    System.out.println(String.format("|%30s|", subDesc));
                }
                System.out.println(String.format("================================"));
            } else {
                System.out.println("No elements");
            }
        } else {
            throw new NullFieldException("Out of menu rage.");
        }
    }
}