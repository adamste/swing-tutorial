package gui;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JDialog {

    public ProgressDialog(Window parent){
        super(parent, "Messages downloading...", ModalityType.APPLICATION_MODAL);

        setSize(400, 200);
    }

}
