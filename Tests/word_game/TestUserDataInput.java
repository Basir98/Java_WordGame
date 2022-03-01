package word_game;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.*;


public class TestUserDataInput {


    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    /**
     * This test case tests if the game accepts input from user as right input.
     */
    @Test
    public void testWithRightDataInput() {
        Words words = new Words();

        Random rand = new Random();

        int length = words.getStrArray()[words.getTypeNumber()].length - 1;

        int randomNumber = rand.nextInt(length);

        LinkedList<String> list = words.getList();

        final String expectedString = list.get(randomNumber);
        provideInput(expectedString);

        main(new String[0]);

        assertEquals(expectedString, getOutput());
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(scanner.nextLine());
        scanner.close();
    }


}
