package ru.job4j;

/**
 * Project
 * Класс проект со свойствами <b>name</b>.
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public class Project {

    private final String name;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name название проекта
     */
    public Project(String name) {
        this.name = name;
    }

    /**
     * Метод получения значения поля {@link Project#name}
     * @return название проекта
     */
    public String getName() {
        return name;
    }
}