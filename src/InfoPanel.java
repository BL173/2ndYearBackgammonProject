import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InfoPanel extends JPanel{

    private JLabel possibleLabel = new JLabel("Possible Turns");
    JTextArea possibleTurns = new JTextArea(10, 25);
    JScrollPane p = new JScrollPane(possibleTurns);
    private String userInput;
    private JLabel infoLabel = new JLabel("Previous Inputs \n");
    JTextArea previousInputs = new JTextArea(10, 25);
    JScrollPane previousTurns = new JScrollPane(previousInputs);

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            userInput = previousInputs.getText();
            previousInputs.setText("");
            //previousTurns.append(userInput + "\n");
        }
    }

    public InfoPanel() {
        GridLayout infoLayout = new GridLayout(2, 2);
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
