package ru.job4j;

/**
 * Исключение которое выбрасывается если строки остались
 * незаполненными
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public class NullFieldException extends RuntimeException {

    public NullFieldException(String msg) {
        super(msg);
    }
}