package assignment4Game;

import javax.swing.*;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;

public class GameGUI extends JFrame {

	JLabel lblWord;
	private Controller controller;
	private Random rand = new Random();
	private JTextField textField;
	private Consumer consumer;
	private Producer producer;
	int randomNumber;
	private JLabel lblStatus;
	private String masked;
	JButton btnSubmit;
	int resultCount = 0;
	JLabel lblResult;
	

	public GameGUI(Words words, WordCategory wordCategory) {
		getContentPane().setBackground(Color.PINK);
		setForeground(Color.WHITE);

		lblWord = new JLabel();
		lblWord.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblWord.setBounds(77, 130, 211, 31);
		getContentPane().add(lblWord);
		lblWord.setText("Guess the word");

		getContentPane().setForeground(Color.WHITE);
		getContentPane().setLayout(null);

		getContentPane().setLayout(null);

		JLabel lblHangmanGame = new JLabel("Word Game");
		lblHangmanGame.setForeground(new Color(255, 0, 0));
		lblHangmanGame.setFont(new Font("OCR A Extended", Font.BOLD, 35));
		lblHangmanGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblHangmanGame.setBounds(65, 39, 357, 71);
		getContentPane().add(lblHangmanGame);

		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(87, 174, 201, 31);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnChange = new JButton("Change");
		btnChange.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChange.setForeground(Color.BLACK);
		btnChange.setBackground(new Color(0, 255, 255));
		btnChange.setBounds(302, 223, 97, 25);
		getContentPane().add(btnChange);

		JButton btnHint = new JButton("Hint");
		btnHint.setForeground(new Color(0, 0, 0));
		btnHint.setBackground(new Color(0, 255, 255));
		btnHint.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHint.setBounds(308, 134, 78, 27);
		getContentPane().add(btnHint);

		int length = words.getStrArray()[words.getTypeNumber()].length - 1;
		
		lblStatus = new JLabel();
		lblStatus.setFont(new Font("Stencil", Font.BOLD, 13));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(137, 270, 117, 16);
		getContentPane().add(lblStatus);
		

		btnSubmit = new JButton("SUBMIT");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnSubmit.setBackground(Color.DARK_GRAY);
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setBounds(146, 223, 97, 25);
		getContentPane().add(btnSubmit);

		randomNumber = rand.nextInt(length - 0) + 0;
		
		btnSubmit.addActionListener(e -> {
			
			String input = textField.getText();
			if (!input.equals("")) {

				//Random word with each iteration, when the submit btn is clicked and the input is correct				
				
				producer = new Producer(input, words);
				producer.start();

				consumer = new Consumer(this, words, randomNumber, textField.getText().length());
				consumer.start();

			} else {
				JOptionPane.showMessageDialog(null, "No input is entered!", "Warning", JOptionPane.WARNING_MESSAGE);
			}

		});

		btnChange.addActionListener(e -> {
			/*
			 * change the a random word and then decrease the points by 1 
			 */
			changeToRandomWord(words);
			btnSubmit.setEnabled(true);
			
			if(resultCount > 0) {
				resultCount--;
				lblResult.setText("Points: "+resultCount);
			}
		});

		String word = words.getStrArray()[words.getTypeNumber()][randomNumber];
		controller = new Controller();
		masked = controller.maskString(word, 2);
		lblWord.setText(masked);							// sets the masked word
		
		lblResult = new JLabel("POINTS: "+resultCount);
		lblResult.setForeground(Color.BLACK);
		lblResult.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblResult.setBounds(323, 270, 76, 16);
		getContentPane().add(lblResult);
		
		JLabel lblWordCategory = new JLabel(wordCategory.toString());
		lblWordCategory.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 14));
		lblWordCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordCategory.setBounds(171, 13, 117, 25);
		getContentPane().add(lblWordCategory);
		
		
		btnHint.addActionListener(e -> {
			String maskedWordBeforeHint = words.getStrArray()[words.getTypeNumber()][randomNumber];
			controller = new Controller();
			String maskedAfterHint= controller.maskString(maskedWordBeforeHint, 3);
			lblWord.setText(maskedAfterHint);
		});
	}
	
	public void textFieldEmpty() {
		textField.setText("");
	}
	
	
	/**
	 * This method changes to a random word, masks the word and displays it
	 * @param words
	 */
	public void changeToRandomWord(Words words) {
		
		int wordArraylength = words.getStrArray()[words.getTypeNumber()].length - 1;
		
		randomNumber = rand.nextInt(wordArraylength - 0) + 0;
		
		String word = words.getStrArray()[words.getTypeNumber()][randomNumber];
		controller = new Controller();
		masked = controller.maskString(word, 2);
		lblWord.setText(masked);
	}
	
	
	public void displayResultMsg(boolean status) {
		if(status) {
			lblStatus.setText("Success");
			lblStatus.setForeground(new Color(0, 128, 0));
		}
		else if(!status) {
			lblStatus.setText("Fail");
			lblStatus.setForeground(Color.RED);
		}
		int delay = 1000; // milliseconds
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				lblStatus.setText("");
			}

		};
		Timer tick = new Timer(delay, taskPerformer);
		tick.setRepeats(false);
		tick.start();
	}
	

	public void startGuiFrame() {
		setBounds(0, 0, 500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false); // Prevent user from change size
		setLocationRelativeTo(null); // Start middle screen
		setTitle("Word game");
		
	}
}