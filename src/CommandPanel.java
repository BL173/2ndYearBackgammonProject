/*
Team: Jives
Written by: Brian Leahy 17372896,
            Oscar Byrne Carty 17430786,
            Gearoid Lynch 17459176
 */

import exceptions.InvalidInputException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;


public class CommandPanel extends JPanel{
    private static final int INPUT_FIELD_WIDTH = 15;
    private String userInput;
    private JLabel commandLabel = new JLabel("What is your bidding master? ");
    private JTextField commandInputField = new JTextField(INPUT_FIELD_WIDTH);
    private JButton submitButton;
    private JTextArea resultArea;
    private DefaultUserInputModel userInputModel;
    private boolean bluePlayerInputCheck = false;
    private boolean redPlayerInputCheck = true;
    private int matchLength;
    private Score matchScore = new Score (0,0);
    private Boolean matchOver = false;
    private Boolean newGameCheck =true;

    public String getUserInput() {
        return userInput;
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            userInput = commandInputField.getText();
            String userName=userInput;
            userInput=userInput.toLowerCase();
            commandInputField.setText("");
            if (userInput.equals("quit")) {
                System.exit(0);
            }else if(userInput.equals("new game")||userInput.equals("newgame")) {
                userInputModel.setInfoPanelOutput("How many points would you like to play to?");
                newGameCheck = true;
            }else if(newGameCheck==true){
                try{
                    matchLength = Integer.parseInt(userInput);
                    userInputModel.setInfoPanelOutput("Match Length: "+matchLength);
                    userInputModel.setInfoPanelOutput("\n\nPlease enter player one name: ");
                    userInputModel.setMatchLength(matchLength);
                    userInputModel.setMatchScore(new Score(0,0));
                    redPlayerInputCheck = true;
                    newGameCheck=false;
                }catch(java.lang.NumberFormatException e){
                    userInputModel.setInfoPanelOutput("Invalid input. Please enter a positive integer.");
                    throw new InvalidInputException();
                }
            }else if(redPlayerInputCheck == true) {
                userInputModel.setRedPlayerName(userName);
                userInputModel.setInfoPanelOutput("Welcome " + userInputModel.getRedPlayerName() + ", you are the red player\nPlease enter player two name: ");
                redPlayerInputCheck = false;
                bluePlayerInputCheck = true;
            }else if(bluePlayerInputCheck == true) {
                if(userName.equals(userInputModel.getRedPlayerName())){
                    userInputModel.setInfoPanelOutput("Please pick a different name to the other player.");
                }else{
                    userInputModel.setInfoPanelOutput("Welcome " + userName + ", you are the blue player");
                    userInputModel.setBluePlayerName(userName);
                    bluePlayerInputCheck = false;
                    userInputModel.setUserInput("newgame");
                }

            }else if(userInput.equals("who controls the british crown?")||userInput.equals("who keeps the metric system down?")||userInput.equals("who keeps atlantis off the maps?")||userInput.equals("who keeps the marshians under wraps?")){
                userInputModel.setInfoPanelOutput("We do!");
            }else if (userInput.equals("next")){
                userInputModel.setTurn(1-(userInputModel.getTurn()));
            }
            else {
                userInputModel.setUserInput(userInput);
            }

        }
    }

    public CommandPanel(DefaultUserInputModel userInputModel) {
        setVisible(true);
        setLayout(new BorderLayout());
        //gameDice = new Dice(userInputModel);

        this.userInputModel = userInputModel;
        this.userInputModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("userInput".equals(evt.getPropertyName())){
                    //User input changed
                }
            }
        });

        submitButton = new JButton ("Submit");
        ActionListener inputListener = new Listener();
        submitButton.addActionListener(inputListener);

        commandInputField.addActionListener(inputListener);
        resultArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        resultArea.setEditable(false);
        resultArea.append("Current Commands:\n\n");
        resultArea.append("To move: enter move index (A, B, C...) or pip number to move from followed by pip number to move to\n");
        resultArea.append("next -- skip to next player's turn\n");
        resultArea.append("quit -- exit program\n");
        resultArea.append("newgame -- start new game\n");
        resultArea.append("cheat -- moves all pieces to cheat position\n");

        add(commandLabel, BorderLayout.LINE_START);
        add(commandInputField, BorderLayout.CENTER);
        add(submitButton, BorderLayout.LINE_END);

        add(scrollPane, BorderLayout.PAGE_END);

    }

}
