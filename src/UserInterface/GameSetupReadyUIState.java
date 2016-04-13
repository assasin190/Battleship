package UserInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Game.Main;
import GameState.GameState;

public class GameSetupReadyUIState extends UI {
	public JDialog dialog;
	public JButton readyBtn;
	
	public GameSetupReadyUIState() {
		stateString = GameState.GAME_SETUP_READY_UI_STATE;
	}
	/**
	 * @wbp.parser.constructor
	 */
	public GameSetupReadyUIState(Main main) {
		super(main);
		stateString = GameState.GAME_SETUP_READY_UI_STATE;
		dialog = new JDialog(main, "Ready!");
		dialog.setLocation(main.getLocation());
		dialog.setPreferredSize(new Dimension(300,80));
		initialize();
	}

	private void initialize() {
		dialog.getContentPane().setLayout(new BorderLayout());
		JLabel label = new JLabel("Press ready to start the game", SwingConstants.CENTER);
		readyBtn = new JButton("Ready");
		readyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.client.startGameSetup();
				readyBtn.setText("Waiting for your opponent...");
				readyBtn.setEnabled(false);
				
			}
		});
		dialog.getContentPane().add(label, BorderLayout.NORTH);
		dialog.getContentPane().add(readyBtn, BorderLayout.SOUTH);
		dialog.pack();
	}
	
	@Override
	public void entered() {
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
