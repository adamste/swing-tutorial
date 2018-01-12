package gui;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JDialog {
    private JButton cancelButton;
    private JProgressBar progressBar;

    public ProgressDialog(Window parent){
        super(parent, "Messages downloading...", ModalityType.APPLICATION_MODAL);

        cancelButton=new JButton("Cancel");
        progressBar=new JProgressBar();
//        setSize(400, 200);

//        progressBar.setIndeterminate(true);

        setLayout(new FlowLayout());

        Dimension size=cancelButton.getPreferredSize();
        size.width=400;
        progressBar.setPreferredSize(size);

        add(progressBar);
        add(cancelButton);

        pack();

        setLocationRelativeTo(parent);
    }

    public void setMaximum(int count){
        progressBar.setMaximum(count);
    }

    public void setValue(int value){
        progressBar.setValue(value);
    }

    @Override
    public void setVisible(boolean visible) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(visible==false){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    progressBar.setValue(0);
                }
                ProgressDialog.super.setVisible(visible);
            }
        });
    }
}
