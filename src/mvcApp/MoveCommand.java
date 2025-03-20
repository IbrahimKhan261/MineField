package mvcApp;

import mvc.Command;
import mvc.Model;

public class MoveCommand extends Command {
    private static final long serialVersionUID = 1L;
    private MineField.Heading heading;

    public MoveCommand(Model model, MineField.Heading heading) {
        super(model);
        this.heading = heading;
    }

    public void execute() {
        try {
            ((MineField)model).move(heading);
        } catch (Exception e) {
            mvc.Utilities.inform(e.getMessage());
        }
    }
}