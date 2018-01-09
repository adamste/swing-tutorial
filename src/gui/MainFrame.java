package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private static FormPanel formPanel;
    private Toolbar toolbar;
    private JFileChooser fileChooser;
    private Controller controller;
    private TablePanel tablePanel;
    private PrefsDialog prefsDialog;
    private Preferences prefs;

    public MainFrame() {
        super("Hello World");

        setLayout(new BorderLayout());
        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        tablePanel = new TablePanel();
        controller = new Controller();
        prefsDialog=new PrefsDialog(this);
        prefs=Preferences.userRoot().node("db");

        tablePanel.setData(controller.getPeople());

        tablePanel.setPersonTableListener(new PersonTableListener(){
            public void rowDeleted(int row){
                controller.removePerson(row);
                System.out.println(row);
            }
        });

        prefsDialog.setPrefsListener(new PrefsListener() {
            @Override
            public void preferencesSet(String user, String password, int portNumber) {
                prefs.put("user", user);
                prefs.put("password", password);
                prefs.putInt("port", portNumber);
            }
        });


        String user=prefs.get("user","");
        String password=prefs.get("password","");
        Integer port=prefs.getInt("port",3306);
        prefsDialog.setDefaults(user,password,port);

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        setJMenuBar(createMenuBar());

        toolbar.setStringListener(new StringListener() {
            @Override
            public void textEmitted(String text) {
                textPanel.appendText(text);
            }
        });

        formPanel.setFormListener(new FormListener() {
            @Override
            public void formEventOccured(FormEvent e) {
//                String name = e.getName();
//                String occupation = e.getOccupation();
//                int ageCat = e.getAgeCategory();
//                String empCat = e.getEmpCat();
//                textPanel.appendText(name + ": " + occupation + " : "
//                        + ageCat + ": " + empCat + " " + e.getGenderCommand() + "\n");
                controller.addPerson(e);
                tablePanel.refresh();
            }
        });

        add(formPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        setMinimumSize(new Dimension(500, 500));
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");
        JMenuItem prefsItem=new JMenuItem("Preferences...");

        JMenuItem exportData = new JMenuItem("Export data...");
        JMenuItem importData = new JMenuItem("Import data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportData);
        fileMenu.add(importData);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);
        windowMenu.add(prefsItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        prefsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prefsDialog.setVisible(true);
            }
        });

        showFormItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
                formPanel.setVisible(menuItem.isSelected());
            }
        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke
                .getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        prefsItem.setAccelerator(KeyStroke
                .getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        importData.setAccelerator(KeyStroke
                .getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));


        importData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.loadFromFile(fileChooser.getSelectedFile());
                        tablePanel.refresh();
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                    System.out.println(fileChooser.getSelectedFile());
                }
            }
        });

        exportData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not save data to file.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                    System.out.println(fileChooser.getSelectedFile());
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String username=JOptionPane.showInputDialog(MainFrame.this,
//                        "Enter your username",
//                        "Enter username", JOptionPane.OK_OPTION|JOptionPane.QUESTION_MESSAGE);
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do you really want to exit?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });

        return menuBar;
    }
}
