/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import Discripter.TextDiscripter;
import Discripter.TileDiscripter;

/**
 *
 * @author chj87
 */
public class TileEllipse2D extends Ellipse2D implements Drawable, Cloneable, Serializable   {
    private int minX, minY, maxX, maxY;
    private TileDiscripter td;
    private TextDiscripter ttd;
    
    public TileEllipse2D()  {
        setTd(new TileDiscripter());
        setTtd(new TextDiscripter());
    }
    
    public TileEllipse2D(int minX, int minY, int maxX, int maxY)  {
        if(minX < maxX) {
            setMinX(minX);
            setMaxX(maxX);
        }
        else {
            setMinX(maxX);
            setMaxX(minX);
        }
        if(minY < maxY) {
            setMinY(minY);
            setMaxY(maxY);
        }
        else {
            setMinY(maxY);
            setMaxY(minY);
        }
            
        setTd(new TileDiscripter());
        setTtd(new TextDiscripter());
    }
    public TileEllipse2D(int minX, int minY, int maxX, int maxY, int rotateDegree, int depth, String textContext, int x, int y, String fontName, int textStyle, int textSize, Color textColor ,Color borderColor, Color fillingColor)  {
        if(minX < maxX) {
            setMinX(minX);
            setMaxX(maxX);
        }
        else {
            setMinX(maxX);
            setMaxX(minX);
        }
        if(minY < maxY) {
            setMinY(minY);
            setMaxY(maxY);
        }
        else {
            setMinY(maxY);
            setMaxY(minY);
        }
            
        setTd(new TileDiscripter());
        setTtd(new TextDiscripter(textContext, textColor, new Font(fontName,textStyle, textSize),x,y));
        getTd().setDepth(depth);
        getTd().setRotateDegree(rotateDegree);
        getTd().setBorderColor(borderColor);
        getTd().setFillingColor(fillingColor);
    }
    
    @Override
    public double getX() {
        return (double)minX;
    }

    @Override
    public double getY() {
        return (double)minY;
    }
    
    public void setMinX(int minX)  {
        this.minX = minX;
    }
    public void setMinY(int minY)   {
        this.minY = minY;
    }
     public void setMaxX(int maxX)  {
         this.maxX = maxX;
     }
      public void setMaxY(int maxY) {
          this.maxY = maxY;
      }
    
    public TextDiscripter getTtd()  {
        return this.ttd;
    }
    
    public void setTtd(TextDiscripter ttd)  {
        this.ttd = ttd;
    }
    
    @Override
    public double getWidth() {
        return (double)maxX - (double)minX;
    }

    @Override
    public double getHeight() {
        return (double)maxY - (double)minY;
    }

    @Override
    public boolean isEmpty() {
        boolean result = true;
        if(getX() == 0 && getY() == 0 && getWidth() == 0 && getHeight() == 0)    {
            result = true;
        }
        return result;
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {
        setMinX((int)x);
        setMinY((int)y);
        setMaxX((int)x + (int)w);
        setMaxY((int)x + (int)h);
    }

    @Override
    public Rectangle2D getBounds2D() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics g) {

        if(getTd().getRotateDegree() == 0)  {
            
            g.setColor(getTd().getFillingColor());
            g.fillOval((int)getMinX(), (int)getMinY(), (int)getWidth(), (int)getHeight());
            g.setColor(getTtd().getTextColor());
            g.setFont(getTtd().getTextFont());
            String[] temp = getTtd().getTextContext().split("\\n");
            for(int i = 0; i < temp.length; i++)    {
                g.drawString(temp[i], getTtd().getX(), getTtd().getY() + ( i * (getTtd().getTextFont().getSize() + 2)));
            }
            
            g.setColor(getTd().getBorderColor());
            g.drawOval((int)getMinX(), (int)getMinY(), (int)getWidth(), (int)getHeight());
            
        }
        else    {
            int a = (int)((int)getMinX() + (int)getMaxX())/2;
            int b = (int)((int)getMinY() + (int)getMaxY())/2;
            
            double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
            double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));
            
            double xRotateValue = (cosTemp-1) * a + sinTemp * b;
            double yRotateValue = (cosTemp-1) * b - sinTemp * a;

            Graphics2D g2d = (Graphics2D)g;
            AffineTransform old = g2d.getTransform();
            g2d.rotate(Math.toRadians(getTd().getRotateDegree()));
            
            g.setColor(getTd().getFillingColor());
            g.fillOval((int)getMinX() + (int)xRotateValue, (int)getMinY() + (int)yRotateValue, (int)getWidth(), (int)getHeight());
            g.setColor(getTtd().getTextColor());
            g.setFont(getTtd().getTextFont());
            String[] temp = getTtd().getTextContext().split("\\n");
            for(int i = 0; i < temp.length; i++)    {
                g.drawString(temp[i], getTtd().getX() + (int)xRotateValue, getTtd().getY() + (int)yRotateValue + ( i * (getTtd().getTextFont().getSize() + 2)));
            }
            
            g.setColor(getTd().getBorderColor());
            g.drawOval((int)getMinX() + (int)xRotateValue, (int)getMinY() + (int)yRotateValue, (int)getWidth(), (int)getHeight());
            
            g2d.setTransform(old);
        }

    }

    @Override
    public boolean isClicked() {
        return this.getTd().isClicked();
    }
    @Override
    public void setClicked(boolean clicked) {
        this.getTd().setClicked(clicked);
    }

    @Override
    public void drawBorder(Graphics g) {
        if(getTd().getRotateDegree() == 0)  {
            g.setColor(Color.BLACK);
            g.drawRect((int)getMinX(), (int)getMinY(), (int)getWidth(), (int)getHeight());
        }
        else    {
            int a = (int)(getMinX() + getMaxX())/2;
            int b = (int)(getMinY() + getMaxY())/2;
            
            double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
            double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));
            
            double xRotateValue = (cosTemp-1) * a + sinTemp * b;
            double yRotateValue = (cosTemp-1) * b - sinTemp * a;

            Graphics2D g2d = (Graphics2D)g;
            AffineTransform old = g2d.getTransform();
            g2d.rotate(Math.toRadians(getTd().getRotateDegree()));
            
            g.setColor(Color.BLACK);
            g.drawRect((int)getMinX() + (int)xRotateValue, (int)getMinY() + (int)yRotateValue, (int)getWidth(), (int)getHeight());
            
            g2d.setTransform(old);
        }
    }
    
    
    public void setTd(TileDiscripter td) {
        this.td = td;
    }
    
    public TileDiscripter getTd()   {
        return this.td;
    }

    @Override
    public boolean contain(int x, int y) {
        boolean result = false;
        
        if(getTd().getRotateDegree() == 0)  {
            double a = getWidth()/2;
            double b = getHeight()/2;
            
            double middleX = getMaxX() + getMinX();
            double middleY = getMaxY() + getMinY();
            
            middleX = middleX/2;
            middleY = middleY/2;

            if((b*b)*(x-middleX)*(x-middleX) + (a*a)*(y-middleY)*(y-middleY) <= a*a*b*b)    {
                result = true;
            }
        }
        else    {
            double a = getWidth()/2;
            double b = getHeight()/2;
            
            double middleX = getMaxX() + getMinX();
            double middleY = getMaxY() + getMinY();
            
            middleX = middleX/2;
            middleY = middleY/2;
            
            int m = (int)(getMinX() + getMaxX())/2;
            int n = (int)(getMinY() + getMaxY())/2;
            
            double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
            double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));
            
            if((b*b)*((x - middleX ) * cosTemp + ( y - middleY ) * sinTemp )*((x - middleX ) * cosTemp + ( y - middleY ) * sinTemp ) + (a*a)*((y - middleY ) * sinTemp - (y - middleY ) * cosTemp)*((y - middleY) * sinTemp - (y - middleY ) * cosTemp ) <= a*a*b*b )    {
                result = true;
            }
        }

        
        return result;
    }

    @Override
    public int getShapeType() {
        return 2;
    }

    @Override
    public int getDepth() {
        return this.getTd().getDepth();
    }
    @Override
    public void setDepth(int depth) {
        this.getTd().setDepth(depth);
    }
    
    @Override
    public TileEllipse2D clone()    {
        TileEllipse2D result = new TileEllipse2D();
        
        result.setTtd(this.getTtd().clone());
        result.setTd(this.getTd().clone());
        result.setMaxX((int)this.getMaxX());
        result.setMaxY((int)this.getMaxY());
        result.setMinX((int)this.getMinX());
        result.setMinY((int)this.getMinY());
        
        return result;
    }
}

