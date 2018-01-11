package gui;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

class ServerInfo {
    private String name;
    private int id;
    private boolean checked;

    public ServerInfo(String name, int id, boolean checked) {
        this.name = name;
        this.id = id;
        this.checked=checked;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return name;
    }

    public boolean isChecked(){
        return checked;
    }

    public void setChecked(boolean checked){
        this.checked=checked;
    }
}

public class MessagePanel extends JPanel {
    private JTree serverTree;
    private ServerTreeCellRendered treeCellRenderer;
    private ServerTreeCellEditor treeCellEditor;

    public MessagePanel() {
//        treeCellRenderer = new ServerTree();
        treeCellRenderer=new ServerTreeCellRendered();
        treeCellEditor=new ServerTreeCellEditor();

        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(treeCellEditor);
        serverTree.setEditable(true);

        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        treeCellEditor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingCanceled(ChangeEvent e) {
            }

            @Override
            public void editingStopped(ChangeEvent e) {
                ServerInfo info=(ServerInfo)treeCellEditor.getCellEditorValue();
                System.out.println(info+" : "+info.getId()+"; "+info.isChecked());
            }
        });

//        serverTree.addTreeSelectionListener(new TreeSelectionListener() {
//            @Override
//            public void valueChanged(TreeSelectionEvent e) {
//                DefaultMutableTreeNode node = (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent();
//                Object userObject = node.getUserObject();
//
//                if (userObject instanceof ServerInfo) {
//                    int id = ((ServerInfo) userObject).getId();
//                    System.out.println("Got user object with id: " + id);
//                }
//
//                System.out.println(userObject);
//            }
//        });
        setLayout(new BorderLayout());

        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");
        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 0, true));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 1, false));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 2, true));
        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London", 3, false));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh", 4,true));
        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);

        return top;
    }
}
