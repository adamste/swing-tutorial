import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;

    public FormPanel(){
        Dimension dimension=getPreferredSize();
        dimension.width=250;
        setPreferredSize(dimension);

        nameLabel=new JLabel("Name: ");
        occupationLabel=new JLabel("Ocupation: ");
        nameField=new JTextField(10);
        occupationField=new JTextField(10);
        okBtn=new JButton("OK"); 

        Border innerBorder=BorderFactory.createTitledBorder("Add Person");
        Border outerBorder=BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }

}
