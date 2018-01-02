import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private JButton btn;

    public MainFrame(){
        super("Hello World");

        setLayout(new BorderLayout());

        btn=new JButton("Click me!");
        textPanel=new TextPanel();

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPanel.appendText("hello\n");
            }
        });

        add(textPanel,BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);


        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
