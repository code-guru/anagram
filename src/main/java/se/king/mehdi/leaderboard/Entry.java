package se.king.mehdi.leaderboard;

import java.util.Objects;

public class Entry {
    private String uid;
    private int score;
    private int position;

    public Entry(String uid, int score) {
        this.uid = uid;
        this.score = score;
    }

    public Entry(String uid, int score, int position) {
        this.uid = uid;
        this.score = score;
        this.position = position;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("Entry{%d:%s(%d)}", position, uid, score);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Entry)) return false;
        Entry entry = (Entry) obj;
        return score == entry.score &&
                position == entry.position &&
                Objects.equals(uid, entry.uid);
    }
}
