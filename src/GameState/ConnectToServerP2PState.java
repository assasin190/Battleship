package GameState;

import javax.swing.JFrame;
import Game.Main;
import UserInterface.ConnectToServerP2PUIState;

public class ConnectToServerP2PState implements GameState {
	JFrame frame;
	ConnectToServerP2PUIState popUpDialog;
	
	public ConnectToServerP2PState(Main main, JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void entered() {
		System.out.println("Main_thread: CONNECT_TO_SERVER_P2P_STATE");
		
	}

	@Override
	public void leaving() {
		
	}

}
