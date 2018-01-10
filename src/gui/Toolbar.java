package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Toolbar extends JToolBar implements ActionListener{

    private JButton saveButton;
    private JButton refreshButton;
    private TextPanel textPanel;
    private ToolbarListener toolbarListener;

    public Toolbar(){
        ///get rid of the border if you want to the toolbar draggable
        setBorder(BorderFactory.createEtchedBorder());
        saveButton =new JButton();
        saveButton.setIcon(createIcon("/images/Save16.gif"));
        saveButton.setToolTipText("Save");

        refreshButton =new JButton();
        refreshButton.setIcon(createIcon("/images/Refresh16.gif"));
        refreshButton.setToolTipText("Refresh");

        saveButton.addActionListener(this);
        refreshButton.addActionListener(this);

        //setLayout(new FlowLayout(FlowLayout.LEFT));

        add(saveButton);
//        addSeparator();
        add(refreshButton);
    }

    private ImageIcon createIcon(String path){
        URL url=getClass().getResource(path);
        if (url==null){
            System.err.println("Unable to load image: "+path);
        }

        ImageIcon imageIcon=new ImageIcon(url, "Nice icon man!");
        return imageIcon;
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
