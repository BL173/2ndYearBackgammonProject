import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MainFrame extends JFrame{
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private String userInput;
    DefaultUserInputModel userInputModel = new DefaultUserInputModel();
    JPanel commandPanel = new CommandPanel(userInputModel);
    JPanel gameBoardPanel = new GameBoardPanel();
    JPanel infoPanel = new InfoPanel(userInputModel);

    public MainFrame() throws HeadlessException {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Backgammon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(new BorderLayout());
        add(commandPanel, BorderLayout.PAGE_END);
        add(gameBoardPanel, BorderLayout.LINE_END);
        add(infoPanel, BorderLayout.LINE_START);
    }


}
