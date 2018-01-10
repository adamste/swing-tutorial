package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener{

    private JButton saveButton;
    private JButton refreshButton;
    private TextPanel textPanel;
    private ToolbarListener toolbarListener;

    public Toolbar(){
        setBorder(BorderFactory.createEtchedBorder());
        saveButton =new JButton("Save");
        refreshButton =new JButton("Refresh");

        saveButton.addActionListener(this);
        refreshButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(saveButton);
        add(refreshButton);
    }

    public void setToolbarListener(ToolbarListener toolbarListener) {
        this.toolbarListener = toolbarListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked= (JButton) e.getSource();
        if (clicked== saveButton){
            if(toolbarListener !=null){
                toolbarListener.saveEventOccured();
            }
        }else if (clicked== refreshButton){
            if(toolbarListener !=null){
                toolbarListener.refreshEventOccured();
            }
        }
    }
}
