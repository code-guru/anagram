package se.king.mehdi;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import se.king.mehdi.leaderboard.Entry;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AnagramTest {
    private Anagram anagram;

    @Before
    public void setUp() {
        anagram = new Anagram();
    }

    @After
    public void tearDown() {
        anagram = null;
        System.gc();
    }

    @Test(expected = NullPointerException.class)
    public void calculateScore_wordIsNull() {
        anagram.calculateScore(null, "abcd");
    }

    @Test(expected = NullPointerException.class)
    public void calculateScore_anagramIsNull() {
        anagram.calculateScore("abcd", null);
    }

    @Test(expected = NullPointerException.class)
    public void calculateScore_wordIsEmpty() {
        anagram.calculateScore("", "abcd");
    }

    @Test(expected = NullPointerException.class)
    public void calculateScore_anagramIsEmpty() {
        anagram.calculateScore("abcd", "");
    }

    @Test
    public void calculateScore_isNotAnagram() {
        int score = anagram.calculateScore("abcd", "efhg");
        assertThat(score, is(equalTo(0)));

    }

    @Test
    public void calculateScore_isAnagram() {
        Object[][] testCases = new Object[][]{
                {"ad", "da", 2}, {"afh", "haf", 3}, {"ayya", "yaya", 4}, {"oputo", "potuo", 5},
                {"alkaja", "aajalk", 6}, {"aaktiii", "akiaiti", 7}, {"åööpqwwp", "pwpwqöåö", 8}
        };
        for (Object[] testData : testCases) {
            String testWord = (String) testData[0], testAnagram = (String) testData[1];
            int expectedScore = (int) testData[2];
            int score = anagram.calculateScore(testWord, testAnagram);
            assertThat(score, is(equalTo(expectedScore)));
        }
    }

    @Test
    public void calculateScore_acceptBlackSpace() {
        int score = anagram.calculateScore("abcd efgh", "hgf edc ab");
        assertThat(score, is(equalTo(9)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateScore_dontAcceptDigit() {
        anagram.calculateScore("adfkjh234", "adsfadf");
    }


    @Test
    public void submitScore() {
        anagram.submitScore("Mehdi", 12);
    }

    @Test
    public void getLeaderBoard_onTop() { // Maximum two entries under the user which make 3 entries
        submitTestScores();
        List<Entry> leaderboard = anagram.getLeaderBoard("Ashton");
        assertThat(leaderboard.size(), is(3));
        assertThat(leaderboard.get(0), is(equalTo(new Entry("Ashton", 11,1))));
        assertThat(leaderboard.get(1), is(equalTo(new Entry("Filiberto", 7,2))));
        assertThat(leaderboard.get(2), is(equalTo(new Entry("Prince", 6,3))));
    }

    @Test
    public void getLeaderBoard_atBottom() {// Maximum two entries above the user which make 3 entries
        submitTestScores();
        List<Entry> leaderboard = anagram.getLeaderBoard("Zulema");
        assertThat(leaderboard.size(), is(3));
        assertThat(leaderboard.get(0), is(equalTo(new Entry("Inga", 4,4))));
        assertThat(leaderboard.get(1), is(equalTo(new Entry("Lemuel", 2,5))));
        assertThat(leaderboard.get(2), is(equalTo(new Entry("Zulema", 0,6))));
    }

    @Test
    public void getLeaderBoard_inMiddle() {
        submitTestScores();
        List<Entry> leaderboard = anagram.getLeaderBoard("Prince");
        assertThat(leaderboard.size(), is(5));
        assertThat(leaderboard.get(0), is(equalTo(new Entry("Ashton", 11,1))));
        assertThat(leaderboard.get(1), is(equalTo(new Entry("Filiberto", 7,2))));
        assertThat(leaderboard.get(2), is(equalTo(new Entry("Prince", 6,3))));
        assertThat(leaderboard.get(3), is(equalTo(new Entry("Inga", 4,4))));
        assertThat(leaderboard.get(4), is(equalTo(new Entry("Lemuel", 2,5))));
    }

    @Test
    public void getLeaderBoard_afterFirst() {
        submitTestScores();
        List<Entry> leaderboard = anagram.getLeaderBoard("Filiberto");
        assertThat(leaderboard.size(), is(4));
        assertThat(leaderboard.get(0), is(equalTo(new Entry("Ashton", 11,1))));
        assertThat(leaderboard.get(1), is(equalTo(new Entry("Filiberto", 7,2))));
        assertThat(leaderboard.get(2), is(equalTo(new Entry("Prince", 6,3))));
        assertThat(leaderboard.get(3), is(equalTo(new Entry("Inga", 4,4))));

    }

    @Test
    public void getLeaderBoard_beforeLast() {
        submitTestScores();
        List<Entry> leaderboard = anagram.getLeaderBoard("Lemuel");
        assertThat(leaderboard.size(), is(4));
        assertThat(leaderboard.get(0), is(equalTo(new Entry("Prince", 6,3))));
        assertThat(leaderboard.get(1), is(equalTo(new Entry("Inga", 4,4))));
        assertThat(leaderboard.get(2), is(equalTo(new Entry("Lemuel", 2,5))));
        assertThat(leaderboard.get(3), is(equalTo(new Entry("Zulema", 0,6))));
    }

    private void submitTestScores() {
        Object[][] scores = new Object[][]{
                {"Zulema", 0}, {"Lemuel", 2}, {"Filiberto", 7}, {"Prince", 6}, {"Inga", 4}, {"Ashton", 11}
        };
        for(Object[] scoreDate : scores) {
            String uid = (String) scoreDate[0];
            int score = (int) scoreDate[1];
            anagram.submitScore(uid, score);
        }
    }
}
