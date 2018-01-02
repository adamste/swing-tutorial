import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormPanel extends JPanel {

    public FormPanel(){
        Dimension dimension=getPreferredSize();
        dimension.width=250;
        setPreferredSize(dimension);
        Border innerBorder=BorderFactory.createTitledBorder("Add Person");
        Border outerBorder=BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }

}
