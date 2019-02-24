
import java.util.Random;

public class Dice {

    private DefaultUserInputModel userInputModel;
    private int turn;
    private int diceOne;
    private int diceTwo;


    public Dice(DefaultUserInputModel userInputModel, int diceOne, int diceTwo, int turn) {
        this.userInputModel = userInputModel;
        this.diceOne = diceOne;
        this.diceTwo = diceTwo;
        this.turn = turn;
    }

}
