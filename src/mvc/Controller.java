package mvc;

public class Controller {
    protected Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void execute(String commandName) throws Exception {
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}