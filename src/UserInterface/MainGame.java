package UserInterface;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

public class MainGame{
	public JPanel panel;

	/**
	 * Create the panel.
	 */
	public MainGame() {
		initialize();
	}
	
	private void initialize() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		panel.setPreferredSize(new Dimension(1024,768)); // 768-568-100
		
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension(1024, 150));
		panel.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JPanel leftTop = new JPanel();
		JPanel rightTop = new JPanel();
		leftTop.setPreferredSize(new Dimension(150,100));
		rightTop.setPreferredSize(new Dimension(150,100));
		
	
		
		JButton logo = new JButton("");
		logo.setIcon ( new ImageIcon ( "logo.png" ) );
		top.add(leftTop,BorderLayout.WEST);
		top.add(logo, BorderLayout.CENTER);
		//top.add(lblPlaceYourShip,BorderLayout.SOUTH);
		top.add(rightTop,BorderLayout.EAST);
		
		/*LEFT BORDER*/
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(150,568));
		panel.add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(0, 0));
		
		
		/*CENTER*/
		JPanel center = new JPanel();
		center.setPreferredSize(new Dimension(724,568));
		panel.add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(0, 0));
		
		/*PLAYER1 TABLE*/
		JPanel leftCol = new JPanel();
		leftCol.setPreferredSize(new Dimension(300,568));
		center.add(leftCol, BorderLayout.WEST);
		
		
		JPanel player1 = new JPanel();
		player1.setBackground(Color.PINK);
		player1.setPreferredSize(new Dimension(300,300));
		
		JPanel topP1 = new JPanel();
		topP1.setPreferredSize(new Dimension(300, 100));
		
		JLabel lblPlaceYourShip = new JLabel("PLACE YOUR SHIPS!");
		lblPlaceYourShip.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPlaceYourShip.setFont(new Font("Avenir", Font.BOLD, 20));
		lblPlaceYourShip.setHorizontalAlignment(SwingConstants.LEFT);
		topP1.add(lblPlaceYourShip);
		
		
		JPanel bottomP1 = new JPanel();
		bottomP1.setPreferredSize(new Dimension(300,100));	
		
		leftCol.setLayout(new BorderLayout(0,0));
		leftCol.add(topP1,BorderLayout.NORTH);
		topP1.setLayout(new GridLayout(1, 0, 0, 0));
		leftCol.add(player1, BorderLayout.CENTER);
		leftCol.add(bottomP1 , BorderLayout.SOUTH);
		GridLayout tableLayout = new GridLayout(8,8);
		player1.setLayout(tableLayout);
		for(int i =0; i<64; i++) {
			player1.add(new JButton("Button"));
		}
		
		
		/*CENTER GAP*/
		JPanel centerCol = new JPanel();
		centerCol.setPreferredSize(new Dimension(124,300));
		center.add(centerCol, BorderLayout.CENTER);
		
		
		/*PLAYER2 TABLE*/
		JPanel rightCol = new JPanel();
		rightCol.setPreferredSize(new Dimension(300,618)); //568+50
		center.add(rightCol, BorderLayout.EAST);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setPreferredSize(new Dimension(300,50));
		statusPanel.setLayout( new BorderLayout(0,0));
		JPanel topP2 = new JPanel();
		
		JPanel bottomP2 = new JPanel();
		bottomP2.setPreferredSize(new Dimension(300,100));	
		
		JPanel player2 = new JPanel();
		player2.setPreferredSize(new Dimension(300,300));
		
		rightCol.setLayout(new BorderLayout(0,0));
		rightCol.add(topP2, BorderLayout.NORTH);
		
		topP2.setLayout(new BorderLayout(0,0));
		
		
		JPanel leftTopP2 = new JPanel();
		leftTopP2.setPreferredSize(new Dimension(100, 40));
		leftTopP2.setLayout(new BoxLayout(leftTopP2, BoxLayout.X_AXIS));
		JLabel status = new JLabel("TIMER:");
		status.setHorizontalAlignment(SwingConstants.CENTER);
		status.setFont(new Font("Avenir", Font.PLAIN, 12));
		topP2.add(leftTopP2,BorderLayout.WEST);
		topP2.add(status,BorderLayout.CENTER);
		
		JPanel rightTopP2 = new JPanel();
		rightTopP2.setBorder(new LineBorder(null, 1, true));
		rightTopP2.setBackground(SystemColor.control);
		rightTopP2.setPreferredSize(new Dimension(130, 40));
		topP2.add(rightTopP2,BorderLayout.EAST);
		
		JPanel gap2 = new JPanel();
		gap2.setPreferredSize(new Dimension(220,10));
		topP2.add(gap2, BorderLayout.SOUTH);
		
		rightTopP2.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel lblMinsec = new JLabel("MIN:SEC");
		lblMinsec.setHorizontalAlignment(SwingConstants.CENTER);
		rightTopP2.add(lblMinsec);
		
		rightCol.add(player2,BorderLayout.CENTER);
		rightCol.add(bottomP2, BorderLayout.SOUTH);
		
		//score
		player2.setLayout(new BorderLayout(0, 0));
		JPanel northPlayer2 = new JPanel();
		northPlayer2.setPreferredSize(new Dimension(300, 100));
		player2.add(northPlayer2, BorderLayout.NORTH);
		northPlayer2.setLayout(new BorderLayout(0, 0));
		JPanel scoreClient = new JPanel();
		scoreClient.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		JPanel scoreServer = new JPanel();
		scoreServer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		JPanel gap4 = new JPanel(); //vertical gap
		JPanel gap5 = new JPanel(); //horizontal gap
		JPanel scorePanel = new JPanel();
		scorePanel.setPreferredSize(new Dimension(250, 90));
		scoreClient.setPreferredSize(new Dimension(110,40));
		scoreServer.setPreferredSize(new Dimension(110,40));
		gap4.setPreferredSize(new Dimension(50,10));
		gap5.setPreferredSize(new Dimension(300,10));
		scorePanel.setLayout(new BorderLayout(0, 0));
		northPlayer2.add(gap4, BorderLayout.WEST);
		northPlayer2.add(scorePanel, BorderLayout.CENTER);
		northPlayer2.add(gap5, BorderLayout.SOUTH);
		scorePanel.add(scoreClient, BorderLayout.WEST);
		scorePanel.add(scoreServer, BorderLayout.EAST);
		//profile player
		JPanel profileClient = new JPanel();
		profileClient.setPreferredSize(new Dimension(30,40));
		profileClient.setBackground(Color.PINK);
		scoreClient.setLayout(new BorderLayout(0, 0));
		//scoreClient.add(profileClient, BorderLayout.WEST);
		
		JPanel client = new JPanel();
		scoreClient.add(client, BorderLayout.CENTER);
		client.setLayout(new BorderLayout(0, 0));
		
		JPanel nameClient = new JPanel();
		nameClient.setBackground(Color.GRAY);
		nameClient.setPreferredSize(new Dimension(60,30));
		client.add(nameClient, BorderLayout.NORTH);
		
		JLabel lblPlayer = new JLabel("PLAYER1");
		lblPlayer.setVerticalAlignment(SwingConstants.TOP);
		lblPlayer.setFont(new Font("Avenir", Font.PLAIN, 12));
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		nameClient.add(lblPlayer);
		
		JPanel clientScore = new JPanel();
		clientScore.setPreferredSize(new Dimension(30, 60));
		client.add(clientScore, BorderLayout.SOUTH);
		
		
		JPanel profileServer = new JPanel();
		profileServer.setPreferredSize(new Dimension(30,40));
		profileServer.setBackground(Color.PINK);
		scoreServer.setLayout(new BorderLayout(0, 0));
		scoreServer.add(profileServer, BorderLayout.EAST);
		
		JPanel server = new JPanel();
		scoreServer.add(server, BorderLayout.CENTER);
		server.setLayout(new BorderLayout(0, 0));
		
		JPanel nameServer = new JPanel();
		server.add(nameServer, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		server.add(panel_1, BorderLayout.SOUTH);
		
		JPanel vsPanel = new JPanel();
		scorePanel.add(vsPanel, BorderLayout.CENTER);
		
		JLabel lblVs = new JLabel("VS");
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVs.setFont(new Font("Avenir", Font.PLAIN, 13));
		vsPanel.add(lblVs);
		
		
	    //Image background = Toolkit.getDefaultToolkit().createImage("Background.png");
	    //profileClient.drawImage(background, 0, 0, null);
		
		
		JPanel gap3 = new JPanel();
		gap3.setPreferredSize(new Dimension(50, 250));
		JPanel southPlayer2 = new JPanel();
		southPlayer2.setBackground(new Color(204, 204, 255));
		southPlayer2.setPreferredSize(new Dimension(250, 250));
		player2.add(gap3,BorderLayout.WEST);
		player2.add(southPlayer2, BorderLayout.CENTER);
		southPlayer2.setLayout(new GridLayout(8, 8, 0, 0));
		for(int i =0; i<64; i++) {
			southPlayer2.add(new JButton("Button"));
		}
		
		/*RIGHT BORDER*/
		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(150,300));
		panel.add(east, BorderLayout.EAST);
		east.setLayout(new BorderLayout(0, 0));
		
		
		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(1024,50));
		bottom.setBackground(Color.BLACK);
		panel.add(bottom, BorderLayout.SOUTH);
	}

}
