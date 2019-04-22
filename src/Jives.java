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
    private double thresholdForDoubling = 57;
    private double thresholdForAcceptingDouble = 35;

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
                    playWeights[i]+=hitWeight(move);
                }else if(move.getToPip()==0){
                    playWeights[i]+=bearOffWeight(move);
                }
                playWeights[i]+=stackWeight(move);
            }
        }
        if (getWinPercentage()>thresholdForDoubling && (!cube.isOwned() || cube.getOwnerId()==me.getId())){
            return "double";
        }
        checkHomeBoard();
        return getBiggestWeight(playWeights);
    }

    public String getDoubleDecision() {

        if(checkHomeBoard() == true) {
            return "y";
        }else if (getWinPercentage()>thresholdForAcceptingDouble){
            return"y";
        }
        return "n";
    }

    public double getWinPercentage(){
        int NUMBER_OF_PIPS_ON_BOARD = 26;
        double myCollectiveDistance = 0;
        double opponentsCollectiveDistance = 0;
        double chanceOfWinning = 0;

        for(int i = 0; i < NUMBER_OF_PIPS_ON_BOARD; i++){
            myCollectiveDistance += i * board.getNumCheckers(me.getId(), i);
            opponentsCollectiveDistance += i * board.getNumCheckers(opponent.getId(), i);
        }

        chanceOfWinning = (opponentsCollectiveDistance / (opponentsCollectiveDistance + myCollectiveDistance)) * 100;
        System.out.println("Chance of Jives winning: "+chanceOfWinning);

        return chanceOfWinning;
    }

    public Boolean checkHomeBoard(){
        int NUMBER_OF_PIPS_ON_BOARD = 26;
        int[] myPiecesOn = new int[NUMBER_OF_PIPS_ON_BOARD];
        int[] opponentsPiecesOn = new int[NUMBER_OF_PIPS_ON_BOARD];
        int combinationOfPieces = 0;

        for(int i = 0; i < NUMBER_OF_PIPS_ON_BOARD; i++){
            myPiecesOn[i] = board.getNumCheckers(me.getId(), i);
            opponentsPiecesOn[i] = board.getNumCheckers(opponent.getId(), i);
        }
        for(int i = 0; i < 7; i++){
            combinationOfPieces += myPiecesOn[i] + opponentsPiecesOn[i];

            if(combinationOfPieces == 30){
                return true;
            }
        }

        return false;
    }



    private Boolean opponentBearOffCheck(){
        if(board.getNumCheckers(opponent.getId(), 0)>0){
            return true;
        }
        return false;
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
        int toPip = move.getToPip();
        int weight;
        if(toPip<6){
            weight = 0;
        }else if(toPip<13){
            weight = 2;
        }else if(toPip<19){
            weight = 3;
        }else{
            weight =4;
        }
        return weight;
    }

    private int stackWeight(Move move){
        int toPip = move.getToPip();
        int fromPip = move.getFromPip();
        int numCheckersOnFrom = board.getNumCheckers(me.getId(),fromPip);
        int numCheckersOnTo = board.getNumCheckers(me.getId(),toPip);
        int weight=0;

        if(contactCheck()){
            if(toPip<6 && numCheckersOnTo==1){
                weight = 4;
            }else if(toPip<13&& numCheckersOnTo==1){
                weight = 3;
            }else if(toPip<19&& numCheckersOnTo==1){
                weight = 2;
            }else if(numCheckersOnTo==1 &&numCheckersOnFrom!=2){
                weight =2;
            }
            if(numCheckersOnFrom>3&&numCheckersOnTo>0){
                weight+=1;
            }
        }
        return weight;
    }

    private Boolean contactCheck(){
        int lastOpponentPip= 0;
        for(int i=0;i<26;i++){
            if(board.getNumCheckers(opponent.getId(),i)>0){
                lastOpponentPip=25-i;
            }
        }

        for(int j=lastOpponentPip;j<26;j++){
            if(board.getNumCheckers(me.getId(),j)>0){
                return true;
            }
        }

        return false;
    }

    private int bearOffWeight(Move move){
        int fromPip = move.getFromPip();
        int numCheckersOnFrom = board.getNumCheckers(me.getId(),fromPip);
        int weight=0;

        if(contactCheck()&&numCheckersOnFrom!=2){
            weight = 5;
        }else{
            weight = 3;
        }
        return weight;
    }

}
