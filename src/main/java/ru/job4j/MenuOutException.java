package ru.job4j;

/**
 * Исключение которое выбрасывается при вводе значения меню
 * выходящего за пределы диапазона.
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public class MenuOutException extends RuntimeException {
	
	public MenuOutException(String msg) {
		super(msg);
	}
}