/*
Team: Jives
Written by: Brian Leahy 17372896,
            Oscar Byrne Carty 17430786,
            Gearoid Lynch 17459176
 */

import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GamePiece extends JComponent {
    private int xCoordinate, yCoordinate;
    private int pipLocation;
    private final int DIAMETER=35;

    public int getPipLocation() {
        return pipLocation;
    }

    public void setPipLocation(int pipLocation) {
        this.pipLocation = pipLocation;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }
    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setXYCoordinate(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /*public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }*/

    //2 constructors allow to specify the location on creation or later on
    public GamePiece(Graphics g){
    }

    public GamePiece(Graphics g,int xCoordinate,int yCoordinate){
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
    }

    public void drawRedPiece(Graphics g){
        //recover Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        if(pipLocation!=25){
            Ellipse2D.Double piece = new Ellipse2D.Double(xCoordinate,yCoordinate,DIAMETER,DIAMETER);
            g2.setColor(Color.red);
            g2.fill(piece);
            g2.setColor(Color.black);
            g2.draw(piece);
        }else{
            //y coordinate is adjusted as the piece is now smaller, and doesn't reach the bottom of the box
            //the fact that Diameter is an int caused some problems here, this was the fastest, though not necessarily the best, solution
            Rectangle piece = new Rectangle(xCoordinate,yCoordinate + (DIAMETER*2)/3 + 1, DIAMETER, DIAMETER/3);
            g2.setColor(Color.red);
            g2.fill(piece);
            g2.setColor(Color.black);
            g2.draw(piece);
        }

    }

    public void drawBluePiece(Graphics g){
        //recover Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        if(pipLocation!=0){
            Ellipse2D.Double piece = new Ellipse2D.Double(xCoordinate,yCoordinate,DIAMETER,DIAMETER);
            g2.setColor(Color.BLUE);
            g2.fill(piece);
            g2.setColor(Color.black);
            g2.draw(piece);
        }else{
            Rectangle piece = new Rectangle(xCoordinate,yCoordinate, DIAMETER, DIAMETER/3);
            g2.setColor(Color.BLUE);
            g2.fill(piece);
            g2.setColor(Color.black);
            g2.draw(piece);
        }

    }

}
