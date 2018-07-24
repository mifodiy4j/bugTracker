package ru.job4j;

/**
 * StartUI
 * Класс отображающий информацию в консоль
 * и ожидающий ввода пользователя.
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public class StartUI {
	private int[] ranges;
	private Input input;

	public StartUI(Input input) {
		this.input = input;
	}

	public void init() {

		BugTracker tracker = new BugTracker();
		MenuBagTracker menu = new MenuBagTracker(this.input, tracker);

		ranges = new int [menu.getActionsLength()];
		for (int i = 0; i < menu.getActionsLength(); i++) {
			ranges[i] = i;
		}

		menu.fillActions();

		do {
			menu.show();
			menu.select(input.ask("Select: ", ranges));
		} while(!"y".equals(this.input.ask("Exit? (y) : ")));
	}

	public static void main(String[] args) {
		Input input = new ValidateInput();
		new StartUI(input).init();
	}
}