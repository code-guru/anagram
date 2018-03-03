package se.king.mehdi;

import se.king.mehdi.anagram.Verification;
import se.king.mehdi.leaderboard.DefaultLeaderBoardFactory;
import se.king.mehdi.leaderboard.Entry;
import se.king.mehdi.leaderboard.Leaderboard;

import java.util.List;

public class Anagram implements Game {
    private final Verification verification;
    private final Leaderboard leaderboard;

    public Anagram() {
        verification = new Verification();
        leaderboard = DefaultLeaderBoardFactory.getInstance();
    }

    @Override
    public int calculateScore(String word, String anagram) {
        return verification.calculateScore(word, anagram);
    }

    @Override
    public void submitScore(String uid, int score) {
        leaderboard.submitScore(uid, score);
    }

    @Override
    public List<Entry> getLeaderBoard(String uid) {
        return leaderboard.getLeaderBoard(uid);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        DefaultLeaderBoardFactory.destroyInstance();
    }
}
