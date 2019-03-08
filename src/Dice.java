
import java.util.Random;
/*
Team: Jives
Written by: Brian Leahy 17372896,
            Oscar Byrne Carty 17430786,
            Gearoid Lynch 17459176
 */
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

<<<<<<< HEAD
=======
    public void RollDice(int turn) {

        Random rand = new Random();
        diceOne = (rand.nextInt(6)) + 1;
        diceTwo = (rand.nextInt(6)) + 1;
        if (userInputModel.getTurn()==1){
          userInputModel.setInfoPanelOutput(userInputModel.getRedPlayerName()+ " rolls:");
        }else{
            userInputModel.setInfoPanelOutput(userInputModel.getBluePlayerName()+ " rolls:");
        }
        userInputModel.setInfoPanelOutput("Dice One: " + diceOne);
        userInputModel.setInfoPanelOutput("Dice Two: " + diceTwo);
    }
>>>>>>> ba3a4beb9738c6a25cc7a5fd1b550dd1b45d37cb
}
