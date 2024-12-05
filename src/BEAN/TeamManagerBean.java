package BEAN;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeamManagerBean {
    private ObservableList<PlayerBean> players;
    private ObservableList<MatchBean> matches;

    public TeamManagerBean() {
        players = FXCollections.observableArrayList();
        matches = FXCollections.observableArrayList();
    }

    // Add player
    public void addPlayer(PlayerBean player) {
        players.add(player);
    }

    // Get ObservableList of players
    public ObservableList<PlayerBean> getPlayers() {
        return players;
    }

    // Add match
    public void addMatch(MatchBean match) {
        matches.add(match);
    }

    // Get ObservableList of matches
    public ObservableList<MatchBean> getMatches() {
        return matches;
    }
}
