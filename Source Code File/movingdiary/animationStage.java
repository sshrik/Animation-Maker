/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movingdiary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author chj87
 */
public class animationStage extends JPanel {
    private Stage thisStage;
    
    
    public animationStage() {
        setThisStage(new Stage());
    }
    public animationStage(Stage thisStage)  {
        setThisStage(thisStage.clone());
    }
    
    public void setThisStage(Stage stage)   {
        this.thisStage = stage;
    }
    
    public Stage getThisStage() {
        return this.thisStage;
    }
    
    public void addingStage()   {
        super.add(getThisStage(), BorderLayout.CENTER);
    }
    
    @Override
    public void paint(Graphics g) {
        // 1072 , 766 Animaition width height
        // 1072 , 127 -> /5 /1 : 214.4 , 127
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 214, 127);
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.scale(0.195, 0.165);
        
        for(int i = 0 ; i < getThisStage().getTileList().size(); i++)  {
            getThisStage().getTileList().get(i).draw(g2d);
        }
        
        g2d.scale(5.10476, 6.03149);
    }
    
}
