package Frazeusz.Crawler;

import javax.swing.*;

public class CrawlerGUI extends JFrame {
    Crawler crawler;

    private JPanel panelRoot;
    private JList list1;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JComboBox comboBox1;
    private JSpinner spinner3;


    public CrawlerGUI(Crawler crawler)
    {
        super("Frazeusz");
        this.crawler = crawler;

        setContentPane(panelRoot);
        pack();
    }



    public void showForm()
    {
        setVisible(true);
    }
}
