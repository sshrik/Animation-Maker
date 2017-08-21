/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movingdiary;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author chj87
 */
public class LoadingPanel extends JPanel {
    private int max;
    private int width;
    private int now;
    
    public LoadingPanel()   {
        
    }
    public LoadingPanel(int max, int now, int width)   {
        this.width = width;
        this.max = max;
        this.now = now;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public int getNow() {
        return this.now;
    }
    
    public void setMax(int max) {
        this.max = max;
    }
    
    public void setNow(int now) {
        this.now = now;
    }
    
    @Override   
    public void paint(Graphics g)   {
        super.paint(g);
        double result = ((double)getNow() / (double)getMax()) * (double)this.width;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, 30);
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, (int)result, 30);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, 30);
    }
    
}
