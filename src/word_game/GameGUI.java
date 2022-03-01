package word_game;


import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Color;

public class GameGUI extends JFrame {

	JLabel lblWord;
	private Controller controller;
	private final Random rand = new Random();
	private final JTextField textField;
	private Consumer consumer;
	private Producer producer;
	int randomNumber;
	private final JLabel lblStatus;
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
		btnChange.setFont(new Font("Tacoma", Font.BOLD, 13));
		btnChange.setForeground(Color.BLACK);
		btnChange.setBackground(new Color(0, 255, 255));
		btnChange.setBounds(302, 223, 97, 25);
		getContentPane().add(btnChange);

		JButton btnHint = new JButton("Hint");
		btnHint.setForeground(new Color(0, 0, 0));
		btnHint.setBackground(new Color(0, 255, 255));
		btnHint.setFont(new Font("Tacoma", Font.BOLD, 13));
		btnHint.setBounds(308, 134, 78, 27);
		getContentPane().add(btnHint);

		int length = words.getStrArray()[words.getTypeNumber()].length - 1;
		
		lblStatus = new JLabel();
		lblStatus.setFont(new Font("Stencil", Font.BOLD, 13));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(137, 270, 117, 16);
		getContentPane().add(lblStatus);
		

		btnSubmit = new JButton("SUBMIT");
		btnSubmit.setFont(new Font("Tacoma", Font.BOLD | Font.ITALIC, 13));
		btnSubmit.setBackground(Color.DARK_GRAY);
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setBounds(146, 223, 97, 25);
		getContentPane().add(btnSubmit);

		randomNumber = rand.nextInt(length);
		
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
			changeToRandomWord(words, randomNumber);
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
		lblResult.setFont(new Font("Tacoma", Font.ITALIC, 13));
		lblResult.setBounds(323, 270, 76, 16);
		getContentPane().add(lblResult);
		
		JLabel lblWordCategory = new JLabel(wordCategory.toString());
		lblWordCategory.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 14));
		lblWordCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordCategory.setBounds(171, 13, 117, 25);
		getContentPane().add(lblWordCategory);
		
		JButton btnEndGame = new JButton("End Game");
		btnEndGame.addActionListener(e -> {

			setEnabled(false);
			JFrame frame  = new JFrame();
			JLabel lbl = new JLabel("Result: "+resultCount);
			lbl.setBounds(110, 30, 100, 25);

			JButton btnEnd = new JButton("END");
			btnEnd.setBounds(40, 80, 80, 25);

			btnEnd.setForeground(Color.WHITE);
			btnEnd.setBackground(Color.RED);
			btnEnd.addActionListener(e1 -> System.exit(0));

			JButton btnWordOption = new JButton("Word Option");
			btnWordOption.setBounds(140, 80, 110, 25);
			btnWordOption.setForeground(Color.WHITE);
			btnWordOption.setBackground(Color.BLUE);
			btnWordOption.addActionListener(e12 -> {
				dispose();
				frame.dispose();
				DialogOption dOption = new DialogOption();
				dOption.startDialogFrame();
			});

			frame.getContentPane().add(lbl);
			frame.getContentPane().add(btnEnd);
			frame.getContentPane().add(btnWordOption);
			frame.getContentPane().setLayout(null);

			frame.setBounds(0, 0, 300, 200);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setVisible(true);
			frame.setResizable(false); // Prevent user from change size
			frame.setLocationRelativeTo(null); // Start middle screen
			frame.setTitle("Game finished");

		});
		btnEndGame.setForeground(Color.WHITE);
		btnEndGame.setBackground(Color.RED);
		btnEndGame.setBounds(203, 321, 105, 21);
		getContentPane().add(btnEndGame);
		
		
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
	 * This method changes to a new random word, masks the word and displays it
	 */
	public String changeToRandomWord(Words words, int randomNum) {
		
		int wordArrayLength = words.getStrArray()[words.getTypeNumber()].length - 1;

		int newRandomNumber = controller.getRandomWithExclusion(rand, 0, wordArrayLength, randomNum);

		randomNumber = newRandomNumber;

		String newWord = words.getStrArray()[words.getTypeNumber()][newRandomNumber];
		controller = new Controller();
		masked = controller.maskString(newWord, 2);
		lblWord.setText(masked);

		return newWord;
	}

	/**
	 *
	 * @param status true if guessed word was correct and false when guessed word is incorrect
	 */
	public boolean displayResultMsg(boolean status) {
		int delay = 1000; // milliseconds
		ActionListener taskPerformer = evt -> lblStatus.setText("");
		Timer tick = new Timer(delay, taskPerformer);
		tick.setRepeats(false);
		tick.start();

		if(status) {
			lblStatus.setText("Success");
			lblStatus.setForeground(new Color(0, 128, 0));
			return true;
		}
		else {
			lblStatus.setText("Fail");
			lblStatus.setForeground(Color.RED);
			return false;
		}
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
