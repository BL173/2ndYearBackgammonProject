import java.awt.*;
import javax.swing.*;


public class MainFrame extends JFrame{
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    JPanel commandPanel = new CommandPanel();
    JPanel gameBoardPanel = new GameBoardPanel();
    JPanel infoPanel = new InfoPanel();


    public MainFrame() throws HeadlessException {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Backgammon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(new BorderLayout());
        add(commandPanel, BorderLayout.PAGE_END);
        add(gameBoardPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.LINE_START);
    }
}
