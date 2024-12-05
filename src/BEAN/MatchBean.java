package BEAN;

public class MatchBean {
    private String opponent;
    private String date;
    private int ourScore;
    private int opponentScore;

    // Constructor
    public MatchBean(String opponent, String date, int ourScore, int opponentScore) {
        this.opponent = opponent;
        this.date = date;
        this.ourScore = ourScore;
        this.opponentScore = opponentScore;
    }

    // Getters
    public String getOpponent() {
        return opponent;
    }

    public String getDate() {
        return date;
    }

    public int getOurScore() {
        return ourScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }
}
