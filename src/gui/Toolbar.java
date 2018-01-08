package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener{

    private JButton helloButton;
    private JButton goodbyeButton;
    private TextPanel textPanel;
    private StringListener stringListener;

    public Toolbar(){
        setBorder(BorderFactory.createEtchedBorder());
        helloButton=new JButton("Hello");
        goodbyeButton=new JButton("Goodbye");

        helloButton.addActionListener(this);
        goodbyeButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(helloButton);
        add(goodbyeButton);
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked= (JButton) e.getSource();
        if (clicked==helloButton){
            if(stringListener!=null){
                stringListener.textEmitted("Hello");
            }
        }else if (clicked==goodbyeButton){
            if(stringListener!=null){
                stringListener.textEmitted("Goodbye ");
            }
        }
    }
}
