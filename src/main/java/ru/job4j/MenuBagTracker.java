package ru.job4j;

/**
 * MenuBagTracker
 * Класс формирующий меню и подменю
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public class MenuBagTracker {

    private Input input;
    private BugTracker tracker;
    private UserAction[] actions = new UserAction[6];

    public MenuBagTracker(Input input, BugTracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public int getActionsLength() {
        return actions.length;
    }

    public void fillActions() {
        this.actions[0] = this.new AddNew("Add new", 0);
        this.actions[1] = this.new ShowAllProjects("Show all projects", 1);
        this.actions[2] = this.new ShowAllUsers("Show all users", 2);
        this.actions[3] = this.new ShowAllIssues("Show all issues", 3);
        this.actions[4] = this.new ShowAllIssuesForTheProjectByTheUser("Show all issues for specified projects by specified user", 4);
        this.actions[5] = this.new ExitProgram("Exit Program", 5);
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddNew extends BaseAction {

        AddNew(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 0;
        }

        public void execute(Input input, BugTracker tracker) {
            String nameProject = input.ask("Please, enter the project name: ");
            String nameUser = input.ask("Please, enter the user name: ");
            String descUssue = input.ask("Please, enter the description issue: ");
            tracker.addNew(new Project(nameProject), new User(nameUser), new Issue(descUssue));
        }
    }

    private class ShowAllProjects extends BaseAction {

        ShowAllProjects(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 1;
        }

        public void execute(Input input, BugTracker tracker) {
            tracker.showAllProjects();
        }
    }

    private class ShowAllUsers extends BaseAction {

        ShowAllUsers(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 2;
        }

        public void execute(Input input, BugTracker tracker) {
            tracker.showAllUsers();
        }
    }

    private class ShowAllIssues extends BaseAction {

        ShowAllIssues(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 3;
        }

        public void execute(Input input, BugTracker tracker) {
            tracker.showAllIssues();
        }
    }

    private class ShowAllIssuesForTheProjectByTheUser extends BaseAction {

        ShowAllIssuesForTheProjectByTheUser(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 4;
        }

        public void execute(Input input, BugTracker tracker) {
            String nameProject = input.ask("Please, enter the project name: ");
            String nameUser = input.ask("Please, enter the user name: ");
            tracker.showAllIssuesForTheProjectByTheUser(nameProject, nameUser);
        }
    }

    private class ExitProgram extends BaseAction {

        ExitProgram(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 5;
        }

        public void execute(Input input, BugTracker tracker) {
            tracker.closeConnect();
        }
    }
}