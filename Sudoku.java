import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Sudoku extends JFrame {
    public static int SCREEN_SIZE = 500;
    private Grid grid;

    public Sudoku() {
        setTitle("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        initGUI();
        pack();
    }

    private void initGUI() {    
        JPanel main = new JPanel();
        main.setLayout(new GridLayout(9, 9));
        grid = new Grid();
        grid.initialize();
        Square[][] array = grid.getArray();
        for (Square[] row : array) {
            for (Square item : row) {
                main.add(item);
            }
        }
        add(main);

        JPanel buttonPanel = new JPanel();
        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grid.initialize();
            }
        });
        buttonPanel.add(generateButton);

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grid.solve();
            }
        });
        buttonPanel.add(solveButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grid.clear();
            }
        });
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] arg0) {
        new Sudoku();
    }
}