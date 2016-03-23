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
		openConnection();
	}
	
	private void openConnection() {
		//Establish a socket connection
		try {
			serverSocket = new ServerSocket(65536);
			otherClientSocket = serverSocket.accept();
			out = new PrintWriter(otherClientSocket.getOutputStream() , true);
			in = new BufferedReader(new InputStreamReader(otherClientSocket.getInputStream()));
			
			//Change client state to GameUI
			
			localClient = new GameClient();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		while(true) {
			if(turn == 1) {
				
			}
		}
		
	}
}