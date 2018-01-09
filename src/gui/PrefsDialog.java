package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrefsDialog extends JDialog {

    private JButton okBtn;
    private JButton cancelBtn;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerNumberModel;
    private JTextField userField;
    private JPasswordField passwordField;

    private PrefsListener prefsListener;

    public PrefsDialog(JFrame parent){
        super(parent, "Preferences", false);
        okBtn=new JButton("OK");
        cancelBtn=new JButton("Cancel");

        spinnerNumberModel=new SpinnerNumberModel(3306,0,9999,1);
        portSpinner=new JSpinner(spinnerNumberModel);

        userField=new JTextField(10);
        passwordField=new JPasswordField(10);

        passwordField.setEchoChar('*');

        layoutControls();

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer value=(Integer) portSpinner.getValue();
                System.out.println(value);
                String user=userField.getText();
                char[] password=passwordField.getPassword();
                System.out.println(user+" "+new String(password));

                if(prefsListener!=null){
                    prefsListener.preferencesSet(user,new String(password),value);
                }

                setVisible(false);
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
            }
        });

        setSize(340,200);
        setLocationRelativeTo(parent);
    }

    private void layoutControls() {
        JPanel controlsPanel=new JPanel();
        JPanel buttonsPanel=new JPanel();

        int space=15;
        Border spaceBorder =BorderFactory.createEmptyBorder(space,space,space,space);
        Border titleBorder=BorderFactory.createTitledBorder("Database Preferences");

        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder));

        controlsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc=new GridBagConstraints();

        gc.gridy=0;
        gc.gridx=0;

        Insets rightPadding =new Insets(0,0,0,15);
        Insets noPadding=new Insets(0,0,0,0);
        //////////// first row////////////////////
        gc.weightx=1;
        gc.weighty=1;
        gc.fill=GridBagConstraints.NONE;

        gc.anchor=GridBagConstraints.EAST;
        gc.insets=rightPadding;
        controlsPanel.add(new JLabel("User: "),gc);
        gc.gridx++;
        gc.anchor=GridBagConstraints.WEST;
        gc.insets=noPadding;
        controlsPanel.add(userField, gc);

        ///////////// next row ///////////////
        gc.gridy++;
        gc.weightx=1;
        gc.weighty=1;
        gc.fill=GridBagConstraints.NONE;

        gc.gridx=0;

        gc.anchor=GridBagConstraints.EAST;
        gc.insets=rightPadding;
        controlsPanel.add(new JLabel("Password: "),gc);
        gc.gridx++;
        gc.anchor=GridBagConstraints.WEST;
        gc.insets=noPadding;
        controlsPanel.add(passwordField, gc);

        ///////////// next row ///////////////
        gc.gridy++;
        gc.weightx=1;
        gc.weighty=1;
        gc.fill=GridBagConstraints.NONE;

        gc.gridx=0;

        gc.anchor=GridBagConstraints.EAST;
        gc.insets=rightPadding;
        controlsPanel.add(new JLabel("Port: "),gc);

        gc.gridx++;
        gc.anchor=GridBagConstraints.WEST;
        gc.insets=noPadding;
        controlsPanel.add(portSpinner, gc);

        //////buttons panel
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okBtn);
        buttonsPanel.add(cancelBtn);

        Dimension buttonSize=cancelBtn.getPreferredSize();
        okBtn.setPreferredSize(buttonSize);

        // add sub panels to dialog
        setLayout(new BorderLayout());
        add(controlsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);


    }

    public void setPrefsListener(PrefsListener prefsListener) {
        this.prefsListener = prefsListener;
    }

    public void setDefaults(String user, String password, int port){
        userField.setText(user);
        passwordField.setText(password);
        portSpinner.setValue(port);
    }


}
