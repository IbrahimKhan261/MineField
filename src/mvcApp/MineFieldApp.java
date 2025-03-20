package mvcApp;

import mvc.*;

public class MineFieldApp {
    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new AppPanel(factory);

        Model model = factory.makeModel();
        Controller controller = new MineFieldController(model);
        View view = factory.makeView(model, controller, null);

        panel.display();
    }
}