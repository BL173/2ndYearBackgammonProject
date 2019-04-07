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

public class InputErrorFrame extends JFrame{

    private BufferedImage image;
    private Graphics2D g;
    private JLabel img;
    private JButton exitErrorFrame;
    private JFrame errorFrame;


    public InputErrorFrame() throws HeadlessException {
        errorFrame = new JFrame("Backgammon");
        JPanel errorPanel = new JPanel();
        setSize(450, 150);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        errorPanel.setLayout(new BorderLayout());

        try {
            image = ImageIO.read(this.getClass().getResource("error.png"));
        } catch (IOException e) {
            System.out.println("Could not find the image");
        }

        exitErrorFrame = new JButton("Exit Error Warning");
        exitErrorFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                CloseFrame();
            }
        });


        JLabel img = new JLabel(new ImageIcon(image));
        add(exitErrorFrame, BorderLayout.PAGE_END);
        add(img, BorderLayout.PAGE_START);
        errorFrame.add(errorPanel);

    }

    public void CloseFrame() {
        super.dispose();
    }



}
