/*
*   Team Jives
*   Brian Leahy
*   Oscar Byrne Carty
*   Gearoid Lynch
*/

public class Jives implements BotAPI {

    private PlayerAPI me, opponent;
    private BoardAPI board;
    private CubeAPI cube;
    private MatchAPI match;
    private InfoPanelAPI info;

    public Jives(PlayerAPI me, PlayerAPI opponent, BoardAPI board, CubeAPI cube, MatchAPI match, InfoPanelAPI info) {
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.cube = cube;
        this.match = match;
        this.info = info;
    }

    public String getName() {
        return "Jives"; // must match the class name
    }

    public String getCommand(Plays possiblePlays) {
        // Add your code here
        int[] playWeights= new int[possiblePlays.number()];
        for(int i=0;i<playWeights.length;i++){
            Play play = possiblePlays.get(i);
            playWeights[i]=1;
            for(int j=0;j<play.numberOfMoves();j++){
                Move move = play.moves.get(j);
                if(move.isHit()){
                    playWeights[i]+=2;
                }
            }
        }
        return getBiggestWeight(playWeights);
    }

    public String getDoubleDecision() {
        // Add your code here
        return "n";
    }

    private String getBiggestWeight(int weights[]){
        int biggestWeight=0;
        Integer biggestWeightPosition=0;
        for(int i=0;i<weights.length;i++){
            if(weights[i]>biggestWeight){
                biggestWeight=weights[i];
                biggestWeightPosition=i;
            }
        }
        //adjust for starting from 1 instead of zero
        biggestWeightPosition+=1;
        String biggestWeightString =biggestWeightPosition.toString();
        return biggestWeightString;
    }

    private int hitWeight(Move move){
        return 0;
    }
}
