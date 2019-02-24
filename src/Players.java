import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTextField;

public class Players {
    private String playerName;
    private int playerColour;

    Players(String playerName, int playerColour){
        this.playerName = playerName;
        this.playerColour = playerColour;
    }




    /*private DefaultUserInputModel userInputModel;

    private String userInput;
    private static final int INPUT_FIELD_WIDTH = 15;
    private JTextField commandInputField;



   public Players(DefaultUserInputModel userInputModel, JTextField commandInputField){
        this.userInputModel = userInputModel;
        this.commandInputField = commandInputField;
        this.userInputModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                commandInputField.setText("please enter player 1 name: ");
                userInput = commandInputField.getText();
                commandInputField.setText("");
                if ("userInput".equals(evt.getPropertyName()) && !userInputModel.getUserInput().equals("")){
                    setPlayerName(userInput);
                }
            }
        });
    }

    private void userInput(String userInput){
        this.userInput = userInput;
    }*/




    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public void setPlayerColour(int playerColour){
        this.playerColour = playerColour;
    }

    public String getPlayerName(){
        return playerName;
    }

}
