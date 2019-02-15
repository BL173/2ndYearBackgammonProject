import java.awt.*;
//import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;
//import java.awt.image.BufferedImage;
import javax.swing.*;
//import java.io.IOException;
//import javax.imageio.ImageIO;


public class GameBoardPanel extends JPanel {

    private GamePiece redPlayerGamePieces[]= new GamePiece[15];
    private GamePiece bluePlayerGamePieces[]= new GamePiece[15];
    private int pointLocationOrderedCounterClockwise[][]=new int[26][2];
    private int numberOfRedPiecesOnPoint[]=new int[26];
    private int numberOfBluePiecesOnPoint[]=new int[26];
    private final int PIECE_DIAMETER=35;
    private boolean newGame =true;
    private int testMoveCounter=0;
    private DefaultUserInputModel userInputModel;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle middleBox = new Rectangle(444, 0, 74, 627);
        g2.draw(middleBox);

        if(newGame==true){
            initialiseGameBoard(g, redPlayerGamePieces,bluePlayerGamePieces);
            newGame = false;
        }


        Rectangle topBox = new Rectangle(0, 0, 962, 50);
        g2.draw(topBox);

        //top left quadrant of the board
        g2.drawString("12", 37, 25);
        g2.drawString("11", 111, 25);
        g2.drawString("10", 185, 25);
        g2.drawString("9", 259, 25);
        g2.drawString("8", 333, 25);
        g2.drawString("7", 407, 25);

        Line2D.Double segment1 = new Line2D.Double(0, 50, 37, 275);
        g2.draw(segment1);
        Line2D.Double segment2 = new Line2D.Double(74, 50, 111, 275);
        g2.draw(segment2);
        Line2D.Double segment3 = new Line2D.Double(148, 50, 185, 275);
        g2.draw(segment3);
        Line2D.Double segment4 = new Line2D.Double(222, 50, 259, 275);
        g2.draw(segment4);
        Line2D.Double segment5 = new Line2D.Double(296, 50, 333, 275);
        g2.draw(segment5);
        Line2D.Double segment6 = new Line2D.Double(370, 50, 407, 275);
        g2.draw(segment6);

        Line2D.Double segment1_2 = new Line2D.Double(74, 50, 37, 275);
        g2.draw(segment1_2);
        Line2D.Double segment2_2 = new Line2D.Double(148, 50, 111, 275);
        g2.draw(segment2_2);
        Line2D.Double segment3_2 = new Line2D.Double(222, 50, 185, 275);
        g2.draw(segment3_2);
        Line2D.Double segment4_2 = new Line2D.Double(296, 50, 259, 275);
        g2.draw(segment4_2);
        Line2D.Double segment5_2 = new Line2D.Double(370, 50, 333, 275);
        g2.draw(segment5_2);
        Line2D.Double segment6_2 = new Line2D.Double(444, 50, 407, 275);
        g2.draw(segment6_2);

        //top right quadrant of the board
        g2.drawString("6", 555, 25);
        g2.drawString("5", 629, 25);
        g2.drawString("4", 703, 25);
        g2.drawString("3", 768, 25);
        g2.drawString("2", 842, 25);
        g2.drawString("1", 916, 25);

        Line2D.Double segment7 = new Line2D.Double(518, 50, 555, 275);
        g2.draw(segment7);
        Line2D.Double segment8 = new Line2D.Double(592, 50, 629, 275);
        g2.draw(segment8);
        Line2D.Double segment9 = new Line2D.Double(666, 50, 703, 275);
        g2.draw(segment9);
        Line2D.Double segment10 = new Line2D.Double(740, 50, 777, 275);
        g2.draw(segment10);
        Line2D.Double segment11 = new Line2D.Double(814, 50, 851, 275);
        g2.draw(segment11);
        Line2D.Double segment12 = new Line2D.Double(888, 50, 925, 275);
        g2.draw(segment12);

        Line2D.Double segment7_2 = new Line2D.Double(592, 50, 555, 275);
        g2.draw(segment7_2);
        Line2D.Double segment8_2 = new Line2D.Double(666, 50, 629, 275);
        g2.draw(segment8_2);
        Line2D.Double segment9_2 = new Line2D.Double(740, 50, 703, 275);
        g2.draw(segment9_2);
        Line2D.Double segment10_2 = new Line2D.Double(814, 50, 777, 275);
        g2.draw(segment10_2);
        Line2D.Double segment11_2 = new Line2D.Double(888, 50, 851, 275);
        g2.draw(segment11_2);
        Line2D.Double segment12_2 = new Line2D.Double(962, 50, 925, 275);
        g2.draw(segment12_2);

        Rectangle bottomBox = new Rectangle(0, 577, 962, 50);
        g2.draw(bottomBox);

        g2.drawString("24", 31, 602);
        g2.drawString("23", 105, 602);
        g2.drawString("22", 179, 602);
        g2.drawString("21", 253, 602);
        g2.drawString("20", 327, 602);
        g2.drawString("19", 401, 602);

        Line2D.Double segment13 = new Line2D.Double(0, 577, 37, 352);
        g2.draw(segment13);
        Line2D.Double segment14 = new Line2D.Double(74, 577, 111, 352);
        g2.draw(segment14);
        Line2D.Double segment15 = new Line2D.Double(148, 577, 185, 352);
        g2.draw(segment15);
        Line2D.Double segment16 = new Line2D.Double(222, 577, 259, 352);
        g2.draw(segment16);
        Line2D.Double segment17 = new Line2D.Double(296, 577, 333, 352);
        g2.draw(segment17);
        Line2D.Double segment18 = new Line2D.Double(370, 577, 407, 352);
        g2.draw(segment18);


        Line2D.Double segment13_2 = new Line2D.Double(74, 577, 37, 352);
        g2.draw(segment13_2);
        Line2D.Double segment14_2 = new Line2D.Double(148, 577, 111, 352);
        g2.draw(segment14_2);
        Line2D.Double segment15_2 = new Line2D.Double(222, 577, 185, 352);
        g2.draw(segment15_2);
        Line2D.Double segment16_2 = new Line2D.Double(296, 577, 259, 352);
        g2.draw(segment16_2);
        Line2D.Double segment17_2 = new Line2D.Double(370, 577, 333, 352);
        g2.draw(segment17_2);
        Line2D.Double segment18_2 = new Line2D.Double(444, 577, 407, 352);
        g2.draw(segment18_2);

        g2.drawString("18", 549, 602);
        g2.drawString("17", 623, 602);
        g2.drawString("16", 697, 602);
        g2.drawString("15", 771, 602);
        g2.drawString("14", 845, 602);
        g2.drawString("13", 919, 602);

        Line2D.Double segment19 = new Line2D.Double(518, 577, 555, 352);
        g2.draw(segment19);
        Line2D.Double segment20 = new Line2D.Double(592, 577, 629, 352);
        g2.draw(segment20);
        Line2D.Double segment21 = new Line2D.Double(666, 577, 703, 352);
        g2.draw(segment21);
        Line2D.Double segment22 = new Line2D.Double(740, 577, 777, 352);
        g2.draw(segment22);
        Line2D.Double segment23 = new Line2D.Double(814, 577, 851, 352);
        g2.draw(segment23);
        Line2D.Double segment24 = new Line2D.Double(888, 577, 925, 352);
        g2.draw(segment24);

        Line2D.Double segment19_2 = new Line2D.Double(592, 577, 555, 352);
        g2.draw(segment19_2);
        Line2D.Double segment20_2 = new Line2D.Double(666, 577, 629, 352);
        g2.draw(segment20_2);
        Line2D.Double segment21_2 = new Line2D.Double(740, 577, 703, 352);
        g2.draw(segment21_2);
        Line2D.Double segment22_2 = new Line2D.Double(814, 577, 777, 352);
        g2.draw(segment22_2);
        Line2D.Double segment23_2 = new Line2D.Double(888, 577, 851, 352);
        g2.draw(segment23_2);
        Line2D.Double segment24_2 = new Line2D.Double(962, 577, 925, 352);
        g2.draw(segment24_2);

        Rectangle sideBar = new Rectangle(962, 0, 100, 627);
        g2.draw(sideBar);

        Rectangle innerSideBar = new Rectangle(962, 50, 100, 527);
        g2.draw(innerSideBar);

        Rectangle gamePieceFinishArea1 = new Rectangle(972, 60, 80, 190);
        g2.draw(gamePieceFinishArea1);

        Rectangle gamePieceFinishArea2 = new Rectangle(972, 377, 80, 190);
        g2.draw(gamePieceFinishArea2);

        Rectangle doublingCubeArea = new Rectangle(972, 258, 80, 81);
        g2.draw(doublingCubeArea);

        Rectangle gameScore = new Rectangle(972, 345, 80, 25);
        g2.draw(gameScore);




        //draw Game Pieces
        /*if(testMoveCounter<24){
            try{
                //removeAll();
                testMove();
                //GameBoardPanel.repaint();
            }catch(Exception InterruptedException){}
        }*/
        for(int i=0;i<15;i++){
            //redPlayerGamePieces[i] = new GamePiece(g);
            redPlayerGamePieces[i].drawRedPiece(g);
            bluePlayerGamePieces[i].drawBluePiece(g);
        }


    }

    public void initialiseGameBoard(Graphics g,GamePiece[] redPlayerGamePieces, GamePiece[] bluePlayerGamePieces){
        numberOfRedPiecesOnPoint[0]=0;
        numberOfBluePiecesOnPoint[0]=0;
        numberOfRedPiecesOnPoint[25]=0;
        numberOfBluePiecesOnPoint[25]=0;
        pointLocationOrderedCounterClockwise[0][1]=60;
        pointLocationOrderedCounterClockwise[0][0]=995;
        pointLocationOrderedCounterClockwise[25][1]=532;
        pointLocationOrderedCounterClockwise[25][0]=995;

        for (int i=1;i<25;i++){
            numberOfRedPiecesOnPoint[i]=0;
            numberOfBluePiecesOnPoint[i]=0;

            if(i<13){
                pointLocationOrderedCounterClockwise[i][1]=50;
                if(i<7){
                    //top right corner
                    pointLocationOrderedCounterClockwise[i][0]=908-74*(i-1);
                }else{
                    //top left corner
                    pointLocationOrderedCounterClockwise[i][0]=390-74*(i-7);
                }
            }else if(i<26){
                pointLocationOrderedCounterClockwise[i][1]=542;
                if(i<19){
                    //bottom left corner
                    pointLocationOrderedCounterClockwise[i][0]=20+74*(i-13);
                }else{
                    pointLocationOrderedCounterClockwise[i][0]=538+74*(i-19);
                }
            }

        }
        for (int i=0;i<2;i++){
            redPlayerGamePieces[i] = new GamePiece(g);
            bluePlayerGamePieces[i] = new GamePiece(g);
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[1][0],pointLocationOrderedCounterClockwise[1][1]);
            numberOfRedPiecesOnPoint[1]++;
            redPlayerGamePieces[i].setPipLocation(1);
            pointLocationOrderedCounterClockwise[1][1]+=PIECE_DIAMETER;
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[24][0],pointLocationOrderedCounterClockwise[24][1]);
            numberOfBluePiecesOnPoint[24]++;
            bluePlayerGamePieces[i].setPipLocation(24);
            pointLocationOrderedCounterClockwise[24][1]-=PIECE_DIAMETER;
        }
        for(int i=2;i<7;i++){
            redPlayerGamePieces[i] = new GamePiece(g);
            bluePlayerGamePieces[i] = new GamePiece(g);
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[12][0],pointLocationOrderedCounterClockwise[12][1]);
            numberOfRedPiecesOnPoint[12]++;
            redPlayerGamePieces[i].setPipLocation(12);
            pointLocationOrderedCounterClockwise[12][1]+=PIECE_DIAMETER;
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[13][0],pointLocationOrderedCounterClockwise[13][1]);
            numberOfBluePiecesOnPoint[13]++;
            bluePlayerGamePieces[i].setPipLocation(13);
            pointLocationOrderedCounterClockwise[13][1]-=PIECE_DIAMETER;
        }
        for(int i=7;i<10;i++){
            redPlayerGamePieces[i] = new GamePiece(g);
            bluePlayerGamePieces[i] = new GamePiece(g);
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[17][0],pointLocationOrderedCounterClockwise[17][1]);
            numberOfRedPiecesOnPoint[17]++;
            redPlayerGamePieces[i].setPipLocation(17);
            pointLocationOrderedCounterClockwise[17][1]-=PIECE_DIAMETER;
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[8][0],pointLocationOrderedCounterClockwise[8][1]);
            numberOfBluePiecesOnPoint[8]++;
            bluePlayerGamePieces[i].setPipLocation(8);
            pointLocationOrderedCounterClockwise[8][1]+=PIECE_DIAMETER;
        }
        for(int i=10;i<15;i++){
            redPlayerGamePieces[i] = new GamePiece(g);
            bluePlayerGamePieces[i] = new GamePiece(g);
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[19][0],pointLocationOrderedCounterClockwise[19][1]);
            numberOfRedPiecesOnPoint[19]++;
            redPlayerGamePieces[i].setPipLocation(19);
            pointLocationOrderedCounterClockwise[19][1]-=PIECE_DIAMETER;
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[6][0],pointLocationOrderedCounterClockwise[6][1]);
            numberOfBluePiecesOnPoint[6]++;
            bluePlayerGamePieces[i].setPipLocation(6);
            pointLocationOrderedCounterClockwise[6][1]+=PIECE_DIAMETER;
        }

    }

    public GameBoardPanel(DefaultUserInputModel userInputModel){
        this.userInputModel = userInputModel;
        this.userInputModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if("userInput".equals(evt.getPropertyName()) && !userInputModel.getUserInput().equals("")){
                    String inputValues[] =userInputModel.getUserInput().split("\\s+");
                    moveRedPiece(Integer.parseInt(inputValues[0]),Integer.parseInt(inputValues[1]));
                }
            }
        });
    }

    public void testMove() throws InterruptedException {
        this.redPlayerGamePieces[0].setXYCoordinate(pointLocationOrderedCounterClockwise[testMoveCounter][0],pointLocationOrderedCounterClockwise[testMoveCounter][1]);
        this.bluePlayerGamePieces[0].setXYCoordinate(pointLocationOrderedCounterClockwise[25-testMoveCounter][0],pointLocationOrderedCounterClockwise[25-testMoveCounter][1]);
        testMoveCounter++;
        repaint();
        //validate();
        TimeUnit.SECONDS.sleep(1);
    }

    public void moveRedPiece(int from, int to){
        for(int i=0;i<15;i++){
            if(redPlayerGamePieces[i].getPipLocation()==from){
                redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[to][0],pointLocationOrderedCounterClockwise[to][1]);
                redPlayerGamePieces[i].setPipLocation(to);
                numberOfRedPiecesOnPoint[from]--;
                numberOfRedPiecesOnPoint[to]++;
                if (from<13){
                    pointLocationOrderedCounterClockwise[from][1]-=PIECE_DIAMETER;
                }else{
                    pointLocationOrderedCounterClockwise[from][1]+=PIECE_DIAMETER;
                }
                if(to <13){
                    pointLocationOrderedCounterClockwise[to][1]+=PIECE_DIAMETER;
                }else if(to >13 &&to !=25){
                    pointLocationOrderedCounterClockwise[to][1]-=PIECE_DIAMETER;
                }else if (to ==25){
                    pointLocationOrderedCounterClockwise[to][1]-=PIECE_DIAMETER/3;
                }

                repaint();
                break;
            }
        }
    }
}