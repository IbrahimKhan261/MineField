package mvcApp;

import mvc.*;

public class MineFieldApp {
    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();

        AppPanel panel = new AppPanel(factory);

        Model model = panel.getModel();

        Controller controller = new MineFieldController(model);

        MineFieldPanel viewPanel = new MineFieldPanel((MineField)model);
        viewPanel.setController(controller);

        panel.add(viewPanel);

        panel.display();
    }
}