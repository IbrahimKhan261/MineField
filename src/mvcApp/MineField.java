package mvcApp;

import mvc.Model;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class MineField extends Model {
    private static final long serialVersionUID = 1L;
    public static int percentMined = 5;
    private static final int GRID_SIZE = 20; // Changed from 10 to 20

    private boolean[][] mines;
    private boolean[][] visited;
    private int playerRow;
    private int playerCol;
    private boolean gameOver;
    private boolean reachedGoal;
    private PropertyChangeSupport pcs;

    public enum Heading {
        NORTH, SOUTH, EAST, WEST,
        NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST
    }


    public MineField() {
        mines = new boolean[GRID_SIZE][GRID_SIZE];
        visited = new boolean[GRID_SIZE][GRID_SIZE];
        playerRow = 0;
        playerCol = 0;
        gameOver = false;
        reachedGoal = false;
        pcs = new PropertyChangeSupport(this);

        initializeField();
        visited[playerRow][playerCol] = true;
    }

    private void initializeField() {
        Random random = new Random();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                // Don't place mines at start or goal
                if ((i == 0 && j == 0) || (i == GRID_SIZE - 1 && j == GRID_SIZE - 1)) {
                    mines[i][j] = false;
                } else {
                    mines[i][j] = random.nextInt(100) < percentMined;
                }
            }
        }
    }

    public void initSupport() {
        if (pcs == null) {
            pcs = new PropertyChangeSupport(this);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        initSupport();
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (pcs != null) {
            pcs.removePropertyChangeListener(listener);
        }
    }

    public void move(Heading heading) throws Exception {
        if (gameOver) {
            throw new Exception("Game over! Cannot move anymore.");
        }

        int newRow = playerRow;
        int newCol = playerCol;

        switch (heading) {
            case NORTH:
                newRow--;
                break;
            case SOUTH:
                newRow++;
                break;
            case EAST:
                newCol++;
                break;
            case WEST:
                newCol--;
                break;
            case NORTHEAST:
                newRow--;
                newCol++;
                break;
            case NORTHWEST:
                newRow--;
                newCol--;
                break;
            case SOUTHEAST:
                newRow++;
                newCol++;
                break;
            case SOUTHWEST:
                newRow++;
                newCol--;
                break;
        }

        // Check if the move is valid
        if (newRow < 0 || newRow >= GRID_SIZE || newCol < 0 || newCol >= GRID_SIZE) {
            throw new Exception("Cannot move off the grid!");
        }

        // Update player position
        playerRow = newRow;
        playerCol = newCol;
        visited[playerRow][playerCol] = true;

        // Check for mine
        if (mines[playerRow][playerCol]) {
            gameOver = true;
            throw new Exception("BOOM! You stepped on a mine!");
        }

        // Check for goal
        if (playerRow == GRID_SIZE - 1 && playerCol == GRID_SIZE - 1) {
            gameOver = true;
            reachedGoal = true;
            throw new Exception("Congratulations! You reached the goal!");
        }

        // Notify observers
        setChanged();
        pcs.firePropertyChange("playerMoved", null, heading);
    }

    public int getAdjacentMines(int row, int col) {
        int count = 0;

        for (int i = Math.max(0, row - 1); i <= Math.min(GRID_SIZE - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(GRID_SIZE - 1, col + 1); j++) {
                if (mines[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public boolean isVisited(int row, int col) {
        return visited[row][col];
    }

    public boolean isMine(int row, int col) {
        return mines[row][col];
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isReachedGoal() {
        return reachedGoal;
    }

    public int getGridSize() {
        return GRID_SIZE;
    }

    public boolean isGoal(int row, int col) {
        return row == GRID_SIZE - 1 && col == GRID_SIZE - 1;
    }

    public int getAdjacentMinesForPlayer() {
        return getAdjacentMines(playerRow, playerCol);
    }
}