package se.king.mehdi;

import se.king.mehdi.leaderboard.Entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class CmdLineDemo {
    public static void main(String[] args) {
        Anagram game = new Anagram();
        BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your name, the word, and its anagram comma separated.");
        while (true) {
            String line;
            try {
                System.out.print("<name, word, anagram>: ");
                line = inReader.readLine();
                if (line == null || line.isEmpty()) {
                    System.out.println("hej då");
                    break;
                }
                List<String> splited = Arrays.stream(line.split(",")).map(String::trim).collect(Collectors.toList());
                if (splited.size() < 3) {
                    System.out.println("invalid input, try again.");
                    continue;
                }
                String uid = splited.get(0), word = splited.get(1), wordAnagram = splited.get(2);

                int score = game.calculateScore(word, wordAnagram);
                game.submitScore(uid, score);
                List<Entry> leaderboard = game.getLeaderBoard(uid);
                String leaderboardStr = leaderboard.stream()
                        .map(e -> String.format("%d\t%s(%d)%n", e.getPosition(), e.getUid(), e.getScore()))
                        .reduce((a, b) -> a + b).orElse("");

                System.out.printf(score > 0 ? "Your got %d scores.%n" : "It was not an anagram.%n", score);
                System.out.println(leaderboardStr);
            } catch (IOException e) {
                System.err.println("Error reading from standard input.");
                break;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }

        }
    }
}
