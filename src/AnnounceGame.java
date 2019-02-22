import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;


public class AnnounceGame extends JFrame implements ActionListener{

    private JButton startGame;
    private JFrame announceFrame;
    private BufferedImage image;
    private Graphics2D g;
    private JLabel img;


    public AnnounceGame() throws HeadlessException {
        announceFrame = new JFrame("Backgammon");
        JPanel announcePanel = new JPanel();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        announcePanel.setLayout(new BorderLayout());
        startGame = new JButton("Start Game");
        startGame.addActionListener(this);
        try {
            image = ImageIO.read(this.getClass().getResource("title.png"));
        } catch (IOException e) {
            System.out.println("Could not find the image");
        }
        JLabel img = new JLabel(new ImageIcon(image));
        add(startGame, BorderLayout.PAGE_END);
        add(img, BorderLayout.PAGE_START);
        announceFrame.add(announcePanel);

    }

    public void actionPerformed(ActionEvent e)
    {
        startGame = (JButton)e.getSource();
        CloseFrame();
        System.out.println("Frame Closed.");
    }

    public static void main(String args[]){
        AnnounceGame test = new AnnounceGame();
        test.setVisible(true);
    }

    public void CloseFrame() {
        super.dispose();
    }






}



