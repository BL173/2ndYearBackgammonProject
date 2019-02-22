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
import javax.swing.*;


public class CommandPanel extends JPanel{
    private static final int INPUT_FIELD_WIDTH = 15;
    private String userInput;
    private JLabel commandLabel = new JLabel("What is your bidding master? ");
    private JTextField commandInputField = new JTextField(INPUT_FIELD_WIDTH);
    private JButton submitButton;
    private JTextArea resultArea;
    private DefaultUserInputModel userInputModel;


    public String getUserInput() {
        return userInput;
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            userInput = commandInputField.getText();
            userInput=userInput.toLowerCase();
            commandInputField.setText("");

            if (userInput.equals("quit")){
                System.exit(0);
            }else if(userInput.equals("who controls the british crown?")||userInput.equals("who keeps the metric system down?")||userInput.equals("who keeps atlantis off the maps?")||userInput.equals("who keeps the marshians under wraps?")){
                userInputModel.setInfoPanelOutput("We do!");
            }else if (userInput.equals("next")){
                userInputModel.setTurn(1-(userInputModel.getTurn()));
            }else {
                userInputModel.setUserInput(userInput);
            }

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
        resultArea.append("Current Commands:\n\n");
        resultArea.append("To move: enter pip number to move from followed by pip number to move to\n");
        resultArea.append("next -- skip to next player's turn\n");
        resultArea.append("quit -- exit program\n");

        add(commandLabel, BorderLayout.LINE_START);
        add(commandInputField, BorderLayout.CENTER);
        add(submitButton, BorderLayout.LINE_END);

        add(scrollPane, BorderLayout.PAGE_END);

    }

}
