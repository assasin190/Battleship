package Game;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import GameState.GameStateManager;
import UserInterface.MainMenuUI;

public class Main {

	public JFrame frame;
	public GameStateManager gsm;
	public boolean isClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private Main() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainMenuUI main = new MainMenuUI(this);
		frame.getContentPane().add(main.panel);
	}
	
	//P2P Server case
	protected void startLocalServer() throws InterruptedException {
		GameClient localClient = new GameClient();
		GameServer gameServer = new GameServer(localClient);
		Thread serverThread = new Thread(gameServer);
		//Run server on new thread
		serverThread.start();
		//Wait for the connection
		/*change UI state
		 ...
		 */
		
		//Wait for server to completes the connection with another client
		synchronized(serverThread) {
			serverThread.wait();
		}
		//Run game setup
		localClient.run();
		/*
		 ...Change UI State
		 */
		
		
	}
	
	//P2P Client case
	protected void Connect() throws UnknownHostException, IOException {
		Socket socket = new Socket("Server Address", 65536);
	}
	
}
