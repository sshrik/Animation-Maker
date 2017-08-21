/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Discripter;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author chj87
 */
public class TileDiscripter implements Cloneable, Serializable {
    private int depth, fillingMode;
    private int rotateDegree;
    private int lineDiameter;
    private Color borderColor, fillingColor;
    private boolean clicked;
    
    public TileDiscripter() {
        setDepth(0);
        setFillingMode(0);
        setRotateDegree(0);
        setLineDiameter(1);
        setBorderColor(Color.BLACK);
        setFillingColor(Color.WHITE);
        setClicked(false);
    }
    public TileDiscripter(Color borderColor, Color fillingColor) {
        setDepth(0);
        setFillingMode(0);
        setRotateDegree(0);
        setLineDiameter(1);
        setBorderColor(borderColor);
        setFillingColor(fillingColor);
        setClicked(false);
    }
    
    public Color getBorderColor() {
        return this.borderColor;
    }
    
    public void setBorderColor(Color borderColor)  {
        this.borderColor = borderColor;
    }
    
    public Color getFillingColor() {
        return this.fillingColor;
    }
    
    public void setFillingColor(Color fillingColor)  {
        this.fillingColor = fillingColor;
    }
    
    public int getDepth()   {
        return this.depth;
    }
    public int getFillingMode() {
        return this.fillingMode;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public void setFillingMode(int fillingMode)    {
        this.fillingMode = fillingMode;
    }
    
    public boolean isClicked()  {
        return this.clicked;
    }
    
    public void setClicked(boolean clicked)    {
        this.clicked = clicked;
    }
    
    public int getRotateDegree()    {
        return this.rotateDegree;
    }
    
    public void setRotateDegree(int rotateDegree)   {
        this.rotateDegree = rotateDegree;
    }
    
    public int getLineDiameter()    {
        return this.lineDiameter;
    }
    
    public void setLineDiameter(int lineDiameter)    {
        this.lineDiameter = lineDiameter;
    }
    @Override
    public TileDiscripter clone()   {
        TileDiscripter td = new TileDiscripter();
        
        td.setDepth(this.getDepth());
        td.setFillingMode(this.getFillingMode());
        td.setRotateDegree(this.getRotateDegree());
        td.setLineDiameter(this.getLineDiameter());
        td.setClicked(this.isClicked());
        td.setBorderColor(new Color(this.getBorderColor().getRed(),this.getBorderColor().getGreen(),this.getBorderColor().getBlue()));
        td.setFillingColor(new Color(this.getFillingColor().getRed(),this.getFillingColor().getGreen(),this.getFillingColor().getBlue()));
        
        return td;
    }
}
