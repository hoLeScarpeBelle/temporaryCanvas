package testCustomizePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import testCustomizePanel.ColorChooserButton.ColorChangedListener;

public class customizePanel extends JFrame
{

    public customizePanel() 
    {
        //JFrame test = new JFrame("paolo2");
        setVisible(true);  
        setSize(new Dimension(500,300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        
        
        
        FlowLayout fl = new FlowLayout();
        fl.setHgap(0);
        JPanel custom = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));//fl questo fa lo stesso lavoro di fl);
        custom.setBackground(Color.GRAY);
        add(custom,BorderLayout.NORTH);
        
        
        
        JPanel sd = new JPanel(new FlowLayout());
        sd.setPreferredSize(new Dimension(300,270));
        add(sd,BorderLayout.CENTER);
        
        JPanel toolPanel = new JPanel(new FlowLayout());
        custom.add(toolPanel);
        toolPanel.add(new JLabel("tools"));
        String[] toolsList = new String[]{"brush","clear","ads","xcasda"};
        JComboBox<String> toolSelection = new JComboBox<>(toolsList);
        toolPanel.add(toolSelection);
        toolSelection.setVisible(true);
        toolSelection.setPreferredSize(new Dimension(80,20));
        
        JPanel StrokePanel = new JPanel(new FlowLayout());
        custom.add(StrokePanel);
        StrokePanel.add(new JLabel("size"));
        JComboBox<Integer> sizeSelection = new JComboBox<>();
        sizeSelection.setEditable(true);
        for(int i = 0 ; i <= 50 ; i++)
            sizeSelection.addItem(i);
        
        StrokePanel.add(sizeSelection);
        sizeSelection.setPreferredSize(new Dimension(50,20));
        
        JButton resize = new JButton("resize");
        resize.setPreferredSize(new Dimension(80,27));
        custom.add(resize);
        custom.add(new JButton("clearAll"));
        ColorChooserButton colorChooser = new ColorChooserButton(Color.WHITE);
        colorChooser.addColorChangedListener(new ColorChangedListener() 
        {
            @Override
            public void colorChanged(Color newColor) {
            // do something with newColor ...
            }
        });
        custom.add(colorChooser);
        
        JMenuBar menu = new JMenuBar();
        menu.setPreferredSize(new Dimension(50,30));
        JMenu primo =  new JMenu("primo");
        menu.add(primo);
        custom.add(menu);
        
        //Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.BLACK); //questo crea la finestra che permette la selezione del colore();
    }
    
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    customizePanel frame = new customizePanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );
        
    }
}
