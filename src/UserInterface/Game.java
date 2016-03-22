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
		
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension(1000,30));
		panel.add(top, BorderLayout.NORTH);
		
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(200,300));
		panel.add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(0, 0));
		
		JPanel bottom1 = new JPanel();
		bottom1.setPreferredSize(new Dimension(200,50));
		west.add(bottom1, BorderLayout.SOUTH);
		
		JPanel player1 = new JPanel();
		player1.setBackground(Color.PINK);
		player1.setPreferredSize(new Dimension(200,200));
		west.add(player1, BorderLayout.CENTER);
		
		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(200,300));
		panel.add(east, BorderLayout.EAST);
		east.setLayout(new BorderLayout(0, 0));
		
		JPanel player2 = new JPanel();
		player2.setBackground(Color.LIGHT_GRAY);
		player2.setPreferredSize(new Dimension(200,200));
		east.add(player2,BorderLayout.CENTER);
		
		JPanel bottom2 = new JPanel();
		bottom2.setPreferredSize(new Dimension(200,50));
		east.add(bottom2,BorderLayout.SOUTH);
		
		
		JPanel center = new JPanel();
		panel.add(center, BorderLayout.CENTER);
	}

}
