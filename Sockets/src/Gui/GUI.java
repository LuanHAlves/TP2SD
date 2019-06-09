package Gui;

import javax.swing.*;

public class GUI extends JFrame {
    private JPanel mainPanel;
    private JLabel imgLabel;
    private JLabel textImg;

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    public void setImgLabel(String fig) {
        ImageIcon img = new ImageIcon (getClass ( ).getResource (fig + ".png"));
        imgLabel.setIcon (img);
        textImg.setText (fig);
    }

    public GUI() {
        setSize (WIDTH, HEIGHT);
        setContentPane (mainPanel);
    }

}
