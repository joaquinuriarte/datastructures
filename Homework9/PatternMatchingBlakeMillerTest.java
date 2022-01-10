import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author: Blake Miller
 * Code may look similar the TA tests'.
 */

public class PatternMatchingBlakeMillerTest {

    private static final int TIMEOUT = 200;

    private String randomPattern;
    private String randomText;
    private String randomNoMatch;
    private List<Integer> randomAnswer;

    private String kmpPattern;
    private String kmpText;
    private String kmpNoMatch;
    private List<Integer> kmpAnswer;

    private String multiplePattern;
    private String multipleText;
    private String multipleNoMatch;
    private List<Integer> multipleAnswer;

    private String duplicatePattern;
    private String duplicateText;
    private List<Integer> duplicateAnswer;

    private List<Integer> emptyList;

    private CharacterComparator comparator;

    @Before
    public void setUp() {
        randomPattern = "djff";
        randomText = "djfsklaksdfjfdslksdjffaosdf";
        randomNoMatch = "diadfslifdskcdsalifjldsikh";
        randomAnswer = new ArrayList<>();
        randomAnswer.add(18);

        kmpPattern = "testestt";
        kmpText = "this is a test testestt this is a test";
        kmpNoMatch = "no testest matches test here testestest";
        kmpAnswer = new ArrayList<>();
        kmpAnswer.add(15);

        multiplePattern = "vvvvb";
        multipleText = "vvvvbavvvvbvvvvbeeevvvvb";
        multipleNoMatch = "vbvvbvvbbvbvvvbbvvvv";
        multipleAnswer = new ArrayList<>();
        multipleAnswer.add(0);
        multipleAnswer.add(6);
        multipleAnswer.add(11);
        multipleAnswer.add(19);

        duplicatePattern = "vv";
        duplicateText = "vvvvvvvv";
        duplicateAnswer = new ArrayList<>();
        duplicateAnswer.add(0);
        duplicateAnswer.add(1);
        duplicateAnswer.add(2);
        duplicateAnswer.add(3);
        duplicateAnswer.add(4);
        duplicateAnswer.add(5);
        duplicateAnswer.add(6);

        comparator = new CharacterComparator();

        emptyList = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testBuildFailureTable() {
        int[] failureTable = PatternMatching
                .buildFailureTable(randomPattern, comparator);
        int[] expected = {0, 0, 0, 0};
        assertArrayEquals(expected, failureTable);
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                3, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildFailureTable2() {
        int[] failureTable = PatternMatching
                .buildFailureTable(kmpPattern, comparator);
        int[] expected = {0, 0, 0, 1, 2, 3, 4, 1};
        assertArrayEquals(expected, failureTable);
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                9, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildFailureTable3() {
        int[] failureTable = PatternMatching
                .buildFailureTable(multiplePattern, comparator);
        int[] expected = {0, 1, 2, 3, 0};
        assertArrayEquals(expected, failureTable);
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                7, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildFailureTable4() {
        int[] failureTable = PatternMatching
                .buildFailureTable(duplicatePattern, comparator);
        int[] expected = {0, 1};
        assertArrayEquals(expected, failureTable);
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                1, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP() {
        assertEquals(randomAnswer,
                PatternMatching.kmp(randomPattern, randomText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                30, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP2() {
        assertEquals(kmpAnswer,
                PatternMatching.kmp(kmpPattern, kmpText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                45, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP3() {
        assertEquals(multipleAnswer,
                PatternMatching.kmp(multiplePattern, multipleText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                31, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP4() {
        assertEquals(duplicateAnswer,
                PatternMatching.kmp(duplicatePattern, duplicateText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                9, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMPNoMatch() {
        assertEquals(emptyList,
                PatternMatching.kmp(randomPattern, randomNoMatch, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                31, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMPNoMatch2() {
        assertEquals(emptyList,
                PatternMatching.kmp(kmpPattern, kmpNoMatch, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                52, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMPNoMatch3() {
        assertEquals(emptyList,
                PatternMatching.kmp(randomPattern, kmpNoMatch, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                39, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable() {
        Map<Character, Integer> lastTable = PatternMatching
                .buildLastTable(randomPattern);
        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('d', 0);
        expectedLastTable.put('j', 1);
        expectedLastTable.put('f', 3);
        assertEquals(expectedLastTable, lastTable);
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMoore() {
        assertEquals(randomAnswer,
                PatternMatching.boyerMoore(randomPattern, randomText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                14, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable2() {
        Map<Character, Integer> lastTable = PatternMatching
                .buildLastTable(kmpPattern);
        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('t', 7);
        expectedLastTable.put('e', 4);
        expectedLastTable.put('s', 5);
        assertEquals(expectedLastTable, lastTable);
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMoore2() {
        assertEquals(kmpAnswer,
                PatternMatching.boyerMoore(kmpPattern, kmpText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                13, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable3() {
        Map<Character, Integer> lastTable = PatternMatching
                .buildLastTable(multiplePattern);
        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('v', 3);
        expectedLastTable.put('b', 4);
        assertEquals(expectedLastTable, lastTable);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable4() {
        Map<Character, Integer> lastTable = PatternMatching
                .buildLastTable(duplicatePattern);
        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('v', 1);
        assertEquals(expectedLastTable, lastTable);
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMoore3() {
        assertEquals(multipleAnswer,
                PatternMatching.boyerMoore(multiplePattern, multipleText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                28, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMoore4() {
        assertEquals(duplicateAnswer,
                PatternMatching.boyerMoore(duplicatePattern, duplicateText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                14, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreNoMatch() {
        assertEquals(emptyList,
                PatternMatching.boyerMoore(randomPattern, randomNoMatch, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                8, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreNoMatch2() {
        assertEquals(emptyList,
                PatternMatching.boyerMoore(kmpPattern, kmpNoMatch, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                6, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreNoMatch3() {
        assertEquals(emptyList,
                PatternMatching.boyerMoore(multiplePattern, multipleNoMatch, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount(),
                30, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpEqualHash() {
        List<Integer> answer = new ArrayList<>();
        answer.add(4);
        answer.add(8);

        // Hash: 227
        assertEquals(answer,
                PatternMatching.rabinKarp("\u0000\u0002\u0001",
                        "\u0003\u0000\u0000\u00E3\u0000\u0002\u0001\u0005\u0000\u0002\u0001", comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 8.", 8, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpNoMatches() {
        List<Integer> answer = new ArrayList<>();

        // Hash: 113
        assertEquals(answer,
                PatternMatching.rabinKarp("\u0000\u0000\u0071",
                        "\u0002\u0005\u0001\u0000\u0001\u0000\u0005\u0004\u0003\u0000\u0000\u0001\u0001", comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 2.", 2, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarp() {
        assertEquals(randomAnswer,
                PatternMatching.rabinKarp(randomPattern, randomText, comparator));
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarp2() {
        assertEquals(kmpAnswer,
                PatternMatching.rabinKarp(kmpPattern, kmpText, comparator));
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarp3() {
        assertEquals(multipleAnswer,
                PatternMatching.rabinKarp(multiplePattern, multipleText, comparator));
    }

    @Test(timeout = 1000)
    public void testBruteForceKMP() {
        for (int iteration = 0; iteration < 1000; iteration++) {
            int oldLoc = 0;

            String generatedText = "";
            String generatedPattern = "";

            int randPatternSize = ((int) (Math.random() * 5)) + 1;
            int randTextSize = ((int) (Math.random() * 1000)) + 1;


            for (int i = 0; i < randPatternSize; i++) {
                int alphabetSize = ((int) (Math.random() * 7));
                char randInt = ((char) (Math.random() * alphabetSize));
                char generatedChar = (char) ('a' + randInt);
                generatedPattern = generatedPattern.concat("" + generatedChar);
            }
            for (int i = 0; i < randTextSize; i++) {
                int alphabetSize = ((int) (Math.random() * 7));
                char randInt = ((char) (Math.random() * alphabetSize));
                char generatedChar = (char) ('a' + randInt);
                generatedText = generatedText.concat("" + generatedChar);
            }

            System.out.println("Pattern: " + generatedPattern);
            System.out.println(" Text: " + generatedText);

            ArrayList<Integer> result = new ArrayList<>();
            String backGeneratedText = generatedText;
            while (backGeneratedText.contains(generatedPattern)) {
                int loc = backGeneratedText.indexOf(generatedPattern);
                oldLoc += loc;
                result.add(oldLoc);
                oldLoc++;
                backGeneratedText = backGeneratedText.substring(loc + 1);
            }

            System.out.println(" Occurrences: " + result.size());
            assertEquals(result,
                    PatternMatching.kmp(generatedPattern, generatedText, comparator));
        }
    }

    @Test(timeout = 1000)
    public void testBruteForceBoyerMoore() {
        for (int iteration = 0; iteration < 1000; iteration++) {
            int oldLoc = 0;

            String generatedText = "";
            String generatedPattern = "";

            int randPatternSize = ((int) (Math.random() * 5)) + 1;
            int randTextSize = ((int) (Math.random() * 1000)) + 1;


            for (int i = 0; i < randPatternSize; i++) {
                int alphabetSize = ((int) (Math.random() * 7));
                char randInt = ((char) (Math.random() * alphabetSize));
                char generatedChar = (char) ('a' + randInt);
                generatedPattern = generatedPattern.concat("" + generatedChar);
            }
            for (int i = 0; i < randTextSize; i++) {
                int alphabetSize = ((int) (Math.random() * 7));
                char randInt = ((char) (Math.random() * alphabetSize));
                char generatedChar = (char) ('a' + randInt);
                generatedText = generatedText.concat("" + generatedChar);
            }

            System.out.println("Pattern: " + generatedPattern);
            System.out.println(" Text: " + generatedText);

            ArrayList<Integer> result = new ArrayList<>();
            String backGeneratedText = generatedText;
            while (backGeneratedText.contains(generatedPattern)) {
                int loc = backGeneratedText.indexOf(generatedPattern);
                oldLoc += loc;
                result.add(oldLoc);
                oldLoc++;
                backGeneratedText = backGeneratedText.substring(loc + 1);
            }

            System.out.println(" Occurrences: " + result.size());
            assertEquals(result,
                    PatternMatching.boyerMoore(generatedPattern, generatedText, comparator));
        }
    }

    @Test(timeout = 1000)
    public void testBruteForceRabinKarp() {
        for (int iteration = 0; iteration < 1000; iteration++) {
            int oldLoc = 0;

            String generatedText = "";
            String generatedPattern = "";

            int randPatternSize = ((int) (Math.random() * 5)) + 1;
            int randTextSize = ((int) (Math.random() * 1000)) + 1;


            for (int i = 0; i < randPatternSize; i++) {
                int alphabetSize = ((int) (Math.random() * 7));
                char randInt = ((char) ('a' + Math.random() * alphabetSize));
                char generatedChar = randInt;
                generatedPattern = generatedPattern.concat("" + generatedChar);
            }
            for (int i = 0; i < randTextSize; i++) {
                int alphabetSize = ((int) (Math.random() * 7));
                char randInt = ((char) ('a' + Math.random() * alphabetSize));
                char generatedChar = randInt;
                generatedText = generatedText.concat("" + generatedChar);
            }

            System.out.println("Pattern: " + generatedPattern);
            System.out.println(" Text: " + generatedText);

            ArrayList<Integer> result = new ArrayList<>();
            String backGeneratedText = generatedText;
            while (backGeneratedText.contains(generatedPattern)) {
                int loc = backGeneratedText.indexOf(generatedPattern);
                oldLoc += loc;
                result.add(oldLoc);
                oldLoc++;
                backGeneratedText = backGeneratedText.substring(loc + 1);
            }

            System.out.println(" Occurrences: " + result.size());
            assertEquals(result,
                    PatternMatching.rabinKarp(generatedPattern, generatedText, comparator));
        }
    }

}