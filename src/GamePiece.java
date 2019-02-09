import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GamePiece extends JComponent {
    private int xCoordinate, yCoordinate;
    private final int DIAMETER=35;

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


    /*public void drawRedPiece(Graphics g,int xCoordinate,int yCoordinate){
        //recover Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double piece = new Ellipse2D.Double(xCoordinate,yCoordinate,DIAMETER,DIAMETER);
        g2.setColor(Color.red);
        g2.fill(piece);
        g2.setColor(Color.black);
        g2.draw(piece);
    }*/
    public void drawRedPiece(Graphics g){
        //recover Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double piece = new Ellipse2D.Double(xCoordinate,yCoordinate,DIAMETER,DIAMETER);
        g2.setColor(Color.red);
        g2.fill(piece);
        g2.setColor(Color.black);
        g2.draw(piece);
    }

    /*public void drawBluePiece(Graphics g,int xCoordinate,int yCoordinate){
        //recover Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double piece = new Ellipse2D.Double(xCoordinate,yCoordinate,DIAMETER,DIAMETER);
        g2.setColor(Color.BLUE);
        g2.fill(piece);
        g2.setColor(Color.black);
        g2.draw(piece);
    }*/
    public void drawBluePiece(Graphics g){
        //recover Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double piece = new Ellipse2D.Double(xCoordinate,yCoordinate,DIAMETER,DIAMETER);
        g2.setColor(Color.BLUE);
        g2.fill(piece);
        g2.setColor(Color.black);
        g2.draw(piece);
    }


}
