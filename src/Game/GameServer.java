package Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import Game.GameClient;
import UserInterface.GameUI;

public class GameServer implements Runnable {
	//Network attributes
	private ServerSocket serverSocket;
	private Socket otherClientSocket;
	private PrintWriter out;
	private BufferedReader in;
	//Game attributes
	private GameClient localClient;
	private GameClient otherClient;
	private int turn;
	
	public static void main(String [] args) {
	}
	
	protected GameServer(InputStream is, OutputStream os){
		initialize(is, os);
	}
	
	private void initialize(InputStream is, OutputStream os) {
		
		
		
	}

	@Override
	public void run() {
		while(true) {
			if(turn == 1) {
				
			}
		}
		
	}
}