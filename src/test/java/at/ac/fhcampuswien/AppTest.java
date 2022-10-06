package at.ac.fhcampuswien;

import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(2)
class AppTest {

    private PrintStream originalOut;
    private InputStream originalIn;
    private ByteArrayOutputStream bos;
    private PrintStream ps;

    @BeforeAll
    public static void init() {
        System.out.println("Testing Exercise2");
    }

    @AfterAll
    public static void finish() {
        System.out.println("Finished Testing Exercise2");
    }

    @BeforeEach
    public void setupStreams() throws IOException {
        originalOut = System.out;
        originalIn = System.in;

        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);
        System.setIn(pis);
        ps = new PrintStream(pos, true);
    }

    @AfterEach
    public void tearDownStreams() {
        // undo the binding in System
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void oneMonthCalendar1() {
        String output = "                1  2 " + System.lineSeparator() +
                " 3  4  5  6  7  8  9 " + System.lineSeparator() +
                "10 11 12 13 14 15 16 " + System.lineSeparator() +
                "17 18 19 20 21 22 23 " + System.lineSeparator() +
                "24 25 26 27 28 29 30 " + System.lineSeparator();
        try {
            // action
            Method m = App.class.getMethod("oneMonthCalendar", int.class, int.class);
            m.invoke(null, 30, 6);
            // assertion
            assertEquals(output, bos.toString());
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called oneMonthCalendar.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred.");
        }
    }

    @Test
    public void oneMonthCalendar2() {
        String output = "    1  2  3  4  5  6 " + System.lineSeparator() +
                " 7  8  9 10 11 12 13 " + System.lineSeparator() +
                "14 15 16 17 18 19 20 " + System.lineSeparator() +
                "21 22 23 24 25 26 27 " + System.lineSeparator() +
                "28 " + System.lineSeparator();

        try {
            // action
            Method m = App.class.getMethod("oneMonthCalendar", int.class, int.class);
            m.invoke(null, 28, 2);
            // assertion
            assertEquals(output, bos.toString());
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called oneMonthCalendar.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred.");
        }
    }

    @Test
    public void lcg() {
        long[] test = new long[]{12345,
                1406932606,
                654583775,
                1449466924,
                229283573,
                1109335178,
                1051550459,
                1293799192,
                794471793,
                551188310};

        try {
            // action
            Method m = App.class.getMethod("lcg", long.class);
            long[] output = (long[])m.invoke(null, 0);
            // assertion
            Assertions.assertArrayEquals(test, output);
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called lcg.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred. Check data types and return types.");
        }
    }

    @Test
    public void guessingGame1() {
        ps.println(50);
        ps.println(15);
        ps.println(49);


        String output = "Guess number 1: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 2: " +
                "The number AI picked is higher than your guess." + System.lineSeparator() +
                "Guess number 3: " +
                "You won wisenheimer!" + System.lineSeparator();

        try {
            // action
            Method m = App.class.getMethod("guessingGame", int.class);
            m.invoke(null, 49);
            // assertion
            assertEquals(output, bos.toString());
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called guessingGame.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred.");
        }
    }

    @Test
    public void guessingGame2() {
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);

        String output = "Guess number 1: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 2: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 3: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 4: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 5: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 6: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 7: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 8: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 9: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 10: " +
                "You lost! Have you ever heard of divide & conquer?" + System.lineSeparator();

        try {
            // action
            Method m = App.class.getMethod("guessingGame", int.class);
            m.invoke(null, 49);
            // assertion
            assertEquals(output, bos.toString());
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called guessingGame.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred.");
        }
    }

    @Test
    public void randomNumberBetweenOneAndHundred() {
        int test = 0;

        try {
            // action
            Method m = App.class.getMethod("randomNumberBetweenOneAndHundred");
            // assertion
            for (int i = 0; i < 100; i++) {
                test = (int)m.invoke(null);
                assertTrue(1 <= test && test <= 100);
            }
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called randomNumberBetweenOneAndHundred.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred. Check data types and return types.");
        }
    }

    @Test
    public void swapArrays1() {
        int[] array1 = new int[]{1, 2, 3, 4, 5, 6};
        int[] array2 = new int[]{100, 202, 30, 14, 15, 16};
        boolean result;

        try {
            // action
            Method m = App.class.getMethod("swapArrays", int[].class, int[].class);
            result = (boolean)m.invoke(null, array1, array2);
            // assertion
            assertTrue(result);
            assertArrayEquals(array1, new int[]{100, 202, 30, 14, 15, 16});
            assertArrayEquals(array2, new int[]{1, 2, 3, 4, 5, 6});
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called swapArrays.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred.");
        }
    }

    @Test
    public void swapArrays2() {
        int[] array1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] array2 = new int[]{100, 202, 30, 14, 15, 16};
        boolean result;

        try {
            // action
            Method m = App.class.getMethod("swapArrays", int[].class, int[].class);
            result = (boolean)m.invoke(null, array1, array2);
            // assertion
            assertFalse(result);
            assertArrayEquals(array2, new int[]{100, 202, 30, 14, 15, 16});
            assertArrayEquals(array1, new int[]{1, 2, 3, 4, 5, 6, 7});
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called swapArrays.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred.");
        }
    }

    @Test
    public void camelCase1() {
        String test = "MyNameIsntAlice";
        String result;
        try {
            // action
            Method m = App.class.getMethod("camelCase", String.class);
            result = (String)m.invoke(null, "my name isn't Alice!");
            // assertion
            assertEquals(test, result);
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called camelCase.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred.");
        }
    }

    @Test
    public void camelCase2() {
        String test = "ButANoisyNoiseAnnoysAnOysterMore";
        String result;
        try {
            // action
            Method m = App.class.getMethod("camelCase", String.class);
            result = (String)m.invoke(null, "but a noisY noise annoYs an oYster more.");
            // assertion
            assertEquals(test, result);
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called camelCase.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred.");
        }
    }

    @Test
    public void checkDigit1() {
        int test = 2;
        int result;
        int[] input = new int[]{4, 0, 4, 4, 4, 8, 2, 9, 3};

        try {
            // action
            Method m = App.class.getMethod("checkDigit", int[].class);
            result = (int)m.invoke(null, input);
            // assertion
            assertEquals(test, result);
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called checkDigit.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred.");
        }
    }

    @Test
    public void checkDigit2() {
        int test = 5;
        int result;
        int[] input = new int[]{3, 9, 1, 5, 8};

        try {
            // action
            Method m = App.class.getMethod("checkDigit", int[].class);
            result = (int)m.invoke(null, input);
            // assertion
            assertEquals(test, result);
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called checkDigit.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred.");
        }
    }

    @Test
    public void checkDigit3() {
        int test = 0;
        int result;
        int[] input = new int[]{0, 0, 4, 4, 4, 8, 2, 9, 3};

        try {
            // action
            Method m = App.class.getMethod("checkDigit", int[].class);
            result = (int)m.invoke(null, input);
            // assertion
            assertEquals(test, result);
        } catch (NoSuchMethodException nsme){
            fail("There should be a static method called checkDigit.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some other problems have occurred.");
        }
    }
}