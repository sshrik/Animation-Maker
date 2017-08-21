/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import Discripter.TileDiscripter;

/**
 *
 * @author chj87
 */
public class TileLine2D extends Line2D implements Drawable,Cloneable, Serializable  {
    private int x1, x2, y1, y2;
    private TileDiscripter td;
    private final static int ERROR_DISTANCE = 10;
    
    public TileLine2D() {
        td = new TileDiscripter();
    }
    
    public TileLine2D(int minX, int minY, int maxX, int maxY) {
        setX1(minX);
        setY1(minY);
        setX2(maxX);
        setY2(maxY);
        setTd(new TileDiscripter());
    }
    
    public TileLine2D(int minX, int minY, int maxX, int maxY, int rotationDegree ,int depth ,Color borderColor) {
        setX1(minX);
        setY1(minY);
        setX2(maxX);
        setY2(maxY);
        setTd(new TileDiscripter());
        getTd().setDepth(depth);
        getTd().setRotateDegree(rotationDegree);
        getTd().setBorderColor(borderColor);
    }
    
    
    
    @Override
    public double getX1() {
        return (double)this.x1;
    }
    
    public void setX1( int x1 ) {
        this.x1 = x1;
    }

    @Override
    public double getY1() {
        return (double)this.y1;
    }
    
    public void setY1(int y1) {
        this.y1 = y1;
    }

    @Override
    public Point2D getP1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getX2() {
        return (double)this.x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }
    
    @Override
    public double getY2() {
        return (double)this.y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
    
    @Override
    public Point2D getP2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getWidth()   {
        return (int)(getX1() - getX2());
    }
    
    public int getHeight()  {
        return (int)(getY1() - getY2());
    }

    @Override
    public void setLine(double x1, double y1, double x2, double y2) {
        setX1((int)x1);
        setY1((int)y1);
        setX2((int)x2);
        setY2((int)y2);
    }

    @Override
    public void setDepth(int depth) {
        this.getTd().setDepth(depth);
    }
    @Override
    public Rectangle2D getBounds2D() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics g) {
        int a = (int)(getX1() + getX2()) / 2;
        int b = (int)(getY1() + getY2()) / 2;

        int[] Xpoint = new int[2];
        int[] Ypoint = new int[2];

        Xpoint[0] = (int)getX1(); Ypoint[0] = (int)getY1();
        Xpoint[1] = (int)getX2(); Ypoint[1] = (int)getY2();

        TilePolygon2D pp = new TilePolygon2D(Xpoint, Ypoint, 2, false, getTd().getRotateDegree(), Color.BLACK, Color.WHITE);
        pp.draw(g);
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
        int[] Xpoint = new int[7];
        int[] Ypoint = new int[7];

        Xpoint[0] = (int)getX1();                       Ypoint[0] = (int)getY1();
        Xpoint[1] = (int)(getX1() + ERROR_DISTANCE);     Ypoint[1] = (int)getY1();
        Xpoint[2] = (int)(getX2() + ERROR_DISTANCE);     Ypoint[2] = (int)getY2();
        Xpoint[3] = (int)getX2();                       Ypoint[3] = (int)getY2();
        Xpoint[4] = (int)(getX2() - ERROR_DISTANCE);     Ypoint[4] = (int)getY2();
        Xpoint[5] = (int)(getX1() - ERROR_DISTANCE);     Ypoint[5] = (int)getY1();
        Xpoint[6] = (int)getX1();                       Ypoint[6] = (int)getY1();
        
        TilePolygon2D pp = new TilePolygon2D(Xpoint, Ypoint, 7, true, getTd().getRotateDegree(), Color.BLACK, Color.WHITE);
        result = pp.contain(x, y);
        
        return result;
    }

    @Override
    public boolean isClicked() {
        return getTd().isClicked();
    }

    @Override
    public void drawBorder(Graphics g) {
        int[] Xpoint = new int[7];
        int[] Ypoint = new int[7];

        Xpoint[0] = (int)getX1();                       Ypoint[0] = (int)getY1();
        Xpoint[1] = (int)(getX1() + ERROR_DISTANCE);     Ypoint[1] = (int)getY1();
        Xpoint[2] = (int)(getX2() + ERROR_DISTANCE);     Ypoint[2] = (int)getY2();
        Xpoint[3] = (int)getX2();                       Ypoint[3] = (int)getY2();
        Xpoint[4] = (int)(getX2() - ERROR_DISTANCE);     Ypoint[4] = (int)getY2();
        Xpoint[5] = (int)(getX1() - ERROR_DISTANCE);     Ypoint[5] = (int)getY1();
        Xpoint[6] = (int)getX1();                       Ypoint[6] = (int)getY1();

        TilePolygon2D pp = new TilePolygon2D(Xpoint, Ypoint, 7, true, getTd().getRotateDegree(), Color.BLACK, Color.WHITE);
        pp.drawBorderLine(g);
    }

    @Override
    public void setClicked(boolean clicked) {
        getTd().setClicked(clicked);
    }

    @Override
    public int getShapeType() {
        return 3;
    }

    @Override
    public int getDepth() {
        return this.getTd().getDepth();
    }
    
    @Override
    public TileLine2D clone()   {
        TileLine2D result = new TileLine2D();
        
        result.setTd(this.getTd().clone());
        result.setX1((int)this.getX1());
        result.setY1((int)this.getY1());
        result.setX2((int)this.getX2());
        result.setY2((int)this.getY2());
        
        return result;
    }
}
