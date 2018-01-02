import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private JButton btn;
    private Toolbar toolbar;
    private FormPanel formPanel;

    public MainFrame(){
        super("Hello World");

        setLayout(new BorderLayout());
        toolbar=new Toolbar();

        btn=new JButton("Click me!");
        textPanel=new TextPanel();
        formPanel=new FormPanel();

        toolbar.setStringListener(new StringListener() {
            @Override
            public void textEmitted(String text) {
                textPanel.appendText(text);
            }
        });

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPanel.appendText("hello\n");
            }
        });

        add(formPanel,BorderLayout.WEST);
        add(toolbar,BorderLayout.NORTH);
        add(textPanel,BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);


        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
