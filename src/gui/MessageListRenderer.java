package gui;

import com.mysql.jdbc.StringUtils;
import model.Message;

import javax.swing.*;
import java.awt.*;


/**
 * Note -- this demonstrates using a arbitrary component as a list a box renderer
 * Probably overkill to use JPanel when JLabel could be used directly
 */
public class MessageListRenderer implements ListCellRenderer {

    private JPanel panel;
    private JLabel label;
    private Color selectedColor;
    private Color normalColor;

    public MessageListRenderer() {
        panel = new JPanel();
        label = new JLabel();

        label.setFont(Utils.createFont("/fonts/CrimewaveBB.ttf").deriveFont(Font.BOLD, 21));

        selectedColor = Color.CYAN;
        normalColor = Color.WHITE;

        label.setIcon(Utils.createIcon("/images/Information24.gif"));

        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel.add(label);
    }

    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        Message message = (Message) value;

        label.setText(message.getTitle());

        panel.setBackground(cellHasFocus ? selectedColor : normalColor);

        return panel;
    }


}
