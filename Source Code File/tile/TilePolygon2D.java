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
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import Discripter.TextDiscripter;
import Discripter.TileDiscripter;

/**
 *
 * @author chj87
 */
public class TilePolygon2D extends Polygon implements Drawable,Cloneable, Serializable  {
    private int largeX, largeY, smallX, smallY;
    private final static int ERROR_DISTANCE = 10;
    private boolean complete;
    private TileDiscripter td;
    private TextDiscripter ttd;
    
    
    
    public TilePolygon2D () {
        setTd(new TileDiscripter());
    }
    
    public TilePolygon2D(int[] Xpoint, int[] Ypoint, int pointSize)  {
        setTd(new TileDiscripter());
        setXpoint(Xpoint);
        setYpoint(Ypoint);
        setPointSize(pointSize);
        setComplete(false);
        getTd().setBorderColor(Color.BLACK);
        getTd().setFillingColor(Color.WHITE);
    }
    
    public TilePolygon2D(int[] Xpoint, int[] Ypoint, int pointSize, boolean complete)  {
        setTd(new TileDiscripter());
        setXpoint(Xpoint);
        setYpoint(Ypoint);
        setPointSize(pointSize);
        setComplete(complete);
        getTd().setBorderColor(Color.BLACK);
        getTd().setFillingColor(Color.WHITE);
    }
    
    public TilePolygon2D(int[] Xpoint, int[] Ypoint, int pointSize, Color borderColor, Color fillingColor)  {
        largeX = 0;
        largeY = 0;
        smallX = 10000000;
        smallY = 10000000;
        
        for(int i = 0 ; i < pointSize; i++) {
            if(Xpoint[i] > largeX) largeX = Xpoint[i];
            if(Ypoint[i] > largeY) largeY = Ypoint[i];
            if(Xpoint[i] < smallX) smallX = Xpoint[i];
            if(Ypoint[i] < smallY) smallY = Ypoint[i];
        }
        
        setTd(new TileDiscripter());
        setComplete(false);
        setXpoint(Xpoint);
        setYpoint(Ypoint);
        setPointSize(pointSize);
        getTd().setBorderColor(borderColor);
        getTd().setFillingColor(fillingColor);
    }
    public TilePolygon2D(int[] Xpoint, int[] Ypoint, int pointSize, boolean complete, int rotateDegree  ,Color borderColor, Color fillingColor)  {
        largeX = 0;
        largeY = 0;
        smallX = 10000000;
        smallY = 10000000;
        
        for(int i = 0 ; i < pointSize; i++) {
            if(Xpoint[i] > largeX) largeX = Xpoint[i];
            if(Ypoint[i] > largeY) largeY = Ypoint[i];
            if(Xpoint[i] < smallX) smallX = Xpoint[i];
            if(Ypoint[i] < smallY) smallY = Ypoint[i];
        }
        
        setTd(new TileDiscripter());
        setComplete(complete);
        setXpoint(Xpoint);
        setYpoint(Ypoint);
        setPointSize(pointSize);
        getTd().setRotateDegree(rotateDegree);
        getTd().setBorderColor(borderColor);
        getTd().setFillingColor(fillingColor);
    }
    
    public TilePolygon2D(int[] Xpoint, int[] Ypoint, int pointSize, boolean complete, int rotateDegree ,int depth, String textContext, int x, int y, String fontName, int textStyle, int textSize, Color textColor ,Color borderColor, Color fillingColor)  {
        largeX = 0;
        largeY = 0;
        smallX = 10000000;
        smallY = 10000000;
        
        for(int i = 0 ; i < pointSize; i++) {
            if(Xpoint[i] > largeX) largeX = Xpoint[i];
            if(Ypoint[i] > largeY) largeY = Ypoint[i];
            if(Xpoint[i] < smallX) smallX = Xpoint[i];
            if(Ypoint[i] < smallY) smallY = Ypoint[i];
        }
        
        setTd(new TileDiscripter());
        setTtd(new TextDiscripter(textContext, textColor, new Font(fontName,textStyle, textSize),x,y));
        setComplete(complete);
        setXpoint(Xpoint);
        setYpoint(Ypoint);
        setPointSize(pointSize);
        getTd().setDepth(depth);
        getTd().setRotateDegree(rotateDegree);
        getTd().setBorderColor(borderColor);
        getTd().setFillingColor(fillingColor);
    }
    
    public int getPointSize()   {
        return super.npoints;
    }
    
    public void setPointSize(int pointSize) {
        super.npoints = pointSize;
    }
    
    public TileDiscripter getTd()   {
        return this.td;
    }
    
    public void setTd(TileDiscripter td)    {
        this.td = td;
    }
    
    public int[] getXpoint()    {
        return super.xpoints;
    }
    
    public int[] getYpoint()    {
        return super.ypoints;
    }
    
    public void setXpoint(int[] Xpoint) {
        super.xpoints = Xpoint;
    }
    
    public void setYpoint(int[] Ypoint) {
        super.ypoints = Ypoint;
    }
    
    public TextDiscripter getTtd()  {
        return this.ttd;
    }
    
    public void setTtd(TextDiscripter ttd)  {
        this.ttd = ttd;
    }
    
    public boolean isComplete() {
        return this.complete;
    }
    
    public void setComplete(boolean complete)   {
        this.complete = complete;
    }
    @Override
    public void setDepth(int depth) {
        this.getTd().setDepth(depth);
    }

    public int getWidth()   {
        return (int)(largeX - smallX);
    }
    
    public int getHeight()  {
        return (int)(largeY - smallY);
    }
    
    public void setBorderPoint()    {
        for(int i = 0 ; i < getPointSize(); i++) {
            if(getXpoint()[i] > largeX) largeX = getXpoint()[i];
            if(getYpoint()[i] > largeY) largeY = getYpoint()[i];
            if(getXpoint()[i] < smallX) smallX = getXpoint()[i];
            if(getYpoint()[i] < smallY) smallY = getYpoint()[i];
        }
    }
    
    @Override
    public void draw(Graphics g) {
        if(isComplete())    {
            if(getTd().getRotateDegree() == 0)  {
                g.setColor(getTd().getFillingColor());
                g.fillPolygon(getXpoint(), getYpoint(), getPointSize());
                g.setColor(getTtd().getTextColor());
                g.setFont(getTtd().getTextFont());
                String[] temp = getTtd().getTextContext().split("\\n");
                for(int i = 0; i < temp.length; i++)    {
                    g.drawString(temp[i], getTtd().getX(), getTtd().getY() + ( i * (getTtd().getTextFont().getSize() + 2)));
                }
                g.setColor(getTd().getBorderColor());
                g.drawPolygon(getXpoint(), getYpoint(), getPointSize());
            }
            else { 
                int a = (int)( ( largeX + smallX )/ 2 );
                int b = (int)( ( largeY + smallY )/ 2 );

                double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
                double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));

                double xRotateValue = (cosTemp-1) * a + sinTemp * b;
                double yRotateValue = (cosTemp-1) * b - sinTemp * a;
                
                for(int i = 0; i < getPointSize(); i++) {
                    getXpoint()[i] = getXpoint()[i] + (int)xRotateValue;
                    getYpoint()[i] = getYpoint()[i] + (int)yRotateValue;
                }
                
                Graphics2D g2d = (Graphics2D)g;
                AffineTransform old = g2d.getTransform();
                g2d.rotate(Math.toRadians(getTd().getRotateDegree()));

                g.setColor(getTd().getFillingColor());
                g.fillPolygon(getXpoint(), getYpoint(), getPointSize());
                g.setColor(getTtd().getTextColor());
                g.setFont(getTtd().getTextFont());
                String[] temp = getTtd().getTextContext().split("\\n");
                for(int i = 0; i < temp.length; i++)    {
                    g.drawString(temp[i], getTtd().getX() + (int)xRotateValue, getTtd().getY() + (int)yRotateValue + ( i * (getTtd().getTextFont().getSize() + 2)));
                }
                g.setColor(getTd().getBorderColor());
                g.drawPolygon(getXpoint(), getYpoint(), getPointSize());

                g2d.setTransform(old);
                
                for(int i = 0; i < getPointSize(); i++) {
                    getXpoint()[i] = getXpoint()[i] - (int)xRotateValue;
                    getYpoint()[i] = getYpoint()[i] - (int)yRotateValue;
                }
            }
        }
        else {
            if(getTd().getRotateDegree() == 0)  {
                g.setColor(getTd().getBorderColor());
                g.drawPolyline(getXpoint(), getYpoint(), getPointSize());
            }
            else {
                int a = (int)( largeX + smallX )/ 2;
                int b = (int)( largeY + smallY )/ 2;

                double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
                double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));

                double xRotateValue = (cosTemp-1) * a + sinTemp * b;
                double yRotateValue = (cosTemp-1) * b - sinTemp * a;

                for(int i = 0; i < getPointSize(); i++) {
                    getXpoint()[i] = getXpoint()[i] + (int)xRotateValue;
                    getYpoint()[i] = getYpoint()[i] + (int)yRotateValue;
                }

                Graphics2D g2d = (Graphics2D)g;
                AffineTransform old = g2d.getTransform();
                g2d.rotate(Math.toRadians(getTd().getRotateDegree()));

                g.setColor(getTd().getBorderColor());
                g.drawPolyline(getXpoint(), getYpoint(), getPointSize());

                g2d.setTransform(old);

                for(int i = 0; i < getPointSize(); i++) {
                    getXpoint()[i] = getXpoint()[i] - (int)xRotateValue;
                    getYpoint()[i] = getYpoint()[i] - (int)yRotateValue;
                }
            }
        }
    }

    @Override
    public boolean contain(int x, int y) {
        boolean result = false;
        
        if(isComplete())    {
            if(getTd().getRotateDegree() == 0)  {
                result = super.contains(x, y);
            }
            else    {
                int a = (int)( ( largeX + smallX ) / 2 );
                int b = (int)( ( largeY + smallY ) / 2 );
                
                double cosTemp = Math.cos(Math.toRadians(getTd().getRotateDegree()));
                double sinTemp = Math.sin(Math.toRadians(getTd().getRotateDegree()));
                
                int[] tempX = new int[getPointSize()];
                int[] tempY = new int[getPointSize()];
                
                for(int i = 0 ; i < getPointSize(); i++)    {
                    tempX[i] = (int)(((getXpoint()[i] - a) * cosTemp) - ((getYpoint()[i] - b) * sinTemp)) + a;
                    tempY[i] = (int)(((getXpoint()[i] - a) * sinTemp) + ((getYpoint()[i] - b) * cosTemp)) + b;
                }
                
                int[] oldX = new int[getPointSize()];
                int[] oldY = new int[getPointSize()];
                
                for(int i = 0 ; i < getPointSize(); i++)    {
                    oldX[i] = getXpoint()[i];
                    oldY[i] = getYpoint()[i];
                    getXpoint()[i] = tempX[i];
                    getYpoint()[i] = tempY[i];
                }
                
                result = super.contains(x, y);
                
                for(int i = 0 ; i < getPointSize(); i++)    {
                    getXpoint()[i] = oldX[i];
                    getYpoint()[i] = oldY[i];
                }
            }
        }
        else {
            if(getTd().getRotateDegree() == 0)  {
                for(int i = 0; i < getPointSize(); i++) {
                    if(x <= getXpoint()[i] + ERROR_DISTANCE && x >= getXpoint()[i] - ERROR_DISTANCE) {
                        if(y <= getYpoint()[i] + ERROR_DISTANCE && y >= getYpoint()[i] - ERROR_DISTANCE) {
                            result = true;
                        }
                    }
                }
            }
            else    {
                int a = (int)( ( largeX + smallX ) / 2 );
                int b = (int)( ( largeY + smallY ) / 2 );
                
                double cosTemp = Math.cos(Math.toRadians(getTd().getRotateDegree()));
                double sinTemp = Math.sin(Math.toRadians(getTd().getRotateDegree()));
                
                int[] tempX = new int[getPointSize()];
                int[] tempY = new int[getPointSize()];
                
                for(int i = 0 ; i < getPointSize(); i++)    {
                    tempX[i] = (int)(((getXpoint()[i] - a) * cosTemp) - ((getYpoint()[i] - b) * sinTemp)) + a;
                    tempY[i] = (int)(((getXpoint()[i] - a) * sinTemp) + ((getYpoint()[i] - b) * cosTemp)) + b;
                }
                
                int[] oldX = new int[getPointSize()];
                int[] oldY = new int[getPointSize()];
                
                for(int i = 0 ; i < getPointSize(); i++)    {
                    oldX[i] = getXpoint()[i];
                    oldY[i] = getYpoint()[i];
                    getXpoint()[i] = tempX[i];
                    getYpoint()[i] = tempY[i];
                }
                
                for(int i = 0; i < getPointSize(); i++) {
                    if(x <= getXpoint()[i] + ERROR_DISTANCE && x >= getXpoint()[i] - ERROR_DISTANCE) {
                        if(y <= getYpoint()[i] + ERROR_DISTANCE && y >= getYpoint()[i] - ERROR_DISTANCE) {
                            result = true;
                        }
                    }
                }
                for(int i = 0 ; i < getPointSize(); i++)    {
                    getXpoint()[i] = oldX[i];
                    getYpoint()[i] = oldY[i];
                }
                
            }
        }
        return result;
    }

    @Override
    public boolean isClicked() {
        return getTd().isClicked();
    }

    @Override
    public void setClicked(boolean clicked) {
        getTd().setClicked(clicked);
    }

    @Override
    public void drawBorder(Graphics g) {
        if(getTd().getRotateDegree() == 0)  {
            g.setColor(Color.BLACK);
            g.drawRect(smallX - 5, smallY - 5, largeX - smallX + 10, largeY - smallY + 10);
        }
        else {
            int a = (int)( largeX + smallX )/ 2;
            int b = (int)( largeY + smallY )/ 2;

            double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
            double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));

            double xRotateValue = (cosTemp-1) * a + sinTemp * b;
            double yRotateValue = (cosTemp-1) * b - sinTemp * a;

            Graphics2D g2d = (Graphics2D)g;
            AffineTransform old = g2d.getTransform();
            g2d.rotate(Math.toRadians(getTd().getRotateDegree()));

            g.setColor(Color.BLACK);
            g.drawRect(smallX + (int)xRotateValue - 5, smallY + (int)yRotateValue - 5, largeX - smallX + 10, largeY - smallY + 10);

            g2d.setTransform(old);
        }
    }
    public void drawBorderLine(Graphics g)  {
        if(isComplete())    {
            if(getTd().getRotateDegree() == 0)  {
                g.setColor(getTd().getBorderColor());
                g.drawPolygon(getXpoint(), getYpoint(), getPointSize());
            }
            else { 
                int a = (int)( ( largeX + smallX )/ 2 );
                int b = (int)( ( largeY + smallY )/ 2 );

                double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
                double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));

                double xRotateValue = (cosTemp-1) * a + sinTemp * b;
                double yRotateValue = (cosTemp-1) * b - sinTemp * a;
                
                for(int i = 0; i < getPointSize(); i++) {
                    getXpoint()[i] = getXpoint()[i] + (int)xRotateValue;
                    getYpoint()[i] = getYpoint()[i] + (int)yRotateValue;
                }
                
                Graphics2D g2d = (Graphics2D)g;
                AffineTransform old = g2d.getTransform();
                g2d.rotate(Math.toRadians(getTd().getRotateDegree()));

                g.setColor(getTd().getBorderColor());
                g.drawPolygon(getXpoint(), getYpoint(), getPointSize());

                g2d.setTransform(old);
                
                for(int i = 0; i < getPointSize(); i++) {
                    getXpoint()[i] = getXpoint()[i] - (int)xRotateValue;
                    getYpoint()[i] = getYpoint()[i] - (int)yRotateValue;
                }
            }
        }
        else {
            if(getTd().getRotateDegree() == 0)  {
                g.setColor(getTd().getBorderColor());
                g.drawPolyline(getXpoint(), getYpoint(), getPointSize());
            }
            else {
                int a = (int)( largeX + smallX )/ 2;
                int b = (int)( largeY + smallY )/ 2;

                double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
                double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));

                double xRotateValue = (cosTemp-1) * a + sinTemp * b;
                double yRotateValue = (cosTemp-1) * b - sinTemp * a;

                for(int i = 0; i < getPointSize(); i++) {
                    getXpoint()[i] = getXpoint()[i] + (int)xRotateValue;
                    getYpoint()[i] = getYpoint()[i] + (int)yRotateValue;
                }

                Graphics2D g2d = (Graphics2D)g;
                AffineTransform old = g2d.getTransform();
                g2d.rotate(Math.toRadians(getTd().getRotateDegree()));

                g.setColor(getTd().getBorderColor());
                g.drawPolyline(getXpoint(), getYpoint(), getPointSize());

                g2d.setTransform(old);

                for(int i = 0; i < getPointSize(); i++) {
                    getXpoint()[i] = getXpoint()[i] - (int)xRotateValue;
                    getYpoint()[i] = getYpoint()[i] - (int)yRotateValue;
                }
            }
        }
    }
    @Override
    public int getShapeType() {
        if(isComplete())    {
            return 7;
        }
        else {
            return 4;
        }
    }
    

    @Override
    public int getDepth() {
        return this.getTd().getDepth();
    }
    
    @Override
    public TilePolygon2D clone()    {
        TilePolygon2D result = new TilePolygon2D();
        
        result.setTtd(this.getTtd().clone());
        result.setTd(this.getTd().clone());
        result.smallX = this.smallX;
        result.smallY = this.smallY;
        result.largeX = this.largeX;
        result.largeY = this.largeY;
        result.setComplete(this.isComplete());
        result.setPointSize(this.getPointSize());
        result.xpoints = new int[this.getPointSize()];
        result.ypoints = new int[this.getPointSize()];
        
        for(int i = 0; i < this.getPointSize(); i++)    {
            result.xpoints[i] = this.xpoints[i];
            result.ypoints[i] = this.ypoints[i];
        }
        
        return result;
    }
}
