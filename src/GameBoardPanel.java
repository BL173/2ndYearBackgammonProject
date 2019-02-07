import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameBoardPanel extends JPanel {
    private final int BOARDWIDTH = 1068;
    private final int BOARDHEIGHT = 627;
    private BufferedImage BoardImageInput;
    JLabel boardImage;



    public GameBoardPanel() {
        try {
            BoardImageInput = ImageIO.read(this.getClass().getResource("GameBoard.png"));
        } catch (IOException ex) {
            System.out.println("Could not find the image file " + ex.toString());
        }
        Image resizedBoardImage = BoardImageInput.getScaledInstance(BOARDWIDTH,BOARDHEIGHT,Image.SCALE_SMOOTH);

        boardImage=new JLabel(new ImageIcon(resizedBoardImage));
        setVisible(true);
        add(boardImage);


    }

}