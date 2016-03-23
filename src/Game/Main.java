package Game;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
	protected void waitForConnection() throws IOException{
		ServerSocket serverSocket = new ServerSocket(65536);
		Socket otherClientSocket = serverSocket.accept();
		/* Change main UI State
		 ...
		 */
		InputStream input = otherClientSocket.getInputStream();
		OutputStream output = otherClientSocket.getOutputStream();
		GameServer gameServer = new GameServer(input, output);
		// run the game server
		gameServer.run();
	}
	
	//P2P Client case
	protected void Connect() {
		
	}
}
