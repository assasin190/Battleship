package userInterface;

import game.Main;
import gameState.GameState;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class OpponentLeftTheGameDialogUIState extends UI {
	public JLabel textLabel;
	
	public OpponentLeftTheGameDialogUIState(Main main) {
		super(main);
		stateString = GameState.END_GAME_DIALOG_STATE;
		dialog = new JDialog(main);
		dialog.setSize(400, 300);
		dialog.setPreferredSize(new Dimension(400, 300));
		dialog.setLocation(Main.getPopUpLocation(this));
		dialog.getContentPane().setBackground(Color.BLACK);
		initialize();
		
	}
	
	private void initialize() {
		dialog.getContentPane().add(panel);
		JPanel mainP = new JPanel();
		mainP.setPreferredSize(new Dimension(400,200));
		mainP.setLayout(new BorderLayout());
		textLabel = new JLabel();
		textLabel.setFont(new Font("Arial", Font.BOLD, 16));
		textLabel.setForeground(Color.WHITE);
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setText("The opponent has left the game. Press OK to return to the main menu");
		mainP.add(textLabel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());
		panel.add(mainP,BorderLayout.NORTH);
		
		JPanel gap1 = new JPanel();
		gap1.setPreferredSize(new Dimension(50,50));
		
		JPanel gap2 = new JPanel();
		gap2.setPreferredSize(new Dimension(50,50));
		panel.add(gap1, BorderLayout.WEST);
		panel.add(gap2, BorderLayout.EAST);
		
		
		JPanel button = new JPanel();
		button.setPreferredSize(new Dimension(300,50));
		button.setLayout(new BorderLayout());
		panel.add(button,BorderLayout.CENTER);
		
		JButton cont = new JButton(new ImageIcon("btn-cont.png"));
		cont.setPreferredSize(new Dimension(150,60));
		cont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Return to main menu
				main.returnToMainMenu();
				
			}
		});
		
		button.add(cont, BorderLayout.CENTER);
		
		JPanel south = new JPanel();
		south.setPreferredSize(new Dimension(400,40));
		panel.add(south, BorderLayout.SOUTH);
		
		dialog.pack();
	}

	@Override
	public void entered() {
		System.out.println(Thread.currentThread().getName() + ": entered " + stateString);
		dialog.setVisible(true);
		
	}

	@Override
	public void leaving() {
		dialog.dispose();
		
	}

	@Override
	public void obscuring() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revealed() {
		// TODO Auto-generated method stub
		
	}

}