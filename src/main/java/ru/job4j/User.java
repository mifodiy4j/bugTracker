package ru.job4j;

/**
 * Issue
 * Класс пользователь со свойствами <b>name</b>.
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public class User {

    private final String name;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name имя пользователя
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Метод получения значения поля {@link User#name}
     * @return имя пользователя
     */
    public String getName() {
        return name;
    }
}