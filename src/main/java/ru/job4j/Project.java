package ru.job4j;

/**
 * Project
 * Класс проект со свойствами <b>name</b>.
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public class Project extends Model {
    private String name;
    private int user_id;
    private int issue_id;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name название проекта
     * @param user_id id пользователя
     * @param issue_id id неисправности
     */
    public Project(String name, int user_id, int issue_id) {
        this.name = name;
        this.user_id = user_id;
        this.issue_id = issue_id;
    }

    /**
     * Метод получения значения поля {@link Project#name}
     * @return название проекта
     */
    public String getName() {
        return name;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getIssue_id() {
        return issue_id;
    }
}