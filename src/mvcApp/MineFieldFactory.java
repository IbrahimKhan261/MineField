package mvcApp;

import mvc.*;

public class MineFieldFactory implements AppFactory {
    @Override
    public Model makeModel() {
        return new MineField();
    }

    @Override
    public View makeView(Model model, Controller controller, ViewPanel panel) {
        return new MineFieldView(model, controller, panel);
    }

    @Override
    public String[] getEditCommands() {
        // Include diagonal directions in the Edit menu
        return new String[]{"North", "South", "East", "West", "NorthEast", "NorthWest", "SouthEast", "SouthWest"};
    }

    @Override
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
            case "NorthEast":
                heading = MineField.Heading.NORTHEAST;
                break;
            case "NorthWest":
                heading = MineField.Heading.NORTHWEST;
                break;
            case "SouthEast":
                heading = MineField.Heading.SOUTHEAST;
                break;
            case "SouthWest":
                heading = MineField.Heading.SOUTHWEST;
                break;
            default:
                return null;
        }

        return new MoveCommand(model, heading);
    }

    @Override
    public String getTitle() {
        return "Mine Field";
    }

    @Override
    public String[] getHelp() {
        return new String[]{
                "North: Move up",
                "South: Move down",
                "East: Move right",
                "West: Move left",
                "NorthEast: Move diagonally up-right",
                "NorthWest: Move diagonally up-left",
                "SouthEast: Move diagonally down-right",
                "SouthWest: Move diagonally down-left",
                "Goal: Reach bottom right corner",
                "Avoid mines.",
                "Numbers indicate how many neighboring cells contain mines"
        };
    }

    @Override
    public String about() {
        return "Mine Field: Avoid mines and reach the goal.";
    }
}