package UserInterface;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
import Game.Main;

public abstract class UI {
	/* Abstract base class for all UI classes
	 * The option is left whether to implement GameState or not
	 * Every UI has a main reference and an instance of JPanel
	 * JPanel can be left as null for some implementations
	 */
	
	public Main main;
	public JPanel panel;
	
	public UI(Main main) {
		this.main = main;
	}
	
	//Create custom anonymous inner class of JPanel
	public static JPanel createJPanelWithBackground(Image backgroundImg) {
		return new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				((Graphics2D)g.create()).drawImage(backgroundImg, 0, 0, 1024, 768, this);
			}
		};
	}
	
}