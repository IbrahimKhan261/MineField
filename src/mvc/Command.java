package mvc;

import java.io.Serializable;

public abstract class Command implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Model model;

    public Command(Model model) {
        this.model = model;
    }

    public abstract void execute();

    public void undo() {
        throw new UnsupportedOperationException("Undo not supported for this command.");
    }
}
