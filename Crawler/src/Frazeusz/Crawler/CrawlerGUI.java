package Frazeusz.Crawler;

import javax.swing.*;

public class CrawlerGUI {
    private JPanel panelRoot;
    private JList listDomains;
    private JSpinner spinnerPages;
    private JSpinner spinnerDepth;
    private JComboBox comboBoxBytesMultiplier;
    private JSpinner spinnerBytes;

    private int getSelectedMultiplier(JComboBox box) {
        int selected = box.getSelectedIndex();

        return 1 << (10 * selected);
    }

    public CrawlerConfigurator getConfigurator() {
        CrawlerConfigurator config = new CrawlerConfigurator();

        config.setPages((Integer)spinnerPages.getValue());
        config.setDepth((Integer) spinnerDepth.getValue());
        config.setBytes((Integer) spinnerBytes.getValue() * getSelectedMultiplier(comboBoxBytesMultiplier));

        ListModel listModel = listDomains.getModel();
        for (int i = 0, l = listModel.getSize(); i < l; i++) {
            config.addDomain((String) listModel.getElementAt(i));
        }

        return config;
    }

    public JPanel getRootPanel() {
        return panelRoot;
    }
}
