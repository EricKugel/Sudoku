import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Square extends JButton {
    public static final int SIZE = Sudoku.SCREEN_SIZE / 9;
    
    private int value;
    private boolean given = false;

    public Square(int value) {
        setValue(value);
        setPreferredSize(new Dimension(SIZE, SIZE));
        setText("" + value);
        setBackground(new Color(240, 240, 240));

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                try {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        setValue(0);
                    } else {
                        int number = Integer.parseInt("" + e.getKeyChar());
                        setValue(number);
                        setGiven(true);
                    }
                } catch(Exception ex) {
                    // do nothing
                }
            }
        });
    }

    public void setValue(int value) {
        this.value = value;
        setText("" + value);
        if (value == 0) {
            setText("");
        }
        repaint();
    }

    public void changeValue(int value) {
        this.value += value;
        setText("" + value);
        repaint();
    }

    public int getValue() {
        return this.value;
    }

    public void setGiven(boolean given) {
        this.given = given;
        if (given) {
            setForeground(Color.BLACK);
        } else {
            setForeground(Color.RED);
        }
        repaint();
    }

    public boolean isGiven() {
        return given;
    }
}