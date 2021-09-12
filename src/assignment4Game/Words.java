package assignment4Game;

import java.util.LinkedList;

public class Words {
	
	String strWordArray[][] = {{"Rome", "Madrid", "Tokyo", "Oslo", "Seol", "Moscow", "Kabul", "Copenhagen", "Cairo", "Paris", "Athens", "New Delhi", "Dublin",
							"Tehran", "Amman", "Amesterdam", "Lisbon", "Doha", "London", "Abu Dhabi", "Warsaw", "Islamabad", "Mexico City", "Berlin"},
			
							{"Tesla", "Apple", "Volvo", "Ferrari", "Lamborghini", "Bugatti", "Chanel", "H&M", "Nike", "Addidas", "Rolex", "Google", "Amazon", "Disney",
							 "Honda", "Netflix", "Gucci", "Pepsi", "Ikea", "Zara", "Audi", "Sony", "Toyota", "Intel", "BMW", "ESPN", "Lexus", "KFC"},
							
							{"Afghanistan", "Albania", "Algeria", "Australia", "Bangladesh", "Belgium", "Brazil", "Chile", "Colombia", "Denmark", "Egypt", 
								"Finland", "France", "Germany", "Iceland", "Italy", "Japan", "Mexico", "Morocco", "Norway", "Peru", "Portugal", "Russia"}};
		
	private LinkedList<String> words = new LinkedList<>();
	private char character;
	private Status status = Status.Empty;				//First the status is empty since nothing is produced or consumed

	private int typeNumber;
	
	public Words() {
		populateChosenList();
	}
	
	public Words(int typeNbr) {
		this.typeNumber = typeNbr;
		populateChosenList();
	}
	
	/*
	 * First time skip wait since status is Empty first, otherwise wait if status is NotEmpty.
	 * Set the character to c from the parameter and then set the status not empty.
	 * Lastly notify other threads that are waiting.
	 */
	public synchronized void writeChar(char c) throws InterruptedException {
			while(status== Status.NotEmpty) {
				wait();
			}
			this.character = c;
			status = Status.NotEmpty;
			notifyAll();
	}

	/*
	 * First time wait to be notified since status is Empty first, otherwise skip is status is NotEmpty.
	 * Then set status to empty and notify all waiting threads.
	 * Lastly return the character.
	 */
	public synchronized char getChar() throws InterruptedException {
			while(status == Status.Empty) {
				wait();
			}
			status = Status.Empty;
			notifyAll();
			return character;
	}

	
	public void populateChosenList() {
		 for(int i=0; i < strWordArray[getTypeNumber()].length; i++) {
			 words.add(strWordArray[getTypeNumber()][i]);
		 }
	}

	public String[][] getStrArray() {
		return strWordArray;
	}
	
	public int getTypeNumber() {
		return typeNumber;
	}

	
	public LinkedList<String> getList() {
		return words;
	}
	

	
}
