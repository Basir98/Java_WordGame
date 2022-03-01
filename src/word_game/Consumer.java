package word_game;

public class Consumer extends Thread {
	private final Words words;
	private final GameGUI gui;
	private final int randomNumber;
	private final int count;

	public Consumer(GameGUI gui, Words words, int randomNum, int textLength) {
		this.gui = gui;
		this.words= words; 
		this.randomNumber = randomNum;
		this.count = textLength - 1;
	}
	
	
	public void run() {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		while(i <= count) {
			char c;
			try {
				c = words.getChar();    // get character from words
				sb.append(c);
				//inputText += c;			// add the character to the inputText
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		Controller controller = new Controller();
		controller.timerTask();


		//check the input
		controller.checkInput(words, sb.toString(), randomNumber, gui);

	}
}
