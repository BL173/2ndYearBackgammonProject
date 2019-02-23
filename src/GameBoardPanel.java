/*
Team: Jives
Written by: Brian Leahy 17372896,
            Oscar Byrne Carty 17430786,
            Gearoid Lynch 17459176
 */

import java.awt.*;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import exceptions.*;



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
    private final int RED_TURN=0, BLUE_TURN=1;

    Dice diceRed = new Dice(userInputModel, 0, 0, 0);
    Dice diceBlue = new Dice(userInputModel, 0, 0, 1);


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

        if(userInputModel.getTurn()==RED_TURN){
            g2.drawString("12", 34, 25);
            g2.drawString("11", 108, 25);
            g2.drawString("10", 182, 25);
            g2.drawString("9", 259, 25);
            g2.drawString("8", 333, 25);
            g2.drawString("7", 407, 25);

            g2.drawString("6", 555, 25);
            g2.drawString("5", 629, 25);
            g2.drawString("4", 703, 25);
            g2.drawString("3", 768, 25);
            g2.drawString("2", 842, 25);
            g2.drawString("1", 916, 25);

            g2.drawString("13", 31, 602);
            g2.drawString("14", 105, 602);
            g2.drawString("15", 179, 602);
            g2.drawString("16", 253, 602);
            g2.drawString("17", 327, 602);
            g2.drawString("18", 401, 602);


            g2.drawString("19", 549, 602);
            g2.drawString("20", 623, 602);
            g2.drawString("21", 697, 602);
            g2.drawString("22", 771, 602);
            g2.drawString("23", 845, 602);
            g2.drawString("24", 919, 602);
        }

        else if(userInputModel.getTurn()==BLUE_TURN){
            g2.drawString("13", 37, 25);
            g2.drawString("14", 111, 25);
            g2.drawString("15", 185, 25);
            g2.drawString("16", 259, 25);
            g2.drawString("17", 333, 25);
            g2.drawString("18", 407, 25);

            g2.drawString("19", 555, 25);
            g2.drawString("20", 629, 25);
            g2.drawString("21", 703, 25);
            g2.drawString("22", 768, 25);
            g2.drawString("23", 842, 25);
            g2.drawString("24", 916, 25);

            g2.drawString("12", 31, 602);
            g2.drawString("11", 105, 602);
            g2.drawString("10", 179, 602);
            g2.drawString("9", 253, 602);
            g2.drawString("8", 327, 602);
            g2.drawString("7", 401, 602);


            g2.drawString("6", 552, 602);
            g2.drawString("5", 626, 602);
            g2.drawString("4", 700, 602);
            g2.drawString("3", 774, 602);
            g2.drawString("2", 848, 602);
            g2.drawString("1", 922, 602);
        }




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
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[1][0],pointLocationOrderedCounterClockwise[1][1]+numberOfRedPiecesOnPoint[1]*PIECE_DIAMETER);
            numberOfRedPiecesOnPoint[1]++;
            redPlayerGamePieces[i].setPipLocation(1);
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[24][0],pointLocationOrderedCounterClockwise[24][1]-numberOfBluePiecesOnPoint[24]*PIECE_DIAMETER);
            numberOfBluePiecesOnPoint[24]++;
            bluePlayerGamePieces[i].setPipLocation(24);
        }
        for(int i=2;i<7;i++){
            redPlayerGamePieces[i] = new GamePiece(g);
            bluePlayerGamePieces[i] = new GamePiece(g);
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[12][0],pointLocationOrderedCounterClockwise[12][1]+numberOfRedPiecesOnPoint[12]*PIECE_DIAMETER);
            numberOfRedPiecesOnPoint[12]++;
            redPlayerGamePieces[i].setPipLocation(12);
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[13][0],pointLocationOrderedCounterClockwise[13][1]-numberOfBluePiecesOnPoint[13]*PIECE_DIAMETER);
            numberOfBluePiecesOnPoint[13]++;
            bluePlayerGamePieces[i].setPipLocation(13);
        }
        for(int i=7;i<10;i++){
            redPlayerGamePieces[i] = new GamePiece(g);
            bluePlayerGamePieces[i] = new GamePiece(g);
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[17][0],pointLocationOrderedCounterClockwise[17][1]-numberOfRedPiecesOnPoint[17]*PIECE_DIAMETER);
            numberOfRedPiecesOnPoint[17]++;
            redPlayerGamePieces[i].setPipLocation(17);
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[8][0],pointLocationOrderedCounterClockwise[8][1]+numberOfBluePiecesOnPoint[8]*PIECE_DIAMETER);
            numberOfBluePiecesOnPoint[8]++;
            bluePlayerGamePieces[i].setPipLocation(8);
        }
        for(int i=10;i<15;i++){
            redPlayerGamePieces[i] = new GamePiece(g);
            bluePlayerGamePieces[i] = new GamePiece(g);
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[19][0],pointLocationOrderedCounterClockwise[19][1]-numberOfRedPiecesOnPoint[19]*PIECE_DIAMETER);
            numberOfRedPiecesOnPoint[19]++;
            redPlayerGamePieces[i].setPipLocation(19);
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[6][0],pointLocationOrderedCounterClockwise[6][1]+numberOfBluePiecesOnPoint[6]*PIECE_DIAMETER);
            numberOfBluePiecesOnPoint[6]++;
            bluePlayerGamePieces[i].setPipLocation(6);
        }

    }

    public GameBoardPanel(DefaultUserInputModel userInputModel){
        this.userInputModel = userInputModel;
        this.userInputModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if("userInput".equals(evt.getPropertyName()) && !userInputModel.getUserInput().equals("")){
                    String inputValues[] =userInputModel.getUserInput().split("\\s+");
                    try{
                        if (inputValues.length !=2){
                            throw new InvalidInputException();
                        }
                        try{
                            if(userInputModel.getTurn()==RED_TURN){
                                moveRedPiece(Integer.parseInt(inputValues[0]),Integer.parseInt(inputValues[1]));
                                userInputModel.setTurn(BLUE_TURN);
                                diceBlue.RollDice(1);
                            }else if(userInputModel.getTurn()==BLUE_TURN){
                                moveBluePiece(Integer.parseInt(inputValues[0]),Integer.parseInt(inputValues[1]));
                                userInputModel.setTurn(RED_TURN);
                                diceRed.RollDice(0);
                            }
                        }catch(java.lang.NumberFormatException e){
                            throw new InvalidInputException();
                        }
                    }catch(InvalidInputException e){
                        userInputModel.setInfoPanelOutput("This is an invalid move, try again.");
                    }
                }
            }
        });
    }



    public void moveRedPiece(int from, int to){
        if (from < 0 || from > 24 /*|| to <= from*/ || to > 25||numberOfRedPiecesOnPoint[from]==0) {
            throw new InvalidInputException();
        }else {
            for(int i=0;i<15;i++){
                if(redPlayerGamePieces[i].getPipLocation()==from){
                    numberOfRedPiecesOnPoint[from]--;
                    int shiftPiecesToBottomOfPipCounter=0;
                    if (from==0&&numberOfRedPiecesOnPoint[from]>0){
                        for(int j=i+1;j<15;j++){
                            if(redPlayerGamePieces[j].getPipLocation()==from){
                                redPlayerGamePieces[j].setXYCoordinate((pointLocationOrderedCounterClockwise[6][0]+pointLocationOrderedCounterClockwise[7][0])/2,50+shiftPiecesToBottomOfPipCounter*PIECE_DIAMETER);
                                shiftPiecesToBottomOfPipCounter++;
                            }
                        }
                    }else if (from<13&&numberOfRedPiecesOnPoint[from]>0){
                        for(int j=i+1;j<15;j++){
                            if(redPlayerGamePieces[j].getPipLocation()==from){
                                redPlayerGamePieces[j].setXYCoordinate(pointLocationOrderedCounterClockwise[from][0],pointLocationOrderedCounterClockwise[from][1]+shiftPiecesToBottomOfPipCounter*PIECE_DIAMETER);
                                shiftPiecesToBottomOfPipCounter++;
                            }
                        }
                    }else if (numberOfRedPiecesOnPoint[from]>0){
                        for(int j=i+1;j<15;j++){
                            if(redPlayerGamePieces[j].getPipLocation()==from){
                                redPlayerGamePieces[j].setXYCoordinate(pointLocationOrderedCounterClockwise[from][0],pointLocationOrderedCounterClockwise[from][1]-shiftPiecesToBottomOfPipCounter*PIECE_DIAMETER);
                                shiftPiecesToBottomOfPipCounter++;
                            }
                        }
                    }
                    if (to ==0){
                        redPlayerGamePieces[i].setXYCoordinate((pointLocationOrderedCounterClockwise[6][0]+pointLocationOrderedCounterClockwise[7][0])/2,50+numberOfRedPiecesOnPoint[to]*PIECE_DIAMETER);
                    }else if(to <13){
                        redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[to][0],pointLocationOrderedCounterClockwise[to][1]+numberOfRedPiecesOnPoint[to]*PIECE_DIAMETER);
                    }else if(to >13 &&to !=25){
                        redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[to][0],pointLocationOrderedCounterClockwise[to][1]-numberOfRedPiecesOnPoint[to]*PIECE_DIAMETER);
                    }else if (to ==25){
                        redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[to][0],pointLocationOrderedCounterClockwise[to][1]-numberOfRedPiecesOnPoint[to]*PIECE_DIAMETER/3);
                    }
                    numberOfRedPiecesOnPoint[to]++;
                    redPlayerGamePieces[i].setPipLocation(to);
                    repaint();
                    break;
                }
            }
        }
    }

    public void moveBluePiece(int from, int to){
        from=25-from;
        to=25-to;
        if (from >25 || from <1 /*|| to >= from */|| to < 0||numberOfBluePiecesOnPoint[from]==0) {
            throw new InvalidInputException();
        }else {
            for(int i=0;i<15;i++){
                if(bluePlayerGamePieces[i].getPipLocation()==from){
                    numberOfBluePiecesOnPoint[from]--;
                    int shiftPiecesToBottomOfPipCounter=0;
                    if (from==25&&numberOfBluePiecesOnPoint[from]>0){
                        for(int j=i+1;j<15;j++){
                            if(bluePlayerGamePieces[j].getPipLocation()==from){
                                bluePlayerGamePieces[j].setXYCoordinate((pointLocationOrderedCounterClockwise[6][0]+pointLocationOrderedCounterClockwise[7][0])/2,542-shiftPiecesToBottomOfPipCounter*PIECE_DIAMETER);
                                shiftPiecesToBottomOfPipCounter++;
                            }
                        }
                    }else if (from<13&&numberOfBluePiecesOnPoint[from]>0){
                        for(int j=i+1;j<15;j++){
                            if(bluePlayerGamePieces[j].getPipLocation()==from){
                                bluePlayerGamePieces[j].setXYCoordinate(pointLocationOrderedCounterClockwise[from][0],pointLocationOrderedCounterClockwise[from][1]+shiftPiecesToBottomOfPipCounter*PIECE_DIAMETER);
                                shiftPiecesToBottomOfPipCounter++;
                            }
                        }
                    }else if(numberOfBluePiecesOnPoint[from]>0){
                        for(int j=i+1;j<15;j++){
                            if(bluePlayerGamePieces[j].getPipLocation()==from){
                                bluePlayerGamePieces[j].setXYCoordinate(pointLocationOrderedCounterClockwise[from][0],pointLocationOrderedCounterClockwise[from][1]-shiftPiecesToBottomOfPipCounter*PIECE_DIAMETER);
                                shiftPiecesToBottomOfPipCounter++;
                            }
                        }
                    }
                    if (to ==25){
                        bluePlayerGamePieces[i].setXYCoordinate((pointLocationOrderedCounterClockwise[6][0]+pointLocationOrderedCounterClockwise[7][0])/2,542-numberOfBluePiecesOnPoint[to]*PIECE_DIAMETER);
                    }else if(to <13&&to != 0){
                        bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[to][0],pointLocationOrderedCounterClockwise[to][1]+numberOfBluePiecesOnPoint[to]*PIECE_DIAMETER);
                    }else if(to >13 /*&&to !=25*/){
                        bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[to][0],pointLocationOrderedCounterClockwise[to][1]-numberOfBluePiecesOnPoint[to]*PIECE_DIAMETER);
                    }else if (to ==0){
                        bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[to][0],pointLocationOrderedCounterClockwise[to][1]+numberOfBluePiecesOnPoint[to]*PIECE_DIAMETER/3);
                    }
                    numberOfBluePiecesOnPoint[to]++;
                    bluePlayerGamePieces[i].setPipLocation(to);
                    repaint();
                    break;
                }
            }
        }
    }
}