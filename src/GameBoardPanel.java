import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.IOException;
import javax.imageio.ImageIO;


public class GameBoardPanel extends JPanel {


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle middleBox = new Rectangle(444, 0, 74, 627);
        g2.draw(middleBox);


        Rectangle topBox = new Rectangle(0, 0, 962, 50);
        g2.draw(topBox);

        //top left quadrant of the board
        g2.drawString("1", 37, 25);
        g2.drawString("2", 111, 25);
        g2.drawString("3", 185, 25);
        g2.drawString("4", 259, 25);
        g2.drawString("5", 333, 25);
        g2.drawString("6", 407, 25);

        Line2D.Double segment1 = new Line2D.Double(0, 50, 37, 250);
        g2.draw(segment1);
        Line2D.Double segment2 = new Line2D.Double(74, 50, 111, 250);
        g2.draw(segment2);
        Line2D.Double segment3 = new Line2D.Double(148, 50, 185, 250);
        g2.draw(segment3);
        Line2D.Double segment4 = new Line2D.Double(222, 50, 259, 250);
        g2.draw(segment4);
        Line2D.Double segment5 = new Line2D.Double(296, 50, 333, 250);
        g2.draw(segment5);
        Line2D.Double segment6 = new Line2D.Double(370, 50, 407, 250);
        g2.draw(segment6);

        Line2D.Double segment1_2 = new Line2D.Double(74, 50, 37, 250);
        g2.draw(segment1_2);
        Line2D.Double segment2_2 = new Line2D.Double(148, 50, 111, 250);
        g2.draw(segment2_2);
        Line2D.Double segment3_2 = new Line2D.Double(222, 50, 185, 250);
        g2.draw(segment3_2);
        Line2D.Double segment4_2 = new Line2D.Double(296, 50, 259, 250);
        g2.draw(segment4_2);
        Line2D.Double segment5_2 = new Line2D.Double(370, 50, 333, 250);
        g2.draw(segment5_2);
        Line2D.Double segment6_2 = new Line2D.Double(444, 50, 407, 250);
        g2.draw(segment6_2);

        //top right quadrant of the board
        g2.drawString("7", 555, 25);
        g2.drawString("8", 629, 25);
        g2.drawString("9", 703, 25);
        g2.drawString("10", 777, 25);
        g2.drawString("11", 851, 25);
        g2.drawString("12", 925, 25);

        Line2D.Double segment7 = new Line2D.Double(518, 50, 555, 250);
        g2.draw(segment7);
        Line2D.Double segment8 = new Line2D.Double(592, 50, 629, 250);
        g2.draw(segment8);
        Line2D.Double segment9 = new Line2D.Double(666, 50, 703, 250);
        g2.draw(segment9);
        Line2D.Double segment10 = new Line2D.Double(740, 50, 777, 250);
        g2.draw(segment10);
        Line2D.Double segment11 = new Line2D.Double(814, 50, 851, 250);
        g2.draw(segment11);
        Line2D.Double segment12 = new Line2D.Double(888, 50, 925, 250);
        g2.draw(segment12);

        Line2D.Double segment7_2 = new Line2D.Double(592, 50, 555, 250);
        g2.draw(segment7_2);
        Line2D.Double segment8_2 = new Line2D.Double(666, 50, 629, 250);
        g2.draw(segment8_2);
        Line2D.Double segment9_2 = new Line2D.Double(740, 50, 703, 250);
        g2.draw(segment9_2);
        Line2D.Double segment10_2 = new Line2D.Double(814, 50, 777, 250);
        g2.draw(segment10_2);
        Line2D.Double segment11_2 = new Line2D.Double(888, 50, 851, 250);
        g2.draw(segment11_2);
        Line2D.Double segment12_2 = new Line2D.Double(962, 50, 925, 250);
        g2.draw(segment12_2);

        Rectangle bottomBox = new Rectangle(0, 577, 962, 50);
        g2.draw(bottomBox);

        g2.drawString("13", 37, 602);
        g2.drawString("14", 111, 602);
        g2.drawString("15", 185, 602);
        g2.drawString("16", 259, 602);
        g2.drawString("17", 333, 602);
        g2.drawString("18", 407, 602);

        Line2D.Double segment13 = new Line2D.Double(0, 577, 37, 377);
        g2.draw(segment13);
        Line2D.Double segment14 = new Line2D.Double(74, 577, 111, 377);
        g2.draw(segment14);
        Line2D.Double segment15 = new Line2D.Double(148, 577, 185, 377);
        g2.draw(segment15);
        Line2D.Double segment16 = new Line2D.Double(222, 577, 259, 377);
        g2.draw(segment16);
        Line2D.Double segment17 = new Line2D.Double(296, 577, 333, 377);
        g2.draw(segment17);
        Line2D.Double segment18 = new Line2D.Double(370, 577, 407, 377);
        g2.draw(segment18);


    }









}