/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporarycanvas;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author user
 */
public class DrawArea extends JPanel implements MouseListener //con il canvas flickera
{

    public Graphics2D g2d,oldG2d;
    public Image directCanvas,oldCanvas;
    private int oldX,oldY,currentX,currentY;
    private DrawArea dr;
    private Color currentColor = Color.BLACK;
    public int strokeSize;
    private boolean ImageRisize  = false , mouseDrag = false;
    private String toolSelected = "brush";
    
    public DrawArea()
    {
        strokeSize = 6;
        dr = this;
       //g = (Graphics2D)getGraphics();
        this.addMouseListener(this);
        setPreferredSize(new Dimension(300,200));
        addMouseMotionListener(new MouseMotionListener()
        {
            @Override
            public void mouseDragged(MouseEvent e) 
            {
                if(toolSelected == "brush")
                {
                    currentX = e.getX();
                    currentY = e.getY();
                
                    g2d.drawLine(oldX,oldY,currentX,currentY);

                    //g2d.fillOval((int)(e.getX()) ,(int)(e.getY()) , 12, 12);
                    //System.out.print("yes"+ "\n");
                    repaint();
                    oldX = currentX;
                    oldY = currentY;
                }
                else if(toolSelected == "line")
                {
                    currentX = e.getX();
                    currentY = e.getY();
                    //directCanvas = oldCanvas;
                    //g2d = oldG2d;
                    //g2d.drawImage(oldCanvas, 0, 0 , null);
                    //repaint();
                    //g2d = (Graphics2D) directCanvas.getGraphics();
                    //System.out.print("yes in mouse drag"+ "\n");
                    //g2d.drawLine(oldX,oldY,currentX,currentY);
                    repaint();
                }
                else if(toolSelected == "clear")
                {
                    g2d.clearRect(e.getX(), e.getY(), strokeSize, strokeSize);
                    repaint();
                }

            }

            @Override
            public void mouseMoved(MouseEvent arg0) 
            {
                
            }
        });
        
        repaint();
    }
    
    
    
    @Override
    public void paintComponent(Graphics g) {
        //super.paint(g); //To change body of generated methods, choose Tools | Templates.
        super.paintComponent(g); // cancella tutto e ridisegna gli oggetti detti
        if(directCanvas == null)
        {
            directCanvas = createImage(getWidth(),getHeight());
            g2d = (Graphics2D)directCanvas.getGraphics();
            g2d.setStroke(new BasicStroke(strokeSize));
            clear();
        }
        else if(ImageRisize == true)
        {
            ImageRisize = false;
        }
        g.drawImage(directCanvas, 0, 0, null);
        if(toolSelected.equals("line") && this.mouseDrag == true)
            g.drawLine(this.oldX , this.oldY , this.currentX , this.currentY);
    }
    //da sistemare
    public void saveImage()
    {
        try{
            //risolvere il getWidth e getHeight 
            BufferedImage buffImg = new BufferedImage(directCanvas.getWidth(dr) , directCanvas.getHeight(dr), BufferedImage.TYPE_INT_RGB);
            System.out.println("widht" + directCanvas.getWidth(dr) + "height" + directCanvas.getHeight(dr));
            File image = new File("image.png");
            ImageIO.write(buffImg, "png", image);
        }catch(Exception e)
        {
            System.out.println("errore = "+ e.toString());
        }
    }
    
    public void resize(int x ,int y)
    {
        setPreferredSize(new Dimension(x,y));
        this.ImageRisize = true;
        repaint();
    }
    
    public void setStrokeSize(int x)
    {
        strokeSize = x;
        g2d.setStroke(new BasicStroke(strokeSize));
    }
    
    public void setColor(Color c)
    {
        this.currentColor = c;
        g2d.setPaint(c);
    }
    
    public void setTool(String s)
    {
        toolSelected = s;
    }
    
    public void clear()
    {
        g2d.setPaint(Color.WHITE);
        g2d.fillRect( 0, 0 ,getWidth() ,getHeight());
        g2d.setColor(currentColor);
        repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //g2d.fillOval((int)(e.getX()) ,(int)(e.getY()) , 25, 25);
        if(toolSelected == "brush")
        {
            //System.out.print("yes"+ "\n");
            oldX = e.getX();
            oldY = e.getY();
            g2d.drawRect(oldX, oldY, strokeSize/4, strokeSize/4);
            repaint();
        }
        else if(toolSelected == "line")
        {
            //this.oldCanvas = this.directCanvas;
            //this.oldG2d = this.g2d;
            this.mouseDrag = true;
            oldX = e.getX();
            oldY = e.getY();
            currentX = e.getX();
            currentY = e.getY();
            
        }
        else if(toolSelected == "clear")
        {
            g2d.clearRect(e.getX(), e.getY(), strokeSize, strokeSize);
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(this.toolSelected == "line")
        {
            this.g2d.drawLine(this.oldX, this.oldY, this.currentX, this.currentY);
            mouseDrag = false;
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
    }
    
}
