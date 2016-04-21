package game;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import game.*;
import GameState.*;
import userInterface.*;

public class Main extends JFrame {
	// public JFrame frame;
	public final GameStateManager GSM = new GameStateManager();
	public JPanel currentStatePanel;
	public boolean isClient;
	public boolean start = true;
	public GameClient client;
	private Socket socket;
	public Player player;
	public Image background;
	Clip battleClip, mainmenuClip, setupClip;

	/**
	 * Launch the application.
	 * 
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Main main = new Main();
				main.setVisible(true);
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
		setResizable(false);
		// insertBGM("login.wav");
		// start = false;
		player = new Player();
		background = createImageIcon("bg/Bg-play.png", 1024, 768).getImage();
		// Change UI state -> MAIN_MENU_STATE
		GSM.setState(new MainMenuUIState(this));
		createSound();
		mainmenuClip.start();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void startLocalServer() {
		System.out.println(Thread.currentThread().getName() + ": P2P server mode running");
		// Create the local server
		GameServer gameServer = new GameServer(8080);
		// Run the game server
		new Thread(gameServer).start();
		// Connect to the game server
		try {
			socket = new Socket("localhost", 8080);
			System.out.println(Thread.currentThread().getName() + ": connected to the server");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Run the game client
		client = new GameClient(socket);
		client.run();

	}

	// P2P Client case
	public void connect(String serverAddr, String serverPort) {
		System.out.println(Thread.currentThread().getName() + ": P2P client mode running");
		// Connect to the server
		System.out.println(Thread.currentThread().getName() + ": connecting to the server...");
		// Connect to match server
		try {
			socket = new Socket(serverAddr, 8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": connection accepted");
		// Run the game client
		client = new GameClient(socket);
		client.run();
	}

	// Server-client case
	public void connect() {
		// Set the server address here
		String serverAddr = "localhost";
		System.out.println(Thread.currentThread().getName() + ": Server-client mode running");
		// Connect to match server
		System.out.println(Thread.currentThread().getName() + ": connecting to the match server...");
		try {
			socket = new Socket(serverAddr, 8000); // Match server use port 8000
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String input = in.readLine();
			String portStr = input.substring(input.indexOf("_") + 1);
			int portNumber = Integer.parseInt(portStr);
			System.out.println(Thread.currentThread().getName() + ": got port number " + portNumber);
			socket.close();
			socket = new Socket(serverAddr, portNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
		client = new GameClient(socket);
		client.run();
	}

	public void createSound() {

		File soundFile1 = new File("sound/menu.wav");
		File soundFile2 = new File("sound/battle.wav");
		File soundFile3 = new File("sound/setup.wav");

		AudioInputStream audioIn1 = null;
		AudioInputStream audioIn2 = null;
		AudioInputStream audioIn3 = null;

		try {

			audioIn1 = AudioSystem.getAudioInputStream(soundFile1);
			mainmenuClip = AudioSystem.getClip();
			mainmenuClip.open(audioIn1);

			audioIn2 = AudioSystem.getAudioInputStream(soundFile2);
			battleClip = AudioSystem.getClip();
			battleClip.open(audioIn2);

			audioIn3 = AudioSystem.getAudioInputStream(soundFile3);
			setupClip = AudioSystem.getClip();
			setupClip.open(audioIn3);

		} catch (UnsupportedAudioFileException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (LineUnavailableException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	public void insertBGM(String sound) {
		File soundFile = new File(sound);
		AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			if (start) {
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
		// if currentStatePanel is not null, remove the currentStatePanel
		if (currentStatePanel != null) {
			getContentPane().remove(currentStatePanel);
		}
		currentStatePanel = panel;
		getContentPane().add(panel);
		repaint();
		revalidate();
	}

	public static Point getPopUpLocation(UI ui) {

		// frame
		Dimension frame_size = ui.main.getSize();
		int frame_width = frame_size.width;
		int frame_height = frame_size.height;

		Point frame_point = ui.main.getLocation();
		int frame_x = frame_point.x;
		int frame_y = frame_point.y;

		// dialog
		Dimension dialog_size = ui.dialog.getSize();
		int dialog_width = dialog_size.width;
		int dialog_height = dialog_size.height;

		// System.out.println("mainFrame_width/2 = "+frame_size.getWidth()/2);
		// System.out.println("mainFrame_height/2 = "+frame_size.getHeight()/2);
		// System.out.println("dialog_width/2 = "+dialog_width/2);
		// System.out.println("dialog_height/2 = "+dialog_height/2);

		int x_dialog = (frame_width / 2) - (dialog_width / 2) + frame_x;
		int y_dialog = (frame_height / 2) - (dialog_height / 2) + frame_y;
		Point result = new Point(x_dialog, y_dialog);

		// System.out.println("sirawich point main x= :
		// "+frame_location.getX()+" , y= "+frame_location.getY());
		// System.out.println("sirawich point dialog x= :" +x_dialog +", y =
		// "+y_dialog);

		return result;
	}

	public static ImageIcon createImageIcon(String path, int width, int height) {
		Image img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
		}
		Image resizedImage = img.getScaledInstance(width, height, 0);
		return new ImageIcon(resizedImage);
	}

	private class SetupConnectionThread extends Thread {
		GameServer gameServer;

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

			// Determine if the server run with a local client (P2P Case)
			if (gameServer.isWithLocalClient()) {
				// Wait for the other connection to come in
				try {
					System.out.println(this.getName() + ": waiting for connection..");
					Socket socket = gameServer.getServerSocket().accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Socket is accepted
				System.out.println(this.getName() + ": connection accepted");
			}

			// If is Server-Client case
			else {
				// TODO Server-client implementation

			}
		}
	}

	public class GameClient implements Runnable {
		// P2P case field
		protected Socket socket;
		protected PrintWriter out;
		protected BufferedReader in;
		protected ObjectOutputStream oos;
		protected ObjectInputStream ois;
		protected GameSetupUIState gameSetupUI;
		protected GameUIState gameUI;
		protected boolean myTurn;
		protected int historicalScore;
		protected int currentScore;
		public String opponentName;
		public Timer timer_turn_duration;
		// UI field related to GameClient

		// Global serializable field
		public BoardGame boardGame;
		protected boolean isWithLocalServer = false;
		private String playerState;

		protected GameClient(Socket socket) {
			this.socket = socket;
			playerState = PlayerState.NULL_STATE;
			myTurn = false;
			currentScore = 0;
			// Create a board game
			boardGame = new BoardGame();

		}

		@Override
		public void run() {
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Run background worker thread
			new BackgroundInputReader().execute();

		}

		public void startGameSetup() {
			out.println(CommandString.CLIENT_GAME_SETUP_READY);
			System.out
					.println(Thread.currentThread().getName() + ": " + CommandString.CLIENT_GAME_SETUP_READY + " sent");
		}

		public void startGame() {
			out.println(CommandString.CLIENT_GAME_START_READY);
			System.out
					.println(Thread.currentThread().getName() + ": " + CommandString.CLIENT_GAME_START_READY + " sent");
			playerState = PlayerState.EXPECT_SERVER_START_GAME;
		}

		public void mark(int y, int x) {
			// Mark the square y,x
			out.println("MARK_" + y + "," + x);
			playerState = PlayerState.IDLE;
			myTurn = false;
		}

		public boolean isMyTurn() {
			return myTurn;
		}

		protected GameClient(GameServer gameServer) {
			// this.gameServer = gameServer;
			player = new Player();
			boardGame = new BoardGame();
			isWithLocalServer = true;

		}

		public void setLocalServer(GameServer gameServer) {
			// this.gameServer = gameServer;

		}

		public void setBoardGame(BoardGame boardGame) {
			this.boardGame = boardGame;
		}

		public void gameSetup() {

		}

		class BackgroundInputReader extends SwingWorker<Void, String> {

			/*
			 * The purpose of BackgroundInputReader is to delegate the input
			 * reading task to worker thread So that it does not block EDT Also
			 * responsible to update UI
			 */
			@Override
			protected Void doInBackground() throws Exception {
				// Repeatedly listen for input message
				while (true) {
					String input = in.readLine();
					System.out.println(Thread.currentThread().getName() + ": " + input + " received");
					if (input != null)
						publish(input);
					else
						; // TODO Raise NullMessageException
				}

			}

			@Override
			protected void process(final List<String> inputList) {
				// Client game logic
				System.out.println(Thread.currentThread().getName() + ": process invoked");
				System.out.println(Thread.currentThread().getName() + ": the size of inputList is " + inputList.size());
				for(String input : inputList) { //Process every input
					if(input == null); //TODO Raise NullMessageException
					switch(input) {
						case CommandString.SERVER_OTHER_CLIENT_NOT_AVAILABLE: //If another client has not connected to the server -> wait until another client's connection is accepted
							if(!(playerState.equals(PlayerState.NULL_STATE))) break; //TODO Raise SynchronizeErrorException
							System.out.println(Thread.currentThread().getName() + ": The other client is not available. waiting...");
							//Push UI state -> WAIT_FOR_CONNECTION_STATE
							GSM.pushState(new WaitForConnectionUIState(Main.this));
							//Wait
							//When another client connects, the server returns a string SERVER_ANOTHER_CLIENT_AVAILABLE to all clients
							break;
							
						case CommandString.SERVER_OTHER_CLIENT_AVAILABLE:
							if(!playerState.equals(PlayerState.NULL_STATE) || !playerState.equals(PlayerState.EXPECT_SERVER_OTHER_CLIENT_AVAILABLE)); //Raise SynchronizationErrorException
							//The other client has connected
							//Pop UI state UNTIL MAIN_GAME_STATE
							GSM.popStateUntil(GameState.MAIN_MENU_STATE);
							//Push GAME_SETUP_READY_STATE
							GSM.pushState(new GameSetupReadyUIState(Main.this));
							//Set playerState EXPECT_SERVER_GAME_SETUP
							playerState = PlayerState.EXPECT_SERVER_GAME_SETUP;
							//Wait for the player to press Ready...
							//System.out.println("name :" + player.name);
							//out.println("CLIENT_NAME_" + player.getName());
							break;
							
						case CommandString.SERVER_START_GAME_SETUP:
							if(!playerState.equals(PlayerState.EXPECT_SERVER_GAME_SETUP)); //Raise SynchronizationErrorException
							//Server is ready to start game setup
							//Start the game setup
							//Pop UI state until MAIN_MENU_STATE
							out.println("CLIENT_NAME_" + player.getName());
							GSM.popStateUntil(GameState.MAIN_MENU_STATE);
							//Change UI state -> GAME_SETUP_STATE
							gameSetupUI = new GameSetupUIState(Main.this);
							mainmenuClip.stop();
							setupClip.start();
							GSM.changeState(gameSetupUI);
							playerState = PlayerState.START_GAME_SETUP;

					case CommandString.SERVER_OPPONENT_NOT_READY:
						if (!playerState.equals(PlayerState.EXPECT_SERVER_START_GAME))
							return; // If not pressing ready yet -> do nothing
						// The other client is not ready
						// Wait
						// Push WAIT_FOR_OPPONENT_READY State
						GSM.pushState(new WaitForOpponentReadyUIState(Main.this));
						break;

					case CommandString.SERVER_START_GAME:
						if (playerState.equals(PlayerState.EXPECT_SERVER_START_GAME)
								|| playerState.equals(PlayerState.START_GAME_SETUP)) {
							// Server is ready to start game
							// Start the game
							// Pop UI state until GAME_SETUP_STATE
							GSM.popStateUntil(GameState.GAME_SETUP_STATE);
							setupClip.close();
							battleClip.start();
							FloatControl gainControl = 
								    (FloatControl) battleClip.getControl(FloatControl.Type.MASTER_GAIN);
								gainControl.setValue(-5.0f); // Reduce volume by 10 decibels.
							// Change UI state -> GAME_STATE
							gameUI = new GameUIState(Main.this);
							GSM.changeState(gameUI);
							playerState = PlayerState.IDLE;
							break;
						}
						// TODO
						break;
					case CommandString.SERVER_GRANT_TURN: // Server give you a
															// turn
						if (playerState.equals(PlayerState.IDLE)) {
							playerState = PlayerState.PLAYING;
							myTurn = true;

							// Sirawich
							ActionListener timerTask = new ActionListener() {
								int countdown = 5;

								@Override
								public void actionPerformed(ActionEvent e) {

									if (countdown == 0) {
										
										// time up    expire random mark(y,x)
										Random r = new Random();
								    	int Low = 0;
								    	int High = 8;
								    	int random_x = r.nextInt(High-Low) + Low;
								    	int random_y = r.nextInt(High-Low) + Low;
										
								    	System.out.println("random:" + random_y + ", " + random_x);
										mark(random_y,random_x);
										
										gameUI.lblTimer.setText("END");
										timer_turn_duration.stop();
						

										// System.out.println("end");
									} else {
										gameUI.lblTimer.setText(countdown + "");

										// call start timer of GameUIState
										System.out.println("countdown = " + countdown);
										countdown--;
									}

								}
							};
							timer_turn_duration = new Timer(1000, timerTask);
							timer_turn_duration.start();
							break;
						}

					case CommandString.SERVER_INDICATE_YOU_WIN: //You won the game
						//TEST
						JOptionPane.showMessageDialog(Main.this, "Congratulations! You win the game.");
						battleClip.close();
						break;
						
					case CommandString.SERVER_INDICATE_YOU_LOSE: //You lose the game
						//TEST
						JOptionPane.showMessageDialog(Main.this, "Congratulations! You lose the game.");
						battleClip.close();
						break;
						
					default:
						if (input.indexOf("RETURN_MARK") != -1) {
							String index = input.substring(input.indexOf("_", input.indexOf("_") + 1) + 1,
									input.indexOf(",") + 2);
							int y = Integer.parseInt(index.substring(0, 1));
							int x = Integer.parseInt(index.substring(2));
							boolean sunk = Boolean.parseBoolean(input.substring(input.lastIndexOf(",") + 1));
							String sub = input.substring(0, input.lastIndexOf(","));
							System.out.println("sub: " + sub);
							boolean hit = Boolean.parseBoolean(sub.substring(sub.lastIndexOf("_") + 1));
							System.out.println("hit: " + hit);
							System.out.println("sunk: " + sunk);
							Square markedSquare = boardGame.board[y][x];
							SquareLabel hitSquareLabel = markedSquare.getSquareLabel();
							markedSquare.marked = true;
							if (hit) { // If hit
								// Update UI (hit)
								hitSquareLabel.setIcon(createImageIcon("effect/hit.png", 37, 37));
								gameUI.P1Score.setText(++currentScore + "");
								insertBGM("sound/hit.wav");
								try {
									Thread.sleep(100);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else { // If not hit
								boardGame.board[y][x].marked = true;
								// Update UI (not hit)
								hitSquareLabel.setIcon(createImageIcon("effect/miss.png", 37, 37));
								insertBGM("sound/miss.wav");
								try {
									Thread.sleep(100);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
							if (currentScore == 16) {
								// Win
								out.println(CommandString.CLIENT_WIN);
								JOptionPane.showMessageDialog(Main.this,
										"Congratulations! " + player.getName() + " win the game.");
							}
								
						} else if(input.indexOf("MARK") != -1) {
							String index = input.substring(input.indexOf("_") + 1);
							int y = Integer.parseInt(index.substring(0, 1));
							int x = Integer.parseInt(index.substring(2));
							boolean[] hitSunk = boardGame.fireShot(y, x);
							boolean hit = hitSunk[0];
							boolean sunk = hitSunk[1];
							boolean lose = hitSunk[2];
							Square markedSquare = boardGame.myBoard[y][x];
							SquareLabel hitSquareLabel = boardGame.myBoard[y][x].getSquareLabel();
							//TODO Update UI
							if(hit) { //If hit
								//Update UI (hit)
								hitSquareLabel.setIcon(createImageIcon("effect/hit.png", 37, 37));
								repaint();
								revalidate();
							} else { //If not hit
								boardGame.board[y][x].marked = true;
								//Update UI (not hit)
								hitSquareLabel.setIcon(createImageIcon("effect/miss.png", 37, 37));
							}
							// TODO check if the player won the game
							out.println("RETURN_MARK_" + y + "," + x + "_" + hit + "," + sunk);
							
							playerState = PlayerState.IDLE;
							myTurn = true;

							// Sirawich
							ActionListener timerTask = new ActionListener() {
								int countdown = 5;

								@Override
								public void actionPerformed(ActionEvent e) {

									if (countdown == 0) {
										
										// time up    expire random mark(y,x)
										Random r = new Random();
								    	int Low = 0;
								    	int High = 8;
								    	int random_x = r.nextInt(High-Low) + Low;
								    	int random_y = r.nextInt(High-Low) + Low;
										
								    	System.out.println("random:" + random_y + ", " + random_x);
										mark(random_y,random_x);
										
										gameUI.lblTimer.setText("END");
										timer_turn_duration.stop();
						

										// System.out.println("end");
									} else {
										gameUI.lblTimer.setText(countdown + "");

										// call start timer of GameUIState
										System.out.println("countdown = " + countdown);
										countdown--;
									}
								}
							};
							timer_turn_duration = new Timer(1000, timerTask);
							timer_turn_duration.start();
							
						} else if (input.indexOf("CLIENT_NAME") != -1) {
							opponentName = input.substring(input.lastIndexOf("_") + 1);
							gameSetupUI.p2.setText(opponentName);
							
						}
					}
				}
			}	
		}
	}
}
