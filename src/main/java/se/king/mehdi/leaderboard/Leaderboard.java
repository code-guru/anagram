package se.king.mehdi.leaderboard;

import java.util.List;

public interface Leaderboard {

    void submitScore(String uid, int score);

    List<Entry> getLeaderBoard(String uid);

}
