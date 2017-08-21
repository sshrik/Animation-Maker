/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import java.awt.Graphics;

/**
 *
 * @author chj87
 */
public interface Drawable{
    public void draw(Graphics g);
    public boolean contain(int x, int y);
    public boolean isClicked();
    public void setClicked(boolean clicked);
    public void drawBorder(Graphics g);
    public int getShapeType();
    public int getDepth();
    public void setDepth(int depth);
    public Drawable clone();
}