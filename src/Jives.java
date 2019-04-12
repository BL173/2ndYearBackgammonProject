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
        return "1";
    }

    public String getDoubleDecision() {
        // Add your code here
        return "n";
    }
}
