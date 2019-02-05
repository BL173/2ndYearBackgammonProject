import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class CommandPanel extends JPanel{
    private static final int INPUT_FIELD_WIDTH = 15;

    private String userInput;
    private JLabel commandLabel = new JLabel("What is your bidding master? ");
    private JTextField commandInputField = new JTextField(INPUT_FIELD_WIDTH);
    private JButton submitButton;
    private JTextArea resultArea;

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            userInput = commandInputField.getText();
            commandInputField.setText("");
           resultArea.append(userInput + "\n");
           if (userInput.equals("quit") ||userInput.equals("Quit")||userInput.equals("QUIT")){
               System.exit(0);
           }
        }
    }




    /*private void createSubmitButton()
    {
        submitButton = new JButton ("Submit");
        ActionListener inputListener = new Listener();
        submitButton.addActionListener(inputListener);
    }*/



    public CommandPanel() {
        setVisible(true);
        setLayout(new BorderLayout());

        //createSubmitButton();
        submitButton = new JButton ("Submit");
        ActionListener inputListener = new Listener();
        submitButton.addActionListener(inputListener);

        commandInputField.addActionListener(inputListener);
        resultArea = new JTextArea(10, 30);
        //scrollpane is temporary until info panel is finished
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(commandLabel, BorderLayout.LINE_START);
        add(commandInputField, BorderLayout.CENTER);
        add(submitButton, BorderLayout.LINE_END);

        add(scrollPane, BorderLayout.PAGE_END);

    }

}
