import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class CommandPanel extends JPanel{
    private static final int INPUT_FIELD_WIDTH = 10;

    private String userInput;
    private JLabel commandLabel = new JLabel("What is your bidding master? ");
    private JTextField commandInputField = new JTextField(INPUT_FIELD_WIDTH);
    private JButton submitButton;
    //private JTextArea resultArea;

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            userInput = commandInputField.getText();
           // resultArea.append(userInput);
        }
    }


    private void createButton()
    {
        submitButton = new JButton ("Submit");
        ActionListener inputListener = new Listener();
        submitButton.addActionListener(inputListener);
    }



    public CommandPanel() {
        setVisible(true);
        add(commandLabel);
        add(commandInputField);
        createButton();
        add(submitButton);
        /* resultArea = new JTextArea(10, 30);
        resultArea.setLocation(100,500);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane);
        scrollPane.setLocation(100,500);*/

    }

}
