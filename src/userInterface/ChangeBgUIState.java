package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
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
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ChangeBgUIState extends UI {
	public static final String[] IMAGES = new String[] { "bg/Bg-play.png", "bg/Bg-play2.png", "bg/Bg-play3.png",
			"bg/Bg-play4.png" };

	public ChangeBgUIState(Main main) {
		super(main);
		stateString = GameState.GameState.CHANGE_BG_STATE;
		dialog = new JDialog(main, "Change Background");
		dialog.setLocation(main.getLocation());
		dialog.setMinimumSize(new Dimension(500,500));
		dialog.setPreferredSize(new Dimension(500,500));
		// sirawich
		Point p = main.getLocation();
		
		Point popUpLocation = Main.getPopUpLocation(this);
		
		dialog.setLocation(popUpLocation);
		
		
		
		initialize();

	}
	
	private void initialize() {
		ImageIcon bgIcon = createImageIcon(IMAGES[1], 1024, 768);
		Image bgImg = bgIcon.getImage();
		//panel = UI.createJPanelWithBackground(bgImg);
		dialog.getContentPane().setLayout(new BorderLayout());
		
		JPanel title = new JPanel();
		title.setPreferredSize(new Dimension(500, 80));
		title.setLayout(new BorderLayout(0, 0));
		JLabel lblChangeBattleshipBackground = new JLabel("CHANGE BACKGROUND");
		lblChangeBattleshipBackground.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeBattleshipBackground.setVerticalAlignment(SwingConstants.BOTTOM);
		lblChangeBattleshipBackground.setFont(new Font("Arial", Font.BOLD, 16));
		title.add(lblChangeBattleshipBackground);
		dialog.getContentPane().add(title, BorderLayout.NORTH);

		JPanel bgPanel = new JPanel();
		bgPanel.setPreferredSize(new Dimension(500, 300));
		dialog.getContentPane().add(bgPanel, BorderLayout.SOUTH);

		JPanel gap = new JPanel();
		gap.setPreferredSize(new Dimension(500, 20));
		dialog.getContentPane().add(gap, BorderLayout.CENTER);

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
					main.background = Main.createImageIcon(IMAGES[index], 1024, 768).getImage();
					//Push this state and change MAIN_MENU_STATE to refresh the background
					main.GSM.popState();
					main.GSM.changeState(new MainMenuUIState(main));
					
					//main.currentStatePanel.setBackground(IMAGES[index]);
					//JPanel panel = UI.createJPanelWithBackground(bgImg);
					//MainMenuUI.bg = IMAGES[index];

					//MainMenuUI.this.revalidate();

					// MainMenuUI.this.repaint();
					//Main.repaint();
					
				}
			});
		}
		dialog.pack();
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
	public void entered() {
		System.out.println(Thread.currentThread().getName() + ": entered " + stateString);
		dialog.setVisible(true);
	}

	@Override
	public void leaving() {
		System.out.println(Thread.currentThread().getName() + ": leaving " + stateString);
		dialog.dispose();
		
	}

	@Override
	public void obscuring() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revealed() {
		// TODO Auto-generated method stub
		
	}

}
