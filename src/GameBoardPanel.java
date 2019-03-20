/*
Team: Jives
Written by: Brian Leahy 17372896,
            Oscar Byrne Carty 17430786,
            Gearoid Lynch 17459176
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
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
    private DefaultUserInputModel userInputModel;
    private final int RED_TURN=0, BLUE_TURN=1;
    private move possibleMoves[] = new move[50];
    private int numberOfPossibleMoves;
    private char[] moveIndex= new char[2];
    private Dice gameDice;
    private int selectedMove;
    private int movesLeftThisTurn;
    private final static int TWO_SECONDS = 2000;
    private Timer timer;

    private int red = numberOfRedPiecesOnPoint[25];
    private int blue = numberOfBluePiecesOnPoint[0];


    private class move{
        public int from;
        public int to;
        public int from2;
        public int to2;
        public int diceNumber;
        public Boolean hit1=false;
        public Boolean hit2=false;
        public Boolean skipSecondMove=true;
        public String index;
        public move(int f, int t, int d, Boolean h1){
            from = f;
            to=t;
            diceNumber=d;
            hit1=h1;
        }

        public void secondMove(int f2, int t2, Boolean h2){
            from2=f2;
            to2=t2;
            hit2=h2;
            //index=moveIndexToString();
            skipSecondMove=false;
        }
        public String toString(){
            String moveString=index+" ('";
            if(from==25){
                moveString+="BAR-"+to;
            }else if (to==0){
                moveString+=from+"-OFF";
            }else {
                moveString+=from+"-"+to;
            }
            if(hit1==true){
                moveString+="*'  ";
            }else{
                moveString+="'  ";
            }
            if(skipSecondMove==true){
                moveString+="SKIP)\n";
            }else {
                if(from2==25){
                    moveString+="'BAR-"+to2;
                }else if (to2==0){
                    moveString+="'"+from2+"-OFF";
                }else {
                    moveString+="'"+from2+"-"+to2;
                }
                if(hit2==true){
                    moveString+="*') \n";
                }else{
                    moveString+="') \n";
                }
            }
            return moveString;
        }
    }

    public void setNewGame(boolean newGame) {
        this.newGame = newGame;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle middleBox = new Rectangle(444, 0, 74, 627);
        g2.draw(middleBox);

        if(newGame==true){
            initialiseGameBoard(g, redPlayerGamePieces,bluePlayerGamePieces);
            newGame = false;
        }





        // all the diagonals on the top of the board going down right
        for(int i = 0; i < 13; i++){
            Line2D.Double segment = new Line2D.Double(i * 74, 50, 37 + (i * 74), 275);
            g2.draw(segment);
            if(i == 5)
                i++;
        }

        //all the diagonals on the top of the board going down left
        for(int i = 0; i < 13; i++){
            Line2D.Double segment2 = new Line2D.Double(74 + (i * 74), 50, 37 + (i * 74), 275);
            g2.draw(segment2);
            if(i == 5)
                i++;
        }


        //all the diagonals at the bottom going up and right
        for(int i = 0; i<13; i++){
            Line2D.Double segment2 = new Line2D.Double(i * 74, 577, 37 + (i * 74), 352);
            g2.draw(segment2);
            if(i == 5)
                i++;
        }

        //all the diagonals at the bottom going up left
        for(int i = 0 ;i<13; i++){
            Line2D.Double segment2 = new Line2D.Double(74 + (i * 74), 577, 37 + (i * 74), 352);
            g2.draw(segment2);
            if(i == 5)
                i++;
        }

        //box at the top of the board containing the numbers
        Rectangle topBox = new Rectangle(0, 0, 1062, 50);
        g2.draw(topBox);

        //box at the bottom of the board containing the numbers
        Rectangle bottomBox = new Rectangle(0, 577, 1062, 50);
        g2.draw(bottomBox);

        Rectangle sideBar = new Rectangle(962, 0, 100, 627);
        g2.draw(sideBar);


        //the finishing are for blue
        Rectangle gamePieceFinishArea1 = new Rectangle(972, 60, 80, 190);
        g2.draw(gamePieceFinishArea1);

        //the finishing are for red
        Rectangle gamePieceFinishArea2 = new Rectangle(972, 377, 80, 190);
        g2.draw(gamePieceFinishArea2);

        Rectangle doublingCubeArea = new Rectangle(972, 258, 80, 81);
        g2.draw(doublingCubeArea);

        Rectangle gameScore = new Rectangle(972, 345, 80, 25);
        g2.draw(gameScore);

        if(userInputModel.getTurn()==BLUE_TURN){
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

        else if(userInputModel.getTurn()==RED_TURN){
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

        for(int i=0;i<15;i++){
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
        gameDice = new Dice(userInputModel);

        this.userInputModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if("userInput".equals(evt.getPropertyName()) && !userInputModel.getUserInput().equals("")){
                    if(userInputModel.getUserInput().equals("newgame")){
                        setNewGame(true);
                        repaint();
                    }
                    else if(userInputModel.getUserInput().equals("cheat")) {
                        if(userInputModel.getTurn()==RED_TURN) {
                            userInputModel.setInfoPanelOutput("Cheat has been activated");
                            cheat();
                            userInputModel.setTurn(BLUE_TURN);
                        }else if(userInputModel.getTurn()==BLUE_TURN) {
                            userInputModel.setInfoPanelOutput("Cheat has been activated");
                            cheat();
                            userInputModel.setTurn(RED_TURN);

                        }
                    }else if(userInputModel.getUserInput().equals("cheatred")){
                        cheatRed();
                    }else if(userInputModel.getUserInput().equals("cheatblue")){
                        cheatBlue();
                    }else if(checkPossibleMoveIndex(userInputModel.getUserInput().toUpperCase())){
                        gameDice.invalidateDice(possibleMoves[selectedMove].diceNumber);
                        if(userInputModel.getTurn()==RED_TURN){
                            moveRedPiece(possibleMoves[selectedMove].from,possibleMoves[selectedMove].to);
                            if(possibleMoves[selectedMove].hit1==true){
                                moveBluePiece(25-possibleMoves[selectedMove].to,25);
                            }
                            decrementMovesLeftThisTurn();
                        }else if(userInputModel.getTurn()==BLUE_TURN){
                            moveBluePiece(possibleMoves[selectedMove].from,possibleMoves[selectedMove].to);
                            if(possibleMoves[selectedMove].hit1==true){
                                moveRedPiece(25-possibleMoves[selectedMove].to,25);
                            }
                            decrementMovesLeftThisTurn();
                        }
                    }else {
                        String inputValues[] =userInputModel.getUserInput().split("\\s+");
                        try{

                            if (inputValues.length !=2||!checkPossibleMove(inputValues)){
                                throw new InvalidInputException();
                            }
                            try{
                                gameDice.invalidateDice(possibleMoves[selectedMove].diceNumber);
                                if(userInputModel.getTurn()==RED_TURN){
                                    moveRedPiece(Integer.parseInt(inputValues[0]),Integer.parseInt(inputValues[1]));
                                    decrementMovesLeftThisTurn();
                                }else if(userInputModel.getTurn()==BLUE_TURN){
                                    moveBluePiece(Integer.parseInt(inputValues[0]),Integer.parseInt(inputValues[1]));
                                    decrementMovesLeftThisTurn();
                                }
                            }catch(java.lang.NumberFormatException e){
                                throw new InvalidInputException();
                            }
                        }catch(InvalidInputException e){
                            userInputModel.setInfoPanelOutput("This is an invalid move, try again.");
                        }
                    }

                }else if("turn".equals(evt.getPropertyName()) && numberOfRedPiecesOnPoint[25] == 15){
                    userInputModel.setWinner("red");
                }else if("turn".equals(evt.getPropertyName()) && numberOfBluePiecesOnPoint[0] == 15){
                    userInputModel.setWinner("blue");
                }else if ("bluePlayerName".equals(evt.getPropertyName()) && !userInputModel.getBluePlayerName().equals("")){
                    gameDice.startDice();
                    repaint();
                }else if("turn".equals(evt.getPropertyName()) && userInputModel.getTurn()==RED_TURN){
                    setMovesLeftThisTurn();
                    generatePossibleRedMoves();
                    repaint();
                }else if("turn".equals(evt.getPropertyName()) && userInputModel.getTurn()==BLUE_TURN){
                    setMovesLeftThisTurn();
                    generatePossibleBlueMoves();
                    repaint();
                }
            }
        });
    }

    public void moveRedPiece(int from, int to){
        from=25-from;
        to=25-to;
        if (from < 0 || from > 25 || to <0 || to > 25||numberOfRedPiecesOnPoint[from]==0) {
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
        if (from >25 || from <0 || to > 25|| to < 0||numberOfBluePiecesOnPoint[from]==0) {
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

    public void generatePossibleRedMoves(){
        numberOfPossibleMoves=0;
        String output="PM";
        moveIndex[0]=' ';
        moveIndex[1]='A';
        if(numberOfRedPiecesOnPoint[0]!=0){
            if(numberOfBluePiecesOnPoint[gameDice.getDiceOne()]==0&&gameDice.getDiceOneValid()){
                possibleMoves[numberOfPossibleMoves]=new move(25,25-gameDice.getDiceOne(),1,false);
                generateRedSecondMoves();
                //output+=possibleMoves[numberOfPossibleMoves].toString();
                //numberOfPossibleMoves++;
            }else if (numberOfBluePiecesOnPoint[gameDice.getDiceOne()]==1&&gameDice.getDiceOneValid()){
                possibleMoves[numberOfPossibleMoves]=new move(25,25-gameDice.getDiceOne(),1,true);
                generateRedSecondMoves();
                //output+=possibleMoves[numberOfPossibleMoves].toString();
                //numberOfPossibleMoves++;
            }
            if(!gameDice.rollDouble()&&numberOfBluePiecesOnPoint[gameDice.getDiceTwo()]==0&&gameDice.getDiceTwoValid()){
                possibleMoves[numberOfPossibleMoves]=new move(25,25-gameDice.getDiceTwo(),2,false);
                generateRedSecondMoves();
                //output+=possibleMoves[numberOfPossibleMoves].toString();
                //numberOfPossibleMoves++;
            }else if (!gameDice.rollDouble()&&numberOfBluePiecesOnPoint[gameDice.getDiceTwo()]==1&&gameDice.getDiceTwoValid()){
                possibleMoves[numberOfPossibleMoves]=new move(25,25-gameDice.getDiceTwo(),2,true);
                generateRedSecondMoves();
                //output+=possibleMoves[numberOfPossibleMoves].toString();
                //numberOfPossibleMoves++;
            }
        }else{
            for(int i=1;i<25;i++){
                if (numberOfRedPiecesOnPoint[i]>0){
                    if(i+gameDice.getDiceOne()<25&&numberOfBluePiecesOnPoint[i+gameDice.getDiceOne()]==0&&gameDice.getDiceOneValid()){
                        possibleMoves[numberOfPossibleMoves]=new move(25-i,25-(i+gameDice.getDiceOne()),1,false);
                        generateRedSecondMoves();
                        //output+=possibleMoves[numberOfPossibleMoves].toString();
                        //numberOfPossibleMoves++;
                    }else if (i+gameDice.getDiceOne()<25&&numberOfBluePiecesOnPoint[i+gameDice.getDiceOne()]==1&&gameDice.getDiceOneValid()){
                        possibleMoves[numberOfPossibleMoves]=new move(25-i,25-(i+gameDice.getDiceOne()),1,true);
                        generateRedSecondMoves();
                        //output+=possibleMoves[numberOfPossibleMoves].toString();
                        //numberOfPossibleMoves++;
                    }else if(i+gameDice.getDiceOne()==25&&gameDice.getDiceOneValid()&&checkBearOffAllowed()){
                        possibleMoves[numberOfPossibleMoves]=new move(25-i,25-(i+gameDice.getDiceOne()),1,false);
                        generateRedSecondMoves();
                        //output+=possibleMoves[numberOfPossibleMoves].toString();
                        //numberOfPossibleMoves++;
                    }
                    if(!gameDice.rollDouble()&&i+gameDice.getDiceTwo()<25&&numberOfBluePiecesOnPoint[i+gameDice.getDiceTwo()]==0&&gameDice.getDiceTwoValid()){
                        possibleMoves[numberOfPossibleMoves]=new move(25-i,25-(i+gameDice.getDiceTwo()),2,false);
                        generateRedSecondMoves();
                        //output+=possibleMoves[numberOfPossibleMoves].toString();
                        //numberOfPossibleMoves++;
                    }else if (!gameDice.rollDouble()&&i+gameDice.getDiceTwo()<25&&numberOfBluePiecesOnPoint[i+gameDice.getDiceTwo()]==1&&gameDice.getDiceTwoValid()){
                        possibleMoves[numberOfPossibleMoves]=new move(25-i,25-(i+gameDice.getDiceTwo()),2,true);
                        generateRedSecondMoves();
                        //output+=possibleMoves[numberOfPossibleMoves].toString();
                        //numberOfPossibleMoves++;
                    }else if(!gameDice.rollDouble()&&i+gameDice.getDiceTwo()==25&&gameDice.getDiceTwoValid()&&checkBearOffAllowed()){
                        possibleMoves[numberOfPossibleMoves]=new move(25-i,25-(i+gameDice.getDiceTwo()),2,false);
                        generateRedSecondMoves();
                        //output+=possibleMoves[numberOfPossibleMoves].toString();
                        //numberOfPossibleMoves++;
                    }
                }
            }
        }
        for (int i=0;i<numberOfPossibleMoves;i++){
            possibleMoves[i].index=moveIndexToString();
            output+=possibleMoves[i].toString();
        }
        if (numberOfPossibleMoves>1){
            userInputModel.setInfoPanelOutput(output+"\nMoves Left: "+ movesLeftThisTurn +"\n");
        }else if(numberOfPossibleMoves==1){
            userInputModel.setInfoPanelOutput(output+"\nOnly 1 possible move, moving automatically.\nPlease do not enter any commands");
            timer = new Timer(TWO_SECONDS, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    //timer.setRepeats(false);
                    moveRedPiece(possibleMoves[0].from,possibleMoves[0].to);
                    if(!gameDice.rollDouble()){
                        gameDice.invalidateDice(possibleMoves[0].diceNumber);
                    }
                    decrementMovesLeftThisTurn();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }else if(numberOfPossibleMoves==0) {
            userInputModel.setInfoPanelOutput(output + "\nNo possible moves, skipping turn.\nPlease do not enter any commands");
            timer = new Timer(TWO_SECONDS, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    //timer.setRepeats(false);
                    userInputModel.setTurn(BLUE_TURN);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public void generateRedSecondMoves(){
        int dice=getOtherDice(possibleMoves[numberOfPossibleMoves].diceNumber);
        Boolean diceValid =getOtherDiceValid(possibleMoves[numberOfPossibleMoves].diceNumber);
        int startingPossibleMoves=numberOfPossibleMoves;
        int f1 = possibleMoves[numberOfPossibleMoves].from;
        int t1 = possibleMoves[numberOfPossibleMoves].to;
        int d =possibleMoves[numberOfPossibleMoves].diceNumber;
        Boolean h1 = possibleMoves[numberOfPossibleMoves].hit1;
        //possibleMoves[numberOfPossibleMoves+1]=possibleMoves[numberOfPossibleMoves];
        if(numberOfRedPiecesOnPoint[0]>1 && diceValid){

        }else if(diceValid){
            if((25-t1) +dice<25&&numberOfBluePiecesOnPoint[25-t1+dice]==0){
                possibleMoves[numberOfPossibleMoves+1]=new move(f1,t1,d,h1);
                possibleMoves[numberOfPossibleMoves].secondMove(t1,t1-dice,false);
                numberOfPossibleMoves++;
            }else if((25-t1) +dice<25&&numberOfBluePiecesOnPoint[25-t1+dice]==0){
                possibleMoves[numberOfPossibleMoves+1]=new move(f1,t1,d,h1);
                possibleMoves[numberOfPossibleMoves].secondMove(t1,t1-dice,true);
                numberOfPossibleMoves++;
            }

            for(int i=1;i<25;i++){
                if (numberOfRedPiecesOnPoint[i]>0){
                    if(i+dice<25&&numberOfBluePiecesOnPoint[i+dice]==0){
                        possibleMoves[numberOfPossibleMoves+1]=new move(f1,t1,d,h1);
                        possibleMoves[numberOfPossibleMoves].secondMove(25-i,25-(i+dice),false);
                        numberOfPossibleMoves++;
                    }else if (i+dice<25&&numberOfBluePiecesOnPoint[i+dice]==1){
                        possibleMoves[numberOfPossibleMoves+1]=new move(f1,t1,d,h1);
                        possibleMoves[numberOfPossibleMoves].secondMove(25-i,25-(i+dice),true);
                        numberOfPossibleMoves++;
                    }else if(i+dice==25&&checkBearOffAllowed()){
                        possibleMoves[numberOfPossibleMoves+1]=new move(f1,t1,d,h1);
                        possibleMoves[numberOfPossibleMoves].secondMove(25-i,25-(i+dice),false);
                        numberOfPossibleMoves++;
                    }
                }
            }
        }
        if(startingPossibleMoves==numberOfPossibleMoves){
            numberOfPossibleMoves++;
        }
    }

    public void generatePossibleBlueMoves(){
        numberOfPossibleMoves=0;
        String output="PM";
        moveIndex[0]=' ';
        moveIndex[1]='A';
        if(numberOfBluePiecesOnPoint[25]!=0){
            if(numberOfRedPiecesOnPoint[25-gameDice.getDiceOne()]==0&&gameDice.getDiceOneValid()){
                possibleMoves[numberOfPossibleMoves]=new move(25,25-gameDice.getDiceOne(),1,false);
                output+=possibleMoves[numberOfPossibleMoves].index+" (BAR, "+(possibleMoves[numberOfPossibleMoves].to)+")\n";
                numberOfPossibleMoves++;
            }else if (numberOfRedPiecesOnPoint[25-gameDice.getDiceOne()]==1&&gameDice.getDiceOneValid()){
                possibleMoves[numberOfPossibleMoves]=new move(25,25-gameDice.getDiceOne(),1,true);
                output+=possibleMoves[numberOfPossibleMoves].index+" (BAR, "+(possibleMoves[numberOfPossibleMoves].to)+"*)\n";
                numberOfPossibleMoves++;
            }
            if(!gameDice.rollDouble()&&numberOfRedPiecesOnPoint[25-gameDice.getDiceTwo()]==0&&gameDice.getDiceTwoValid()){
                possibleMoves[numberOfPossibleMoves]=new move(25,25-gameDice.getDiceTwo(),2,false);
                output+=possibleMoves[numberOfPossibleMoves].index+" (BAR, "+(possibleMoves[numberOfPossibleMoves].to)+")\n";
                numberOfPossibleMoves++;
            }else if (!gameDice.rollDouble()&&numberOfRedPiecesOnPoint[25-gameDice.getDiceTwo()]==1&&gameDice.getDiceTwoValid()){
                possibleMoves[numberOfPossibleMoves]=new move(25,gameDice.getDiceTwo(),2,true);
                output+=possibleMoves[numberOfPossibleMoves].index+" (BAR, "+(possibleMoves[numberOfPossibleMoves].to)+"*)\n";
                numberOfPossibleMoves++;
            }
        }else{
            for(int i=25;i>0;i--){
                if (numberOfBluePiecesOnPoint[i]>0){
                    if(i-gameDice.getDiceOne()>0&&numberOfRedPiecesOnPoint[i-gameDice.getDiceOne()]==0&&gameDice.getDiceOneValid()){
                        possibleMoves[numberOfPossibleMoves]=new move(i,i-gameDice.getDiceOne(),1,false);
                        output+=possibleMoves[numberOfPossibleMoves].index+" ("+i+", "+possibleMoves[numberOfPossibleMoves].to+")\n";
                        numberOfPossibleMoves++;
                    }else if (i-gameDice.getDiceOne()>0&&numberOfRedPiecesOnPoint[i-gameDice.getDiceOne()]==1&&gameDice.getDiceOneValid()){
                        possibleMoves[numberOfPossibleMoves]=new move(i,i-gameDice.getDiceOne(),1,true);
                        output+=possibleMoves[numberOfPossibleMoves].index+" ("+i+", "+possibleMoves[numberOfPossibleMoves].to+"*)\n";
                        numberOfPossibleMoves++;
                    }else if(i-gameDice.getDiceOne()==0&&gameDice.getDiceOneValid()&&checkBearOffAllowed()){
                        possibleMoves[numberOfPossibleMoves]=new move(i,i-gameDice.getDiceOne(),1,false);
                        output+=possibleMoves[numberOfPossibleMoves].index+" ("+i+", "+"OFF)\n";
                        numberOfPossibleMoves++;
                    }
                    if(!gameDice.rollDouble()&&i-gameDice.getDiceTwo()>0&&numberOfRedPiecesOnPoint[i-gameDice.getDiceTwo()]==0&&gameDice.getDiceTwoValid()){
                        possibleMoves[numberOfPossibleMoves]=new move(i,i-gameDice.getDiceTwo(),2,false);
                        output+=possibleMoves[numberOfPossibleMoves].index+" ("+i+", "+possibleMoves[numberOfPossibleMoves].to+")\n";
                        numberOfPossibleMoves++;
                    }else if (!gameDice.rollDouble()&&i-gameDice.getDiceTwo()>0&&numberOfRedPiecesOnPoint[i-gameDice.getDiceTwo()]==1&&gameDice.getDiceTwoValid()){
                        possibleMoves[numberOfPossibleMoves]=new move(i,i-gameDice.getDiceTwo(),2,true);
                        output+=possibleMoves[numberOfPossibleMoves].index+" ("+i+", "+possibleMoves[numberOfPossibleMoves].to+"*)\n";
                        numberOfPossibleMoves++;
                    }else if(!gameDice.rollDouble()&&i-gameDice.getDiceTwo()==0&&gameDice.getDiceTwoValid()&&checkBearOffAllowed()){
                        possibleMoves[numberOfPossibleMoves]=new move(i,i-gameDice.getDiceTwo(),2,false);
                        output+=possibleMoves[numberOfPossibleMoves].index+" ("+i+", "+"OFF)\n";
                        numberOfPossibleMoves++;
                    }
                }
            }
        }
        if (numberOfPossibleMoves>1){
            userInputModel.setInfoPanelOutput(output+"\nMoves Left: "+ movesLeftThisTurn +"\n");
        }else if(numberOfPossibleMoves==1){
            userInputModel.setInfoPanelOutput(output+"\nOnly 1 possible move,moving automatically.\nPlease do not enter any commands");
            timer = new Timer(TWO_SECONDS, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    //timer.setRepeats(false);
                    moveBluePiece(possibleMoves[0].from,possibleMoves[0].to);
                    if(!gameDice.rollDouble()){
                        gameDice.invalidateDice(possibleMoves[0].diceNumber);
                    }
                    decrementMovesLeftThisTurn();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }else if(numberOfPossibleMoves==0) {
            userInputModel.setInfoPanelOutput(output + "\nNo possible moves, skipping turn.\nPlease do not enter any commands");
            timer = new Timer(TWO_SECONDS, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    //timer.setRepeats(false);
                    userInputModel.setTurn(RED_TURN);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public void incrementMoveIndex(){
        if (moveIndex[1]!='Z'){
            moveIndex[1]++;
        }else if(moveIndex[0]==' '){
            moveIndex[0]='A';
            moveIndex[1]='A';
        }else {
            moveIndex[0]++;
            moveIndex[1]='A';
        }
    }

    public String moveIndexToString(){
        String mIString="";
        if(moveIndex[0]!=' '){
            mIString=Character.toString(moveIndex[0])+Character.toString(moveIndex[1]);
            incrementMoveIndex();
            return mIString;
        }
        mIString=Character.toString(moveIndex[1]);
        incrementMoveIndex();
        return mIString;
    }

    public Boolean checkPossibleMoveIndex(String input){
        for (int i=0;i<numberOfPossibleMoves;i++){
            if (input.equals(possibleMoves[i].index)){
                selectedMove=i;
                return true;
            }
        }
        return false;
    }

    public Boolean checkPossibleMove(String input[]){
        for(int i=0;i<numberOfPossibleMoves;i++){
            if(Integer.parseInt(input[0])==possibleMoves[i].from&&Integer.parseInt(input[1])==possibleMoves[i].to){
                selectedMove=i;
                return true;
            }
        }
        return false;
    }

    public void setMovesLeftThisTurn() {
        if(gameDice.rollDouble()){
            movesLeftThisTurn=4;
        }else{
            movesLeftThisTurn=2;
        }
    }

    public void decrementMovesLeftThisTurn(){
        if(movesLeftThisTurn>1&&userInputModel.getTurn()==RED_TURN){
            movesLeftThisTurn--;
            generatePossibleRedMoves();
        }else if (movesLeftThisTurn>1&&userInputModel.getTurn()==BLUE_TURN){
            movesLeftThisTurn--;
            generatePossibleBlueMoves();
        }else if(userInputModel.getTurn()==RED_TURN){
            userInputModel.setTurn(BLUE_TURN);
        }else if(userInputModel.getTurn()==BLUE_TURN){
            userInputModel.setTurn(RED_TURN);
        }
    }

    public void cheat() {
        for(int i = 0; i < 26; i++) {
            numberOfBluePiecesOnPoint[i] = 0;
            numberOfRedPiecesOnPoint[i] = 0;
        }
        for(int i = 0; i < 3; i++) {
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[3][0], pointLocationOrderedCounterClockwise[3][1]+numberOfBluePiecesOnPoint[3] * PIECE_DIAMETER);
            numberOfBluePiecesOnPoint[3]++;
            bluePlayerGamePieces[i].setPipLocation(3);
        }
        for(int i = 3; i < 6; i++) {
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[4][0], pointLocationOrderedCounterClockwise[4][1]+numberOfBluePiecesOnPoint[4] * PIECE_DIAMETER);
            numberOfBluePiecesOnPoint[4]++;
            bluePlayerGamePieces[i].setPipLocation(4);
        }
        for(int i = 6; i < 9; i++) {
            bluePlayerGamePieces[i].setXYCoordinate((pointLocationOrderedCounterClockwise[18][0] + pointLocationOrderedCounterClockwise[19][0]) / 2, pointLocationOrderedCounterClockwise[25][1] - numberOfBluePiecesOnPoint[25] * PIECE_DIAMETER);
            numberOfBluePiecesOnPoint[25]++;
            bluePlayerGamePieces[i].setPipLocation(25);
        }
        for(int i = 9; i < 12; i++) {
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[1][0], pointLocationOrderedCounterClockwise[1][1]+numberOfBluePiecesOnPoint[1] * PIECE_DIAMETER);
            numberOfBluePiecesOnPoint[1]++;
            bluePlayerGamePieces[i].setPipLocation(1);
        }
        for(int i = 12; i < 15; i++) {
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[0][0], pointLocationOrderedCounterClockwise[0][1]+numberOfBluePiecesOnPoint[0] * (PIECE_DIAMETER / 3));
            numberOfBluePiecesOnPoint[0]++;
            bluePlayerGamePieces[i].setPipLocation(0);
        }
        for(int i = 0; i < 2; i++) {
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[20][0], pointLocationOrderedCounterClockwise[20][1]- numberOfRedPiecesOnPoint[20] * PIECE_DIAMETER);
            numberOfRedPiecesOnPoint[20]++;
            redPlayerGamePieces[i].setPipLocation(20);
        }
        for(int i = 2; i < 4; i++) {
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[21][0], pointLocationOrderedCounterClockwise[21][1] - numberOfRedPiecesOnPoint[21] * PIECE_DIAMETER);
            numberOfRedPiecesOnPoint[21]++;
            redPlayerGamePieces[i].setPipLocation(21);
        }
        for(int i = 4; i < 6; i++) {
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[22][0], pointLocationOrderedCounterClockwise[22][1] - numberOfRedPiecesOnPoint[22] * PIECE_DIAMETER);
            numberOfRedPiecesOnPoint[22]++;
            redPlayerGamePieces[i].setPipLocation(22);
        }
        for(int i = 6; i < 8; i++) {
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[23][0], pointLocationOrderedCounterClockwise[23][1] - numberOfRedPiecesOnPoint[23] * PIECE_DIAMETER);
            numberOfRedPiecesOnPoint[23]++;
            redPlayerGamePieces[i].setPipLocation(23);
        }
        for(int i = 8; i < 10; i++) {
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[24][0], pointLocationOrderedCounterClockwise[24][1] - numberOfRedPiecesOnPoint[24] * PIECE_DIAMETER);
            numberOfRedPiecesOnPoint[24]++;
            redPlayerGamePieces[i].setPipLocation(24);
        }
        for(int i = 10; i < 12; i++) {
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[25][0], pointLocationOrderedCounterClockwise[25][1] - numberOfRedPiecesOnPoint[25] * (PIECE_DIAMETER / 3));
            numberOfRedPiecesOnPoint[25]++;
            redPlayerGamePieces[i].setPipLocation(25);
        }
        for(int i = 12; i < 15; i++) {
            redPlayerGamePieces[i].setXYCoordinate((pointLocationOrderedCounterClockwise[18][0] + pointLocationOrderedCounterClockwise[19][0]) / 2, pointLocationOrderedCounterClockwise[0][1] + numberOfRedPiecesOnPoint[0] * PIECE_DIAMETER );
            numberOfRedPiecesOnPoint[0]++;
            redPlayerGamePieces[i].setPipLocation(0);
        }
        repaint();
    }


    public void cheatRed() {
        for(int i = 0; i < 26; i++) {
            numberOfRedPiecesOnPoint[i] = 0;
        }
        for(int i = 0; i < 15; i++) {
            redPlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[25][0], pointLocationOrderedCounterClockwise[25][1] - numberOfRedPiecesOnPoint[25] * (PIECE_DIAMETER / 3));
            numberOfRedPiecesOnPoint[25]++;
            redPlayerGamePieces[i].setPipLocation(25);
        }
        repaint();
    }

    public void cheatBlue() {
        for (int i = 0; i < 26; i++) {
            numberOfBluePiecesOnPoint[i] = 0;
        }
        for(int i = 0; i < 15; i++) {
            bluePlayerGamePieces[i].setXYCoordinate(pointLocationOrderedCounterClockwise[0][0], pointLocationOrderedCounterClockwise[0][1]+numberOfBluePiecesOnPoint[0] * (PIECE_DIAMETER / 3));
            numberOfBluePiecesOnPoint[0]++;
            bluePlayerGamePieces[i].setPipLocation(0);
        }
        repaint();
    }


    public int getOtherDice(int d){
        if(d==1){
            return gameDice.getDiceTwo();
        }else{
            return gameDice.getDiceOne();
        }
    }
    public Boolean getOtherDiceValid(int d){
        if(d==1){
            return gameDice.getDiceTwoValid();
        }else{
            return gameDice.getDiceOneValid();
        }
    }

    public Boolean checkBearOffAllowed(){
        int piecesInFinalQuarter=0;
        if(userInputModel.getTurn()==RED_TURN){
            for(int i=25;i>18;i--){
                piecesInFinalQuarter+=numberOfRedPiecesOnPoint[i];
            }
        }else{
            for (int i=0;i<7;i++){
                piecesInFinalQuarter+=numberOfBluePiecesOnPoint[i];
            }
        }
        if(piecesInFinalQuarter==15)
            return true;
        return false;
    }


}