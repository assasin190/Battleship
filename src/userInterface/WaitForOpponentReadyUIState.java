package userInterface;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import GameState.GameState;
import game.Main;

public class WaitForOpponentReadyUIState extends UI {
	JDialog mydialog;
	
	public WaitForOpponentReadyUIState(Main main) {
		super(main);
		stateString = GameState.WAIT_FOR_OPPONENT_READY_STATE;
		mydialog = new JDialog(main, "");
		mydialog.setLocation(main.getLocation());
		mydialog.setPreferredSize(new Dimension(300,50));
		initialize();
	}
	
	private void initialize() {
		mydialog.getContentPane().setLayout(new FlowLayout());
		mydialog.setTitle("Waiting for your opponent...");
		JLabel waiting = new JLabel("Waiting for your opponent...");
		mydialog.add(waiting, SwingUtilities.CENTER);
		// TODO JDialog implementation
		mydialog.pack();
	}
	
	@Override
	public void entered() {
		System.out.println(Thread.currentThread().getName() + ": entered " + stateString);
		mydialog.setVisible(true);
		
	}

	@Override
	public void leaving() {
		System.out.println(Thread.currentThread().getName() + ": leaving " + stateString);
		mydialog.dispose();
		
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
