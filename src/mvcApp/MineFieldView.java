package mvcApp;

import mvc.*;
import java.util.Observable;

public class MineFieldView extends View {
    private static final long serialVersionUID = 1L;
    private MineFieldPanel panel;

    public MineFieldView(Model model, Controller controller, ViewPanel viewPanel) {
        super(model, controller, null);

        panel = new MineFieldPanel((MineField) model);
        panel.setController(controller);
        add(panel);

        addMenu("&Movement", new String[]{
                "&North",
                "&South",
                "&East",
                "&West",
                "North&East",
                "North&West",
                "South&East",
                "South&West"
        });

        setTitle("Mine Field");
        setSize(600, 400);
        setVisible(true);
    }

    public void update(Observable subject, Object message) {
        repaint();
    }

    public void setModel(Model newModel) {
        super.setModel(newModel);
        panel.setModel(newModel);
        repaint();
    }
}