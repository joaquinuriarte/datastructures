import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Set of tests for HW09: Pattern Matching
 * 
 * Please let me know if anything can be improved/fixed!
 * 
 * I hope these help!
 * 
 * @author Liam Jones
 * @version 1.01
 */
public class LiamPatternMatchingTest {

    private static final int TIMEOUT = 200;

    private String fireText;
    private String firePattern1;
    private String firePattern2;
    private String firePattern3;
    private String nonExistent;
    private ArrayList<Integer> fireAnswer1;
    private ArrayList<Integer> fireAnswer2;
    private ArrayList<Integer> fireAnswer3;
    
    private String almaMaterText;
    private String almaMaterPattern1;
    private String almaMaterPattern2;
    private String almaMaterPattern3;
    private ArrayList<Integer> almaMaterAnswer1;
    private ArrayList<Integer> almaMaterAnswer2;
    private ArrayList<Integer> almaMaterAnswer3;

    private CharacterComparator comparator;

    @Before
    public void setUp() {
        fireText = "Do you ever feel like a plastic bag\n" +
                "Drifting through the wind, wanting to start again?\n" +
                "Do you ever feel, feel so paper thin\n" +
                "Like a house of cards, one blow from cavin' in?\n" +
                "Do you ever feel already buried deep?\n" +
                "Six feet under screams, but no one seems to hear a thing\n" +
                "Do you know that there's still a chance for you?\n" +
                "'Cause there's a spark in you\n" +
                "You just gotta ignite the light\n" +
                "And let it shine\n" +
                "Just own the night\n" +
                "Like the Fourth of July\n" +
                "'Cause baby, you're a firework\n" +
                "Come on, show 'em what you're worth\n" +
                "Make 'em go, \"Oh, oh, oh\"\n" +
                "As you shoot across the sky\n" +
                "Baby, you're a firework\n" +
                "Come on, let your colors burst\n" +
                "Make 'em go, \"Oh, oh, oh\"\n" +
                "You're gonna leave 'em all in awe, awe, awe";

        firePattern1 = "you";
        fireAnswer1 = new ArrayList<>();
        fireAnswer1.add(3);
        fireAnswer1.add(90);
        fireAnswer1.add(175);
        fireAnswer1.add(270);
        fireAnswer1.add(311);
        fireAnswer1.add(342);
        fireAnswer1.add(451);
        fireAnswer1.add(492);
        fireAnswer1.add(534);
        fireAnswer1.add(565);
        fireAnswer1.add(596);

        firePattern2 = "Do";
        fireAnswer2 = new ArrayList<>();
        fireAnswer2.add(0);
        fireAnswer2.add(87);
        fireAnswer2.add(172);
        fireAnswer2.add(267);

        firePattern3 = "show";
        fireAnswer3 = new ArrayList<>();
        fireAnswer3.add(478);

        nonExistent = "cake";
        //Will return an empty ArrayList
        
        almaMaterText = "Oh sons of Tech arise behold\n" +
                "The banner as it reigns supreme\n" +
                "For from on high the White and Gold\n" +
                "Waves in its triumphant gleam,\n" +
                "The spirit of the cheering Throng\n" +
                "Resounds with joy revealing\n" +
                "A brotherhood in praise and song\n" +
                "In memory of the days gone by\n" +
                "Oh, Scion of the Southland\n" +
                "In our hearts you shall forever fly!\n" +
                "We cherish thoughts so dear for three\n" +
                "Oh Alma Mater in our pray’r\n" +
                "We plead for you in victory\n" +
                "And in the victory we share,\n" +
                "But when the battle seems in vain\n" +
                "Our spirit never falters\n" +
                "We’re ever one in joy and pain\n" +
                "And our union is a lasting bond;\n" +
                "Oh may we be united\n" +
                "Till the victory of life is won!";

        almaMaterPattern1 = "ing";
        almaMaterAnswer1 = new ArrayList<>();
        almaMaterAnswer1.add(151);
        almaMaterAnswer1.add(186);
        almaMaterAnswer1.add(553);

        almaMaterPattern2 = "ever one in joy";
        almaMaterAnswer2 = new ArrayList<>();
        almaMaterAnswer2.add(505);

        almaMaterPattern3 = "a";
        int[] answers = new int[]{16, 34, 40, 88, 98, 117, 124, 184, 209, 214, 241, 276, 289, 300, 342, 361, 364, 378, 
                389, 435, 454, 470, 492, 521, 526, 547, 550, 567};
        almaMaterAnswer3 = new ArrayList<>();
        for (int a : answers) {
            almaMaterAnswer3.add(a);
        }
        
        comparator = new CharacterComparator();
    }

    /* ******************************************************************
    Tests boyerMoore on a variety of single, multiple and no matches
    ****************************************************************** */

    @Test(timeout = TIMEOUT)
    public void boyerMoore1() {
        assertEquals(fireAnswer1, PatternMatching.boyerMoore(firePattern1, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 283 >= comparator.getComparisonCount());
    }
    
    @Test(timeout = TIMEOUT)
    public void boyerMoore2() {
        assertEquals(fireAnswer2, PatternMatching.boyerMoore(firePattern2, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 387 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void boyerMoore3() {
        assertEquals(fireAnswer3, PatternMatching.boyerMoore(firePattern3, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 199 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void boyerMoore4() {
        assertEquals(new ArrayList<>(), PatternMatching.boyerMoore(nonExistent, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 224 >= comparator.getComparisonCount());
    }
    

    @Test(timeout = TIMEOUT)
    public void boyerMoore5() {
        assertEquals(almaMaterAnswer1, PatternMatching.boyerMoore(almaMaterPattern1, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 233 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void boyerMoore6() {
        assertEquals(almaMaterAnswer2, PatternMatching.boyerMoore(almaMaterPattern2, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 85 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void boyerMoore7() {
        assertEquals(almaMaterAnswer3, PatternMatching.boyerMoore(almaMaterPattern3, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 615 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void boyerMoore8() {
        assertEquals(new ArrayList<>(), PatternMatching.boyerMoore(nonExistent, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 176 >= comparator.getComparisonCount());
    }

    /* ******************************************************************
    Tests boyerMoore when text is shorter than pattern
    ****************************************************************** */

    @Test(timeout = TIMEOUT)
    public void boyerMoore9() {
        String text = "buzz";
        String pattern = "Ramblin";
        assertEquals(new ArrayList<>(), PatternMatching.boyerMoore(pattern, text, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 0 >= comparator.getComparisonCount());
    }

    /* ******************************************************************
    Tests boyerMoore when text and pattern are equal
    ****************************************************************** */

    @Test(timeout = TIMEOUT)
    public void boyerMoore10() {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        assertEquals(ans, PatternMatching.boyerMoore(fireText, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 683 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void boyerMoore11() {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        assertEquals(ans, PatternMatching.boyerMoore(almaMaterText, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 615 >= comparator.getComparisonCount());
    }

    /* ******************************************************************
    Tests boyerMoore's exception to null/empty parameters
    ****************************************************************** */

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void boyerMooreException1() {
        PatternMatching.boyerMoore(null, fireText, comparator);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void boyerMooreException2() {
        PatternMatching.boyerMoore(firePattern1, null, comparator);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void boyerMooreException3() {
        PatternMatching.boyerMoore(firePattern1, fireText, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void boyerMooreException4() {
        PatternMatching.boyerMoore("", fireText, comparator);
    }

    /* ******************************************************************
    Tests buildLastTable on a variety of patterns
    ****************************************************************** */

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable1() {
        Map<Character, Integer> actual = PatternMatching.buildLastTable(firePattern1);
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('y', 0);
        expected.put('o', 1);
        expected.put('u', 2);
        assertEquals(actual, expected);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable2() {
        Map<Character, Integer> actual = PatternMatching.buildLastTable(firePattern2);
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('D', 0);
        expected.put('o', 1);
        assertEquals(actual, expected);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable3() {
        Map<Character, Integer> actual = PatternMatching.buildLastTable(firePattern3);
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('s', 0);
        expected.put('h', 1);
        expected.put('o', 2);
        expected.put('w', 3);
        assertEquals(actual, expected);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable4() {
        Map<Character, Integer> actual = PatternMatching.buildLastTable(almaMaterPattern1);
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('i', 0);
        expected.put('n', 1);
        expected.put('g', 2);
        assertEquals(actual, expected);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable5() {
        Map<Character, Integer> actual = PatternMatching.buildLastTable(almaMaterPattern2);
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('e', 7);
        expected.put('v', 1);
        expected.put('r', 3);
        expected.put(' ', 11);
        expected.put('o', 13);
        expected.put('n', 10);
        expected.put('i', 9);
        expected.put('j', 12);
        expected.put('y', 14);
        assertEquals(actual, expected);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable6() {
        Map<Character, Integer> actual = PatternMatching.buildLastTable(almaMaterPattern3);
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('a', 0);
        assertEquals(actual, expected);
    }

    /* ******************************************************************
    Tests buildLastTable's exception to null pattern
    ****************************************************************** */

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBuildLastTableException() {
        PatternMatching.buildLastTable(null);
    }

    /* ******************************************************************
    Tests kmp on a variety of single, multiple and no matches
    ****************************************************************** */

    @Test(timeout = TIMEOUT)
    public void kmp1() {
        assertEquals(fireAnswer1, PatternMatching.kmp(firePattern1, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 688 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void kmp2() {
        assertEquals(fireAnswer2, PatternMatching.kmp(firePattern2, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 684 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void kmp3() {
        assertEquals(fireAnswer3, PatternMatching.kmp(firePattern3, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 708 >= comparator.getComparisonCount());
    }
    
    @Test(timeout = TIMEOUT)
    public void kmp4() {
        assertEquals(new ArrayList<>(), PatternMatching.kmp(nonExistent, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 691 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void kmp5() {
        assertEquals(almaMaterAnswer1, PatternMatching.kmp(almaMaterPattern1, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 648 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void kmp6() {
        assertEquals(almaMaterAnswer2, PatternMatching.kmp(almaMaterPattern2, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 668 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void kmp7() {
        assertEquals(almaMaterAnswer3, PatternMatching.kmp(almaMaterPattern3, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 615 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void kmp8() {
        assertEquals(new ArrayList<>(), PatternMatching.kmp(nonExistent, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 622 >= comparator.getComparisonCount());
    }

    /* ******************************************************************
    Tests kmp when text is shorter than pattern
    ****************************************************************** */

    @Test(timeout = TIMEOUT)
    public void kmp9() {
        String text = "buzz";
        String pattern = "Ramblin";
        assertEquals(new ArrayList<>(), PatternMatching.kmp(pattern, text, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 0 >= comparator.getComparisonCount());
    }

    /* ******************************************************************
    Tests kmp when text and pattern are equal
    ****************************************************************** */

    @Test(timeout = TIMEOUT)
    public void kmp10() {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        assertEquals(ans, PatternMatching.kmp(fireText, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 1369 
                >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void kmp11() {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        assertEquals(ans, PatternMatching.kmp(almaMaterText, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 1233 
                >= comparator.getComparisonCount());
    }

    /* ******************************************************************
    Tests kmp's exceptions
    ****************************************************************** */

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void kmpException1() {
        PatternMatching.kmp(null, fireText, comparator);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void kmpException2() {
        PatternMatching.kmp(firePattern1, null, comparator);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void kmpException3() {
        PatternMatching.kmp(firePattern1, fireText, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void kmpException4() {
        PatternMatching.kmp("", fireText, comparator);
    }

    /* ******************************************************************
    Tests buildFailureTable with a variety of patterns
    ****************************************************************** */

    @Test(timeout = TIMEOUT)
    public void buildFailureTable1() {
        int[] actual = PatternMatching.buildFailureTable(firePattern1, comparator);
        int[] expected = new int[]{0, 0, 0};
        assertArrayEquals(expected, actual);
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 2 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void buildFailureTable2() {
        int[] actual = PatternMatching.buildFailureTable(firePattern2, comparator);
        int[] expected = new int[]{0, 0};
        assertArrayEquals(expected, actual);
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 1 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void buildFailureTable3() {
        int[] actual = PatternMatching.buildFailureTable(firePattern3, comparator);
        int[] expected = new int[]{0, 0, 0, 0};
        assertArrayEquals(expected, actual);
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 3 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void buildFailureTable4() {
        int[] actual = PatternMatching.buildFailureTable(almaMaterPattern1, comparator);
        int[] expected = new int[]{0, 0, 0};
        assertArrayEquals(expected, actual);
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 2 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void buildFailureTable5() {
        int[] actual = PatternMatching.buildFailureTable(almaMaterPattern2, comparator);
        int[] expected = new int[]{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(expected, actual);
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 16 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void buildFailureTable6() {
        int[] actual = PatternMatching.buildFailureTable(almaMaterPattern3, comparator);
        int[] expected = new int[]{0};
        assertArrayEquals(expected, actual);
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 0 >= comparator.getComparisonCount());
    }

    /* ******************************************************************
    Tests buildFailureTable's exceptions
    ****************************************************************** */

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void buildFailureTableException1() {
        PatternMatching.buildFailureTable(null, comparator);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void buildFailureTableException2() {
        PatternMatching.buildFailureTable(firePattern1, null);
    }

    /* ******************************************************************
    Tests Rabin Karp for a variety of single, multiple and no matches
    ****************************************************************** */

    @Test(timeout = TIMEOUT)
    public void rabinKarp1() {
        assertEquals(fireAnswer1, PatternMatching.rabinKarp(firePattern1, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 33 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarp2() {
        assertEquals(fireAnswer2, PatternMatching.rabinKarp(firePattern2, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 8 >= comparator.getComparisonCount());
    }
    
    @Test(timeout = TIMEOUT)
    public void rabinKarp3() {
        assertEquals(fireAnswer3, PatternMatching.rabinKarp(firePattern3, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 4 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarp4() {
        assertEquals(new ArrayList<>(), PatternMatching.rabinKarp(nonExistent, fireText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 0 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarp5() {
        assertEquals(almaMaterAnswer1, PatternMatching.rabinKarp(almaMaterPattern1, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 9 >= comparator.getComparisonCount());
    }

    //almaMaterPattern2 causes integer overflow, so its test was excluded

    @Test(timeout = TIMEOUT)
    public void rabinKarp6() {
        assertEquals(almaMaterAnswer3, PatternMatching.rabinKarp(almaMaterPattern3, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 28 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarp7() {
        assertEquals(new ArrayList<>(), PatternMatching.rabinKarp(nonExistent, almaMaterText, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 0 >= comparator.getComparisonCount());
    }

    /* ***************************************************
    Tests Rabin Karp when pattern is longer than text
    *************************************************** */

    @Test(timeout = TIMEOUT)
    public void rabinKarp8() {
        String text = "buzz";
        String pattern = "Ramblin";
        assertEquals(new ArrayList<>(), PatternMatching.kmp(pattern, text, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 0 >= comparator.getComparisonCount());
    }

    /* ***************************************************
    Tests Rabin Karp when text and pattern are equal
    *************************************************** */

    @Test(timeout = TIMEOUT)
    public void rabinKarp9() {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        assertEquals(ans, PatternMatching.rabinKarp(firePattern1, firePattern1, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 3 >= comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarp10() {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        assertEquals(ans, PatternMatching.rabinKarp(firePattern2, firePattern2, comparator));
        assertTrue("Number of comparisons: " + comparator.getComparisonCount(), 2 >= comparator.getComparisonCount());
    }

    /* ******************************************************************
    Tests Rabin Karp's exceptions
    ****************************************************************** */

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void rabinKarpException1() {
        PatternMatching.rabinKarp(null, fireText, comparator);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void rabinKarpException2() {
        PatternMatching.rabinKarp(firePattern1, null, comparator);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void rabinKarpException3() {
        PatternMatching.rabinKarp(firePattern1, fireText, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void rabinKarpException4() {
        PatternMatching.rabinKarp("", fireText, comparator);
    }

}
