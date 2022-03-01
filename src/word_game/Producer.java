package word_game;


public class Producer extends Thread {
	private final String input;
	private final Words words;
	private final int count;
	
	public Producer(String input, Words words) {
		this.input = input;
		this.words = words;
		this.count = input.length() -1;	
	}

	public void run() {
		int i= 0;
		while(i <= count) {
			try {
				words.writeChar(input.charAt(i));  //send the char one by one
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Controller controller = new Controller();
		controller.timerTask();
	}

}
