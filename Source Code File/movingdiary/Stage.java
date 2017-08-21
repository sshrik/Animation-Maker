/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movingdiary;

import tile.Drawable;
import tile.TileLine2D;
import tile.TileRoundRectangle2D;
import tile.TileEllipse2D;
import tile.TileRectangle2D;
import tile.TilePolygon2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import tile.TileImage2D;
/**
 *
 * @author chj87
 */
public class Stage extends JPanel implements Cloneable , Serializable {
    private ArrayList<Drawable> tileList;
    private int nowDrawingNumber;
    private boolean freeLine, line, rectangle, circle, oval;
    private boolean arc, roundRect, polygon, image;
    private boolean polygonDrawing;
    /**
     *This Value Only use for Drawing Things.
     *So don't have to serialize.
     */
    public transient Drawable tempD;
    private Color borderColor, fillingColor, textColor;
    
    public Stage()  {
        setTileList(new ArrayList<Drawable>());
        setAllBoolean(false);
        setPolygonDrawing(false);
        setBoderColor(new Color(240,240,240));
        setFillingColor(new Color(240,240,240));
    }
    public Stage(ArrayList<Drawable> tileList)   {
        setTileList(tileList);
        setAllBoolean(false);
        setPolygonDrawing(false);
        setBoderColor(new Color(240,240,240));
        setFillingColor(new Color(240,240,240));
    }
    
    public Stage(Stage stage)   {
        Stage tempStage = stage.clone();
        this.setTileList(tempStage.getTileList());
        this.setAllBoolean(false);
        this.setPolygonDrawing(false);
        this.setBoderColor(tempStage.getBorderColor());
        this.setFillingColor(tempStage.getFillingColor());
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i = 0 ; i < getTileList().size(); i++)  {
            getTileList().get(i).draw(g);
        }
        paintSelcted(g);
    }
    
    private void paintSelcted(Graphics g)   {
        for(int i = 0 ; i < getTileList().size(); i++)  {
            if(getTileList().get(i).isClicked())    {
                getTileList().get(i).drawBorder(g);
            }
        }
        if(tempD != null)   {
            tempD.draw(g);
        }
    }
    
    public void saveToJPG(String fileName) {
        BufferedImage image = new BufferedImage(1000,800,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        g.fillRect(0, 0, 1000, 800);
        for(int i = 0 ; i < getTileList().size(); i++)  {
            getTileList().get(i).draw(g);
        }
        try {
            File file = new File(fileName);
            ImageIO.write(image,"jpeg",file);
            
        } catch (Exception e) {
            
        }
    }
    
    
    /**
     *
     * @param allValue is user side value, This value can set all boolean members of stage to allValue.
     */
    
    public void setAllBoolean(boolean allValue)    {
        setOval(allValue);
        setCircle(allValue);        
        setRectangle(allValue);
        setArc(allValue);
        setRoundRect(allValue);
        setLine(allValue);        
        setFreeLine(allValue);   
        setPolygon(allValue);
    }
    
    public void setTileList(ArrayList<Drawable> tileList)  {
        this.tileList = tileList;
    }
    public ArrayList<Drawable> getTileList()   {
        return this.tileList;
    }
    
    public void setOval(boolean oval)   {
        this.oval = oval;
    }
    
    public boolean isOval() {
        return this.oval;
    }
    
    public void setCircle(boolean circle) {
        this.circle = circle;
    }
    
    public boolean isCircle()   {
        return this.circle;
    }
    
    public void setRectangle(boolean rectangle) {
        this.rectangle = rectangle;
    }
    
    public boolean isRectangle()    {
        return this.rectangle;
    }
    
    public void setLine( boolean line)  {
        this.line = line;
    }
    
    public boolean isLine() {
        return this.line;
    }
    
    public void setFreeLine( boolean freeLine ) {
        this.freeLine = freeLine;
    }
    
    public boolean isFreeLine() {
        return this.freeLine;
    }
    
    public int getNowDrawingNumber()   {
        return this.nowDrawingNumber;
    }
    
    public void setNowDrawingNumber(int nowDrawingNumber)    {
        this.nowDrawingNumber = nowDrawingNumber;
    }
    
    public void setBoderColor(Color borderColor)    {
        this.borderColor = borderColor;
    }
    
    public Color getBorderColor()   {
        return this.borderColor;
    }
    
    public void setTextColor(Color textColor)    {
        this.textColor = textColor;
    }
    
    public Color getTextColor()   {
        return this.textColor;
    }
    
    
    public void setFillingColor(Color fillingColor)    {
        this.fillingColor = fillingColor;
    }
    
    public boolean isArc() {
        return this.arc;
    }
    
    public boolean isRoundRect()   {
        return this.roundRect;
    }
    
    public void setArc(boolean arc) {
        this.arc = arc;
    }
    
    public void setRoundRect(boolean roundRect) {
        this.roundRect = roundRect;
    }
    
    public boolean isPolygon()  {
        return this.polygon;
    }
    
    public void setPolygon(boolean polygon) {
        this.polygon = polygon;
    }
    
    public boolean isPolygonDrawing()   {
        return this.polygonDrawing;
    }
    
    public void setPolygonDrawing( boolean polygonDrawing ) {
        this.polygonDrawing = polygonDrawing;
    }
    
    public boolean isImage()   {
        return this.image;
    }
    
    public void setImage(boolean image)  {
        this.image = image;
    }
    
    
    public Color getFillingColor()  {
        return this.fillingColor;
    }  
    public int getTileTop(int x, int y)    {
        int result = -1;
        if(getTileList().isEmpty() == false)   {
            for(int i = getTileList().size() - 1; i >= 0; i--)   {
                if(getTileList().get(i).contain(x, y))   {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }
    
    @Override
    public Stage clone()    {
        Stage result = new Stage();
        for(int i = 0 ; i < this.getTileList().size(); i++) {
            switch(this.getTileList().get(i).getShapeType())    {
                case 1:
                    
                    break;
                case 2:
                    TileEllipse2D te2D = ((TileEllipse2D)this.getTileList().get(i)).clone();
                    result.getTileList().add(te2D);
                    break;
                case 3:
                    TileLine2D tl2D = ((TileLine2D)this.getTileList().get(i)).clone();
                    result.getTileList().add(tl2D);
                    break;
                case 4:
                    TilePolygon2D tp2D = ((TilePolygon2D)this.getTileList().get(i)).clone();
                    result.getTileList().add(tp2D);
                    break;
                case 5:
                    TileRectangle2D tr2D = ((TileRectangle2D)this.getTileList().get(i)).clone();
                    result.getTileList().add(tr2D);
                    break;
                case 6:
                    TileRoundRectangle2D trr2D = ((TileRoundRectangle2D)this.getTileList().get(i)).clone();
                    result.getTileList().add(trr2D);
                    break;
                case 7:
                    TilePolygon2D tpp2D = ((TilePolygon2D)this.getTileList().get(i)).clone();
                    result.getTileList().add(tpp2D);
                    break;
                case 8:
                    TileImage2D ti2D = ((TileImage2D)this.getTileList().get(i)).clone();
                    result.getTileList().add(ti2D);
                    break;
                default:
                    break;
            }
        }
        
        result.nowDrawingNumber = this.nowDrawingNumber;
        result.setAllBoolean(false);
        result.setPolygon(this.isPolygon());
        result.setPolygonDrawing(this.isPolygonDrawing());
        result.setBoderColor(new Color(this.getBorderColor().getRed(),this.getBorderColor().getGreen(),this.getBorderColor().getBlue()));
        result.setFillingColor(new Color(this.getFillingColor().getRed(),this.getFillingColor().getGreen(),this.getFillingColor().getBlue()));
        result.setTextColor(new Color(this.getTextColor().getRed(),this.getTextColor().getGreen(),this.getTextColor().getBlue()));
        
        if(tempD != null)   {
            switch(this.tempD.getShapeType())    {
                case 1:
                    
                    break;
                case 2:
                    TileEllipse2D te2D = ((TileEllipse2D)this.tempD).clone();
                    result.tempD = te2D;
                    break;
                case 3:
                    TileLine2D tl2D = ((TileLine2D)this.tempD).clone();
                    result.tempD = tl2D;
                    break;
                case 4:
                    TilePolygon2D tp2D = ((TilePolygon2D)this.tempD).clone();
                    result.tempD = tp2D;
                    break;
                case 5:
                    TileRectangle2D tr2D = ((TileRectangle2D)this.tempD).clone();
                    result.tempD = tr2D;
                    break;
                case 6:
                    TileRoundRectangle2D trr2D = ((TileRoundRectangle2D)this.tempD).clone();
                    result.tempD = trr2D;
                    break;
                case 7:
                    TilePolygon2D tpp2D = ((TilePolygon2D)this.tempD).clone();
                    result.tempD = tpp2D;
                    break;
                case 8:
                    TileImage2D ti2D = ((TileImage2D)this.tempD).clone();
                    result.tempD = ti2D;
                    break;
                default:
                    break;
            }
        }
        return result;
    }
}
