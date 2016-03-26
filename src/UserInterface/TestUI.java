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
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.CardLayout;

public class TestUI{
	
	public JPanel panel;

	public TestUI() {
		initialize();
	}
	
	private void initialize() {
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(1024,768));
		
		ImageIcon icon = new ImageIcon("bg.png");
		panel.setLayout(null);
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 1024, 768);
		bg.setIcon(icon);
		panel.add(bg);
		
		

		
		
	}
}
