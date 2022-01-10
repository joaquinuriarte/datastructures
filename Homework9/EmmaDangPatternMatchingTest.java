import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * JUnit tests for Homework 9
 * @class CS 1332 A
 * @Semester Spring 2021
 * @Author Emma Dang
 * Resources: PatternMatchingStudentTest.java from homework 9 package
 */
public class EmmaDangPatternMatchingTest {
    private static final int TIME_OUT = 200;
    private CharacterComparator comparator;
    private String emptyString;
    private String simpleString;
    private String kmpWorstPattern;
    private String kmpRandPattern;
    private String kmpRandPattern2;
    private String kmpRandPattern3;
    private String simpleText1;
    private String simpleText2;
    private String simpleText3;
    private String simpleText4;
    private String kmpRandText2;
    private String kmpRandText3;

    private String bmPattern1;
    private String bmPattern2;
    private String bmPattern3;
    private String bmText1;
    private String bmText2;
    private String bmText3;
    private String bmPattern4;
    private String bmText4;

    @Before
    public void setUp() {
        comparator = new CharacterComparator();
        emptyString = "";
        simpleString = "aaaa";
        kmpWorstPattern = "aaab";
        kmpRandPattern = "revararev";
        kmpRandPattern2 = "theathe";
        kmpRandPattern3 = "babaababab";
        simpleText1 = "aaaaaaa";
        simpleText2 = "bbbbbbb";
        simpleText3 = "aaabbbb";
        simpleText4 = "aaacbbb";
        kmpRandText2 = "the-theath-theatheatha";
        kmpRandText3 = "babababababaababab";

        bmPattern1 = "aaab";
        bmText1 = "aaabbbbaaabbabab";
        bmPattern2 = "baaaaaa";
        bmText2 = "aaaaaaaaaaaaaaaa";
        bmPattern3 = "abacab";
        bmText3 = "abacbabadcabacab";
        bmPattern4 = "happy";
        bmText4 = "because im happy";
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testKMPFailureTable1() {
        PatternMatching.buildFailureTable(null, comparator);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testKMPFailureTable2() {
        PatternMatching.buildFailureTable("lol", null);
    }

    @Test(timeout = TIME_OUT)
    public void testKMPFailureTable3() {
        /*
        test empty pattern. expect 0 comparison counts
         */
        int[] failureTable = PatternMatching.buildFailureTable(emptyString, comparator);
        int[] expected = {};
        assertArrayEquals(expected, failureTable);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
            + " while the expected number of counts: " + 0, comparator.getComparisonCount() == 0);
    }

    @Test(timeout = TIME_OUT)
    public void testKMPFailureTable4() {
        int[] failureTable = PatternMatching.buildFailureTable(simpleString, comparator);
        int[] expected = new int[simpleString.length()];
        for (int i = 0; i < simpleString.length(); i++) {
            expected[i] = i;
        }
        int counts = simpleString.length() - 1;
        assertArrayEquals(expected, failureTable);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
            + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testKMPFailureTable5() {
        int[] failureTable = PatternMatching.buildFailureTable(kmpWorstPattern, comparator);
        int[] expected = new int[kmpWorstPattern.length()];
        for (int i = 0; i < kmpWorstPattern.length() - 1; i++) {
            expected[i] = i;
        }
        int counts = (kmpWorstPattern.length() - 1) + (kmpWorstPattern.length() - 2);
        assertArrayEquals(expected, failureTable);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
            + " while the expected counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testKMPFailureTable6() {
        int[] failureTable = PatternMatching.buildFailureTable(kmpRandPattern, comparator);
        int[] expected = new int[kmpRandPattern.length()];
        expected[4] = 1;
        expected[6] = 1;
        expected[7] = 2;
        expected[8] = 3;
        int counts = 9;
        assertArrayEquals(expected, failureTable);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
            + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testKMPFailureTable7() {
        int[] failureTable = PatternMatching.buildFailureTable(kmpRandPattern2, comparator);
        int[] expected = new int[kmpRandPattern2.length()];
        expected[4] = 1;
        expected[5] = 2;
        expected[6] = 3;
        int counts = 6;
        assertArrayEquals(expected, failureTable);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testKMPFailureTable8() {
        int[] failureTable = PatternMatching.buildFailureTable(kmpRandPattern3, comparator);
        int[] expected = new int[kmpRandPattern3.length()];
        expected[2] = 1;
        expected[3] = 2;
        expected[5] = 1;
        expected[6] = 2;
        expected[7] = 3;
        expected[8] = 4;
        expected[9] = 3;
        int counts = 11;
        assertArrayEquals(expected, failureTable);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testKMP1() {
        PatternMatching.kmp("lol", "lol", null);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testKMP2() {
        PatternMatching.kmp(null, "lol", comparator);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testKMP3() {
        PatternMatching.kmp("lol", null, comparator);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testKMP4() {
        PatternMatching.kmp("", "lol", comparator);
    }

    @Test(timeout = TIME_OUT)
    public void testKMP5() {
        List<Integer> actual = PatternMatching.kmp("lol", "", comparator);
        List<Integer> expected = new ArrayList<>();
        int counts = 0;
        assertEquals(expected, actual);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testKMP6() {
        List<Integer> actual = PatternMatching.kmp(simpleString, simpleText1, comparator);
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i <= simpleText1.length() - simpleString.length(); i++) {
            expected.add(i);
        }
        int counts = 10;
        assertEquals(expected, actual);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testKMP7() {
        List<Integer> actual = PatternMatching.kmp(simpleString, simpleText2, comparator);
        List<Integer> expected = new ArrayList<>();
        int counts = 7;
        assertEquals(expected, actual);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testKMP8() {
        List<Integer> actual = PatternMatching.kmp(kmpWorstPattern, simpleText1, comparator);
        List<Integer> expected = new ArrayList<>();
        int counts = 15;
        assertEquals(expected, actual);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testKMP9() {
        List<Integer> actual = PatternMatching.kmp(kmpWorstPattern, simpleText3, comparator);
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        int counts = 9;
        assertEquals(expected, actual);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testKMP10() {
        List<Integer> actual = PatternMatching.kmp(kmpWorstPattern, simpleText4, comparator);
        List<Integer> expected = new ArrayList<>();
        int counts = 12;
        assertEquals(expected, actual);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testKMP11() {
        List<Integer> actual = PatternMatching.kmp(kmpRandPattern2, kmpRandText2, comparator);
        List<Integer> expected = new ArrayList<>();
        expected.add(11);
        int counts = 31;
        assertEquals(expected, actual);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testKMP112() {
        List<Integer> actual = PatternMatching.kmp(kmpRandPattern3, kmpRandText3, comparator);
        List<Integer> expected = new ArrayList<>();
        expected.add(8);
        int counts = 33;
        assertEquals(expected, actual);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testLOT1() {
        PatternMatching.buildLastTable(null);
    }

    @Test(timeout = TIME_OUT)
    public void testLOT2() {
        Map<Character, Integer> lot = PatternMatching.buildLastTable(emptyString);
        Map<Character, Integer> expected = new HashMap<>();
        assertEquals(expected, lot);
    }

    @Test(timeout = TIME_OUT)
    public void testLOT3() {
        Map<Character, Integer> lot = PatternMatching.buildLastTable(simpleString);
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('a', 3);
        assertEquals(expected, lot);
    }

    @Test(timeout = TIME_OUT)
    public void testLOT4() {
        Map<Character, Integer> lot = PatternMatching.buildLastTable(bmPattern1);
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('a', 2);
        expected.put('b', 3);
        assertEquals(expected, lot);
    }

    @Test(timeout = TIME_OUT)
    public void testLOT5() {
        Map<Character, Integer> lot = PatternMatching.buildLastTable(bmPattern2);
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('b',0);
        expected.put('a', 6);
        assertEquals(expected, lot);
    }

    @Test(timeout = TIME_OUT)
    public void testLOT6() {
        Map<Character, Integer> lot = PatternMatching.buildLastTable(bmPattern3);
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('a',4);
        expected.put('b', 5);
        expected.put('c', 3);
        assertEquals(expected, lot);
    }

    @Test(timeout = TIME_OUT)
    public void testLOT7() {
        Map<Character, Integer> lot = PatternMatching.buildLastTable(bmPattern4);
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('a',1);
        expected.put('h', 0);
        expected.put('p', 3);
        expected.put('y', 4);
        assertEquals(expected, lot);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testBM1() {
        PatternMatching.boyerMoore("", "abc", comparator);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testBM2() {
        PatternMatching.boyerMoore(null, "abc", comparator);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testBM3() {
        PatternMatching.boyerMoore("abc", null, comparator);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testBM4() {
        PatternMatching.boyerMoore("abc", "abc", null);
    }

    @Test(timeout = TIME_OUT)
    public void testBM5() {
        List<Integer> actual = PatternMatching.boyerMoore(bmPattern1, bmText1, comparator);
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(7);
        int counts = 27;
        assertEquals(expected, actual);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testBM6() {
        List<Integer> actual = PatternMatching.boyerMoore(bmPattern2, bmText2, comparator);
        List<Integer> expected = new ArrayList<>();
        int counts = 70;
        assertEquals(expected, actual);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testBM7() {
        List<Integer> actual = PatternMatching.boyerMoore(bmPattern3, bmText3, comparator);
        List<Integer> expected = new ArrayList<>();
        expected.add(10);
        int counts = 13;
        assertEquals(expected, actual);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT)
    public void testBM8() {
        List<Integer> actual = PatternMatching.boyerMoore(bmPattern4, bmText4, comparator);
        List<Integer> expected = new ArrayList<>();
        expected.add(11);
        int counts = 8;
        assertEquals(expected, actual);
        assertTrue("The actual number of comparisons: " + comparator.getComparisonCount()
                + " while the expected number of counts: " + counts, comparator.getComparisonCount() == counts);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testRK1() {
        PatternMatching.rabinKarp("", "abc", comparator);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testRK2() {
        PatternMatching.rabinKarp(null, "abc", comparator);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testRK3() {
        PatternMatching.rabinKarp("abc", null, comparator);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testRK4() {
        PatternMatching.rabinKarp("abc", "abc", null);
    }
}
