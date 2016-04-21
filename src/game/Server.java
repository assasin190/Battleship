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
	public int port;
	
	public static void main(String [] args) throws IOException {
		Server server = new Server();
		server.executor.execute(server.new SocketConnectionThread());
		
	}
	
	public Server() throws IOException {
		socketList = new ArrayList<Socket>();
		gameServerList = new ArrayList<GameServer>();
		executor = Executors.newFixedThreadPool(20);
		serverSocket = new ServerSocket(8000);
		port = 8001;
		matchingLock = new CustomLock();
	}
	
	public void createGameServer() {
		
	}
	
	public int getPortNumber() {
		return port;
	}
	
	public void incrementPortNumber() {
		port++;
	}
	
	class SocketInputThread implements Runnable {
		Socket socket;
		
		public SocketInputThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				//Prepare (create) the game server
				GameServer gameServer = new GameServer(port);
				//Return port number
				out.println("PORTNUM_" + port);
				System.out.println(Thread.currentThread().getName() + ": " + "PORTNUM_" + port + " sent");
				gameServerList.add(gameServer);
				executor.execute(gameServer);
			} catch (IOException e) {
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
					System.out.println(Thread.currentThread().getName() + ": waiting for connection...");
					socket = serverSocket.accept();
					if(firstClientMatching == null) {
						System.out.println(Thread.currentThread().getName() + ": first client connected");
						firstClientMatching = socket;
						executor.execute(new SocketInputThread(socket));
					} else { //Second client connection -> Match
						System.out.println(Thread.currentThread().getName() + ": second client connected");
						PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
						out.println("PORTNUM_" + port++);
						//Clear first client matching and wait for the next connection
						firstClientMatching = null;
						System.out.println(Thread.currentThread().getName() + ": firstClientMatching cleared");
					}
				} catch (IOException e) {
						e.printStackTrace();
				}
			}
		}	
	}
}
