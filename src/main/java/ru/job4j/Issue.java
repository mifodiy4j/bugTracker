package ru.job4j;

/**
 * Issue
 * Класс проблема со свойствами <b>description</b>.
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public class Issue extends Model {
    private final String description;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param description описание
     */
    public Issue(String description) {
        this.description = description;
    }

    /**
     * Метод получения значения поля {@link Issue#description}
     * @return описание проблемы
     */
    public String getDescription() {
        return description;
    }
}