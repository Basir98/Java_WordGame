package assignment4Game;

import java.util.LinkedList;


public class Controller {
	int num = 0; 
	
	/**
	 * this method masks the received token for the user to make the word harder to read
	 * @param token
	 * @param count
	 * @return masked token
	 */
	public String maskString(String token, int count) {
		if ((token != null) && (token.length() > 0) && (token.length() >= 3)) {
			StringBuilder sb = new StringBuilder();
			sb.append(token.substring(0, count));
			for (int i = count+1; i <= token.length(); ++i) {
				sb.append("*");
			}
			return sb.toString();
		} else if (token.length() < 3) {
			return token;
		}
		return null;
	}

	/**
	 * This method compares the input text with the current random word from the word list   
	 * @param words 
	 * @param text
	 * @param random
	 * @param gui
	 */

	public void checkInput(Words words, String text, int randomNbr, GameGUI gui) {
		LinkedList<String> list = words.getList();
		if (text.toLowerCase().equals(list.get(randomNbr).toLowerCase())) {
			gui.displayResultMsg(true);
			gui.btnSubmit.setEnabled(false);
			gui.resultCount++;
			gui.lblResult.setText("Points: "+gui.resultCount);
			System.out.println("Success");
			
			gui.changeToRandomWord(words);
			gui.btnSubmit.setEnabled(true);
			gui.textFieldEmpty();
				
		} else {
			System.out.println("Fail");
			gui.displayResultMsg(false);
			if(gui.resultCount > 0) {
				gui.resultCount--;
				gui.lblResult.setText("Points: "+gui.resultCount);
			}
		}
	}
	
	/*
	 * Sleep for 1 sec
	 */
	public void timerTask() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}

}
