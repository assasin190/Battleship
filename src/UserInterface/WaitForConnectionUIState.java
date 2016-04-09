package UserInterface;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;

import Game.Main;

public class WaitForConnectionUIState extends UI {

	public WaitForConnectionUIState(Main main) {
		super(main);
		stateString = "WAIT_FOR_CONNECTION_STATE";
		dialog = new JDialog();
		dialog = new JDialog(main);
		dialog.setLocation(350,200); //262
		dialog.setPreferredSize(new Dimension(500,300));
		initialize();
	}
	
	private void initialize() {
		dialog.setTitle("Waiting for connection");
		dialog.getContentPane().add(new JLabel("Waiting for connection..."));
		// TODO JDialog implementation
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
