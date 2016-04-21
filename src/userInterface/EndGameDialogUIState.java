package userInterface;

import game.Main;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GameState.GameState;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class EndGameDialogUIState extends UI {
	public JLabel textLabel;
	
	public EndGameDialogUIState(Main main, String text) {
		super(main);
		stateString = GameState.END_GAME_DIALOG_STATE;
		dialog = new JDialog(main);
		dialog.setSize(400, 300);
		dialog.setPreferredSize(new Dimension(400, 300));
		dialog.setLocation(Main.getPopUpLocation(this));
		initialize(text);
		
	}
	
	private void initialize(String text) {
		JPanel panel = new JPanel();
		dialog.getContentPane().add(panel);
		JPanel mainP = new JPanel();
		mainP.setPreferredSize(new Dimension(400,200));
		mainP.setLayout(new BorderLayout());
		textLabel = new JLabel(text);
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
		
		JButton cont = new JButton("Continue");
		cont.setPreferredSize(new Dimension(150,50));
		cont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Request to start a new game
				main.client.requestNewGame();
				
			}
		});
		
		
		JButton exit = new JButton("Exit");
		exit.setPreferredSize(new Dimension(150,50));
		
		
		button.add(cont, BorderLayout.WEST);
		button.add(exit, BorderLayout.EAST);
		
		JPanel south = new JPanel();
		south.setPreferredSize(new Dimension(400,50));
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
