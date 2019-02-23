/*
Team: Jives
Written by: Brian Leahy 17372896,
            Oscar Byrne Carty 17430786,
            Gearoid Lynch 17459176
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
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
    private boolean redPlayerInputCheck = false;


    Random rand = new Random();

    int diceOne = (rand.nextInt(6)) + 1;
    int diceTwo = (rand.nextInt(6)) + 1;

    int diceThree = (rand.nextInt(6)) + 1;
    int diceFour = (rand.nextInt(6)) + 1;

    public String getUserInput() {
        return userInput;
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            userInput = commandInputField.getText();
            userInput=userInput.toLowerCase();
            commandInputField.setText("");
            if (userInput.equals("quit")) {
                System.exit(0);
            }else if(userInput.equals("new game")) {
                userInputModel.setInfoPanelOutput("please enter player one name: ");
                redPlayerInputCheck = true;
            }else if(redPlayerInputCheck == true) {
                Players redPlayer = new Players(userInput, 0);
                userInputModel.setInfoPanelOutput("Welcome " + userInput + ", you are the red player\nPlease enter player two name: ");
                redPlayerInputCheck = false;
                bluePlayerInputCheck = true;
            }else if(bluePlayerInputCheck == true) {
                Players bluePlayer = new Players(userInput, 1);
                userInputModel.setInfoPanelOutput("Welcome " + userInput + ", you are the blue player");
                bluePlayerInputCheck = false;
            }else if(userInput.equals("who controls the british crown?")||userInput.equals("who keeps the metric system down?")||userInput.equals("who keeps atlantis off the maps?")||userInput.equals("who keeps the marshians under wraps?")){
                userInputModel.setInfoPanelOutput("We do!");
            }else if (userInput.equals("next")){
                userInputModel.setTurn(1-(userInputModel.getTurn()));
            }else if(userInput.equals("roll")) {
                StartDice();

            }
            else {
                userInputModel.setUserInput(userInput);
            }

        }
    }
    public void StartDice() {

        Random rand = new Random();

        diceOne = (rand.nextInt(6)) + 1;
        diceTwo = (rand.nextInt(6)) + 1;

        diceThree = (rand.nextInt(6)) + 1;
        diceFour = (rand.nextInt(6)) + 1;

        int x = diceOne + diceTwo;
        int y = diceThree + diceFour;

        userInputModel.setInfoPanelOutput("\nPlayer 1 rolls: " + diceOne +" "+ diceTwo + "\nCombined is " + x);
        userInputModel.setInfoPanelOutput("Player 2 rolls: " + diceThree +" "+ diceFour + "\nCombined is " + y);

        if((diceOne + diceTwo) > (diceThree + diceFour)) {
            userInputModel.setInfoPanelOutput("Player 1 goes first:");
            userInputModel.setTurn(0);
        }
        else if((diceOne + diceTwo) < (diceThree + diceFour)) {
            userInputModel.setInfoPanelOutput("Player 2 goes first:");
            userInputModel.setTurn(1);
        }
        else {
            userInputModel.setInfoPanelOutput("Since both rolls are equal, we roll again:\n");
            StartDice();
        }

    }

    public CommandPanel(DefaultUserInputModel userInputModel) {
        setVisible(true);
        setLayout(new BorderLayout());
        //Current Work
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
        //scrollpane is temporary until info panel is finished
        JScrollPane scrollPane = new JScrollPane(resultArea);
        resultArea.setEditable(false);
        resultArea.append("Current Commands:\n\n");
        resultArea.append("quit -- exit program\n\n");
        resultArea.append("new game -- start new game\n\n");
        resultArea.append("roll -- rolls dice to decide who goes first\n\n");



        add(commandLabel, BorderLayout.LINE_START);
        add(commandInputField, BorderLayout.CENTER);
        add(submitButton, BorderLayout.LINE_END);

        add(scrollPane, BorderLayout.PAGE_END);

    }

}
