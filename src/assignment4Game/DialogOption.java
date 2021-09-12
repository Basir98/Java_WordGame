package assignment4Game;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

public class DialogOption extends JFrame {
	
	private Words words;
	private WordCategory wordCategory;
	
	public DialogOption() {
		getContentPane().setBackground(Color.GRAY);
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().setLayout(null);
		
		JButton btnConfirm = new JButton("CONFIRM");
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setBackground(Color.BLUE);
		btnConfirm.setBounds(152, 29, 97, 25);
		getContentPane().add(btnConfirm);
		

		JRadioButton rdbtnCapital = new JRadioButton("Capitals");
		rdbtnCapital.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		rdbtnCapital.setBounds(21, 9, 127, 25);
		getContentPane().add(rdbtnCapital);
		
		JRadioButton rdbtnBrands = new JRadioButton("Brands");
		rdbtnBrands.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		rdbtnBrands.setBounds(21, 33, 127, 25);
		getContentPane().add(rdbtnBrands);
		
		JRadioButton rdbtnCountry = new JRadioButton("Countries");
		rdbtnCountry.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		rdbtnCountry.setBounds(21, 57, 127, 25);
		getContentPane().add(rdbtnCountry);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnCapital);
		btnGroup.add(rdbtnBrands);
		btnGroup.add(rdbtnCountry);
		
		
		btnConfirm.addActionListener(e -> {
			if(rdbtnCapital.isSelected()) {
				wordCategory= WordCategory.Capital;
				words = new Words(0);
				this.dispose();
				GameGUI gui = new GameGUI(words, wordCategory);
				gui.startGuiFrame();
				
			} else if(rdbtnBrands.isSelected()) {
				wordCategory= WordCategory.Brand;
				
				words = new Words(1);
				this.dispose();
				GameGUI gui = new GameGUI(words, wordCategory);
				gui.startGuiFrame();

			}else if(rdbtnCountry.isSelected()) {
				wordCategory= WordCategory.Country;
				words = new Words(2);
				this.dispose();
				GameGUI gui = new GameGUI(words, wordCategory);
				gui.startGuiFrame();
			}else {
				System.out.println("Please select a option");
			}
		});
		
	}

	public void startDialogFrame() {
		setBounds(0, 0, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false); // Prevent user from change size
		setLocationRelativeTo(null); // Start middle screen
		setTitle("Word option");
	}
}
