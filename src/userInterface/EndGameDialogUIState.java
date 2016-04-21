package userInterface;

import game.Main;

import java.awt.Dimension;

import javax.swing.JDialog;

import GameState.GameState;

public class EndGameDialogUIState extends UI {
	
	public EndGameDialogUIState(Main main) {
		super(main);
		stateString = GameState.END_GAME_DIALOG_STATE;
		dialog = new JDialog(main);
		dialog.setSize(300, 100);
		dialog.setPreferredSize(new Dimension(300, 100));
		initialize();
		
	}
	
	private void initialize() {
		
		
		dialog.pack();
	}

	@Override
	public void entered() {
		System.out.println(Thread.currentThread().getName() + ": entered " + stateString);
		
		
	}

	@Override
	public void leaving() {
		// TODO Auto-generated method stub
		
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
