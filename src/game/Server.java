package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	public ArrayList<Socket> socketList;
	public ArrayList<GameServer> gameServerList;
	public ServerSocket serverSocket;
	public ExecutorService executor;
	public CustomLock matchingLock;
	public Socket firstClientMatching;
	public Socket secondClientMatching;
	
	public static void main(String [] args) throws IOException {
		Server server = new Server();
		server.executor.execute(server.new SocketConnectionThread());
		
	}
	
	public Server() throws IOException {
		socketList = new ArrayList<Socket>();
		gameServerList = new ArrayList<GameServer>();
		executor = Executors.newFixedThreadPool(20);
		serverSocket = new ServerSocket(8000);
		matchingLock = new CustomLock();
	}
	
	public void createGameServer() {
		
	}
	
	class SocketInputThread implements Runnable {
		Socket socket;
		
		public SocketInputThread(Socket socket) {
			this.socket = socket;
		}
		@Override
		public void run() {
			try {
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//Print connection successful
				//...
				//Wait for input
				String input;
				while((input = in.readLine()) != null) {
					switch(input) {
						//CASES
					}
					//If input = match -> Start matching
					if(firstClientMatching == null) {
						firstClientMatching = socket;
						//Prepare (create) the game server
						GameServer gameServer = new GameServer(8080);
						gameServerList.add(gameServer);
						executor.execute(gameServer);
						//Print MATCH_SERVER_OTHER_MATCHING_NOT_AVAILABLE
						out.println(CommandString.MATCH_SERVER_OTHER_MATCHING_NOT_AVAILABLE);
						while(!matchingLock.wasSignaled()) {
							matchingLock.wait();
						}
					} else { //If first matching client is available
						secondClientMatching = socket;
						matchingLock.signal(true);
						matchingLock.notify();
					}
					out.println(CommandString.MATCH_SERVER_OTHER_MATCHING_AVAILABLE);
					//Client will connect to the available Game Server prepared
					//Clear ClientMatching
					firstClientMatching = null;
					secondClientMatching = null;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	class SocketConnectionThread implements Runnable {

		@Override
		public void run() {
			while(true) {
				//Wait for input connection
				Socket socket;
				try {
					socket = serverSocket.accept();
					executor.execute(new SocketInputThread(socket));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
}
