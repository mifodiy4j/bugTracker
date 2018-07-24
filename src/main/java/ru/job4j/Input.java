package ru.job4j;

/**
 * Input
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public interface Input {

	String ask(String question);
	
	int ask(String question, int[] range);
}