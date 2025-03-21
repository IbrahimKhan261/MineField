package mvcApp;

import mvc.*;

public class MineFieldApp {
    public static void main(String[] args) {
        // Create factory
        AppFactory factory = new MineFieldFactory();

        // Create panel with factory (this creates the model internally)
        AppPanel panel = new AppPanel(factory);

        // Get model from panel
        Model model = panel.getModel();

        // Create controller for the model
        Controller controller = new MineFieldController(model);

        // Create panel for the view
        MineFieldPanel viewPanel = new MineFieldPanel((MineField)model);
        viewPanel.setController(controller);

        // Add view panel to the app panel
        panel.add(viewPanel);

        // Display the panel (shows the frame)
        panel.display();
    }
}