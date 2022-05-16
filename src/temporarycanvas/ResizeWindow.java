/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporarycanvas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class ResizeWindow extends JFrame
{
    
    public ResizeWindow(Window win)
    {
        setSize(new Dimension(300,110));
        setVisible(true);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(win);
        
        JPanel selection = new JPanel(new FlowLayout());
        add(selection,BorderLayout.CENTER);
        
        JPanel width = new JPanel(new FlowLayout());
        width.setPreferredSize(new Dimension(120,50));
        width.add(new JLabel("width"));
        TextField widthValue = new TextField(5);
        width.add(widthValue);
        selection.add(width);
        
        JPanel height = new JPanel(new FlowLayout());
        height.setPreferredSize(new Dimension(120,50));
        height.add(new JLabel("height"));
        TextField heightValue = new TextField(5);
        height.add(heightValue);
        selection.add(height);
        
        JPanel south = new JPanel(new BorderLayout());
        add(south,BorderLayout.SOUTH);
        
        JPanel southEst = new JPanel(new FlowLayout());
        south.add(southEst,BorderLayout.EAST);
        
        JButton cancel = new JButton("cancel");
        JButton ok = new JButton("ok");
        
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(e.getSource() == ok)
                {
                    boolean flag = true;
                    int width = 0;
                    int height = 0;
                    try
                    {
                        width = Integer.parseInt(widthValue.getText());
                        height = Integer.parseInt(heightValue.getText());
                    }
                    catch(Exception ex)
                    {
                        flag = false;
                    }
                    if(flag == true)
                    {
                        win.resizeWin(width, height);
                        dispose();
                    }
                }
                else if(e.getSource() == cancel)
                {
                    dispose();  
                }
            }
        };
        
        ok.addActionListener(buttonListener);
        cancel.addActionListener(buttonListener);
        
        southEst.add(ok);
        southEst.add(cancel);
        
        
    }
    
}
