package Gui;

import javax.swing.*;

public class GUI extends JFrame {
    private JPanel mainPanel;
    private JLabel imgLabel;
    private JLabel textImg;
    private JLabel iterLabel;

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    public void setImgLabel(String fig) {
        ImageIcon img = new ImageIcon (getClass ( ).getResource ("figuras/"+fig + ".png"));
        imgLabel.setIcon (img);
        textImg.setText (fig);
    }

    public void setIterLabel(int i) {
        String iStr = Integer.toString (i);
        iterLabel.setText (iStr);
    }

    public int setOpcao() {
        String opInt = JOptionPane.showInputDialog ("Número de Requisições: ");
        return Integer.parseInt (opInt);
    }

    public GUI() {
        setSize (WIDTH, HEIGHT);
        setContentPane (mainPanel);
    }

}
