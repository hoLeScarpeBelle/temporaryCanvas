/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporarycanvas;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author user
 */
public class Window extends JFrame
{
    public DrawArea canvas;
            
    public Window()
    {
        canvas = new DrawArea();
        this.setTitle("temporaneusCanvas");
        Window win = this;
        setLayout(new BorderLayout());
        setVisible(true);
        setSize(new Dimension(500,500));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel customizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        customizePanel.setPreferredSize(new Dimension(300,30));
        add(customizePanel,BorderLayout.NORTH);
        
        JMenuBar bar = new JMenuBar();
        bar.setPreferredSize(new Dimension(120,30));
        
        JMenu tools = new JMenu("tools");
        bar.add(tools);
        JMenuItem brush = new JMenuItem("brush");
        brush.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                canvas.setTool("brush");
            }
        });
        JMenuItem clear = new JMenuItem("clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                canvas.setTool("clear");
            }
        });
        JMenuItem line = new JMenuItem("line");
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                canvas.setTool("line");
            }
        });
        tools.add(brush);
        tools.add(clear);
        tools.add(line);
        
        JMenu windowSet = new JMenu("windowSet");
        bar.add(windowSet);
        JMenuItem resize = new JMenuItem("resize");
        resize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                new ResizeWindow(win);
            }
        });
        JMenuItem clearAll = new JMenuItem("clearAll");
        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                canvas.clear();
            }
        });
        windowSet.add(resize);
        windowSet.add(clearAll);
        
        /*windowSet.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) 
            {
            }
        });
        /*
        windowSet.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) 
            {
            }

            @Override
            public void menuDeselected(MenuEvent e) 
            {
            }

            @Override
            public void menuCanceled(MenuEvent e) 
            {
            }
        });
        */
        customizePanel.add(bar);
        
        JComboBox<Integer> sizeSelection = new JComboBox<>();
        sizeSelection.setEditable(true);
        sizeSelection.setPreferredSize(new Dimension(50,30));
        for(int i=0;i < getWidth();i++)
            sizeSelection.addItem(i);
        sizeSelection.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) 
            {
                if(e.getStateChange() == ItemEvent.SELECTED) 
                {
                    Object item = e.getItem();
                    try
                    {
                        int x = (int) item;
                        canvas.setStrokeSize(x);
                        //System.out.print("" + x);
                    }
                    catch(Exception Ex)
                    {
                        System.out.print("no va");
                    }
                    // do something with object
                }
            }
        });
        customizePanel.add(sizeSelection);
        
        testCustomizePanel.ColorChooserButton colorChooser = new testCustomizePanel.ColorChooserButton(Color.BLACK);
        colorChooser.setPreferredSize(new Dimension(50,30));
        colorChooser.addColorChangedListener(new testCustomizePanel.ColorChooserButton.ColorChangedListener() 
        {
            @Override
            public void colorChanged(Color newColor) {
               canvas.setColor(newColor);
            }
        });
        customizePanel.add(colorChooser);
        
        JButton save = new JButton("save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                canvas.saveImage();
            }
        });
        save.setPreferredSize(new Dimension(70,30));
        customizePanel.add(save);
        
        add(canvas,BorderLayout.CENTER);

    }
    
    public void resizeWin(int x, int y)
    {
        setSize(new Dimension(x,y + 30));
        canvas.resize(x,y);
    }

    
}
