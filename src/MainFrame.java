import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame extends JFrame{
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    public MainFrame() throws HeadlessException {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Backgammon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(new BorderLayout());
        JPanel commandPanel = new CommandPanel();
        add(commandPanel, BorderLayout.LINE_START);
    }
}
