/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Discripter;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

/**
 *
 * @author chj87
 */
public class TextDiscripter implements Cloneable, Serializable {
    private String textContext;
    private Color textColor;
    private Font textFont;
    private int x, y;
    
    public TextDiscripter() {
        setTextContext("");
        setTextColor(Color.BLACK);
        setTextFont(new Font("Serif",0,15));
        setX(0);
        setY(0);
    }
    
    public TextDiscripter(String textContext, Color textColor, Font textFont, int x, int y) {
        setTextContext(textContext);
        setTextColor(textColor);
        setTextFont(textFont);
        setX(x);
        setY(y);
    }
    
    public String getTextContext()  {
        return this.textContext;
    }
    
    public int getX()   {
        return this.x;
    }
    
    public int getY()   {
        return this.y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setTextContext(String textContext)  {
        this.textContext = textContext;
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public void setTextColor(Color textColor)   {
        this.textColor = textColor;
    }
    
    public Font getTextFont()   {
        return this.textFont;
    }
    
    public void setTextFont(Font textFont)  {
        this.textFont = textFont;
    }
    
    @Override
    public TextDiscripter clone()   {
        TextDiscripter result = new TextDiscripter();
        result.setTextContext(new String(this.getTextContext()));
        result.setTextColor(new Color(this.getTextColor().getRed(),this.getTextColor().getGreen(),this.getTextColor().getBlue()));
        result.setTextFont(new Font(this.getTextFont().getFontName(),this.getTextFont().getStyle(),this.getTextFont().getSize()));
        result.setX(this.getX());
        result.setY(this.getY());
        return result;
    }
}
