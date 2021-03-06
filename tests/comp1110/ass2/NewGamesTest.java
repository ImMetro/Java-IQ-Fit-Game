package comp1110.ass2;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;

public class NewGamesTest {
    public int getDifficulty(Games objective) {
        for (int i = 0; i < Games.SOLUTIONS.length; i++) {
            if (objective == Games.SOLUTIONS[i])
                return i / (Games.SOLUTIONS.length/4);
        }
        return -1;
    }

    private int countObjectives(Games[] objectives) {
        HashSet<Games> set = new HashSet<>();
        for (Games o : objectives)
            set.add(o);
        return set.size();
    }

    private void doTest(int difficulty) {
        Games[] out = new Games[30];
        for (int i = 0; i < out.length; i++) {
            out[i] = Games.newObjective(difficulty);
            int diff = getDifficulty(out[i]);
            assertTrue("Expected difficulty " + difficulty + ", but " + (diff == -1 ? "did not get one from the prepared objectives" : "got one of difficulty " + diff) + ": problem number " + out[i].getProblemNumber() + ".", diff == difficulty);
        }
        int unique = countObjectives(out);
        assertTrue("Expected at least 3 different objectives after calling newObjective() 12 times, but only got " + unique + ".", unique >= 3);
    }

    @Test
    public void testNothing() {
        doTest(0);
    }

    @Test
    public void testSmall() {
        doTest(1);
    }

    @Test
    public void testAverage() {
        doTest(2);
    }

    @Test
    public void testBig() {
        doTest(3);
    }

}
