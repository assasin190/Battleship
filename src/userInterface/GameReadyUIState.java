package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.Main;
import gameState.GameState;

public class GameReadyUIState extends UI {
	public JButton readyBtn;
	
	public GameReadyUIState() {
		stateString = GameState.GAME_SETUP_READY_STATE;
	}
	/**
	 * @wbp.parser.constructor
	 */
	public GameReadyUIState(Main main) {
		super(main);
		stateString = GameState.GAME_SETUP_READY_STATE;
		dialog = new JDialog(main, "Ready!");
		dialog.setSize(300, 80);
		dialog.setPreferredSize(new Dimension(300,80));
		dialog.setLocation(Main.getPopUpLocation(this));
		initialize();
	}

	private void initialize() {
		dialog.getContentPane().setLayout(new BorderLayout());
		JLabel label = new JLabel("Press ready to start the game", SwingConstants.CENTER);
		readyBtn = new JButton("Waiting for your opponent");
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
