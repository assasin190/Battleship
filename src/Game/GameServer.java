package Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Game.GameClient;
import UserInterface.GameUI;

public class GameServer implements Runnable, Serializable {
	//Network attributes
	private ServerSocket serverSocket;
	private Socket socket;
	private ArrayList<Socket> clientSocket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private OutputStream out;
	private InputStream in;
	//Game attributes
	private GameClient localClient;
	private GameClient otherClient;
	private boolean withLocalClient;
	
	
	public static void main(String [] args) {
	}
	
	protected GameServer(boolean withLocalClient){
		this.withLocalClient = withLocalClient;
	}

	@Override
	public void run() {
		setupClient();
		
		
	}
	
	private void setupClient() {
		try {
			serverSocket = new ServerSocket(65536);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Determine if the server run with a local client (P2P Case)
		if(withLocalClient) {
			//Start a thread that wait for another client's connection
			
			/*
			 Wait for the setup of the local client
			 ...
			*/
			
			//Wait for the thread to finish
			
			
			/* Notify the other client (Network)
			 ...
			 */
			
			//Notify the main thread (Local client)
			notify();
			
		}
		//If is Server-Client case
		else {
			// TODO Server-client implementation
		}
		
	}
	
	protected void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	 
	protected ServerSocket getServerSocket() {
		return serverSocket;
	}
	
	protected boolean isWithLocalClient() {
		return this.isWithLocalClient();
	}
	
	protected void setLocalClient(GameClient localClient) {
		this.localClient = localClient;
	}
	
}