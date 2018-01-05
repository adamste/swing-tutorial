import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private JButton btn;
    private static FormPanel formPanel;
    private Toolbar toolbar;
    private JFileChooser fileChooser;

    public MainFrame(){
        super("Hello World");

        setLayout(new BorderLayout());
        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        fileChooser=new JFileChooser();

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
                String name = e.getName();
                String occupation = e.getOccupation();
                int ageCat = e.getAgeCategory();
                String empCat = e.getEmpCat();
                textPanel.appendText(name + ": " + occupation + " : "
                        + ageCat + ": " + empCat + " " + e.getGenderCommand() + "\n");
            }
        });

//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                textPanel.appendText("hello\n");
//            }
//        });

        add(formPanel,BorderLayout.WEST);
        add(toolbar,BorderLayout.NORTH);
        add(textPanel,BorderLayout.CENTER);
//        add(btn, BorderLayout.SOUTH);


        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem exportData = new JMenuItem("Export data...");
        JMenuItem importData = new JMenuItem("Import data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportData);
        fileMenu.add(importData);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");

        final JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        showFormItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem= (JCheckBoxMenuItem)e.getSource();
                formPanel.setVisible(menuItem.isSelected());
            }
        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        importData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // x
            }
        });

        return menuBar;
    }
}
