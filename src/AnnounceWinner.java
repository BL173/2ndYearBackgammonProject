import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/*
Team: Jives
Written by: Brian Leahy 17372896,
            Oscar Byrne Carty 17430786,
            Gearoid Lynch 17459176
 */
public class AnnounceWinner extends JFrame {

    private static final int INPUT_FIELD_WIDTH = 7;
    private String userInput;
    private JLabel restartLabel = new JLabel("Play Again?(yes/no)");
    private JTextField userInputField = new JTextField(INPUT_FIELD_WIDTH);


    //private JButton restartGame;
    //private JButton exitGame;
    private JFrame winnerFrame;
    private BufferedImage image;
    private Graphics2D g;
    private JLabel img;

    public String getInput() {
        return userInput;
    }

    public class Listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            userInput = userInputField.getText();
            userInput = userInput.toLowerCase();

            if (userInput.equals("no")) {
                System.exit(0);
            } else if (userInput.equals("yes")) {
                restartGame();
            } else {
                JFrame errorFrame = new InputErrorFrame();
                errorFrame.setVisible(true);
            }

        }
    }

    public AnnounceWinner(String winner, int totalBlueWins, int totalRedWins) throws HeadlessException {
        winnerFrame = new JFrame("Backgammon");
        JPanel announcePanel = new JPanel();
        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        announcePanel.setLayout(new BorderLayout());

        ActionListener inputListener = new Listener();
        userInputField.addActionListener(inputListener);

        /*
        restartGame = new JButton("Restart Game");
        restartGame.addActionListener(this);
        exitGame = new JButton("Exit Game");
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        */

        if(totalBlueWins == totalRedWins) {
            try {
                image = ImageIO.read(this.getClass().getResource("draw.png"));
            } catch (IOException e) {
                System.out.println("Could not find the image");
            }
        }else{
            if (winner.equals("red")) {
                //if red wins
                try {
                    image = ImageIO.read(this.getClass().getResource("red.png"));
                } catch (IOException e) {
                    System.out.println("Could not find the image");
                }
            } else if (winner.equals("blue")) {
                try {
                    image = ImageIO.read(this.getClass().getResource("blue.png"));
                } catch (IOException e) {
                    System.out.println("Could not find the image");
                }
            }
        }


        JLabel img = new JLabel(new ImageIcon(image));
        /*
        add(exitGame, BorderLayout.PAGE_END);
        add(restartGame, BorderLayout.CENTER);
        */
        add(restartLabel, BorderLayout.LINE_START);
        add(userInputField, BorderLayout.CENTER);
        add(img, BorderLayout.PAGE_START);
        winnerFrame.add(announcePanel);

    }

    public void restartGame() {
        CloseFrame();
        System.out.println("Frame Closed.");
        JFrame gameFrame = new MainFrame();
        gameFrame.setVisible(true);
    }


    public void CloseFrame() {
        super.dispose();
    }






}

