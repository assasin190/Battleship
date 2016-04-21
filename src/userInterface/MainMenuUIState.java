package userInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import GameState.ConnectToServerP2PState;
import GameState.GameState;
import GameState.GameStateManager;
import game.Main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;

public class MainMenuUIState extends UI {
	//public static String bg = "Bg-play.png";
	static ChangeBgUIState panelSetting;
	public ConnectToServerP2PUIState popUpDialog;
	public JTextField nameField; // Player's name
	public ImageIcon profilePic; // Player's profile photo

	//Test purpose
	public MainMenuUIState() {
		stateString = GameState.MAIN_MENU_STATE;
	}
	
	public MainMenuUIState(Main main) {
		super(main);
		stateString = GameState.MAIN_MENU_STATE;
		initialize();
	//	panel = UI.createJPanelWithBackground(bgImg);
	}
	
	protected void initialize() {
		panel = UI.createJPanelWithBackground(main.background);
		System.out.println(main.background.toString());
		/*
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon bgIcon = createImageIcon(bg, 1024, 768);
				Image img = bgIcon.getImage();
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawImage(img, 0, 0, 1024, 768, this);
			}
		};
		*/
		panel.setLayout(new BorderLayout(0, 0));
		panel.setPreferredSize(new Dimension(1024, 768));

		JPanel gapLeft = new JPanel();
		gapLeft.setPreferredSize(new Dimension(412, 300));
		panel.add(gapLeft, BorderLayout.WEST);
		gapLeft.setOpaque(false);

		JPanel gapRight = new JPanel();
		gapRight.setPreferredSize(new Dimension(412, 300));
		panel.add(gapRight, BorderLayout.EAST);
		gapRight.setOpaque(false);

		JPanel gapNorth = new JPanel();
		gapNorth.setPreferredSize(new Dimension(1024, 368));
		panel.add(gapNorth, BorderLayout.NORTH);
		gapNorth.setOpaque(false);

		JPanel northMenu = new JPanel();
		northMenu.setPreferredSize(new Dimension(1024, 100));
		gapNorth.setLayout(new BorderLayout());
		gapNorth.add(northMenu, BorderLayout.NORTH);
		northMenu.setOpaque(false);
		northMenu.setLayout(new BorderLayout());
		JPanel menu = new JPanel();
		menu.setPreferredSize(new Dimension(1024, 50));
		menu.setLayout(new BorderLayout());
		northMenu.add(menu, BorderLayout.NORTH);
		menu.setOpaque(false);

		JPanel gapMenu = new JPanel();
		gapMenu.setPreferredSize(new Dimension(724, 50));
		gapMenu.setOpaque(false);
		JPanel menuItem = new JPanel();
		menuItem.setOpaque(false);
		menuItem.setPreferredSize(new Dimension(300, 50));
		menu.add(gapMenu, BorderLayout.WEST);
		menu.add(menuItem, BorderLayout.EAST);

		JButton btnSetting = new JButton(new ImageIcon("btn-setting.png"));
		btnSetting.setVerticalAlignment(SwingConstants.TOP);
		menuItem.add(btnSetting);

		btnSetting.setBorderPainted(false);
		btnSetting.setContentAreaFilled(false);
		btnSetting.setFocusPainted(false);

		// sirawich click setting
		btnSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Push CHANGE_BG_STATE
				main.GSM.pushState(new ChangeBgUIState(main));
				
				// ChangeBgDialog frame = new ChangeBgDialog();
				// frame.setVisible(true);
				
				/*
				JFrame frame = new JFrame();
				panelSetting = new ChangeBgDialog(main);
				frame.add(panelSetting.panel);
				frame.setVisible(true);
				
		
				System.out.println("click setting");
				*/
				
				// open new jframe for bg setting

			}
		});

		JButton btnExit = new JButton(new ImageIcon("btn-EXIT.png"));
		btnExit.setVerticalAlignment(SwingConstants.TOP);
		menuItem.add(btnExit);
		btnExit.setBorderPainted(false); 
		btnExit.setContentAreaFilled(false); 
		btnExit.setFocusPainted(false); 
		
		btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
			
		});
		
		JLabel select = new JLabel("SELECT YOUR PROFILE");
		select.setForeground(Color.WHITE);
		select.setFont(new Font("Arial", Font.BOLD, 20));
		select.setHorizontalAlignment(SwingConstants.CENTER);
		northMenu.add(select, BorderLayout.SOUTH);

		ImageIcon P = Main.createImageIcon("player/p0.png", 200, 200);
		ImageIcon pNull = Main.createImageIcon("player/p0.png", 80, 80);
		profilePic = pNull;

		JPanel player = new JPanel();
		player.setOpaque(false);
		player.setPreferredSize(new Dimension(1024, 200));
		gapNorth.add(player, BorderLayout.CENTER);
		JLabel profile = new JLabel(P);
		player.add(profile);

		JPanel selectPlayer = new JPanel();
		selectPlayer.setPreferredSize(new Dimension(1024, 68));
		gapNorth.add(selectPlayer, BorderLayout.SOUTH);
		selectPlayer.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		selectPlayer.setOpaque(false);

		JButton btnP[] = new JButton[6];
		ImageIcon img[] = new ImageIcon[6];
		ImageIcon imgP[] = new ImageIcon[6];
		ImageIcon imgPP[] = new ImageIcon[6];

		for (int i = 0; i < 6; i++) {
			img[i] = Main.createImageIcon("player/p" + (i + 1) + ".png", 50, 50);
			imgP[i] = Main.createImageIcon("player/p" + (i + 1) + ".png", 200, 200);
			imgPP[i] = Main.createImageIcon("player/p" + (i + 1) + ".png", 80, 80); //variable for setup profile pic 
			btnP[i] = new JButton(img[i]);
			btnP[i].setName(i + "");
			selectPlayer.add(btnP[i]);
			btnP[i].addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					String name = ((JButton) e.getComponent()).getName();
					int index = Integer.parseInt(name);
					profile.setIcon(imgP[index]);
					profilePic = imgPP[index];
					main.player.setImage(profilePic);
				}
			});
		}

		JPanel gapSouth = new JPanel();
		gapSouth.setPreferredSize(new Dimension(1024, 50));
		panel.add(gapSouth, BorderLayout.SOUTH);
		gapSouth.setOpaque(false);

		JPanel center = new JPanel();
		panel.add(center, BorderLayout.CENTER);
		center.setPreferredSize(new Dimension(200, 300));
		center.setLayout(new BorderLayout(0, 0));
		center.setOpaque(false);

		JPanel modePanel = new JPanel();
		modePanel.setPreferredSize(new Dimension(200, 80));
		modePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		modePanel.setOpaque(false);

		JLabel lblLogInAs = new JLabel("LOG IN AS");
		lblLogInAs.setForeground(Color.WHITE);
		lblLogInAs.setFont(new Font("Arial", Font.BOLD, 20));
		lblLogInAs.setHorizontalAlignment(SwingConstants.CENTER);
		modePanel.add(lblLogInAs);
		center.add(modePanel, BorderLayout.NORTH);

		nameField = new JTextField();
		nameField.setFont(new Font("Avenir", Font.PLAIN, 14));
		nameField.setColumns(10);
		modePanel.add(nameField);

		// modePanel.setOpaque(false);

		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		buttonPanel.setPreferredSize(new Dimension(200, 250));
		center.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setOpaque(false);
		JPanel gapButton = new JPanel();
		gapButton.setPreferredSize(new Dimension(200, 20));
		// buttonPanel.add(gapButton,BorderLayout.CENTER);

		JButton clientBtn = new JButton(Main.createImageIcon("btn-client.png", 200, 70));
		clientBtn.setBorderPainted(false);
		clientBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// User clicked client button
				//Push UI state -> CONNECT_TO_SERVER_P2P_STATE
				main.player.setImage(profilePic);
				main.player.setName(nameField.getText());
				main.GSM.pushState(new ConnectToServerP2PUIState(MainMenuUIState.this.main));

			}
		});

		JButton serverBtn = new JButton(Main.createImageIcon("btn-server.png", 200, 70));
		serverBtn.setBorderPainted(false);
		serverBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//User clicked server button
				//Run server mode
				main.player.setImage(profilePic);
				main.player.setName(nameField.getText());
				main.startLocalServer();
				/* Push UI state -> WAIT_FOR_CONNECTION_STATE
				   startLocalServer will update UI
				*/
			}
		});
		
		JButton connectBtn = new JButton(Main.createImageIcon("btn-connect.png", 200, 70));
		connectBtn.setBorderPainted(false);
		
		buttonPanel.add(clientBtn);
		buttonPanel.add(serverBtn);
		buttonPanel.add(connectBtn);
	}

	@Override
	public void entered() {
		System.out.println(Thread.currentThread().getName() + ": entered " + stateString);
		main.replaceCurrentPanel(panel);
	}

	@Override
	public void leaving() {
		//Buffer the MAIN_MENU_STATE
		System.out.println(Thread.currentThread().getName() + ": leaving " + stateString);
		main.getContentPane().remove(main.currentStatePanel);
		//main.GSM.storeBufferedState(GameStateManager.MAIN_MENU_STATE, this);
		
	}

	@Override
	public void obscuring() {
		System.out.println(Thread.currentThread().getName() + ": " + stateString + " being stacked");
		main.setEnabled(false);
		
	}

	@Override
	public void revealed() {
		System.out.println(Thread.currentThread().getName() + ": " + stateString + " resumed");
		main.setEnabled(true);
		
	}
}