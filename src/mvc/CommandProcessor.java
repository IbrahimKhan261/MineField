package mvc;

import java.util.Stack;

public class CommandProcessor {
    private static Stack<Command> undoStack = new Stack<Command>();
    private static Stack<Command> redoStack = new Stack<Command>();

    public static void execute(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear(); // Clear redo stack when a new command is executed
    }

    public static boolean undo() {
        if (undoStack.isEmpty()) {
            return false;
        }

        Command command = undoStack.pop();
        command.undo();
        redoStack.push(command);
        return true;
    }

    public static boolean redo() {
        if (redoStack.isEmpty()) {
            return false;
        }

        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
        return true;
    }

    public static void clearHistory() {
        undoStack.clear();
        redoStack.clear();
    }
}