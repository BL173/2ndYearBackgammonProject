import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

/*
Team: Jives
Written by: Brian Leahy 17372896,
            Oscar Byrne Carty 17430786,
            Gearoid Lynch 17459176
 */
public class AnnounceWinner extends JFrame implements ActionListener{

    private JButton restartGame;
    private JButton exitGame;
    private JFrame winnerFrame;
    private BufferedImage image;
    private Graphics2D g;
    private JLabel img;

    public AnnounceWinner(String x) throws HeadlessException {
        winnerFrame = new JFrame("Backgammon");
        JPanel announcePanel = new JPanel();
        setSize(900, 735);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        announcePanel.setLayout(new BorderLayout());
        restartGame = new JButton("Restart Game");
        restartGame.addActionListener(this);
        exitGame = new JButton("Exit Game");
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        if(x.equals("red")) {
            //if red wins
            try {
                image = ImageIO.read(this.getClass().getResource("red.png"));
            } catch (IOException e) {
                System.out.println("Could not find the image");
            }
        }else if(x.equals("blue")){
            try {
                image = ImageIO.read(this.getClass().getResource("blue.png"));
            } catch (IOException e) {
                System.out.println("Could not find the image");
            }
        }


        JLabel img = new JLabel(new ImageIcon(image));
        add(exitGame, BorderLayout.PAGE_END);
        add(restartGame, BorderLayout.CENTER);
        add(img, BorderLayout.PAGE_START);
        winnerFrame.add(announcePanel);

    }

    public void actionPerformed(ActionEvent e)
    {
        restartGame = (JButton)e.getSource();
        CloseFrame();
        System.out.println("Frame Closed.");
        JFrame gameFrame = new MainFrame();
        gameFrame.setVisible(true);
    }


    public void CloseFrame() {
        super.dispose();
    }






}

