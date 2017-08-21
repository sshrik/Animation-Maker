/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Discripter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JTextField;
import movingdiary.Stage;
import movingdiary.animationStage;
import tile.TileEllipse2D;
import tile.TileImage2D;
import tile.TileLine2D;
import tile.TilePolygon2D;
import tile.TileRectangle2D;
import tile.TileRoundRectangle2D;
import tile.Drawable;

/**
 *
 * @author chj87
 */
public class MainFrameDiscripter {
    private int selcetNumber;
    private int lineDiameter = 1;
    private boolean selectState, removeState, eraserState, polygonState;
    private boolean imageSlected;
    private boolean selected;
    private Drawable tempShape;
    private BufferedImage bufferedImage;
    private Color borderColor, fillingColor;
// Drawing State, Not have to serialize
    
    private int stageNumber = 0;
    private int maxStageNumber = 1;
    private int animationStageNumber = 0;
    private int animationMaxStageNumber = 1;
    private ArrayList<Stage> stageList;
    private ArrayList<ArrayList<Stage>> animationList;
    private animationStage[] as = new animationStage[5];
    private Stage stage;
    // Drawable State, Have to Serializing
    
    
    public MainFrameDiscripter()    {
        
    }
    
    public void colorSetting()  {
        if(getSelectState())   {
            if(isSelected())    {
                if(getSelectNumber() == -1) {

                }
                else {
                    Drawable compareTile = getStage().getTileList().get(getSelectNumber());
                    
                    switch( compareTile.getShapeType() ) {
                        case 1:

                        break;
                        
                        case 2:
                            TileEllipse2D oldte2D = (TileEllipse2D)compareTile;
                            TileEllipse2D te2D = oldte2D.clone();
                            
                            te2D.getTd().setBorderColor(getBorderColor());
                            te2D.getTd().setFillingColor(getFillingColor());
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),te2D);
                        break;
                        
                        case 3:
                            TileLine2D oldtl2D = (TileLine2D)compareTile;
                            TileLine2D tl2D = oldtl2D.clone();
                            
                            tl2D.getTd().setBorderColor(getBorderColor());
                            tl2D.getTd().setFillingColor(getFillingColor());
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),tl2D);
                        break;
                        
                        case 4:
                            TilePolygon2D oldtp2D = (TilePolygon2D)compareTile;
                            TilePolygon2D tp2D = oldtp2D.clone();
                            
                            tp2D.getTd().setBorderColor(getBorderColor());
                            tp2D.getTd().setFillingColor(getFillingColor());
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),tp2D);
                        break;
                        
                        case 5:
                            TileRectangle2D oldtr2D = (TileRectangle2D)compareTile;
                            TileRectangle2D tr2D = oldtr2D.clone();
                            
                            tr2D.getTd().setBorderColor(getBorderColor());
                            tr2D.getTd().setFillingColor(getFillingColor());
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),tr2D);
                        break;
                        
                        case 6:
                            TileRoundRectangle2D oldtrr2D = (TileRoundRectangle2D)compareTile;
                            TileRoundRectangle2D trr2D = oldtrr2D.clone();
                            
                            trr2D.getTd().setBorderColor(getBorderColor());
                            trr2D.getTd().setFillingColor(getFillingColor());
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),trr2D);
                        break;
                        
                        case 7:
                            TilePolygon2D oldtpp2D = (TilePolygon2D)compareTile;
                            TilePolygon2D tpp2D = oldtpp2D.clone();
                            
                            tpp2D.getTd().setBorderColor(getBorderColor());
                            tpp2D.getTd().setFillingColor(getFillingColor());
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),tpp2D);
                        break;
                        
                        case 8:
                            TileImage2D oldti2D = (TileImage2D)compareTile;
                            TileImage2D ti2D = oldti2D.clone();
                            
                            ti2D.getTd().setBorderColor(getBorderColor());
                            ti2D.getTd().setFillingColor(getFillingColor());
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),ti2D);
                        break;
                        default:
                            
                        break;
                    }
                    getStage().repaint();
                    
                    setSelectNumber(-1);
                    setPastState();
                }
            }
        }
        //setDownStage();
    }
    /*
    public void mouseReaalised(int draggedX, int draggedY)    {
        if(getRemoveState()) {
            
        }
        else if(getSelectState())   {
            if(isSelected())    {
                if(getSelectNumber() == -1) {

                }
                else {
                    Drawable compareTile = getStage().getTileList().get(getSelectNumber());
                    
                    switch (getTempShape().getShapeType()) {
                        case 1:
                            
                            break;
                        case 2:
                            TileEllipse2D oldte2D = (TileEllipse2D)compareTile;
                            TileEllipse2D te2D = oldte2D.clone();
                            
                            te2D.setMinX((int)te2D.getMinX() + draggedX);
                            te2D.setMinY((int)te2D.getMinY() + draggedY);
                            
                            te2D.setMaxX((int)te2D.getMaxX() + draggedX);
                            te2D.setMaxY((int)te2D.getMaxY() + draggedY);
                            
                            te2D.getTtd().setX(te2D.getTtd().getX() + draggedX);
                            te2D.getTtd().setY(te2D.getTtd().getY() + draggedY);
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),te2D);
                          
                            break;
                        case 3:
                           TileLine2D oldtl2D = (TileLine2D)compareTile;
                            TileLine2D tl2D = oldtl2D.clone();
                            
                            tl2D.setX1((int)tl2D.getX1() + draggedX);
                            tl2D.setY1((int)tl2D.getY1() + draggedY);
                            
                            tl2D.setX2((int)tl2D.getX2() + draggedX);
                            tl2D.setY2((int)tl2D.getY2() + draggedY);
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),tl2D);
                            
                            break;
                        case 4:
                            TilePolygon2D oldtp2D = (TilePolygon2D)compareTile;
                            TilePolygon2D tp2D = oldtp2D.clone();
                            
                            int[] x1 = new int[oldtp2D.getPointSize()];
                            int[] y1 = new int[oldtp2D.getPointSize()];
                            
                            for(int i = 0; i < ((TilePolygon2D)getTempShape()).getPointSize(); i++) {
                                x1[i] = oldtp2D.getXpoint()[i] + draggedX;
                                y1[i] = oldtp2D.getYpoint()[i] + draggedY;
                            }
                            
                            tp2D.setXpoint(x1);
                            tp2D.setYpoint(y1);
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),tp2D);
                            
                            break;
                        case 5:
                            TileRectangle2D oldtr2D = (TileRectangle2D)compareTile;
                            TileRectangle2D tr2D = oldtr2D.clone();
                            
                            tr2D.setLeftTopX(tr2D.getLeftTopX() + draggedX);
                            tr2D.setLeftTopY(tr2D.getLeftTopY() + draggedY);
                            
                            tr2D.setRightBottomX(tr2D.getRightBottomX() + draggedX);
                            tr2D.setRightBottomY(tr2D.getRightBottomY() + draggedY);
                            
                            tr2D.getTtd().setX(tr2D.getTtd().getX());
                            tr2D.getTtd().setY(tr2D.getTtd().getY());
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),tr2D);
                            break;
                        case 6:
                            TileRoundRectangle2D oldtrr2D = (TileRoundRectangle2D)compareTile;
                            TileRoundRectangle2D trr2D = oldtrr2D.clone();
                            
                            trr2D.setLeftTopX(trr2D.getLeftTopX() + draggedX);
                            trr2D.setLeftTopY(trr2D.getLeftTopY() + draggedY);
                            
                            trr2D.setRightBottomX(trr2D.getRightBottomX() + draggedX);
                            trr2D.setRightBottomY(trr2D.getRightBottomY() + draggedY);
                            
                            trr2D.getTtd().setX(trr2D.getTtd().getX());
                            trr2D.getTtd().setY(trr2D.getTtd().getY());
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),trr2D);
                            break;
                        case 7:
                            TilePolygon2D oldtpp2D = (TilePolygon2D)compareTile;
                            TilePolygon2D tpp2D = oldtpp2D.clone();
                            
                            int[] x2 = new int[oldtpp2D.getPointSize()];
                            int[] y2 = new int[oldtpp2D.getPointSize()];
                            
                            for(int i = 0; i < ((TilePolygon2D)getTempShape()).getPointSize(); i++) {
                                x2[i] = oldtpp2D.getXpoint()[i] + draggedX;
                                y2[i] = oldtpp2D.getYpoint()[i] + draggedY;
                            }
                            
                            tpp2D.setXpoint(x2);
                            tpp2D.setYpoint(y2);
                            
                            tpp2D.getTtd().setX(tpp2D.getTtd().getX() + draggedX);
                            tpp2D.getTtd().setY(tpp2D.getTtd().getY() + draggedY);
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),tpp2D);
                            break;
                        case 8:
                            TileImage2D oldti2D = (TileImage2D)compareTile;
                            TileImage2D ti2D = oldti2D.clone();
                            
                            ti2D.setX1(ti2D.getX1() + draggedX);
                            ti2D.setY1(ti2D.getY1() + draggedY);
                            
                            ti2D.setX2(ti2D.getX2() + draggedX);
                            ti2D.setY2(ti2D.getY2() + draggedY);
                            
                            
                            getStage().getTileList().remove(getSelectNumber());
                            getStage().getTileList().add(getSelectNumber(),ti2D);
                            break;
                        default:
                            break;
                    }
                    setPastState();
                    //setDownStage();
                }
            }
        }
        else if(getEraserState()) {
            if(getStage().isFreeLine()) {
                int[] x1 = new int[xList.size()];
                int[] y1 = new int[xList.size()];

                for(int i = 0; i < xList.size(); i++) {
                    x1[i] = xList.get(i);
                    y1[i] = yList.get(i);
                }
                getStage().getTileList().add(new TilePolygon2D(x1, y1, xList.size(), false, Integer.parseInt(rotationTextField.getText()), getStage().getTileList().size() , "", 0, 0, "Serif" , 0, 15, textColorPanel.getBackground() , Color.WHITE, Color.WHITE));

                getStage().tempD = null;
                
                if(getStageList().size() > getMaxStageNumber()) {
                    for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                        getStageList().remove(i);
                        i--;
                    }
                }
                
                getStageList().add(getStage().clone());
                setStageNumber(getStageNumber() + 1);
                setMaxStageNumber(getStageNumber() + 1);
            }
            else if(getStage().isLine())    {
                getStage().getTileList().add(new TileLine2D(getFirstX(), getFirstY(), getSecondX(), Integer.parseInt(rotationTextField.getText()), getStage().getTileList().size() , getSecondY(),Color.WHITE));
                
                getStage().tempD = null;
                
                if(getStageList().size() > getMaxStageNumber()) {
                    for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                        getStageList().remove(i);
                        i--;
                    }
                }
                
                getStageList().add(getStage().clone());
                setStageNumber(getStageNumber() + 1);
                setMaxStageNumber(getStageNumber() + 1);
            }
            else if(getStage().isRectangle())   {
                rotationTextField.setText("0");
                getStage().getTileList().add(new TileRectangle2D(minX, minY, maxX, maxY,Integer.parseInt(rotationTextField.getText()), getStage().getTileList().size() , "", minX, minY, "Serif" , 0, 15, textColorPanel.getBackground() , Color.WHITE, Color.WHITE));
           
                getStage().tempD = null;
                
                if(getStageList().size() > getMaxStageNumber()) {
                    for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                        getStageList().remove(i);
                        i--;
                    }
                }
                
                getStageList().add(getStage().clone());
                setStageNumber(getStageNumber() + 1);
                setMaxStageNumber(getStageNumber() + 1);
            }
            else if(getStage().isCircle())  {
                if(width > height)  {
                    maxY = minY + width;
                }
                else if(width <= height)    {
                    maxX = minX + height;
                }
                getStage().getTileList().add(new TileEllipse2D(minX, minY, maxX, maxY,Integer.parseInt(rotationTextField.getText()), getStage().getTileList().size(),"", minX, minY, "Serif" , 0, 15, textColorPanel.getBackground() ,  Color.WHITE, Color.WHITE));
                
                getStage().tempD = null;
                
                if(getStageList().size() > getMaxStageNumber()) {
                    for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                        getStageList().remove(i);
                    }
                }
                
                getStageList().add(getStage().clone());
                setStageNumber(getStageNumber() + 1);
                setMaxStageNumber(getStageNumber() + 1);

            }
            else if(getStage().isOval())    {
                getStage().getTileList().add(new TileEllipse2D(minX, minY, maxX, maxY,Integer.parseInt(rotationTextField.getText()), getStage().getTileList().size(),"", minX, minY, "Serif" , 0, 15, textColorPanel.getBackground() ,  Color.WHITE, Color.WHITE));
                
                getStage().tempD = null;
                
                if(getStageList().size() > getMaxStageNumber()) {
                    for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                        getStageList().remove(i);
                    }
                }
                
                getStageList().add(getStage().clone());
                setStageNumber(getStageNumber() + 1);
                setMaxStageNumber(getStageNumber() + 1);
            }
            else if(getStage().isRoundRect())   {
                getStage().getTileList().add(new TileRoundRectangle2D(minX, minY, maxX, maxY, (int)(width/Integer.parseInt(circularPercentText.getText())), (int)(height/Integer.parseInt(circularPercentText.getText())), Integer.parseInt(rotationTextField.getText()), getStage().getTileList().size(), "", 0, 0, "Serif" , 0, 15, textColorPanel.getBackground() ,  Color.WHITE, Color.WHITE));
                
                getStage().tempD = null;
                
                if(getStageList().size() > getMaxStageNumber()) {
                    for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                        getStageList().remove(i);
                    }
                }
                
                getStageList().add(getStage().clone());
                setStageNumber(getStageNumber() + 1);
                setMaxStageNumber(getStageNumber() + 1);
            }
            setDownStage();
        }
        else {
            if(getStage().isFreeLine()) {
                int[] x = new int[xList.size()];
                int[] y = new int[yList.size()];
                
                for(int i = 0 ; i < xList.size(); i++)  {
                    x[i] = xList.get(i);
                    y[i] = yList.get(i);
                }
                
                getStage().getTileList().add(new TilePolygon2D(x,y, xList.size(), false, Integer.parseInt(rotationTextField.getText()), getStage().getTileList().size() ,"", 0, 0, "Serif" , 0, 15, textColorPanel.getBackground() , getStage().getBorderColor(), getStage().getFillingColor()));
            
                getStage().tempD = null;
                
                if(getStageList().size() > getMaxStageNumber()) {
                    for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                        getStageList().remove(i);
                    }
                }
                
                getStageList().add(getStage().clone());
                setStageNumber(getStageNumber() + 1);
                setMaxStageNumber(getStageNumber() + 1);
            }
            else if(getStage().isLine())    {
                getStage().getTileList().add(new TileLine2D(getFirstX(), getFirstY(), getSecondX(), getSecondY(), Integer.parseInt(rotationTextField.getText()), getStage().getTileList().size() , getStage().getBorderColor()));
            
                getStage().tempD = null;
                
                if(getStageList().size() > getMaxStageNumber()) {
                    for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                        getStageList().remove(i);
                    }
                }
                
                getStageList().add(getStage().clone());
                setStageNumber(getStageNumber() + 1);
                setMaxStageNumber(getStageNumber() + 1);
            }
            else if(getStage().isRectangle())   {
                rotationTextField.setText("0");
                getStage().getTileList().add(new TileRectangle2D(minX, minY, maxX, maxY,Integer.parseInt(rotationTextField.getText()), getStage().getTileList().size(),"", minX, minY, "Serif" , 0, 15, textColorPanel.getBackground() ,  getStage().getBorderColor(), getStage().getFillingColor()));
            
                getStage().tempD = null;
                
                if(getStageList().size() > getMaxStageNumber()) {
                    for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                        getStageList().remove(i);
                    }
                }
                
                getStageList().add(getStage().clone());
                setStageNumber(getStageNumber() + 1);
                setMaxStageNumber(getStageNumber() + 1);
            }
            else if(getStage().isCircle())  {
                width = maxX - minX;
                height = maxY - minY;

                if(width > height)  {
                    maxY = minY + width;
                }
                else if(width <= height)    {
                    maxX = minX + height;
                }
                getStage().getTileList().add(new TileEllipse2D(minX, minY, maxX, maxY,Integer.parseInt(rotationTextField.getText()), getStage().getTileList().size() ,"", minX, minY, "Serif" , 0, 15, textColorPanel.getBackground() ,  getStage().getBorderColor(), getStage().getFillingColor()));
           
                getStage().tempD = null;
                
                if(getStageList().size() > getMaxStageNumber()) {
                    for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                        getStageList().remove(i);
                        i--;
                    }
                }
                
                getStageList().add(getStage().clone());
                setStageNumber(getStageNumber() + 1);
                setMaxStageNumber(getStageNumber() + 1);
                
            }
            else if(getStage().isOval())    {
                getStage().getTileList().add(new TileEllipse2D(minX, minY, maxX, maxY,Integer.parseInt(rotationTextField.getText()), getStage().getTileList().size() ,"", minX, minY, "Serif" , 0, 15, textColorPanel.getBackground() , getStage().getBorderColor(), getStage().getFillingColor()));
            
                getStage().tempD = null;
                
                if(getStageList().size() > getMaxStageNumber()) {
                    for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                        getStageList().remove(i);
                        i--;
                    }
                }
                
                getStageList().add(getStage().clone());
                setStageNumber(getStageNumber() + 1);
                setMaxStageNumber(getStageNumber() + 1);
            }
            else if(getStage().isRoundRect())   {
                getStage().getTileList().add(new TileRoundRectangle2D(minX, minY, maxX, maxY, (int)(width/Integer.parseInt(circularPercentText.getText())),(int)(height/Integer.parseInt(circularPercentText.getText())), Integer.parseInt(rotationTextField.getText()),  getStage().getTileList().size(),"", minX, minY, "Serif" , 0, 15, textColorPanel.getBackground() ,  getStage().getBorderColor(), getStage().getFillingColor()));
            
                getStage().tempD = null;
                
                if(getStageList().size() > getMaxStageNumber()) {
                    for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                        getStageList().remove(i);
                        i--;
                    }
                }
                
                getStageList().add(getStage().clone());
                setStageNumber(getStageNumber() + 1);
                setMaxStageNumber(getStageNumber() + 1);
            }
            else if(getStage().isImage())   {
                if(isImageSelected())   {
                    getStage().getTileList().add(new TileImage2D(minX, minY, maxX, maxY,Integer.parseInt(rotationTextField.getText()),getStage().getTileList().size(),getBufferedImage(), getStage().getBorderColor(), getStage().getFillingColor()));
                    setBufferedImage(new BufferedImage(1000,1000,BufferedImage.TYPE_INT_RGB));
                    setImageSelected(false);
                    
                    getStage().tempD = null;

                    if(getStageList().size() > getMaxStageNumber()) {
                        for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                            getStageList().remove(i);
                            i--;
                        }
                    }

                    getStageList().add(getStage().clone());
                    setStageNumber(getStageNumber() + 1);
                    setMaxStageNumber(getStageNumber() + 1);
                    
                }
            }
            setDownStage();
        }
    }
    
    */
    
    public void removeTile(int x , int y)    {
        if(getStage().getTileTop(x, y) == -1) {
                
            }
            else {
                getStage().getTileList().remove(getStage().getTileTop(x, y));
                setPastState();
                //setDownStage();   
            }
    }
    
    public void selectTile(int x , int y, JTextField[] tf)    {
        /*
        if(getStage().getTileTop(e.getX(), e.getY()) == -1) {
                for(int i = 0; i < getStage().getTileList().size(); i++)    {
                    getStage().getTileList().get(i).setClicked(false);
                    setSelected(false);
                    setSelectNumber(-1);
                    rotationTextField.setText("0");
                }
            }
            else {
                if(getStage().getTileList().get(getStage().getTileTop(e.getX(),e.getY())).isClicked())  {
                    getStage().getTileList().get(getStage().getTileTop(e.getX(),e.getY())).setClicked(false);
                    setSelected(false);
                    setSelectNumber(-1);
                    rotationTextField.setText("0");
                }
                else {
                    for(int i = 0; i < getStage().getTileList().size(); i++)    {
                        getStage().getTileList().get(i).setClicked(false);
                        setSelectNumber(-1);
                    }
                    getStage().getTileList().get(getStage().getTileTop(e.getX(),e.getY())).setClicked(true);
                    setSelected(true);
                    setSelectNumber(getStage().getTileTop(e.getX(),e.getY()));
                    
                    switch (getStage().getTileList().get(getSelectNumber()).getShapeType()) {
                        case 1:
                            
                            break;
                        case 2:
                            TileEllipse2D te2D = (TileEllipse2D)getStage().getTileList().get(getSelectNumber());
                            rotationTextField.setText(String.valueOf(te2D.getTd().getRotateDegree()));
                            depthTextField.setText(String.valueOf(te2D.getTd().getDepth()));
                            textInput.setText(te2D.getTtd().getTextContext());
                            textLocalX.setText(String.valueOf(te2D.getTtd().getX()));
                            textLocalY.setText(String.valueOf(te2D.getTtd().getY()));
                            textSize.setText(String.valueOf(te2D.getTtd().getTextFont().getSize()));
                            
                            break;
                        case 3:
                            TileLine2D tl2D = (TileLine2D)getStage().getTileList().get(getSelectNumber());
                            rotationTextField.setText(String.valueOf(tl2D.getTd().getRotateDegree()));
                            depthTextField.setText(String.valueOf(tl2D.getTd().getDepth()));
                            break;
                        case 4:
                            TilePolygon2D tp2D = (TilePolygon2D)getStage().getTileList().get(getSelectNumber());
                            rotationTextField.setText(String.valueOf(tp2D.getTd().getRotateDegree()));
                            depthTextField.setText(String.valueOf(tp2D.getTd().getDepth()));
                            textInput.setText(tp2D.getTtd().getTextContext());
                            textLocalX.setText(String.valueOf(tp2D.getTtd().getX()));
                            textLocalY.setText(String.valueOf(tp2D.getTtd().getY()));
                            textSize.setText(String.valueOf(tp2D.getTtd().getTextFont().getSize()));
                            break;
                        case 5:
                            TileRectangle2D tr2D = (TileRectangle2D)getStage().getTileList().get(getSelectNumber());
                            rotationTextField.setText(String.valueOf(tr2D.getTd().getRotateDegree()));
                            depthTextField.setText(String.valueOf(tr2D.getTd().getDepth()));
                            textInput.setText(tr2D.getTtd().getTextContext());
                            textLocalX.setText(String.valueOf(tr2D.getTtd().getX()));
                            textLocalY.setText(String.valueOf(tr2D.getTtd().getY()));
                            textSize.setText(String.valueOf(tr2D.getTtd().getTextFont().getSize()));
                            break;
                        case 6:
                            TileRoundRectangle2D trr2D = (TileRoundRectangle2D)getStage().getTileList().get(getSelectNumber());
                            rotationTextField.setText(String.valueOf(trr2D.getTd().getRotateDegree()));
                            depthTextField.setText(String.valueOf(trr2D.getTd().getDepth()));
                            textInput.setText(trr2D.getTtd().getTextContext());
                            textLocalX.setText(String.valueOf(trr2D.getTtd().getX()));
                            textLocalY.setText(String.valueOf(trr2D.getTtd().getY()));
                            textSize.setText(String.valueOf(trr2D.getTtd().getTextFont().getSize()));
                            break;
                        case 7:
                            TilePolygon2D tpg2D = (TilePolygon2D)getStage().getTileList().get(getSelectNumber());
                            rotationTextField.setText(String.valueOf(tpg2D.getTd().getRotateDegree()));
                            depthTextField.setText(String.valueOf(tpg2D.getTd().getDepth()));
                            textInput.setText(tpg2D.getTtd().getTextContext());
                            textLocalX.setText(String.valueOf(tpg2D.getTtd().getX()));
                            textLocalY.setText(String.valueOf(tpg2D.getTtd().getY()));
                            textSize.setText(String.valueOf(tpg2D.getTtd().getTextFont().getSize()));
                            break;
                        case 8:
                            TileImage2D ti2D = (TileImage2D)getStage().getTileList().get(getSelectNumber());
                            rotationTextField.setText(String.valueOf(ti2D.getTd().getRotateDegree()));
                            depthTextField.setText(String.valueOf(ti2D.getTd().getDepth()));
                            break;
                        default:
                            break;
                    }
                }
            }
        */
        if(getStage().getTileTop(x, y) == -1) {
                for(int i = 0; i < getStage().getTileList().size(); i++)    {
                    getStage().getTileList().get(i).setClicked(false);
                    setSelected(false);
                    setSelectNumber(-1);
                    
                    
                    
        /*
        rotationTextField.setText(String.valueOf(te2D.getTd().getRotateDegree()));
        depthTextField.setText(String.valueOf(te2D.getTd().getDepth()));
        textInput.setText(te2D.getTtd().getTextContext());
        textLocalX.setText(String.valueOf(te2D.getTtd().getX()));
        textLocalY.setText(String.valueOf(te2D.getTtd().getY()));
        textSize.setText(String.valueOf(te2D.getTtd().getTextFont().getSize()));
                    
                    rotationTextField.setText("0");
        */
                }
            }
            else {
                if(getStage().getTileList().get(getStage().getTileTop(x,y)).isClicked())  {
                    getStage().getTileList().get(getStage().getTileTop(x,y)).setClicked(false);
                    setSelected(false);
                    setSelectNumber(-1);
                   // rotationTextField.setText("0");
                }
                else {
                    for(int i = 0; i < getStage().getTileList().size(); i++)    {
                        getStage().getTileList().get(i).setClicked(false);
                        setSelectNumber(-1);
                    }
                    getStage().getTileList().get(getStage().getTileTop(x,y)).setClicked(true);
                    setSelected(true);
                    setSelectNumber(getStage().getTileTop(x,y));
                    
                    switch (getStage().getTileList().get(getSelectNumber()).getShapeType()) {
                        case 1:
                            
                            break;
                        case 2:
                            TileEllipse2D te2D = (TileEllipse2D)getStage().getTileList().get(getSelectNumber());
                            
                            break;
                        case 3:
                            TileLine2D tl2D = (TileLine2D)getStage().getTileList().get(getSelectNumber());
                            break;
                        case 4:
                            TilePolygon2D tp2D = (TilePolygon2D)getStage().getTileList().get(getSelectNumber());
                            break;
                        case 5:
                            TileRectangle2D tr2D = (TileRectangle2D)getStage().getTileList().get(getSelectNumber());
                            break;
                        case 6:
                            TileRoundRectangle2D trr2D = (TileRoundRectangle2D)getStage().getTileList().get(getSelectNumber());
                            break;
                        case 7:
                            TilePolygon2D tpg2D = (TilePolygon2D)getStage().getTileList().get(getSelectNumber());
                            break;
                        case 8:
                            TileImage2D ti2D = (TileImage2D)getStage().getTileList().get(getSelectNumber());
                            break;
                        default:
                            break;
                    }
                }
            }
    }
    
    public void setTextFields( JTextField[] tf, String[] str )  {
        for(int i = 0; i < tf.length; i++)  {
            tf[i].setText(str[i]);
        }
    }
    
    
    
    
    
    public void setPastState() {
        getStage().tempD = null;
        
        if(getStageList().size() > getMaxStageNumber()) {
            for(int i = getMaxStageNumber(); i < getStageList().size(); i++)   {
                getStageList().remove(i);
                i--;
            }
        }
        
        getStageList().add(getStage().clone());
        setStageNumber(getStageNumber() + 1);
        setMaxStageNumber(getStageNumber() + 1);
    }
   
    
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Stage getStage() {
        return this.stage;
    }
    public boolean getSelectState() {
        return this.selectState;
    }
    public boolean getRemoveState() {
        return this.removeState;
    }
    public boolean getEraserState() {
        return this.eraserState;
    }
    public void setSelectState(boolean selectState)    {
        this.selectState = selectState;
    }
    public void setRemoveState(boolean removeState)    {
        this.removeState = removeState;
    }
    public void setEraserState(boolean eraserState)    {
        this.eraserState = eraserState;
    }
    public void setSelectNumber(int selcetNumber)   {
        this.selcetNumber = selcetNumber;
    }
    public int getSelectNumber()    {
        return selcetNumber;
    }
    public boolean isSelected() {
        return this.selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public Drawable getTempShape()  {
        return this.tempShape;
    }
    public void setTempShape(Drawable tempShape)    {
        this.tempShape = tempShape;
    }
    public boolean isPolygonState() {
        return this.polygonState;
    }
    public void setPolygonState(boolean polygonState)   {
        this.polygonState = polygonState;
    }
    public ArrayList<Stage> getStageList() {
        return this.stageList;
    }
    public void setStageList(ArrayList<Stage> stageList)    {
        this.stageList = stageList;
    }
    public int getStageNumber() {
        return this.stageNumber;
    }
    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }
    public int getMaxStageNumber() {
        return this.maxStageNumber;
    }
    public void setMaxStageNumber(int maxStageNumber) {
        this.maxStageNumber = maxStageNumber;
    }
    public void setAnimationList( ArrayList<ArrayList<Stage>> animationList )  {
        this.animationList = animationList;
    }
    public ArrayList<ArrayList<Stage>> getAnimationList()   {
        return this.animationList;
    }
    public int getAnimationStageNumber ()  {
        return this.animationStageNumber;
    }
    public int getAnimationMaxStageNumber ()  {
        return this.animationMaxStageNumber;
    }
    public void setAnimationStageNumber ( int animationStageNumber )  {
        this.animationStageNumber = animationStageNumber;
    }
    public void setAnimationMaxStageNumber ( int animationMaxStageNumber )  {
        this.animationMaxStageNumber = animationMaxStageNumber;
    }
    public void setBufferedImage( BufferedImage bufferedImage )  {
        this.bufferedImage = bufferedImage;
    }
    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }
    public boolean isImageSelected()   {
        return this.imageSlected;
    }
    public void setImageSelected(boolean imageSlected)  {
        this.imageSlected = imageSlected;
    }
    public void setLineDiameter(int lineDiameter)   {
        this.lineDiameter = lineDiameter;
    }
    public int getLineDiameter()    {
        return this.lineDiameter;
    }
    public void setBorderColor(Color borderColor)    {
        this.borderColor = borderColor;
    }
    public void setFillingColor(Color fillingColor)    {
        this.fillingColor = fillingColor;
    }
    public Color getBorderColor()    {
        return this.borderColor;
    }
    public Color getFillingColor()    {
        return this.fillingColor;
    }
}
