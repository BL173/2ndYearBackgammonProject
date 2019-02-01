import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class CommandPanel extends JPanel{
    private static final int INPUT_FIELD_WIDTH = 10;

    JLabel commandLabel = new JLabel("What is your bidding master?");
    JTextField commandInputField = new JTextField(INPUT_FIELD_WIDTH);
    class inputListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        }
    }


    public CommandPanel() {
        add(label);


    }

}
