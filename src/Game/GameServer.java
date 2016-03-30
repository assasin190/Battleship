package Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Game.GameClient;
import UserInterface.GameUI;

public class GameServer implements Runnable {
	//Network attributes
	private ServerSocket serverSocket;
	private ArrayList<Socket> clientSocket;
	private Socket socket;
	//private ObjectInputStream ois;
	//private ObjectOutputStream oos;
	private PrintWriter out;
	private BufferedReader in;
	//Game attributes
	private GameClient localClient;
	//private GameClient otherClient;
	private boolean withLocalClient;
	
	
	public static void main(String [] args) {
	}
	
	protected GameServer() {
		
	}
	
	protected GameServer(GameClient localClient){
		withLocalClient = true;
		this.localClient = localClient;
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
			Thread setupClientThread = new Thread() {
				@Override
				public void run() {
					try {
						socket = serverSocket.accept();
						out = new PrintWriter(socket.getOutputStream());
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			setupClientThread.start();
			
			//Wait for the thread to finish
			try {
				setupClientThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			/* Notify the other client (Network)
			 ...
			 */
			
			//Notify the main thread (Local client
			notify();
			
		}
		//If is Server-Client case
		else {
			// TODO Server-client implementation
		}
	}
	
	private class SocketThread extends Thread {
		@Override
		public void run() {
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}