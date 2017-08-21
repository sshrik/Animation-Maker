/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.swing.JPanel;
import Discripter.TileDiscripter;

/**
 *
 * @author chj87
 */
public class TileImage2D implements Drawable,Cloneable, Serializable{
    private int x1,y1,x2,y2;
    private BufferedImage bufferedImage;
    private TileDiscripter td;
    
    
    public TileImage2D()    {
        
    }
    
    
    
    public TileImage2D(int x1,int y1,int x2,int y2,int rotateDegree,int depth, BufferedImage bufferedImage,  Color borderColor, Color fillingColor)    {
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setTd(new TileDiscripter());
        setBufferedImage(bufferedImage);
        getTd().setRotateDegree(rotateDegree);
        getTd().setDepth(depth);
        getTd().setBorderColor(borderColor);
        getTd().setFillingColor(fillingColor);
    }
    
    public TileDiscripter getTd()   {
        return this.td;
    }
    
    public void setTd(TileDiscripter td)    {
        this.td = td;
    }
    
    public void setBufferedImage(BufferedImage bufferedImage)   {
        this.bufferedImage = bufferedImage;
    }
    
    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }
    
    public void setX1(int x1) {
        this.x1 = x1;
    }
    public void setX2(int x2) {
        this.x2 = x2;
    }
    public void setY1(int y1) {
        this.y1 = y1;
    }
    public void setY2(int y2) {
        this.y2 = y2;
    }
    public int getX1() {
        return this.x1;
    }
    public int getX2() {
        return this.x2;
    }
    public int getY1() {
        return this.y1;
    }
    public int getY2() {
        return this.y2;
    }
    
    public int getWidth()   {
        return this.getX2() - this.getX1();
    }
    
    public int getHeight()  {
        return this.getY2() - this.getY1();
    }
    
    @Override
    public void draw(Graphics g) {
        if(getTd().getRotateDegree() == 0) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(getBufferedImage(), (int)getX1(), (int)getY1(), (int)getWidth(),(int)getHeight(), new JPanel());
            g.setColor(getTd().getBorderColor());
            g.drawRect((int)getX1(),(int)getY1(),(int)getWidth(),(int)getHeight());
        }  
        else {
            int a = (int)(getX1() + getX2())/2;
            int b = (int)(getY1() + getY2())/2;
            
            double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
            double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));
            
            double xRotateValue = (cosTemp-1) * a + sinTemp * b;
            double yRotateValue = (cosTemp-1) * b - sinTemp * a;
            
            Graphics2D g2d = (Graphics2D)g;
            AffineTransform old = g2d.getTransform();
            g2d.rotate(Math.toRadians(getTd().getRotateDegree()));
            
            g2d.drawImage(getBufferedImage(),(int)getX1() + (int)xRotateValue,(int)getY1() + (int)yRotateValue, new JPanel());

            g.setColor(getTd().getBorderColor());
            g.drawRect((int)getX1() + (int)xRotateValue,(int)getY1() + (int)yRotateValue, (int)getWidth(), (int)getHeight());
            
            g2d.setTransform(old);
        }
    }

    @Override
    public boolean contain(int x, int y) {
    
        boolean result = false;
        
        if(getTd().getRotateDegree() == 0)  {
            if(getX1() <= x && getX2() >= x && getY1() <= y && getY2() >= y) {
                result = true;
            }
        }
        
        else {
            int x1 = getX1();
            int x2 = getX2();
            int y1 = getY1();
            int y2 = getY2();
            
            int w = getWidth();
            int h = getHeight();
            
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
    public void setClicked(boolean clicked)     {
            this.getTd().setClicked(clicked);
    }

    @Override
    public void drawBorder(Graphics g) {
        
        if(getTd().getRotateDegree() == 0) {
            g.setColor(Color.BLACK);
            g.drawRect((int)getX1() - 5, (int)getY1() - 5, (int)getWidth() + 10, (int)getHeight() + 10);
        }
        else {
            int a = (int)(getX1() + getX2())/2;
            int b = (int)(getY1() + getY2())/2;
            
            double cosTemp = Math.cos(Math.toRadians((double)getTd().getRotateDegree()));
            double sinTemp = Math.sin(Math.toRadians((double)getTd().getRotateDegree()));
            
            double xRotateValue = (cosTemp-1) * a + sinTemp * b;
            double yRotateValue = (cosTemp-1) * b - sinTemp * a;
            
            Graphics2D g2d = (Graphics2D)g;
            AffineTransform old = g2d.getTransform();
            g2d.rotate(Math.toRadians(getTd().getRotateDegree()));
            
            g.setColor(Color.BLACK);
            g.drawRect((int)getX1() + (int)xRotateValue - 5,(int)getY1() + (int)yRotateValue - 5,(int)getWidth() + 10,(int)getHeight() + 10);
            
            g2d.setTransform(old);
        }
    }

    @Override
    public int getShapeType() {
        return 8;
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
    public TileImage2D clone()  {
        TileImage2D result = new TileImage2D();
        
        result.setTd(this.getTd().clone());
        result.setX1((int)this.getX1());
        result.setY1((int)this.getY1());
        result.setX2((int)this.getX2());
        result.setY2((int)this.getY2());
        
        BufferedImage bf = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bf.createGraphics();
        g.scale((double)this.getWidth()/(double)getBufferedImage().getWidth(), (double)this.getHeight()/(double)getBufferedImage().getHeight());
        g.drawImage(this.getBufferedImage(), 0, 0, null);
        g.scale((double)getBufferedImage().getWidth()/(double)this.getWidth(), (double)getBufferedImage().getHeight()/(double)this.getHeight());
        result.setBufferedImage(bf);
        
        return result;
    }
    
}
