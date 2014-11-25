package Frazeusz.GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JTabbedPane tabs;
    private JPanel panelRoot;
    private JButton buttonStart;

    public GUI(ActionListener onStart) {
        super("Frazeusz");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setContentPane(panelRoot);
        pack();

        buttonStart.addActionListener(onStart);
    }

    public void showForm() {
        setVisible(true);
    }

    public void addPane(String title, JPanel panel) {
        tabs.addTab(title, panel);
    }
}
