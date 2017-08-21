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
import java.util.ArrayList;
import javax.swing.JPanel;
import tile.TileImage2D;

/**
 *
 * @author chj87
 */
public class AnimationVeiwer extends JPanel{
    private ArrayList<ArrayList<Drawable>> specifiedTile;
    private ArrayList<Stage> stageList = new ArrayList<Stage>();
    private boolean rotation, stopped;
    private int nowVeiwNumber;
    private int maxVeiwNumber;
    
    public AnimationVeiwer()    {
        
    }
    public AnimationVeiwer(ArrayList<ArrayList<Drawable>> animationList, boolean rt)    {
        
        setRotation(true);
        setStopped(true);
        maxVeiwNumber = animationList.size();
        this.specifiedTile = animationList;
    }
    public AnimationVeiwer(ArrayList<ArrayList<Stage>> animationList)    {
        
        setRotation(true);
        setStopped(true);
        
        for(int i = 0; i < animationList.size(); i++)   {
            stageList.add(animationList.get(i).get(animationList.get(i).size() - 1));
        }
        
        maxVeiwNumber = 10 * (stageList.size() - 1) + 1;
        
        this.specifiedTile = makeSpecifiedTile();
    }
    
    public ArrayList<ArrayList<Drawable>> getSpecifiedTile()  {
        return this.specifiedTile;
    }
    
    public void setSpecifiedTile(ArrayList<ArrayList<Drawable>> specifiedTile)  {
        this.specifiedTile = specifiedTile;
    }
    
    
    public void setNowViewNumber(int nowVeiwNumber)  {
        this.nowVeiwNumber = nowVeiwNumber;
    }
    public int getNowViewNumber()   {
        return this.nowVeiwNumber;
    }
    public void setMaxViewNumber(int maxVeiwNumber) {
        this.maxVeiwNumber = maxVeiwNumber;
    }
    public int getMaxViewNumber()   {
        return this.maxVeiwNumber;
    }
    public void setRotation( boolean rotation ) {
        this.rotation = rotation;
    }
    public boolean isRotation() {
        return this.rotation;
    }
    public void setStopped( boolean stopped ) {
        this.stopped = stopped;
    }
    public boolean isStopped()  {
        return this.stopped;
    }
    public ArrayList<ArrayList<Drawable>> makeSpecifiedTile()    {
        ArrayList<ArrayList<Drawable>> specifiedTileList = new ArrayList<ArrayList<Drawable>>();
        
        for(int i = 0 ; i < maxVeiwNumber ; i++)  {
            specifiedTileList.add(new ArrayList<Drawable>());
        }
        
        for(int i = 0 ; i < stageList.size() - 1; i++)  {
            ArrayList<Drawable> oldTileList = stageList.get(i).getTileList();
            ArrayList<Drawable> newTileList = stageList.get(i + 1).getTileList();
            
            for(int k = 0; k < oldTileList.size(); k++) {
                int changedX, changedY, changedR;
                int changedMX, changedMY;
                int changedBorderR, changedBorderG, changedBorderB;
                int changedFillingR, changedFillingG, changedFillingB;
                switch(oldTileList.get(k).getShapeType()) {
                    case 2 : // TileEllipse2D
                        // minX , minY ' s change amount can be the indicator of specifed stage.
                        // rotate ' s change amount will be accessible with no specifed value
                        TileEllipse2D oldTe2D = (TileEllipse2D)oldTileList.get(k);
                        TileEllipse2D newTe2D = (TileEllipse2D)newTileList.get(k);
                        
                        changedX = (int)(newTe2D.getMinX() - oldTe2D.getMinX());
                        changedY = (int)(newTe2D.getMinY() - oldTe2D.getMinY());
                        changedR = (int)(newTe2D.getTd().getRotateDegree() - oldTe2D.getTd().getRotateDegree());
                        changedMX = (int)(newTe2D.getMaxX() - oldTe2D.getMaxX());
                        changedMY = (int)(newTe2D.getMaxY() - oldTe2D.getMaxY());
                        
                        changedX = (int)((double)changedX / 10);
                        changedY = (int)((double)changedY / 10);
                        changedR = (int)((double)changedR / 10);
                        changedMX = (int)((double)changedMX / 10);
                        changedMY = (int)((double)changedMY / 10);
                        
                        changedBorderR = newTe2D.getTd().getBorderColor().getRed() - oldTe2D.getTd().getBorderColor().getRed();
                        changedFillingR = newTe2D.getTd().getFillingColor().getRed() - oldTe2D.getTd().getFillingColor().getRed();
                        
                        changedBorderR = (int)((double)changedBorderR/10);
                        changedFillingR = (int)((double)changedFillingR/10);
                        
                        changedBorderG = newTe2D.getTd().getBorderColor().getGreen() - oldTe2D.getTd().getBorderColor().getGreen();
                        changedFillingG = newTe2D.getTd().getFillingColor().getGreen() - oldTe2D.getTd().getFillingColor().getGreen();
                        
                        changedBorderG = (int)((double)changedBorderG/10);
                        changedFillingG = (int)((double)changedFillingG/10);
                        
                        changedBorderB = newTe2D.getTd().getBorderColor().getBlue() - oldTe2D.getTd().getBorderColor().getBlue();
                        changedFillingB = newTe2D.getTd().getFillingColor().getBlue() - oldTe2D.getTd().getFillingColor().getBlue();
                        
                        changedBorderB = (int)((double)changedBorderB/10);
                        changedFillingB = (int)((double)changedFillingB/10);
                        
                        for(int j = 0; j < 10; j++) {
                            specifiedTileList.get(i * 10 + j).add(new TileEllipse2D((int)oldTe2D.getMinX() + changedX * j, (int)oldTe2D.getMinY() + changedY * j, (int)oldTe2D.getMaxX() + changedMX * j, (int)oldTe2D.getMaxY() + changedMY * j, (int)oldTe2D.getTd().getRotateDegree() + changedR * j, oldTe2D.getTd().getDepth(), oldTe2D.getTtd().getTextContext(), oldTe2D.getTtd().getX() + changedX * j, oldTe2D.getTtd().getY() + changedY * j, oldTe2D.getTtd().getTextFont().getFontName(), oldTe2D.getTtd().getTextFont().getStyle(),oldTe2D. getTtd().getTextFont().getSize(), oldTe2D.getTtd().getTextColor() , new Color(oldTe2D.getTd().getBorderColor().getRed() + changedBorderR * j, oldTe2D.getTd().getBorderColor().getGreen() + changedBorderG * j , oldTe2D.getTd().getBorderColor().getBlue() + changedBorderB * j),  new Color(oldTe2D.getTd().getFillingColor().getRed() + changedFillingR * j, oldTe2D.getTd().getFillingColor().getGreen() + changedFillingG * j , oldTe2D.getTd().getFillingColor().getBlue() + changedFillingB * j )));
                        }
                        break;
                    case 3 : // TileLine2D
                        // x1 , y1 ' s change amount can be the indicator of specifed stage.
                        // rotate ' s change amount will be accessible with no specifed value
                        TileLine2D oldTl2D = (TileLine2D)oldTileList.get(k);
                        TileLine2D newTl2D = (TileLine2D)newTileList.get(k);
                        
                        changedX = (int)(newTl2D.getX1() - oldTl2D.getX1());
                        changedY = (int)(newTl2D.getY1() - oldTl2D.getY1());
                        changedR = (int)(newTl2D.getTd().getRotateDegree() - oldTl2D.getTd().getRotateDegree());
                        changedMX = (int)(newTl2D.getX2() - oldTl2D.getX2());
                        changedMY = (int)(newTl2D.getY2() - oldTl2D.getY2());
                        
                        changedX = (int)((double)changedX / 10);
                        changedY = (int)((double)changedY / 10);
                        changedR = (int)((double)changedR / 10);
                        changedMX = (int)((double)changedMX / 10);
                        changedMY = (int)((double)changedMY / 10);
                        
                        changedBorderR = newTl2D.getTd().getBorderColor().getRed() - oldTl2D.getTd().getBorderColor().getRed();
                        changedFillingR = newTl2D.getTd().getFillingColor().getRed() - oldTl2D.getTd().getFillingColor().getRed();
                        
                        changedBorderR = (int)((double)changedBorderR/10);
                        changedFillingR = (int)((double)changedFillingR/10);
                        
                        changedBorderG = newTl2D.getTd().getBorderColor().getGreen() - oldTl2D.getTd().getBorderColor().getGreen();
                        changedFillingG = newTl2D.getTd().getFillingColor().getGreen() - oldTl2D.getTd().getFillingColor().getGreen();
                        
                        changedBorderG = (int)((double)changedBorderG/10);
                        changedFillingG = (int)((double)changedFillingG/10);
                        
                        changedBorderB = newTl2D.getTd().getBorderColor().getBlue() - oldTl2D.getTd().getBorderColor().getBlue();
                        changedFillingB = newTl2D.getTd().getFillingColor().getBlue() - oldTl2D.getTd().getFillingColor().getBlue();
                        
                        changedBorderB = (int)((double)changedBorderB/10);
                        changedFillingB = (int)((double)changedFillingB/10);
                        
                        for(int j = 0; j < 10; j++) {
                            specifiedTileList.get(i * 10 + j).add(new TileLine2D((int)oldTl2D.getX1() + changedX * j, (int)oldTl2D.getY1() + changedY * j, (int)oldTl2D.getX2() + changedMX * j, (int)oldTl2D.getY2() + changedMY * j, oldTl2D.getTd().getRotateDegree() + changedR * j, oldTl2D.getTd().getDepth(), new Color(oldTl2D.getTd().getBorderColor().getRed() + changedBorderR * j, oldTl2D.getTd().getBorderColor().getGreen() + changedBorderG * j , oldTl2D.getTd().getBorderColor().getBlue() + changedBorderB * j)));
                        }
                        break;
                    case 4 : // TilePolygon2D
                        // xpoints[0] , ypoints[0] ' s change amount can be the indicator of specifed stage.
                        // rotate ' s change amount will be accessible with no specifed value
                        
                        TilePolygon2D oldTp2D = (TilePolygon2D)oldTileList.get(k);
                        TilePolygon2D newTp2D = (TilePolygon2D)newTileList.get(k);
                        
                        changedX = (int)(newTp2D.getXpoint()[0] - oldTp2D.getXpoint()[0]);
                        changedY = (int)(newTp2D.getYpoint()[0] - oldTp2D.getYpoint()[0]);
                        changedR = (int)(newTp2D.getTd().getRotateDegree() - oldTp2D.getTd().getRotateDegree());
                        
                        changedX = (int)((double)changedX / 10);
                        changedY = (int)((double)changedY / 10);
                        changedR = (int)((double)changedR / 10);
                        
                        changedBorderR = newTp2D.getTd().getBorderColor().getRed() - oldTp2D.getTd().getBorderColor().getRed();
                        changedFillingR = newTp2D.getTd().getFillingColor().getRed() - oldTp2D.getTd().getFillingColor().getRed();
                        
                        changedBorderR = (int)((double)changedBorderR/10);
                        changedFillingR = (int)((double)changedFillingR/10);
                        
                        changedBorderG = newTp2D.getTd().getBorderColor().getGreen() - oldTp2D.getTd().getBorderColor().getGreen();
                        changedFillingG = newTp2D.getTd().getFillingColor().getGreen() - oldTp2D.getTd().getFillingColor().getGreen();
                        
                        changedBorderG = (int)((double)changedBorderG/10);
                        changedFillingG = (int)((double)changedFillingG/10);
                        
                        changedBorderB = newTp2D.getTd().getBorderColor().getBlue() - oldTp2D.getTd().getBorderColor().getBlue();
                        changedFillingB = newTp2D.getTd().getFillingColor().getBlue() - oldTp2D.getTd().getFillingColor().getBlue();
                        
                        changedBorderB = (int)((double)changedBorderB/10);
                        changedFillingB = (int)((double)changedFillingB/10);
                        
                        for(int j = 0; j < 10; j++) {
                            int[] tempX = new int[oldTp2D.getXpoint().length];
                            int[] tempY = new int[oldTp2D.getYpoint().length];
                            for(int o = 0; o < oldTp2D.getXpoint().length; o++) {
                                tempX[o] = oldTp2D.getXpoint()[o] + changedX * j;
                                tempY[o] = oldTp2D.getYpoint()[o] + changedY * j;
                            }
                            specifiedTileList.get(i * 10 + j).add(new TilePolygon2D(tempX, tempY, oldTp2D.getXpoint().length, oldTp2D.isComplete(), oldTp2D.getTd().getRotateDegree() + changedR * j , oldTp2D.getTd().getDepth() , oldTp2D.getTtd().getTextContext(), oldTp2D.getTtd().getX() + changedX * j, oldTp2D.getTtd().getY() + changedY * j, oldTp2D.getTtd().getTextFont().getFontName(),oldTp2D.getTtd().getTextFont().getStyle() , oldTp2D.getTtd().getTextFont().getSize(), oldTp2D.getTtd().getTextColor() , new Color(oldTp2D.getTd().getBorderColor().getRed() + changedBorderR * j, oldTp2D.getTd().getBorderColor().getGreen() + changedBorderG * j , oldTp2D.getTd().getBorderColor().getBlue() + changedBorderB * j),  new Color(oldTp2D.getTd().getFillingColor().getRed() + changedFillingR * j, oldTp2D.getTd().getFillingColor().getGreen() + changedFillingG * j , oldTp2D.getTd().getFillingColor().getBlue() + changedFillingB * j )));
                        }
                        break;
                    case 5 : // TileRectangle2D
                        // leftTopX, leftTopY ' s change amount can be the indicator of specifed stage.
                        // rotate ' s change amount will be accessible with no specifed value
                        TileRectangle2D oldTr2D = (TileRectangle2D)oldTileList.get(k);
                        TileRectangle2D newTr2D = (TileRectangle2D)newTileList.get(k);
                        
                        changedX = (int)(newTr2D.getLeftTopX() - oldTr2D.getLeftTopX());
                        changedY = (int)(newTr2D.getLeftTopY() - oldTr2D.getLeftTopY());
                        changedR = (int)(newTr2D.getTd().getRotateDegree() - oldTr2D.getTd().getRotateDegree());
                        changedMX = (int)(newTr2D.getRightBottomX() - oldTr2D.getRightBottomX());
                        changedMY = (int)(newTr2D.getRightBottomY() - oldTr2D.getRightBottomY());
                        
                        changedX = (int)((double)changedX / 10);
                        changedY = (int)((double)changedY / 10);
                        changedR = (int)((double)changedR / 10);
                        changedMX = (int)((double)changedMX / 10);
                        changedMY = (int)((double)changedMY / 10);
                        
                        changedBorderR = newTr2D.getTd().getBorderColor().getRed() - oldTr2D.getTd().getBorderColor().getRed();
                        changedFillingR = newTr2D.getTd().getFillingColor().getRed() - oldTr2D.getTd().getFillingColor().getRed();
                        
                        changedBorderR = (int)((double)changedBorderR/10);
                        changedFillingR = (int)((double)changedFillingR/10);
                        
                        changedBorderG = newTr2D.getTd().getBorderColor().getGreen() - oldTr2D.getTd().getBorderColor().getGreen();
                        changedFillingG = newTr2D.getTd().getFillingColor().getGreen() - oldTr2D.getTd().getFillingColor().getGreen();
                        
                        changedBorderG = (int)((double)changedBorderG/10);
                        changedFillingG = (int)((double)changedFillingG/10);
                        
                        changedBorderB = newTr2D.getTd().getBorderColor().getBlue() - oldTr2D.getTd().getBorderColor().getBlue();
                        changedFillingB = newTr2D.getTd().getFillingColor().getBlue() - oldTr2D.getTd().getFillingColor().getBlue();
                        
                        changedBorderB = (int)((double)changedBorderB/10);
                        changedFillingB = (int)((double)changedFillingB/10);
                        
                        for(int j = 0; j < 10; j++) {
                            specifiedTileList.get(i * 10 + j).add(new TileRectangle2D((int) oldTr2D.getLeftTopX() + changedX * j, (int)oldTr2D.getLeftTopY() + changedY * j, (int)oldTr2D.getRightBottomX() + changedMX * j, (int)oldTr2D.getRightBottomY() + changedMY * j, oldTr2D.getTd().getRotateDegree() + changedR * j ,oldTr2D.getTd().getDepth() , oldTr2D.getTtd().getTextContext() , oldTr2D.getTtd().getX() + changedX * j , oldTr2D.getTtd().getY() + changedY * j, oldTr2D.getTtd().getTextFont().getFontName() , oldTr2D.getTtd().getTextFont().getStyle() , oldTr2D.getTtd().getTextFont().getSize(), oldTr2D.getTtd().getTextColor(),  new Color(oldTr2D.getTd().getBorderColor().getRed() + changedBorderR * j, oldTr2D.getTd().getBorderColor().getGreen() + changedBorderG * j , oldTr2D.getTd().getBorderColor().getBlue() + changedBorderB * j),  new Color(oldTr2D.getTd().getFillingColor().getRed() + changedFillingR * j, oldTr2D.getTd().getFillingColor().getGreen() + changedFillingG * j , oldTr2D.getTd().getFillingColor().getBlue() + changedFillingB * j )));
                        }
                        break;
                    case 6 : // TileRoundRectangle2D
                        // leftTopX, leftTopY ' s change amount can be the indicator of specifed stage.
                        // rotate ' s change amount will be accessible with no specifed value
                        TileRoundRectangle2D oldTrr2D = (TileRoundRectangle2D)oldTileList.get(k);
                        TileRoundRectangle2D newTrr2D = (TileRoundRectangle2D)newTileList.get(k);
                        
                        changedX = (int)(newTrr2D.getLeftTopX() - oldTrr2D.getLeftTopX());
                        changedY = (int)(newTrr2D.getLeftTopY() - oldTrr2D.getLeftTopY());
                        changedR = (int)(newTrr2D.getTd().getRotateDegree() - oldTrr2D.getTd().getRotateDegree());
                        changedMX = (int)(newTrr2D.getRightBottomX() - oldTrr2D.getRightBottomX());
                        changedMY = (int)(newTrr2D.getRightBottomY() - oldTrr2D.getRightBottomY());
                        
                        changedX = (int)((double)changedX / 10);
                        changedY = (int)((double)changedY / 10);
                        changedR = (int)((double)changedR / 10);
                        changedMX = (int)((double)changedMX / 10);
                        changedMY = (int)((double)changedMY / 10);
                        
                        changedBorderR = newTrr2D.getTd().getBorderColor().getRed() - oldTrr2D.getTd().getBorderColor().getRed();
                        changedFillingR = newTrr2D.getTd().getFillingColor().getRed() - oldTrr2D.getTd().getFillingColor().getRed();
                        
                        changedBorderR = (int)((double)changedBorderR/10);
                        changedFillingR = (int)((double)changedFillingR/10);
                        
                        changedBorderG = newTrr2D.getTd().getBorderColor().getGreen() - oldTrr2D.getTd().getBorderColor().getGreen();
                        changedFillingG = newTrr2D.getTd().getFillingColor().getGreen() - oldTrr2D.getTd().getFillingColor().getGreen();
                        
                        changedBorderG = (int)((double)changedBorderG/10);
                        changedFillingG = (int)((double)changedFillingG/10);
                        
                        changedBorderB = newTrr2D.getTd().getBorderColor().getBlue() - oldTrr2D.getTd().getBorderColor().getBlue();
                        changedFillingB = newTrr2D.getTd().getFillingColor().getBlue() - oldTrr2D.getTd().getFillingColor().getBlue();
                        
                        changedBorderB = (int)((double)changedBorderB/10);
                        changedFillingB = (int)((double)changedFillingB/10);
                        
                        for(int j = 0; j < 10; j++) {
                            specifiedTileList.get(i * 10 + j).add(new TileRoundRectangle2D((int) oldTrr2D.getLeftTopX() + changedX * j, (int)oldTrr2D.getLeftTopY() + changedY * j, (int)oldTrr2D.getRightBottomX() + changedMX * j, (int)oldTrr2D.getRightBottomY() + changedMY * j, (int)oldTrr2D.getArcWidth(), (int)oldTrr2D.getArcHeight() , oldTrr2D.getTd().getRotateDegree() + changedR * j ,oldTrr2D.getTd().getDepth() , oldTrr2D.getTtd().getTextContext() , oldTrr2D.getTtd().getX() + changedX * j , oldTrr2D.getTtd().getY() + changedY * j, oldTrr2D.getTtd().getTextFont().getFontName() , oldTrr2D.getTtd().getTextFont().getStyle() , oldTrr2D.getTtd().getTextFont().getSize(), oldTrr2D.getTtd().getTextColor(),  new Color(oldTrr2D.getTd().getBorderColor().getRed() + changedBorderR * j, oldTrr2D.getTd().getBorderColor().getGreen() + changedBorderG * j , oldTrr2D.getTd().getBorderColor().getBlue() + changedBorderB * j),  new Color(oldTrr2D.getTd().getFillingColor().getRed() + changedFillingR * j, oldTrr2D.getTd().getFillingColor().getGreen() + changedFillingG * j , oldTrr2D.getTd().getFillingColor().getBlue() + changedFillingB * j )));
                        }
                        break;
                    case 7 : // TilePolygon2D
                        
                        // xpoints[0] , ypoints[0] ' s change amount can be the indicator of specifed stage.
                        // rotate ' s change amount will be accessible with no specifed value
                        TilePolygon2D oldTpp2D = (TilePolygon2D)oldTileList.get(k);
                        TilePolygon2D newTpp2D = (TilePolygon2D)newTileList.get(k);
                        
                        changedX = (int)(newTpp2D.getXpoint()[0] - oldTpp2D.getXpoint()[0]);
                        changedY = (int)(newTpp2D.getYpoint()[0] - oldTpp2D.getYpoint()[0]);
                        changedR = (int)(newTpp2D.getTd().getRotateDegree() - oldTpp2D.getTd().getRotateDegree());
                        
                        changedX = (int)((double)changedX / 10);
                        changedY = (int)((double)changedY / 10);
                        changedR = (int)((double)changedR / 10);
                        
                        changedBorderR = newTpp2D.getTd().getBorderColor().getRed() - oldTpp2D.getTd().getBorderColor().getRed();
                        changedFillingR = newTpp2D.getTd().getFillingColor().getRed() - oldTpp2D.getTd().getFillingColor().getRed();
                        
                        changedBorderR = (int)((double)changedBorderR/10);
                        changedFillingR = (int)((double)changedFillingR/10);
                        
                        changedBorderG = newTpp2D.getTd().getBorderColor().getGreen() - oldTpp2D.getTd().getBorderColor().getGreen();
                        changedFillingG = newTpp2D.getTd().getFillingColor().getGreen() - oldTpp2D.getTd().getFillingColor().getGreen();
                        
                        changedBorderG = (int)((double)changedBorderG/10);
                        changedFillingG = (int)((double)changedFillingG/10);
                        
                        changedBorderB = newTpp2D.getTd().getBorderColor().getBlue() - oldTpp2D.getTd().getBorderColor().getBlue();
                        changedFillingB = newTpp2D.getTd().getFillingColor().getBlue() - oldTpp2D.getTd().getFillingColor().getBlue();
                        
                        changedBorderB = (int)((double)changedBorderB/10);
                        changedFillingB = (int)((double)changedFillingB/10);
                        
                        for(int j = 0; j < 10; j++) {
                            int[] tempX = new int[oldTpp2D.getXpoint().length];
                            int[] tempY = new int[oldTpp2D.getYpoint().length];
                            for(int o = 0; o < oldTpp2D.getXpoint().length; o++) {
                                tempX[o] = oldTpp2D.getXpoint()[o] + changedX * j;
                                tempY[o] = oldTpp2D.getYpoint()[o] + changedY * j;
                            }
                            specifiedTileList.get(i * 10 + j).add(new TilePolygon2D(tempX, tempY, oldTpp2D.getXpoint().length, oldTpp2D.isComplete(), oldTpp2D.getTd().getRotateDegree() + changedR * j , oldTpp2D.getTd().getDepth() , oldTpp2D.getTtd().getTextContext(), oldTpp2D.getTtd().getX() + changedX * j, oldTpp2D.getTtd().getY() + changedY * j, oldTpp2D.getTtd().getTextFont().getFontName(),oldTpp2D.getTtd().getTextFont().getStyle() , oldTpp2D.getTtd().getTextFont().getSize(), oldTpp2D.getTtd().getTextColor() , new Color(oldTpp2D.getTd().getBorderColor().getRed() + changedBorderR * j, oldTpp2D.getTd().getBorderColor().getGreen() + changedBorderG * j , oldTpp2D.getTd().getBorderColor().getBlue() + changedBorderB * j),  new Color(oldTpp2D.getTd().getFillingColor().getRed() + changedFillingR * j, oldTpp2D.getTd().getFillingColor().getGreen() + changedFillingG * j , oldTpp2D.getTd().getFillingColor().getBlue() + changedFillingB * j )));
                        }
                        break;
                    case 8 : // TileImage2D
                        
                        TileImage2D oldTi2D = (TileImage2D)oldTileList.get(k);
                        TileImage2D newTi2D = (TileImage2D)newTileList.get(k);
                        
                        changedX = (int)(newTi2D.getX1() - oldTi2D.getX1());
                        changedY = (int)(newTi2D.getY1() - oldTi2D.getY1());
                        changedR = (int)(newTi2D.getTd().getRotateDegree() - oldTi2D.getTd().getRotateDegree());
                        changedMX = (int)(newTi2D.getX2() - oldTi2D.getX2());
                        changedMY = (int)(newTi2D.getY2() - oldTi2D.getY2());
                        
                        changedX = (int)((double)changedX / 10);
                        changedY = (int)((double)changedY / 10);
                        changedR = (int)((double)changedR / 10);
                        changedMX = (int)((double)changedMX / 10);
                        changedMY = (int)((double)changedMY / 10);
                        
                        changedBorderR = newTi2D.getTd().getBorderColor().getRed() - oldTi2D.getTd().getBorderColor().getRed();
                        changedFillingR = newTi2D.getTd().getFillingColor().getRed() - oldTi2D.getTd().getFillingColor().getRed();
                        
                        changedBorderR = (int)((double)changedBorderR/10);
                        changedFillingR = (int)((double)changedFillingR/10);
                        
                        changedBorderG = newTi2D.getTd().getBorderColor().getGreen() - oldTi2D.getTd().getBorderColor().getGreen();
                        changedFillingG = newTi2D.getTd().getFillingColor().getGreen() - oldTi2D.getTd().getFillingColor().getGreen();
                        
                        changedBorderG = (int)((double)changedBorderG/10);
                        changedFillingG = (int)((double)changedFillingG/10);
                        
                        changedBorderB = newTi2D.getTd().getBorderColor().getBlue() - oldTi2D.getTd().getBorderColor().getBlue();
                        changedFillingB = newTi2D.getTd().getFillingColor().getBlue() - oldTi2D.getTd().getFillingColor().getBlue();
                        
                        changedBorderB = (int)((double)changedBorderB/10);
                        changedFillingB = (int)((double)changedFillingB/10);
                        
                        for(int j = 0; j < 10; j++) {
                            specifiedTileList.get(i * 10 + j).add(new TileImage2D((int)oldTi2D.getX1() + changedX * j, (int)oldTi2D.getY1() + changedY * j, (int)oldTi2D.getX2() + changedMX * j, (int)oldTi2D.getY2() + changedMY * j, oldTi2D.getTd().getRotateDegree() + changedR * j, oldTi2D.getTd().getDepth(), oldTi2D.getBufferedImage(),new Color(oldTi2D.getTd().getBorderColor().getRed() + changedBorderR * j, oldTi2D.getTd().getBorderColor().getGreen() + changedBorderG * j , oldTi2D.getTd().getBorderColor().getBlue() + changedBorderB * j ), new Color(oldTi2D.getTd().getFillingColor().getRed() + changedFillingR * j, oldTi2D.getTd().getFillingColor().getGreen() + changedFillingG * j , oldTi2D.getTd().getFillingColor().getBlue() + changedFillingB * j )));
                        }
                        break;
                    default :
                        break;
                }
            }
        }
        return specifiedTileList;
    }
    
    
    @Override
    public void paint(Graphics g)    {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1800,1200);
        for(int i = 0 ; i < specifiedTile.get(getNowViewNumber()).size(); i++)  {
            specifiedTile.get(getNowViewNumber()).get(i).draw(g);
        }
        setNowViewNumber(getNowViewNumber() + 1);
        
        if(isStopped()) {
            setNowViewNumber(getNowViewNumber() - 1);
        }
        else {
            if(isRotation())  {
                if(getNowViewNumber() == getMaxViewNumber())    {
                    setNowViewNumber(0);
                }
            }
            else {
                if(getNowViewNumber() == getMaxViewNumber())    {
                    setNowViewNumber(getNowViewNumber() - 1);
                }
            }
        }
    }
}
