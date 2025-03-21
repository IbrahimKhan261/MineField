package mvc;

public interface AppFactory {
    Model makeModel();

    View makeView(Model model, Controller controller, ViewPanel panel);

    String[] getEditCommands();

    Command makeEditCommand(Model model, String type);

    String getTitle();

    String[] getHelp();

    String about();
}
