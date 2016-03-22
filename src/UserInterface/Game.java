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
		
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(200,300));
		panel.add(west, BorderLayout.WEST);
		
		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(200,300));
		panel.add(east, BorderLayout.EAST);
		
		JPanel center = new JPanel();
		panel.add(center, BorderLayout.CENTER);
	}

}
