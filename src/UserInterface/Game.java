package UserInterface;

import javax.swing.JPanel;
import java.awt.GridLayout;
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

public class Game{
	public JPanel panel;

	/**
	 * Create the panel.
	 */
	public Game() {
		initialize();
	}
	
	private void initialize() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		panel.setPreferredSize(new Dimension(1024,768));
		
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension(1024, 150));
		panel.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JPanel leftTop = new JPanel();
		JPanel rightTop = new JPanel();
		leftTop.setPreferredSize(new Dimension(150,100));
		rightTop.setPreferredSize(new Dimension(150,100));
		
		JLabel lblPlaceYourShip = new JLabel("Place your ship");
		lblPlaceYourShip.setFont(new Font("Avenir", Font.BOLD, 20));
		lblPlaceYourShip.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton logo = new JButton("");
		logo.setIcon ( new ImageIcon ( "logo.png" ) );
		top.add(leftTop,BorderLayout.WEST);
		top.add(logo, BorderLayout.CENTER);
		top.add(lblPlaceYourShip,BorderLayout.SOUTH);
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
		topP1.setPreferredSize(new Dimension(300, 50));
		
		JPanel bottomP1 = new JPanel();
		bottomP1.setPreferredSize(new Dimension(300,100));	
		
		leftCol.setLayout(new BorderLayout(0,0));
		leftCol.add(topP1,BorderLayout.NORTH);
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
		rightCol.setPreferredSize(new Dimension(300,568));
		center.add(rightCol, BorderLayout.EAST);
		
		JPanel topP2 = new JPanel();
		topP2.setPreferredSize(new Dimension(300, 50));
		
		JPanel bottomP2 = new JPanel();
		bottomP2.setPreferredSize(new Dimension(300,100));	
		
		JPanel player2 = new JPanel();
		player2.setPreferredSize(new Dimension(300,300));
		
		rightCol.setLayout(new BorderLayout(0,0));
		rightCol.add(topP2, BorderLayout.NORTH);
		rightCol.add(player2,BorderLayout.CENTER);
		rightCol.add(bottomP2, BorderLayout.SOUTH);
		
		player2.setLayout(new BorderLayout(0, 0));
		JPanel northPlayer2 = new JPanel();
		northPlayer2.setPreferredSize(new Dimension(300,130));
		player2.add(northPlayer2, BorderLayout.NORTH);
		northPlayer2.setLayout(new BorderLayout(0, 0));
		
		
		/*PLAYER PANEL*/
		JPanel profile = new JPanel();
		profile.setBackground(Color.GRAY);
		profile.setPreferredSize(new Dimension(100,100));
		northPlayer2.add(profile, BorderLayout.WEST);
		
		JPanel gapCol=new JPanel();
		gapCol.setPreferredSize(new Dimension(10,100));
		northPlayer2.add(gapCol, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(190,100));
		northPlayer2.add(buttonPanel, BorderLayout.EAST);
		buttonPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel namePanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) namePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		namePanel.setPreferredSize(new Dimension(190, 40));
		JLabel name = new JLabel("NAME: ");
		name.setFont(new Font("Avenir", Font.PLAIN, 13));
		name.setHorizontalAlignment(SwingConstants.LEFT);
		namePanel.add(name);
		buttonPanel.add(namePanel, BorderLayout.NORTH);
		
		JButton readyButton = new JButton("Ready");
		readyButton.setFont(new Font("Avenir", Font.PLAIN, 13));
		readyButton.setPreferredSize(new Dimension(190, 60));
		buttonPanel.add(readyButton, BorderLayout.CENTER);
		
		JButton randomButton = new JButton("Random Place");
		randomButton.setFont(new Font("Avenir", Font.PLAIN, 13));
		randomButton.setPreferredSize(new Dimension(190, 30));
		buttonPanel.add(randomButton, BorderLayout.SOUTH);
		
	
		
		
		/*SHIP PANEL*/
		
//		logo.setIcon ( new ImageIcon ( "logo.png" ) );
		
		JPanel shipPanel = new JPanel();
		shipPanel.setPreferredSize(new Dimension(300,150));
		player2.add(shipPanel, BorderLayout.SOUTH);
		shipPanel.setLayout(new GridLayout(4, 0, 0, 0));
		JButton ship1 = new JButton("");
		ship1.setIcon(new ImageIcon("ship1.gif"));
		JButton ship2 = new JButton("");
		ship2.setIcon(new ImageIcon("ship2.gif"));
		JButton ship3 = new JButton("");
		ship3.setIcon(new ImageIcon("ship3.gif"));
		JButton ship4 = new JButton("");
		ship4.setIcon(new ImageIcon("ship4.gif"));

		shipPanel.add(ship1);
		shipPanel.add(ship2);
		shipPanel.add(ship3);
		shipPanel.add(ship4);
		
		/*RIGHT BORDER*/
		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(150,300));
		panel.add(east, BorderLayout.EAST);
		east.setLayout(new BorderLayout(0, 0));
		
		
		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(1024,100));
		panel.add(bottom, BorderLayout.SOUTH);
	}

}
