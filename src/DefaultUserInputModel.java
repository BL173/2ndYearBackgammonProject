/*
Team: Jives
Written by: Brian Leahy 17372896,
            Oscar Byrne Carty 17430786,
            Gearoid Lynch 17459176
 */

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DefaultUserInputModel implements UserInputModel{
    private PropertyChangeSupport propertyChangeSupport;
    private String userInput;
    private String infoPanelOutput;
    private int turn=0;
    private String redPlayerName;
    private String bluePlayerName;
    private String winner = " ";
    private int matchLength;
    private Score matchScore = new Score (0,0);
    private Boolean matchOver = false;
    private int totalBlueWins = 0;
    private int totalRedWins = 0;
    private int match;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        String oldWinner = this.winner;
        this.winner = winner;
        propertyChangeSupport.firePropertyChange("winner", oldWinner, winner);
    }

    public DefaultUserInputModel(){
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public String getUserInput(){
        return userInput;
    }
    //@Override
    public void setUserInput(String newUserInput){
        String oldUserInput = userInput;
        userInput = newUserInput;
        propertyChangeSupport.firePropertyChange("userInput", oldUserInput, userInput);
        userInput = "";
        propertyChangeSupport.firePropertyChange("userInput", oldUserInput, userInput);
    }

    public String getInfoPanelOutput(){
        return infoPanelOutput;
    }
    //@Override
    public void setInfoPanelOutput(String newInfoPanelOutput){
        String oldInfoPanelOutput = infoPanelOutput;
        infoPanelOutput = newInfoPanelOutput;
        propertyChangeSupport.firePropertyChange("infoPanelOutput", oldInfoPanelOutput, infoPanelOutput);
        infoPanelOutput = "";
        propertyChangeSupport.firePropertyChange("infoPanelOutput", oldInfoPanelOutput, infoPanelOutput);
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int newTurn) {
        int oldTurn = turn;
        turn = newTurn;
        propertyChangeSupport.firePropertyChange("turn", oldTurn, infoPanelOutput);
        if(!matchOver){
            if(turn==0){
                setInfoPanelOutput(redPlayerName + "'s Turn\n");
            }else if(turn==1){
                setInfoPanelOutput(bluePlayerName + "'s Turn\n");
            }
        }else{
            matchOver=false;
        }

    }


    public void setRedPlayerName(String newPlayerName){
        String oldRedPlayerName = redPlayerName;
        this.redPlayerName = newPlayerName;
        propertyChangeSupport.firePropertyChange("redPlayerName", oldRedPlayerName, newPlayerName);
    }

    public String getRedPlayerName() {
        return redPlayerName;
    }

    public void setBluePlayerName(String newPlayerName){
        String oldBluePlayerName = redPlayerName;
        this.bluePlayerName = newPlayerName;
        propertyChangeSupport.firePropertyChange("bluePlayerName", oldBluePlayerName, newPlayerName);
    }

    public String getBluePlayerName() {
        return bluePlayerName;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public int getMatchLength() {
        return matchLength;
    }

    public void setMatchLength(int matchLength) {
        int oldNum = this.matchLength;
        this.matchLength = matchLength;
        propertyChangeSupport.firePropertyChange("matchLength", oldNum, matchLength);
    }

    public Score getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(Score matchScore) {
        Score oldScore =this.matchScore;
        this.matchScore = matchScore;
        if(matchScore.getRedScore()==matchLength){
            setWinner("red");
        }else if(matchScore.getBlueScore()==matchLength){
            setWinner("blue");
        }
        propertyChangeSupport.firePropertyChange("matchLength", oldScore, matchScore);
    }

    public Boolean getMatchOver() {
        return matchOver;
    }

    public void setMatchOver(Boolean matchOver) {
        Boolean old = this.matchOver;
        this.matchOver = matchOver;
        propertyChangeSupport.firePropertyChange("matchOver", old, matchOver);
    }



}
