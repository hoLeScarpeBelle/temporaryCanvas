/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporarycanvas;

import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;
import testCustomizePanel.customizePanel;

/**
 *
 * @author user
 */
public class main extends Thread
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException
    {
        
        EventQueue.invokeLater(new Runnable() {//questo esegue tutti la lista di eventi poi comincia a calcolare
        public void run() 
            {
                try 
                {
                    Window win = new Window();
                    win.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("paolo");
        /*while(true)
        {
            //Timer ds = new Timer;
            //TimeUnit.MILLISECONDS.sleep(13);
            win.canvas.repaint();
        }*/
    }
    
}
