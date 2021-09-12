package assignment4Game;

public class Producer extends Thread {
	private String input;
	private Controller controller;
	private Words words;
	private int count;
	
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
		controller = new Controller();        
		controller.timerTask();
	}

}
