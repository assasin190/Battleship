package UserInterface;
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

import Game.Main;
import GameState.ConnectToServerP2PState;
import GameState.GameStateManager;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;

public class MainMenuUI extends JPanel {
	public static String bg = "Bg-play.png";
	Main main;
	public ModeSelectDialog popUpDialog;
	public JTextField name; //Player's name
	public ImageIcon profilePic; //Player's profile photo

	public MainMenuUI(Main main) {
		initialize(main);
	}
	
	private void initialize(Main main) {
		this.main = main;
		//panel = new JPanel();
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(1024,768));

		JPanel gapLeft = new JPanel();
		gapLeft.setPreferredSize(new Dimension(412,300));
		add(gapLeft, BorderLayout.WEST);
		gapLeft.setOpaque(false);
		
		JPanel gapRight = new JPanel();
		gapRight.setPreferredSize(new Dimension(412,300));
		add( gapRight, BorderLayout.EAST);
		gapRight.setOpaque(false);
		
		JPanel gapNorth = new JPanel();
		gapNorth.setPreferredSize(new Dimension(1024, 368));
		add(gapNorth,BorderLayout.NORTH);
		gapNorth.setOpaque(false);
		
		JPanel northMenu = new JPanel();
		northMenu.setPreferredSize(new Dimension(1024,100));
		gapNorth.setLayout(new BorderLayout());
		gapNorth.add(northMenu,BorderLayout.NORTH);
		northMenu.setOpaque(false);
		northMenu.setLayout(new BorderLayout());
		JPanel menu = new JPanel();
		menu.setPreferredSize(new Dimension(1024,50));
		menu.setLayout(new BorderLayout());
		northMenu.add(menu,BorderLayout.NORTH);
		menu.setOpaque(false);
		
		JPanel gapMenu = new JPanel();
		gapMenu.setPreferredSize(new Dimension(724,50));
		gapMenu.setOpaque(false);
		JPanel menuItem = new JPanel();
		menuItem.setOpaque(false);
		menuItem.setPreferredSize(new Dimension(300,50));
		menu.add(gapMenu,BorderLayout.WEST);
		menu.add(menuItem,BorderLayout.EAST);
		
		
	
		JButton btnSetting = new JButton(new ImageIcon("btn-setting.png"));
		btnSetting.setVerticalAlignment(SwingConstants.TOP);
		menuItem.add(btnSetting);
		
		btnSetting.setBorderPainted(false); 
		btnSetting.setContentAreaFilled(false); 
		btnSetting.setFocusPainted(false); 
		
		btnSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeBgDialog frame = new ChangeBgDialog();
			//open new jframe for bg setting
				
			}
		});
		
		
		JButton btnExit = new JButton(new ImageIcon("btn-EXIT.png"));
		btnExit.setVerticalAlignment(SwingConstants.TOP);
		menuItem.add(btnExit);
		btnExit.setBorderPainted(false); 
		btnExit.setContentAreaFilled(false); 
		btnExit.setFocusPainted(false); 
		
		JLabel select = new JLabel("SELECT YOUR PROFILE");
		select.setForeground(Color.WHITE);
		select.setFont(new Font("Arial", Font.BOLD, 20));
		select.setHorizontalAlignment(SwingConstants.CENTER);
		northMenu.add(select,BorderLayout.SOUTH);
		
		
		
		
		ImageIcon P = createImageIcon("avatar.png",200,200);
		
		JPanel player = new JPanel();
		player.setOpaque(false);
		player.setPreferredSize(new Dimension(1024,200));
		gapNorth.add(player,BorderLayout.CENTER);
		JLabel profile = new JLabel(P);
		player.add(profile);
		
		JPanel selectPlayer = new JPanel();
		selectPlayer.setPreferredSize(new Dimension(1024,68));
		gapNorth.add(selectPlayer,BorderLayout.SOUTH);
		selectPlayer.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		selectPlayer.setOpaque(false);
		 
		JButton btnP []= new JButton[6];
	    ImageIcon img [] = new ImageIcon[6];
	    ImageIcon imgP[] = new ImageIcon[6];
	  
	    	    
		for(int i =0;i<6;i++){
		        	img[i]= createImageIcon("p"+(i+1)+".png",50,50);
		        	imgP[i] =  createImageIcon("p"+(i+1)+".png",200,200);
		        	btnP[i]=new JButton(img[i]);
		        	btnP[i].setName(i + "");
		        	selectPlayer.add(btnP[i]); 	
		        	 btnP[i].addMouseListener(new MouseAdapter() {
						 
						 @Override
						 public void mouseClicked(MouseEvent e) {
							 String name = ((JButton)e.getComponent()).getName();
							 int index = Integer.parseInt(name); 
							profile.setIcon(imgP[index]);
							profilePic=imgP[index];
						 }
					 });
		        }
		


		JPanel gapSouth = new JPanel();
		gapSouth.setPreferredSize(new Dimension(1024, 50));
		add(gapSouth,BorderLayout.SOUTH);
		gapSouth.setOpaque(false);
		
		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);
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
		
		name = new JTextField();
		name.setFont(new Font("Avenir", Font.PLAIN, 14));
		name.setColumns(10);
		modePanel.add(name);
		
		//modePanel.setOpaque(false);
		
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		buttonPanel.setPreferredSize(new Dimension(200,250));
		center.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setOpaque(false);
		JPanel gapButton = new JPanel();
		gapButton.setPreferredSize(new Dimension(200,20));
		//buttonPanel.add(gapButton,BorderLayout.CENTER);
		

		JButton clientBtn = new JButton(createImageIcon("btn-client.png",200,70));
		clientBtn.setBorderPainted(false); 
		
		
	
		JButton serverBtn = new JButton(createImageIcon("btn-server.png",200,70));
		serverBtn.setBorderPainted(false);
		
		buttonPanel.add(clientBtn);
		buttonPanel.add(serverBtn);

	
		clientBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// user click client button
				//System.out.println("click");
				JFrame theFrame = (JFrame) SwingUtilities.windowForComponent(MainMenuUI.this);
				//Change UI state -> CONNECT_TO_SERVER_P2P_STATE
				GameStateManager.changeState(new ConnectToServerP2PState(MainMenuUI.this.main, theFrame));
				//popUpDialog = new ModeSelectDialog((JFrame) SwingUtilities.windowForComponent(MainMenuUI.this), "Select Mode", MainMenuUI.this.main);
				
			}
		});
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
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon bgIcon = createImageIcon(bg,1024, 768);
		Image img = bgIcon.getImage();
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(img, 0, 0, 1024, 768, this);
	}
}