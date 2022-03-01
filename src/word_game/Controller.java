package word_game;

import java.util.LinkedList;
import java.util.Random;


public class Controller {

	/**
	 * this method masks the received token for the user to make the word harder to read
	 * @param token
	 * @param count
	 * @return masked token
	 */
	public String maskString(String token, int count) {
		if ((token != null) && (token.length() > 0) && (token.length() >= 3)) {
			StringBuilder sb = new StringBuilder();
			sb.append(token, 0, count);
			for (int i = count+1; i <= token.length(); ++i) {
				sb.append("*");
			}
			return sb.toString();
		} else {
			assert token != null;
			return token;
		}
	}

	/**
	 * This method compares the input text with the current random word from the word list   
	 * @param words
	 * @param text
	 * @param randomNbr
	 * @param gui
	 */

	public int checkInput(Words words, String text, int randomNbr, GameGUI gui) {
		LinkedList<String> list = words.getList();
		if (text.equalsIgnoreCase(list.get(randomNbr))) {
			gui.displayResultMsg(true);
			gui.btnSubmit.setEnabled(false);
			gui.resultCount++;
			gui.lblResult.setText("Points: "+gui.resultCount);
			System.out.println("Success\t\tPoints: "+gui.resultCount);
			
			gui.changeToRandomWord(words, randomNbr);	// current text
			gui.btnSubmit.setEnabled(true);
			gui.textFieldEmpty();
				
		} else {
			System.out.println("Fail\t\tPoints: "+gui.resultCount);
			gui.displayResultMsg(false);
			if(gui.resultCount > 0) {
				gui.resultCount--;
				gui.lblResult.setText("Points: "+gui.resultCount);

			}
		}
		return gui.resultCount;
	}




	public int getRandomWithExclusion(Random rand, int start, int end, int... exclude) {
		int random = start + rand.nextInt(end - start + 1 - exclude.length);
		for (int ex : exclude) {
			if (random < ex) {
				break;
			}
			random++;
		}
		return random;
	}

	
	/*
	 * Sleep for 1 sec
	 */
	public void timerTask() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
