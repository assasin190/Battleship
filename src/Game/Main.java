package Game;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

public class Main extends JFrame{
	//public JFrame frame;
	public final GameStateManager GSM = new GameStateManager();
	public JPanel currentStatePanel;
	public boolean isClient;
	boolean start = true;
	public GameClient client;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//Create main JFrame
				Main main = new Main();
				main.setVisible(true);
				
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
		//Change UI state -> WAIT_FOR_CONNECTION_STATE
		//Wait for the thread to finish
		try {
			setupConnectionThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		gameServer.setLocalClient(localClient);
		
		//Run server on new thread
		Thread serverThread = new Thread(gameServer);
		serverThread.start();
		
		/*change UI state -> GAME_SETUP_STATE
		...
		*/
		//Run the game (local client)
		System.out.println("The game is running...");
		localClient.run();

		
	}
	
	//P2P Client case
	public void Connect(String serverAddr, String serverPort) {
		System.out.println("Main_thread: P2P client running");
		//Connect to the server
		System.out.println("Main_thread: connecting to the server...");
		try {
			Socket socket = new Socket(serverAddr , 8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Main_thread: connection accepted");
		//Socket is accepted
		//Start the local game
		GameClient networkClient = new GameClient();
		/* change UI state -> GAME_SETUP_STATE
		 *
		 */
		//Run the game (distant client)
		networkClient.run();
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
	
	private class SocketThread extends Thread {
		GameServer gameServer;
		
		public SocketThread(GameServer gameServer) {
			this.gameServer = gameServer;
		}
		
		@Override
		public void run() {
			try {
				Socket socket = gameServer.getServerSocket().accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	protected class GameClient implements Runnable {
		//P2P case field
		protected transient GameServer gameServer;
		//Local, Non-serializable field
		protected Socket socket;
		protected transient GameSetupUIState setupGameUI;
		//Non-serializable field
		protected transient MainGameUI gameUI;
		//Global serializable field
		protected Player player;
		protected BoardGame board;
		protected boolean isYourTurn;
		protected boolean isWithLocalServer = false;
		
		protected GameClient() {
			player = new Player();
			board = new BoardGame();
			
		}
		
		public void setLocalServer(GameServer gameServer2) {
			// TODO Auto-generated method stub
			
		}

		protected GameClient(GameServer gameServer) {
			this.gameServer = gameServer;
			player = new Player();
			board = new BoardGame();
			isWithLocalServer = true;
			
		}

		@Override
		public void run() {
			//Setup the game
			gameSetup();
			if(isWithLocalServer) {
				
			}
			else {
				
			}
		}
		
		public void gameSetup() {
			
		}
		
}
	
}
