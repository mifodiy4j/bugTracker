package ru.job4j;

import java.util.Scanner;

/**
 * ConsoleInput
 * Класс для выполнения операций
 * обработки ввода из консоли.
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public class ConsoleInput implements Input {

	private Scanner scanner = new Scanner(System.in);

	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}

	public int ask(String question, int[] range) {
		int key = Integer.valueOf(this.ask(question));
		boolean exist = false;
		for (int value : range) {
			if (value == key) {
				exist = true;
				break;
			}
		}
		if (exist) {
			return key;
		} else {
			throw new MenuOutException("Out of menu rage.");
		}
	}
}