public class Score {
    private int redScore=0;
    private int blueScore=0;

    public int getRedScore() {
        return redScore;
    }

    public void setRedScore(int redScore) {
        this.redScore = redScore;
    }

    public int getBlueScore() {
        return blueScore;
    }

    public void setBlueScore(int blueScore) {
        this.blueScore = blueScore;
    }

    public Score(int redScore, int blueScore) {
        this.redScore = redScore;
        this.blueScore = blueScore;
    }
}
