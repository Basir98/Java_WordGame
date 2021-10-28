package word_game;

public class Consumer extends Thread {
	private Words words;
	private GameGUI gui;
	private Controller controller;
	private int randomNumber;
	private int count;
	private String inputText= "";
	
	
	public Consumer(GameGUI gui, Words words, int randomNum, int textLength) {
		this.gui = gui;
		this.words= words; 
		this.randomNumber = randomNum;
		this.count = textLength - 1;
	}
	
	
	public void run() {
		int i = 0;
		while(i <= count) {
			char c;
			try {
				c = words.getChar();    // get character from words
				inputText += c;			// add the character to the inputText
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		controller = new Controller();     
		controller.timerTask();
		
		//check the input
		controller.checkInput(words, inputText, randomNumber, gui);
	}
}
