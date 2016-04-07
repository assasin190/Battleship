package Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer implements Runnable, Serializable {
	//Network attributes
	private ServerSocket serverSocket;
	private Socket socket;
	private ArrayList<Socket> clientSocket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private OutputStream out;
	private InputStream in;
	//private ObjectInputStream ois;
	//private ObjectOutputStream oos;
	//Game attributes
	private Main.GameClient localClient;
	//private GameClient otherClient;
	private boolean isWithLocalClient = false;
	
	
	public static void main(String [] args) {
	}
	
	protected GameServer() {
		
	}
	
	protected GameServer(Main.GameClient localClient){
		isWithLocalClient = true;
		this.localClient = localClient;
	}

	@Override
	public void run() {
		System.out.println("ServerThread: Server is running...");
		//setupClient();
		
		
	}
	
	private void setupClient() {
		try {
			serverSocket = new ServerSocket(65536);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Determine if the server run with a local client (P2P Case)
		if(isWithLocalClient) {
			//Start a thread that wait for another client's connection
			Thread setupClientThread = new Thread() {
				@Override
				public void run() {
					try {
						socket = serverSocket.accept();
						out = socket.getOutputStream();
						in = socket.getInputStream();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			setupClientThread.start();
			
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
		return this.isWithLocalClient;
	}
	
	protected void setLocalClient(Main.GameClient localClient) {
		this.localClient = localClient;
	}
	
}