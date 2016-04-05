package UserInterface;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import Game.Main;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;

public class MainMenuUI extends JPanel {
	Main main;
	public ModeSelectDialog popUpDialog;

	public MainMenuUI(Main main) {
		initialize(main);
	}
	
	private void initialize(Main main) {
		this.main = main;
		//panel = new JPanel();
		setBackground(new Color(0, 0, 0));
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
		gapNorth.setPreferredSize(new Dimension(1024, 150));
		add(gapNorth,BorderLayout.NORTH);
		gapNorth.setOpaque(false);

		JPanel gapSouth = new JPanel();
		gapSouth.setPreferredSize(new Dimension(1024, 318));
		add(gapSouth,BorderLayout.SOUTH);
		gapSouth.setOpaque(false);
		
		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);
		center.setPreferredSize(new Dimension(200, 300));
		center.setLayout(new BorderLayout(0, 0));
		center.setOpaque(false);
		
		JPanel modePanel = new JPanel();
		modePanel.setPreferredSize(new Dimension(200, 80));
		modePanel.setLayout(new BorderLayout(0, 0));
		modePanel.setOpaque(false);
		
		JLabel label = new JLabel("Mode");
		label.setFont(new Font("Avenir", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		modePanel.add(label);
		add(modePanel, BorderLayout.NORTH);
		//modePanel.setOpaque(false);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(200,200));
		add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setOpaque(false);
		
		buttonPanel.setLayout(new BorderLayout(0, 0));
		JButton clientBtn = new JButton("Client");
		clientBtn.setFont(new Font("Avenir", Font.PLAIN, 16));
		add(clientBtn, BorderLayout.NORTH);
		clientBtn.setPreferredSize(new Dimension(200, 80));
	
		JButton serverBtn = new JButton("Server");
		serverBtn.setFont(new Font("Avenir", Font.PLAIN, 16));
		add(serverBtn,BorderLayout.SOUTH);
		serverBtn.setPreferredSize(new Dimension(200, 80));

	
		clientBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame theFrame = (JFrame) SwingUtilities.windowForComponent(MainMenuUI.this);
				popUpDialog = new ModeSelectDialog((JFrame) SwingUtilities.windowForComponent(MainMenuUI.this), "Select Mode");
				
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
		ImageIcon bgIcon = createImageIcon("bg.png",1024, 768);
		Image img = bgIcon.getImage();
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(img, 0, 0, 1024, 768, this);
	}
}