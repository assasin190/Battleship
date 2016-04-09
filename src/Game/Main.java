package Game;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import GameState.*;
import UserInterface.GameSetupUIState;
import UserInterface.MainGameUI;
import UserInterface.MainMenuUIState;
import UserInterface.WaitForConnectionUIState;

public class Main extends JFrame{
	//public JFrame frame;
	public final GameStateManager GSM = new GameStateManager();
	public JPanel currentStatePanel;
	public boolean isClient;
	boolean start = true;
	public GameClient client;
	private Socket socket;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//Create main JFrame
				Main main = new Main();
				main.setVisible(true);
				Thread.currentThread().setName("Main_thread");
				/*
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				*/
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		super();
		setBounds(100, 100, 1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		insertBGM("login.wav");
		start = false;
		//Change UI state -> MAIN_MENU_STATE
		GSM.setState(new MainMenuUIState(this));
		//initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		currentStatePanel = new MainMenuUI(this);
		frame.getContentPane().add(currentStatePanel);
		*/
	}
	
	//P2P Server case
	public void startLocalServer() {
		/* OLD START LOCAL SERVER
		System.out.println("Main_thread: P2P server running");
		//Create and set the local game client
		GameClient localClient = client = new GameClient();
		GameServer gameServer = new GameServer(localClient);
		localClient.setLocalServer(gameServer);
		Thread setupConnectionThread = new SetupConnectionThread(gameServer);
		//Setup another client's connection
		//And wait for a connection
		setupConnectionThread.setName("P2PsetupConThread");
		setupConnectionThread.start();
		//Push UI state -> WAIT_FOR_CONNECTION_STATE
		GSM.pushState(new WaitForConnectionUIState(this));
		//Wait for the thread to finish
		try {
			setupConnectionThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Connection accepted
		//Run server on new thread
		new Thread(gameServer).start();
		//Pop WAIT_FOR_CONNECTION_STATE
		GSM.popState();
		//change UI state -> GAME_SETUP_STATE
		GSM.changeState(new GameSetupUIState(this));
		//Run the game (local client)
		System.out.println("The game is running...");
		localClient.run();
		*/
		//NEW IMPLEMENTATION
		System.out.println(Thread.currentThread().getName() + ": P2P server mode running" );
		//Create the local server
		GameServer gameServer = new GameServer(8080);
		//Run the game server
		Thread serverThread = new Thread(gameServer);
		serverThread.setName("Server_thread");
		serverThread.start();
		
		//System.out.println(Thread.currentThread().getName() + ": is waked");
		
		//Connect to the game server
		try {
			socket = new Socket("localhost", 8080);
			System.out.println(Thread.currentThread().getName() +": connected to the server");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Run the game client
		client = new GameClient(socket);
		client.run();
		
	}
	
	//P2P Client case
	public void Connect(String serverAddr, String serverPort) {
		System.out.println("Main_thread: P2P client running");
		//Connect to the server
		System.out.println("Main_thread: connecting to the server...");
		//Push UI state -> WAIT_FOR_CONNECTION_STATE
		GSM.pushState(new WaitForConnectionUIState(this));
		try {
			socket = new Socket(serverAddr , 8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Main_thread: connection accepted");
		//Socket is accepted
		//Pop WAIT_FOR_CONNECTION_STATE
		GSM.popState();
		//Pop CONNECT_TO_SERVER_P2P_STATE
		GSM.popState();
		//Start the local game
		client = new GameClient(socket);
		//Run the game
		client.run();
		// change UI state -> GAME_SETUP_STATE
		GSM.changeState(new GameSetupUIState(this));
				
	}

	public void insertBGM(String sound) {
		File soundFile = new File(sound);
		AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			if(start){
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void replaceCurrentPanel(JPanel panel) {
		//if currentStatePanel is not null, remove the currentStatePanel
		if(currentStatePanel != null) {
			getContentPane().remove(currentStatePanel);
		}
		currentStatePanel = panel;
		getContentPane().add(panel);
		repaint();
		revalidate();
	}
	
	class SetupConnectionThread extends Thread {
		GameServer gameServer;
		GameClient otherClient;
		
		public SetupConnectionThread(GameServer gameServer) {
			this.gameServer = gameServer;
		}
		
		@Override
		public void run() {
			try {
				gameServer.setServerSocket(new ServerSocket(8080));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Determine if the server run with a local client (P2P Case)
			if(gameServer.isWithLocalClient()) {
				//Wait for the other connection to come in
				try {
					System.out.println(this.getName() + ": waiting for connection..");
					Socket socket = gameServer.getServerSocket().accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Socket is accepted
				System.out.println(this.getName() + ": connection accepted");
			}
			
			//If is Server-Client case
			else {
				// TODO Server-client implementation
				
				
				
			}
		}
	}
	
	protected class GameClient implements Runnable {
		//P2P case field
		protected Socket socket;
		private PrintWriter out;
		private BufferedReader in;
		//Non-serializable field
		protected transient MainGameUI gameUI;
		//Global serializable field
		private Player player;
		private BoardGame boardGame;
		public boolean isYourTurn;
		protected boolean isWithLocalServer = false;
		
		protected GameClient(Socket socket) {
			this.socket = socket;
			
		}
		
		@Override
		public void run() {
			//Game logic
			while(true) {
				try {
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					switch(in.readLine()) {
						case CommandString.SERVER_ANOTHER_CLIENT_NOT_AVAILABLE: //If another client has not connected to the server -> wait until another client's connection is accepted
							//Push UI state -> WAIT_FOR_CONNECTION_STATE
							GSM.pushState(new WaitForConnectionUIState(Main.this));
							//Wait
							//When another client connects, the server returns a string SERVER_ANOTHER_CLIENT_AVAILABLE to all clients
							String input;
							input = in.readLine();
							if(input != null) {
								while(!input.equals(CommandString.SERVER_ANOTHER_CLIENT_AVAILABLE)) {
									System.out.println(Thread.currentThread().getName() + ": Synchronization error!");
									/*Invoke unsync state
									 ...
									 */
								}
							}
							//Server is ready to start the game
							//Change UI state -> GAME_SETUP_READY_STATE
							//GSM.changeState();
							//TODO To be continued
							//
							break;
						case CommandString.SERVER_ANOTHER_CLIENT_AVAILABLE:
							break;
							
							
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

		protected GameClient(GameServer gameServer) {
			//this.gameServer = gameServer;
			player = new Player();
			boardGame = new BoardGame();
			isWithLocalServer = true;
			
		}
		
		public void setLocalServer(GameServer gameServer) {
			//this.gameServer = gameServer;
			
		}
		
		public void setBoardGame(BoardGame boardGame) {
			this.boardGame = boardGame;
		}
		
		public void gameSetup() {
			
		}
		
}
	
}
