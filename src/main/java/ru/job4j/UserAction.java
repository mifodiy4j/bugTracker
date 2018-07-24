package ru.job4j;

/**
 * UserAction
 *
 * @author Mikhailov Sergey Mikhailov
 * @since 23.07.18
 */
public interface UserAction {
	
	int key();

	void execute(Input input, BugTracker tracker);

	String info();
}