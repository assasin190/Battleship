package Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer implements Runnable, Serializable {
	//Network attributes
	public ServerSocket serverSocket;
	private Socket firstClientSocket;
	private Socket secondClientSocket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private OutputStream out;
	private InputStream in;
	protected CustomLock gameSetupLock;
	//private ObjectInputStream ois;
	//private ObjectOutputStream oos;
	//Game attributes
	private Main.GameClient localClient;
	//private GameClient otherClient;
	private boolean isWithLocalClient = false;
	
	
	public static void main(String [] args) {
		//Client-Server
	}
	
	protected GameServer(int portAddr) {
		try {
			serverSocket = new ServerSocket(portAddr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected GameServer(Main.GameClient localClient){
		isWithLocalClient = true;
		this.localClient = localClient;
	}

	@Override
	public void run() {
		System.out.println("ServerThread: Server is running...");
		//Create a connection thread between two clients and the server
		try {
			/*	Accept and start the SocketThreads
			*	If there is a local client, firstClientSocket is accepted before the game server runs (which is always before secondClientSocket connects)
			*	Skip the firstClientSocket acceptance if firstClientSocket is not null
			
			if(firstClientSocket == null) {
				firstClientSocket = serverSocket.accept();
			}
			*/
			firstClientSocket = serverSocket.accept();
			System.out.println(Thread.currentThread().getName() + ": first client connection accepted");
			secondClientSocket = serverSocket.accept();
			System.out.println(Thread.currentThread().getName() + ": second client connection accepted");
			new SocketThread(secondClientSocket, this).start();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Wait for setup
		while(true) {
			CustomLock gameSetupLock = new CustomLock("SETUP_LOCK");
			synchronized(gameSetupLock) {
				while(gameSetupLock.lockCounter != 2) {
					try {
						gameSetupLock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
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
	
	protected class SocketThread extends Thread {
		/*	Thread responsible for accepting input from client
		 *	
		 */
		Socket socket;
		PrintWriter out;
		BufferedReader in;
		GameServer gameServer;
		CustomLock currentLock;
		
		public SocketThread(Socket socket, GameServer gameServer) {
			this.socket = socket;
		}
		
		public void setLock(CustomLock newLock) {
			currentLock = newLock;
		}
		
		@Override
		public void run() {
			//Wait for input
			while(true) {
				String input;
				try {
					out = new PrintWriter(socket.getOutputStream());
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					while((input = in.readLine()) != null) {
						switch(input) {
							case CommandString.CLIENT_GAME_SETUP_FINISHED: //Case client finished game setup -> increment lock counter
								//Verify current lock
								if(currentLock.getLockName().equals("SETUP_LOCK")) {
									currentLock.incrementCounter();
									break;
								}
								else { //Invalid lock
									System.out.println("Invalid lock, not a SETUP_LOCK...");
									break;
								}
							case CommandString.CLIENT_GAME_START_READY:
								//Start the game
								
						}
						//Send input to GameServer
						//gameServer.validateInput(input);
					}
					in.close();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
	}
	
	//Thread class that controls the synchronization in game server
	protected class CustomLockThread extends Thread {
		CustomLock lock;
		
		public CustomLockThread(CustomLock lockObject) {
			this.lock = lockObject;
		}
		
		@Override
		//Wasteful while loop to check for two synchronizations
		public void run() {
			synchronized(lock) {
				while(lock.lockCounter != 2) {
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				lock.notify();
			}
			
		}
	}
	
	/*	Customized lock object for GameSetup phase
		lockCounter initial value is 0
		lockCounter will increment after a SocketThread received "CLIENT_GAME_SETUP_FINISHED" message
	*/
	protected class CustomLock {
		private int lockCounter;
		private String lockName;
		
		protected CustomLock(String lockName) {
			lockCounter = 0;
			this.lockName = lockName;
		}
		
		protected String getLockName() {
			return lockName;
		}
		
		public synchronized void incrementCounter() { 
			lockCounter++;
		}
	
	}
}