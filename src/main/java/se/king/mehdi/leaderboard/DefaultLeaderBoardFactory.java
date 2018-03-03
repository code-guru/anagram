package se.king.mehdi.leaderboard;

public class DefaultLeaderBoardFactory {
    private static Leaderboard instance = null;

    private DefaultLeaderBoardFactory() {
    }

    public static synchronized Leaderboard getInstance() {
        if(instance == null) {
            instance = new DefaultLeaderboard();
        }

        return instance;
    }

    public static synchronized void destroyInstance() {
        instance = null;
    }
}
