package GameState;

import javax.swing.JFrame;
import Game.Main;
import UserInterface.ModeSelectDialog;

public class ConnectToServerP2PState extends GameState {
	JFrame frame;
	ModeSelectDialog popUpDialog;
	
	public ConnectToServerP2PState(Main main, JFrame frame) {
		super(main);
		this.frame = frame;
	}

	@Override
	public void entered() {
		System.out.println("Main_thread: CONNECT_TO_SERVER_P2P_STATE");
		popUpDialog = new ModeSelectDialog(frame, "Select Mode");
		
	}

	@Override
	public void leaving() {
		popUpDialog.dispose();
		
	}

}
