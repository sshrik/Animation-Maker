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
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;
import Discripter.TextDiscripter;
import Discripter.TileDiscripter;

/**
 *
 * @author chj87
 */
public class TileRoundRectangle2D extends RoundRectangle2D implements Drawable,Cloneable, Serializable  {
    private int leftTopX, leftTopY;
    private int rightBottomX, rightBottomY;
    private int arcWidth, arcHeight;
    private TileDiscripter td;
    private TextDiscripter ttd;
    
    
    public TileRoundRectangle2D()   {
        setTd(new TileDiscripter());
    }
    
    public TileRoundRectangle2D( int leftTopX, int leftTopY, int rightBottomX, int rightBottomY, int arcWidth, int arcHeight)    {
        setTd(new TileDiscripter());
        setLeftTopX(leftTopX);
        setLeftTopY(leftTopY);
        setRightBottomX(rightBottomX);
        setRightBottomY(rightBottomY);
        setArcWidth(arcWidth);
        setArcHeight(arcHeight);
    }
    
    public TileRoundRectangle2D( int leftTopX, int leftTopY, int rightBottomX, int rightBottomY, int arcWidth, int arcHeight, int rotationDegree ,int depth, String textContext, int x, int y, String fontName, int textStyle, int textSize, Color textColor, Color borderColor, Color fillingColor)    {
        setTd(new TileDiscripter());
        setTtd(new TextDiscripter(textContext, textColor, new Font(fontName,textStyle, textSize),x,y));
        setLeftTopX(leftTopX);
        setLeftTopY(leftTopY);
        setRightBottomX(rightBottomX);
        setRightBottomY(rightBottomY);
        setArcWidth(arcWidth);
        setArcHeight(arcHeight);
        getTd().setRotateDegree(rotationDegree);
        getTd().setDepth(depth);
        getTd().setBorderColor(borderColor);
        getTd().setFillingColor(fillingColor);
    }
    
    public int getLeftTopX()    {
        return this.leftTopX;
    }
    
    public int getLeftTopY()    {
        return this.leftTopY;
    }
    
    public int getRightBottomX()    {
        return this.rightBottomX;
    }
    
    public int getRightBottomY()    {
        return this.rightBottomY;
    }
    
    public void setLeftTopX(int leftTopX)   {
        this.leftTopX = leftTopX;
    
    }
    
    public void setLeftTopY(int leftTopY)   {
        this.leftTopY = leftTopY;
    }
    
    public void setRightBottomX(int rightBottomX)   {
        this.rightBottomX = rightBottomX;
    }
    
    public void setRightBottomY(int rightBottomY)   {
        this.rightBottomY = rightBottomY;
    }
    
    public TileDiscripter getTd()   {
        return this.td;
    }
    
    public void setTd(TileDiscripter td) {
        this.td = td;
    }
    
    public TextDiscripter getTtd()  {
        return this.ttd;
    }
    
    public void setTtd(TextDiscripter ttd)  {
        this.ttd = ttd;
    }
    
    
    @Override
    public double getArcWidth() {
        return (double)this.arcWidth;
    }
    
    public void setArcWidth(int arcWidth)    {
        this.arcWidth = arcWidth;
    }

    @Override
    public double getArcHeight() {
        return (double)this.arcHeight;
    }
    
    public void setArcHeight(int arcHeight)  {
        this.arcHeight = arcHeight;
    }
    
    public int getMiddleX() {
        return (int)(getRightBottomX() + getLeftTopX())/2;
    }
    
    public int getMiddleY() {
        return (int)(getRightBottomY() + getLeftTopY())/2;
    }
    

    @Override
    public void setRoundRect(double x, double y, double w, double h, double arcWidth, double arcHeight) {
        setLeftTopX((int)x);
        setLeftTopY((int)y);
        setRightBottomX((int)x + (int)w);
        setRightBottomY((int)y + (int)h);
        setArcWidth((int)arcWidth);
        setArcHeight((int)arcHeight);
    }

    @Override
    public double getX() {
        return (double)this.leftTopX;
    }

    @Override
    public double getY() {
        return (double)this.leftTopY;
    }

    @Override
    public double getWidth() {
        return (double)(rightBottomX - leftTopX);
    }

    @Override
    public double getHeight() {
        return (double)(rightBottomY - leftTopY);
    }

    @Override
    public boolean isEmpty() {
        boolean result = false;
            if(rightBottomX == leftTopX)    {
                if(rightBottomY == leftTopY)    {
                    result = true;
                }
            }
        return result;
    }

    @Override
    public Rectangle2D getBounds2D() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics g) {
        if(getTd().getRotateDegree() == 0) {
            g.setColor(getTd().getFillingColor());
            g.fillRoundRect(getLeftTopX(), getLeftTopY(), (int)getWidth(), (int)getHeight(), (int)getArcWidth(), (int)getArcHeight());
            
            g.setColor(getTtd().getTextColor());
            g.setFont(getTtd().getTextFont());
            String[] temp = getTtd().getTextContext().split("\\n");
            for(int i = 0; i < temp.length; i++)    {
                g.drawString(temp[i], getTtd().getX(), getTtd().getY() + ( i * (getTtd().getTextFont().getSize() + 2)));
            }
            
            g.setColor(getTd().getBorderColor());
            g.drawRoundRect(getLeftTopX(), getLeftTopY(), (int)getWidth(), (int)getHeight(), (int)getArcWidth(), (int)getArcHeight());
        }
        else {
            int a = getMiddleX();
            int b = getMiddleY();
            
            double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
            double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));
            
            double xRotateValue = (cosTemp-1) * a + sinTemp * b;
            double yRotateValue = (cosTemp-1) * b - sinTemp * a;
            
            Graphics2D g2d = (Graphics2D)g;
            AffineTransform old = g2d.getTransform();
            g2d.rotate(Math.toRadians(getTd().getRotateDegree()));
            
            g.setColor(getTd().getFillingColor());
            g.fillRoundRect(getLeftTopX() + (int)xRotateValue, getLeftTopY() + (int)yRotateValue, (int)getWidth(), (int)getHeight(), (int)getArcWidth(), (int)getArcHeight());
            
            g.setColor(getTtd().getTextColor());
            g.setFont(getTtd().getTextFont());
            String[] temp = getTtd().getTextContext().split("\\n");
            for(int i = 0; i < temp.length; i++)    {
                g.drawString(temp[i], getTtd().getX() + (int)xRotateValue, getTtd().getY() + (int)yRotateValue + ( i * (getTtd().getTextFont().getSize() + 2)));
            }
            
            g.setColor(getTd().getBorderColor());
            g.drawRoundRect(getLeftTopX() + (int)xRotateValue, getLeftTopY() + (int)yRotateValue, (int)getWidth(), (int)getHeight(), (int)getArcWidth(), (int)getArcHeight());
            
            g2d.setTransform(old);
        }
    }
    

    @Override
    public boolean contain(int x, int y) {
        boolean result = false;
        
        if(getTd().getRotateDegree() == 0)  {
            if(getLeftTopX() <= x && getRightBottomX() >= x && getLeftTopY() <= y && getRightBottomY() >= y) {
                result = true;
            }
        }
        else {
            int x1 = getLeftTopX();
            int x2 = getRightBottomX();
            int y1 = getLeftTopY();
            int y2 = getRightBottomY();
            
            int w = (int)getWidth();
            int h = (int)getHeight();
            
            double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
            double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));
            
            x1 = (int) ( x1 + w * ( 1 - cosTemp ) / 2 + h * sinTemp / 2 );
            y1 = (int) ( y1 + h * ( 1 - cosTemp ) / 2 - w * sinTemp / 2 );
            x2 = (int) ( x2 + w * ( cosTemp - 1 ) / 2 - h * sinTemp / 2 );
            y2 = (int) ( y2 + h * ( cosTemp - 1 ) / 2 + w * sinTemp / 2 );
            
            int[] tempX = new int[5];
            int[] tempY = new int[5];
            
            tempX[0] = x1; tempY[0] = y1;
            tempX[1] = (int) (x1 + w * cosTemp) ; tempY[1] = (int) (y1 + w * sinTemp);
            tempX[2] = x2; tempY[2] = y2;
            tempX[3] = (int) (x2 - w * cosTemp ); tempY[3] = (int) (y2 - w * sinTemp);
            tempX[4] = x1; tempY[4] = y1;
            
            TilePolygon2D pp = new TilePolygon2D(tempX,tempY,5,true);
            result = pp.contains(x,y);
        }
        
        return result;
    }

    @Override
    public boolean isClicked() {
        return getTd().isClicked();
    }

    @Override
    public void drawBorder(Graphics g) {
        if(getTd().getRotateDegree() == 0) {
            g.setColor(Color.BLACK);
            g.drawRect((int)getMinX() - 5, (int)getMinY() - 5, (int)getWidth() + 10, (int)getHeight() + 10);
        }
        else {
            int a = getMiddleX();
            int b = getMiddleY();
            
            double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
            double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));
            
            double xRotateValue = (cosTemp-1) * a + sinTemp * b;
            double yRotateValue = (cosTemp-1) * b - sinTemp * a;
            
            Graphics2D g2d = (Graphics2D)g;
            AffineTransform old = g2d.getTransform();
            g2d.rotate(Math.toRadians(getTd().getRotateDegree()));
            
            g.setColor(Color.BLACK);
            g.drawRect((int)getX() + (int)xRotateValue - 5,(int)getY() + (int)yRotateValue - 5,(int)getWidth() + 10,(int)getHeight() + 10);
            
            g2d.setTransform(old);
        }
    }

    @Override
    public void setClicked(boolean clicked) {
        getTd().setClicked(clicked);
    }

    @Override
    public int getShapeType() {
        return 6;
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
    public TileRoundRectangle2D clone()  {
        TileRoundRectangle2D result = new TileRoundRectangle2D();
        
        result.setTtd(this.getTtd().clone());
        result.setTd(this.getTd().clone());
        result.setLeftTopX(this.getLeftTopX());
        result.setLeftTopY(this.getLeftTopY());
        result.setRightBottomX(this.getRightBottomX());
        result.setRightBottomY(this.getRightBottomY());
        result.setArcWidth((int)this.getArcWidth());
        result.setArcHeight((int)this.getArcHeight());
        
        return result;
    }
}
