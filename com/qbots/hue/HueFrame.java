package com.qbots.hue;

import com.qbots.hue.tasks.DropAllExcept;
import com.qbots.hue.tasks.EndIf;
import com.qbots.hue.tasks.If;
import org.dreambot.api.methods.MethodContext;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tyler on 2/18/2015.
 */
public class HueFrame extends JFrame {

    public HueFrame(HueScript ctx) {
        list = new JList();
        menuBar = new MenuBar();

        scriptMenu = new Menu("Script");
        start = new MenuItem("Start");
        start.addActionListener(e->ctx.running = true);
        stop = new MenuItem("Stop");
        stop.addActionListener(e->ctx.running = false);
        scriptMenu.add(start);
        scriptMenu.add(stop);
        menuBar.add(scriptMenu);

        invMenu = new Menu("Inventory");
        dropAll = new MenuItem("Drop All");
        dropAllExcept = new MenuItem("Drop All Except");
        dropAllExcept.addActionListener(e -> {
            String s = (String)JOptionPane.showInputDialog(
                    HueFrame.this,
                    "Item Name:",
                    "Drop All Except",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");

            if ((s != null) && (s.length() > 0)) {
                ctx.tasks.add(new DropAllExcept(s.split(","),ctx));
                DefaultListModel model = new DefaultListModel();
                for(int i = 0; i < list.getModel().getSize(); i++)
                    model.addElement(list.getModel().getElementAt(i));
                model.addElement("Drop all except " + s);
                list.setModel(model);
            }
        });
        drop = new MenuItem("Drop Item");

        //Add inventory shit to the menu
        invMenu.add(drop);
        invMenu.add(dropAll);
        invMenu.add(dropAllExcept);
        menuBar.add(invMenu);

        //Conditional Menu
        conditionMenu = new Menu("Conditionals");
        ifC  = new MenuItem("If");
        ifC.addActionListener(e->{
            String[] actions = new String[] {"Inv is Full(Example)"};
            String s = (String)JOptionPane.showInputDialog(
                    HueFrame.this,
                    "Item Name:",
                    "Drop All Except",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    actions,
                    actions[0]);

            if ((s != null) && (s.length() > 0)) {
                ctx.tasks.add(new If(new Condition() {
                    @Override
                    public boolean validate(MethodContext ctx) {
                        return ctx.getInventory().isFull();
                    }
                },ctx));
                DefaultListModel model = new DefaultListModel();
                for(int i = 0; i < list.getModel().getSize(); i++)
                    model.addElement(list.getModel().getElementAt(i));
                model.addElement("If " + s);
                list.setModel(model);
            }
        });
        endIf = new MenuItem("EndIf");
        endIf.addActionListener(e->{
            ctx.tasks.add(new EndIf());
            DefaultListModel model = (DefaultListModel)list.getModel();
            model.addElement("EndIf");
            list.setModel(model);
            list.invalidate();
        });
        conditionMenu.add(ifC);
        conditionMenu.add(endIf);
        menuBar.add(conditionMenu);

        setMenuBar(menuBar);
        scrollPane = new JScrollPane(list);
        add(scrollPane);
        setSize(300,500);
        setPreferredSize(new Dimension(300,500));
        setVisible(true);
    }
    private Menu invMenu,conditionMenu,scriptMenu;
    private MenuBar menuBar;
    private MenuItem dropAll,dropAllExcept,drop,ifC,endIf,start,stop;
    public JList list;
    private JScrollPane scrollPane;
}
