import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;

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

        JButton printButton = new JButton("Print");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openHTML();
            }
        });
        buttonPanel.add(printButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void openHTML() {
        String html = "<!DOCTYPE html><html><head><style>.red {color: red;}table, td {border: 1px solid black;}table {border-collapse: collapse;margin: auto;}td {width: 50px;height: 50px;text-align: center;font-size: 24px;line-height: 50px;}#author {position: absolute;bottom: 20px;left: 20px;}</style><body onload = \"print()\">";
        html += grid.generateHTML();
        html += "<div id = \"author\">Eric Kugel 2022</div></body></head></html>";
        try {
            File file = new File("C:/Users/" + System.getProperty("user.name") + "/Desktop/sudoku.html");
            if (file.exists()) {
                file.delete();
            }
            
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(html);
            writer.close();

            URI uri = new URI("file:///" + file.getAbsolutePath().replace("\\", "/").replace(" ", "%20"));
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg0) {
        new Sudoku();
    }
}