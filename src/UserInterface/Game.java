package UserInterface;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.BoxLayout;
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
		FlowLayout flowLayout = (FlowLayout) top.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		top.setPreferredSize(new Dimension(1024,100));
		panel.add(top, BorderLayout.NORTH);
		
		JLabel lblPlaceYourShip = new JLabel("Place your ship");
		lblPlaceYourShip.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblPlaceYourShip.setHorizontalAlignment(SwingConstants.LEFT);
		top.add(lblPlaceYourShip);
		
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
		JPanel westCenter = new JPanel();
		center.add(westCenter, BorderLayout.WEST);
		JPanel player1 = new JPanel();
		player1.setBackground(Color.PINK);
		player1.setPreferredSize(new Dimension(300,300));
		player1.setLayout(new BorderLayout(0,0));
		westCenter.add(player1, BorderLayout.CENTER);
		GridLayout tableLayout = new GridLayout(8,8);
		player1.setLayout(tableLayout);
		for(int i =0; i<64; i++) {
			player1.add(new JButton("Button"));
		}
		
		
		/*CENTER GAP*/
		JPanel centerCol = new JPanel();
		centerCol.setPreferredSize(new Dimension(124,300));
		center.add(centerCol, BorderLayout.CENTER);
		JPanel gapColCenter = new JPanel();
		gapColCenter.setBackground(Color.BLACK);
		center.add(gapColCenter, BorderLayout.CENTER);
		
		/*PLAYER2 TABLE*/
		JPanel eastCenter = new JPanel();
		center.add(eastCenter, BorderLayout.EAST);
		JPanel player2 = new JPanel();
		player2.setPreferredSize(new Dimension(300,300));
		eastCenter.add(player2,BorderLayout.CENTER);
		player2.setLayout(new BorderLayout(0, 0));
		
		JPanel northPlayer2 = new JPanel();
		northPlayer2.setPreferredSize(new Dimension(250,125));
		eastCenter.add(northPlayer2, BorderLayout.NORTH);
		northPlayer2.setLayout(new BorderLayout(0, 0));
		
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
		
		JButton readyButton = new JButton("Ready");
		readyButton.setPreferredSize(new Dimension(190,50));
		buttonPanel.add(readyButton, BorderLayout.NORTH);
		
		JButton randomButton = new JButton("Random Place");
		randomButton.setPreferredSize(new Dimension(190,50));
		buttonPanel.add(randomButton, BorderLayout.CENTER);
		
		/*
		JPanel shipPanel = new JPanel();
		shipPanel.setPreferredSize(new Dimension(100,200));
		player2.add(shipPanel, BorderLayout.CENTER);
		shipPanel.setLayout(new GridLayout(4, 0, 0, 0));
		JPanel ship1 = new JPanel();
		ship1.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel ship2 = new JPanel();
		ship2.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel ship3 = new JPanel();
		ship3.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel ship4 = new JPanel();
		ship4.setBorder(new LineBorder(new Color(0, 0, 0)));
		shipPanel.add(ship1);
		shipPanel.add(ship2);
		shipPanel.add(ship3);
		shipPanel.add(ship4);
		*/
		
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
