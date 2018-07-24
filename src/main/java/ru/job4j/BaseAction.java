package ru.job4j;

/**
 * BaseAction
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public abstract class BaseAction implements UserAction {
	
	private String name;
	public int key;

	public BaseAction(String name, int key) {
		this.name = name;
		this.key = key;
	}

	public String info() {
		return String.format("%s. %s", this.key(), name);
	}
}