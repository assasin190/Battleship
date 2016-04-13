package UserInterface;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JPanel;
import Game.Main;
import GameState.GameState;

public abstract class UI implements GameState{
	/* Abstract base class for all UI classes
	 * Every UI has a GameState
	 * Every UI has a main reference and an instance of JPanel
	 * JPanel can be left as null for some implementations
	 * JDialog can be left as null for some implementations
	 * Usually, a UI either contains a JPanel or a JDialog
	 */
	
	public Main main;
	public JPanel panel;
	public JDialog dialog;
	public String stateString;
	
	//For test purpose
	public UI() {}
	
	public UI(Main main) {
		this.main = main;
	}
	
	public String getStateString() {
		return stateString;
	}
	
	//Use this method to create a JPanel with custom background paint
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