package mvc;

import javax.swing.JPanel;

public class ViewPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    protected Model model;
    protected Controller controller;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Model getModel() {
        return model;
    }

    public Controller getController() {
        return controller;
    }
}