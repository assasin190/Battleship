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
import UserInterface.MainMenuUI;

public class Main extends JFrame{
	public static GameStateManager gsm = new GameStateManager();
	//public JFrame frame;
	public JPanel currentStatePanel;
	public boolean isClient;
	boolean start = true;
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
		//Change UI state -> MAIN_MENU_STATE
		Main.gsm.setState(new MainMenuState(this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		insertBGM("login.wav");
		start = false;
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
		System.out.println("Start local server...");
		GameServer gameServer = new GameServer();
		Thread setUpConnectionThread = new SetupConnectionThread(gameServer);
		//Setup another client's connection
		//And wait for a connection
		setUpConnectionThread.start();
		//Change UI state -> WAIT_FOR_CONNECTION_STATE
		//Wait for the thread to finish
		try {
			setUpConnectionThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Create and set the local game client
		GameClient localClient = new GameClient(gameServer);
		gameServer.setLocalClient(localClient);
		
		//Run server on new thread
		Thread serverThread = new Thread(gameServer);
		serverThread.start();
		
		//change UI state
		 Main.gsm.changeState(new GameSetupState(this));
		int z = 1;
		//Run the game (local client)
		localClient.run();

		
	}
	
	//P2P Client case
	public void Connect() {
		MainMenuUI mainUI = (MainMenuUI) currentStatePanel;
		String serverAddr = mainUI.popUpDialog.ipTextField.getText();
		String serverPort = mainUI.popUpDialog.ipTextField.getText();
		//Connect to the server
		try {
			Socket socket = new Socket(serverAddr , 8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Socket is accepted
		//Start the local game
		GameClient networkClient = new GameClient();
		/* change UI state
		 *
		 */
		//Run the game (distant client)
		networkClient.run();
		
		
	}
	
	private class WaitForConnectionReadyThread extends Thread {
		public void run(boolean isLocal) throws IOException {
			ServerSocket serverSocket = new ServerSocket(8080);
			Socket socket = serverSocket.accept();
			/* Change main UI State
			 ...
			 */
			
		}
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
					Socket socket = gameServer.getServerSocket().accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Socket is accepted
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
}
