/*
Team: Jives
Written by: Brian Leahy 17372896,
            Oscar Byrne Carty 17430786,
            Gearoid Lynch 17459176
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class InfoPanel extends JPanel{
    private static final int INPUT_FIELD_WIDTH = 10;

    private JLabel possibleLabel = new JLabel("Possible Turns");
    JTextArea possibleTurns = new JTextArea(10, 25);
    JScrollPane p = new JScrollPane(possibleTurns);
    //private String userInput;
    private JLabel infoLabel = new JLabel("Game Info\n");
    JTextArea previousInputs = new JTextArea(10, 25);
    JScrollPane previousTurns = new JScrollPane(previousInputs);
    private DefaultUserInputModel userInputModel;


    public InfoPanel(DefaultUserInputModel userInputModel) {
        GridLayout infoLayout = new GridLayout(2, 2);
        previousInputs.append("Red Player Turn \n");
        this.userInputModel =userInputModel;
        this.userInputModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if("infoPanelOutput".equals(evt.getPropertyName()) && !userInputModel.getInfoPanelOutput().equals("")){
                    previousInputs.append(userInputModel.getInfoPanelOutput() + "\n");
                }
            }
        });

        infoLayout.setHgap(-50);
        infoLayout.setVgap(10);

        setLayout(infoLayout);
        add(possibleLabel);
        Dimension sizePossible = possibleLabel.getPreferredSize();
        possibleLabel.setBounds(10, 10, sizePossible.width, sizePossible.height);
        add(p);
        possibleTurns.setEditable(false);
        setVisible(true);

        add(infoLabel);

        infoLabel.setVisible(true);
        infoLabel.setPreferredSize(new Dimension(10, 10));
        add(previousTurns);
        previousInputs.setEditable(false);

    }

}
