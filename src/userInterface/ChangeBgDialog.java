package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import game.Main;
import userInterface.GameSetupUIState;
import userInterface.GameUIState;
import userInterface.TestUI;

import java.awt.Choice;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ChangeBgDialog extends JPanel {
	public static final String[] IMAGES = new String[] { "Bg-play.png", "Bg-play2.png", "Bg-play3.png",
			"Bg-play4.png" };

	public static void main(String[] args) {
		// Create a class object
		JFrame frame = new JFrame();
		frame.getContentPane().add(new ChangeBgDialog());
		frame.setPreferredSize(new Dimension(500, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);

	}

	public ChangeBgDialog() {

		setLayout(new BorderLayout());

		JPanel title = new JPanel();
		title.setPreferredSize(new Dimension(500, 80));
		add(title, BorderLayout.NORTH);
		title.setLayout(new BorderLayout(0, 0));

		JLabel lblChangeBattleshipBackground = new JLabel("CHANGE BACKGROUND");
		lblChangeBattleshipBackground.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeBattleshipBackground.setVerticalAlignment(SwingConstants.BOTTOM);
		lblChangeBattleshipBackground.setFont(new Font("Arial", Font.BOLD, 16));
		title.add(lblChangeBattleshipBackground);

		JPanel bgPanel = new JPanel();
		bgPanel.setPreferredSize(new Dimension(500, 300));
		add(bgPanel, BorderLayout.SOUTH);

		JPanel gap = new JPanel();
		gap.setPreferredSize(new Dimension(500, 20));
		add(gap, BorderLayout.CENTER);

		JButton bgButton[] = new JButton[4];

		for (int i = 0; i < 4; i++) {
			bgButton[i] = new JButton(createImageIcon(IMAGES[i], 200, 120));
			bgButton[i].setName(i + "");
			bgButton[i].setPreferredSize(new Dimension(200, 120));
			bgPanel.add(bgButton[i]);
			bgButton[i].addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					String name = ((JButton) e.getComponent()).getName();
					int index = Integer.parseInt(name);

					
					//MainMenuUI.bg = IMAGES[index];
					
					System.out.println("print in ChangeBgDialog");
					MainMenuUIState.changeBg(IMAGES[index]);
					
					
					//MainMenuUI.bg = IMAGES[index];

					//MainMenuUI.this.revalidate();

					// MainMenuUI.this.repaint();
					//Main.repaint();
					
				}
			});
		}

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

}
