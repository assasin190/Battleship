package Game;
import java.awt.Dimension;
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
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainMenuUI main = new MainMenuUI(this);
		frame.getContentPane().add(main.panel);
	}
	
	//P2P Server case
	protected void startLocalServer() throws InterruptedException {
		GameServer gameServer = new GameServer(true);
		Thread serverThread = new Thread(gameServer);
		//Run server on new thread
		serverThread.start();
		//Run local client's game setup
		GameClient localClient = new GameClient();
		/*change UI state
		 ...
		 */
		localClient.run();
		
		//Wait for server to completes the connection with another client
		synchronized(serverThread) {
			serverThread.wait();
		}
		//Run the game
		localClient.run();
		/*
		 ...Change UI State
		 */
		
		
	}
	
	//P2P Client case
	protected void Connect() throws UnknownHostException, IOException {
		Socket socket = new Socket("Server Address", 65536);
	}
	
	private class WaitForConnectionReadyThread extends Thread {
		public void run(boolean isLocal) throws IOException {
			ServerSocket serverSocket = new ServerSocket(65536);
			Socket socket = serverSocket.accept();
			/* Change main UI State
			 ...
			 */
			
		}
	}
}
