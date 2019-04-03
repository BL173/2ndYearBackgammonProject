import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;


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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
