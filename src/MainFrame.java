import java.awt.*;
import javax.swing.*;

//Frame for the game, so far only has a command panel
public class MainFrame extends JFrame{
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    JPanel commandPanel = new CommandPanel();

    public MainFrame() throws HeadlessException {
        //setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Backgammon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(new BorderLayout());
        add(commandPanel, BorderLayout.PAGE_END);
    }
}
