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
            //                textPanel.appendText("Hello\n");
        }else if (clicked==goodbyeButton){
//            textPanel.appendText("Goodbye\n");
            if(stringListener!=null){
                stringListener.textEmitted("Goodbye ");
            }
        }
    }
}
