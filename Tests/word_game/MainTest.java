package word_game;

import org.junit.Test;
import java.util.LinkedList;
import java.util.Random;
import static org.junit.Assert.*;

public class MainTest {

    Controller controller = new Controller();
    Random rand = new Random();

    /**
     * This test case tests the Masked string before hint to
     * check whether a string is correctly masked before hint is performed
     */
    @Test
    public void testMaskedStringBeforeHint() {
        Words words = new Words();
        LinkedList<String> list = words.getList();
        int length = words.getStrArray()[words.getTypeNumber()].length - 1;

        int randomNumber = rand.nextInt(length);
        String city = list.get(randomNumber);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< city.length(); i++){
            if(i < 2){
                sb.append(city.charAt(i));
            }else {
                sb.append('*');
            }
        }

        System.out.println("\nMasked String before hint\nExpected:\t"+sb);

        String maskedString = controller.maskString(city, 2);

        System.out.println("Actual:\t"+maskedString);
    	assertEquals(sb.toString(), maskedString);
    }

    /**
     * This test case tests the Masked string after hint to
     * check whether a string is correctly masked after hint is performed
     */
    @Test
    public void testMaskedStringAfterHint(){
        Words words = new Words();
        LinkedList<String> list = words.getList();
        int length = words.getStrArray()[words.getTypeNumber()].length - 1;

        int randomNumber = rand.nextInt(length);
        String city = list.get(randomNumber);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< city.length(); i++){
            if(i < 3){
                sb.append(city.charAt(i));
            }else {
                sb.append('*');
            }
        }
        System.out.println("\nMasked String after hint\nExpected:\t"+sb);

        String maskedString = controller.maskString(city, 3);
        System.out.println("Actual:\t"+maskedString);
        assertEquals(sb.toString(), maskedString);
    }


    /**
     * This test case compares the original "Capital" word to a changed word to check if the word is changed every time
     *  If the changed word and original word are not the same this test case is successful
     */
    @Test
    public void testTheChangedCapitalWordsWithOriginalWords(){
        Words capitalWords = new Words(0);
        LinkedList<String> list = capitalWords.getList();
        int length = capitalWords.getStrArray()[capitalWords.getTypeNumber()].length - 1;

        System.out.println("\nCapital words");
        for(int i=0; i< 100; i++){
            WordCategory wordCategory = WordCategory.Capital;

            int randomNumber = rand.nextInt(length);

            String city = list.get(randomNumber);

            GameGUI gui = new GameGUI(capitalWords, wordCategory);
            String changedString = gui.changeToRandomWord(capitalWords, randomNumber);

            System.out.println(i+": Original word: "+city+"\t\tNew word: "+changedString);

            assertNotEquals(changedString, city);
        }

    }

    /**
     * This test case compares the original "Brand" word to a changed word to check if the word is changed every time
     * If the changed word and original word are not the same this test case is successful
     * To increase chances of finding errors/defect this test is repeated 100 times
     * (note: this test case has been tested with 1000 repeats and has been successful)
     */
    @Test
    public void testTheChangedBrandWordsWithOriginalWords(){

        Words brandWords = new Words(1);
        LinkedList<String> list = brandWords.getList();
        int length = brandWords.getStrArray()[brandWords.getTypeNumber()].length - 1;

        System.out.println("\nBrand words");
        for(int i=0; i< 100; i++){
            WordCategory wordCategory = WordCategory.Brand;

            int randomNumber = rand.nextInt(length);

            String city = list.get(randomNumber);

            GameGUI gui = new GameGUI(brandWords, wordCategory);
            String changedString = gui.changeToRandomWord(brandWords, randomNumber);

            System.out.println(i+": Original word: "+city+"\t\tNew word: "+changedString);

            assertNotEquals(changedString, city);
        }

    }

    /**
     * This test case compares the original "Country" word to a changed word to check if the word is changed every time
     *  If the changed word and original word are not the same this test case is successful
     */
    @Test
    public void testTheChangedCountryWordsWithOriginalWords(){
        Words countryWords = new Words(2);
        LinkedList<String> list = countryWords.getList();
        int length = countryWords.getStrArray()[countryWords.getTypeNumber()].length - 1;

        System.out.println("\nCountry words");
        for(int i=0; i< 100; i++){
            WordCategory wordCategory = WordCategory.Country;

            int randomNumber = rand.nextInt(length);

            String city = list.get(randomNumber);

            GameGUI gui = new GameGUI(countryWords, wordCategory);
            String changedString = gui.changeToRandomWord(countryWords, randomNumber);

            System.out.println(i+": Original word: "+city+"\t\tNew word: "+changedString);

            assertNotEquals(changedString, city);
        }
    }



    /**
     * This test case checks whether game returns right word as unmasked and right word as masked before hint
     * Expected output and actual output should not be equal to each other for this test case to work
     */
    @Test
    public void testWordAsUnmaskedBeforeHint(){

        Words words = new Words();
        LinkedList<String> list = words.getList();
        int length = words.getStrArray()[words.getTypeNumber()].length - 1;

        int randomNumber = rand.nextInt(length);
        String city = list.get(randomNumber);

        String maskedString = controller.maskString(city, 2);

        System.out.println("\nWord before hint\nMasked:\t"+maskedString);
        System.out.println("Actual:\t"+city);

        assertNotEquals(maskedString, city);
    }

    /**
     * This test case tests whether the game can differentiate between masked and unmasked words after hint
     * Expected output and actual output should not be equal to each other for this test case to work.
     */
    @Test
    public void testWordAsUnmaskedAfterHint(){
        Words words = new Words();
        LinkedList<String> list = words.getList();
        int length = words.getStrArray()[words.getTypeNumber()].length - 1;

        int randomNumber = rand.nextInt(length);
        String city = list.get(randomNumber);

        String maskedString = controller.maskString(city, 3);

        System.out.println("\nWord After hint\nMasked:\t"+maskedString);
        System.out.println("Actual:\t"+city);

        assertNotEquals(maskedString, city);
    }

    /**
     * This method test the status when a displayResult is called with true status as parameter.
     * It should return true for this test case to work
     */
    @Test
    public void testDisplayResultWithCorrectGuess(){
        Words words = new Words();
        WordCategory wordCategory = WordCategory.Capital;

        GameGUI gui = new GameGUI(words, wordCategory);

        boolean status = gui.displayResultMsg(true);

        assertTrue(status);
    }

    /**
     * This method test the status when a displayResult is called with false status as parameter.
     * It should return false for this test case to work
     */
    @Test
    public void testDisplayResultWithInCorrectGuess(){
        Words words = new Words();

        WordCategory wordCategory = WordCategory.Capital;
        GameGUI gui = new GameGUI(words, wordCategory);
        boolean status = gui.displayResultMsg(false);

        assertFalse(status);
    }


    @Test
    public void testResultCount_ShouldReturnZero(){
        Words words = new Words();
        WordCategory wordCategory = WordCategory.Capital;

        GameGUI gui = new GameGUI(words, wordCategory);

        LinkedList<String> list = words.getList();
        int length = words.getStrArray()[words.getTypeNumber()].length - 1;

        int randomNumber = rand.nextInt(length);

        int resultCount =controller.checkInput(words, "bad guess", randomNumber, gui);

        assertEquals(0, resultCount);

    }

    @Test
    public void testResultCount_ShouldReturnOne(){
        Words words = new Words();
        WordCategory wordCategory = WordCategory.Capital;

        GameGUI gui = new GameGUI(words, wordCategory);

        LinkedList<String> list = words.getList();
        int length = words.getStrArray()[words.getTypeNumber()].length - 1;

        int randomNumber = rand.nextInt(length);
        String city = list.get(randomNumber);

        int resultCount =controller.checkInput(words, city, randomNumber, gui);

        assertEquals(1, resultCount);
    }


}