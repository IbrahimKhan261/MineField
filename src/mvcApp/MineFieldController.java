package mvcApp;

import mvc.Controller;
import mvc.Model;

public class MineFieldController extends Controller {

    public MineFieldController(Model model) {
        super(model);
    }

    public void execute(String commandName) throws Exception {
        MineField.Heading heading = null;

        switch (commandName) {
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
                throw new Exception("Unknown command: " + commandName);
        }

        MoveCommand command = new MoveCommand(model, heading);
        command.execute();
    }
}