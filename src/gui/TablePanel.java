package gui;

import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TablePanel extends JPanel {
    private JTable table;
    private PersonTableModel tableModel;
    private JPopupMenu popupMenu;

    public TablePanel() {
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        popupMenu=new JPopupMenu();

        JMenuItem removeItem=new JMenuItem("Delete row");
        popupMenu.add(removeItem);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3){
                    popupMenu.show(table, e.getX(), e.getY());
                }
            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(ArrayList<Person> db) {
        tableModel.setData(db);
    }

    public void refresh(){
        tableModel.fireTableDataChanged();
    }
}
