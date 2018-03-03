package se.king.mehdi;

import se.king.mehdi.leaderboard.Entry;

import java.util.List;

interface Game {
    int calculateScore(String word, String anagram);

    void submitScore(String uid, int score);

    List<Entry> getLeaderBoard(String uid);

}
