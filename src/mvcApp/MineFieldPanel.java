package mvcApp;

import mvc.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.awt.event.*;

public class MineFieldPanel extends ViewPanel implements PropertyChangeListener {
    private static final long serialVersionUID = 1L;
    private MineField model;
    private Cell[][] cells;
    private JLabel statusLabel;

    public MineFieldPanel(MineField model) {
        this.model = model;
        model.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        // Grid panel
        JPanel gridPanel = new JPanel(new GridLayout(model.getGridSize(), model.getGridSize()));
        cells = new Cell[model.getGridSize()][model.getGridSize()];

        for (int i = 0; i < model.getGridSize(); i++) {
            for (int j = 0; j < model.getGridSize(); j++) {
                cells[i][j] = new Cell(i, j);
                gridPanel.add(cells[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        // Status panel
        JPanel statusPanel = new JPanel();
        statusLabel = new JLabel("Mine detector: 0 adjacent mines");
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);

        // Control panel
        JPanel controlPanel = new JPanel(new GridLayout(3, 3));
        controlPanel.add(new JLabel("")); // Empty
        JButton northButton = new JButton("North");
        northButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    model.move(MineField.Heading.NORTH);
                } catch (Exception ex) {
                    mvc.Utilities.inform(ex.getMessage());
                }
            }
        });
        controlPanel.add(northButton);
        controlPanel.add(new JLabel("")); // Empty

        JButton westButton = new JButton("West");
        westButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    model.move(MineField.Heading.WEST);
                } catch (Exception ex) {
                    mvc.Utilities.inform(ex.getMessage());
                }
            }
        });
        controlPanel.add(westButton);
        controlPanel.add(new JLabel("")); // Empty
        JButton eastButton = new JButton("East");
        eastButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    model.move(MineField.Heading.EAST);
                } catch (Exception ex) {
                    mvc.Utilities.inform(ex.getMessage());
                }
            }
        });
        controlPanel.add(eastButton);

        controlPanel.add(new JLabel("")); // Empty
        JButton southButton = new JButton("South");
        southButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    model.move(MineField.Heading.SOUTH);
                } catch (Exception ex) {
                    mvc.Utilities.inform(ex.getMessage());
                }
            }
        });
        controlPanel.add(southButton);
        controlPanel.add(new JLabel("")); // Empty

        add(controlPanel, BorderLayout.EAST);

        updateView();
    }

    private void updateView() {
        for (int i = 0; i < model.getGridSize(); i++) {
            for (int j = 0; j < model.getGridSize(); j++) {
                cells[i][j].update();
            }
        }

        int adjacentMines = model.getAdjacentMinesForPlayer();
        statusLabel.setText("Mine detector: " + adjacentMines + " adjacent mines");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateView();
    }

    private class Cell extends JPanel {
        private static final long serialVersionUID = 1L;
        private int row;
        private int col;
        private JLabel label;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
            setPreferredSize(new Dimension(25, 25)); // Smaller cells for 20x20 grid
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
            setLayout(new BorderLayout());
            label = new JLabel("", JLabel.CENTER);
            add(label, BorderLayout.CENTER);
            update();
        }

        public void update() {
            // No colored background squares as requested

            // Clear previous content
            label.setText("");

            // Show adjacent mine count for visited cells
            if (model.isVisited(row, col)) {
                if (model.getPlayerRow() == row && model.getPlayerCol() == col) {
                    label.setText("X"); // Mark player position with X
                } else {
                    label.setText(String.valueOf(model.getAdjacentMines(row, col)));
                }
            }

            // Mark goal (can be subtle)
            if (model.isGoal(row, col)) {
                setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
            } else {
                setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            revalidate();
            repaint();
        }
    }

    @Override
    public void setModel(Model newModel) {
        super.setModel(newModel);
        this.model = (MineField) newModel;
        model.addPropertyChangeListener(this);
        updateView();
    }
}