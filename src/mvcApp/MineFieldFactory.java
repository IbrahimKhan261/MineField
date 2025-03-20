package mvcApp;

import mvc.*;

public class MineFieldFactory implements AppFactory {
    public Model makeModel() {
        return new MineField();
    }

    public View makeView(Model model, Controller controller, ViewPanel panel) {
        return new MineFieldView(model, controller, panel);
    }

    public String[] getEditCommands() {
        return new String[]{"North", "South", "East", "West"};
    }

    public Command makeEditCommand(Model model, String type) {
        MineField.Heading heading;

        switch (type) {
            case "North":
                heading = MineField.Heading.NORTH;
                break;
            case "South":
                heading = MineField.Heading.SOUTH;
                break;
            case "East":
                heading = MineField.Heading.EAST;
                break;
            case "West":
                heading = MineField.Heading.WEST;
                break;
            default:
                return null;
        }

        return new MoveCommand(model, heading);
    }

    public String getTitle() {
        return "Mine Field";
    }

    public String[] getHelp() {
        return new String[]{
                "North: Move up",
                "South: Move down",
                "East: Move right",
                "West: Move left",
                "Goal: Reach bottom right corner",
                "Avoid mines.",
                "Numbers indicate how many neighboring cells contain mines"
        };
    }

    public String about() {
        return "Mine Field: Avoid mines and reach the goal.";
    }
}