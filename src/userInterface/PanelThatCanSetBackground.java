package userInterface;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelThatCanSetBackground extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 687234844510329085L;
	public Image backgroundImg;
	public PanelThatCanSetBackground(Image background){
		backgroundImg = background;
	}
	public void setBackground(Image backgroundImage){
		this.backgroundImg = backgroundImage;
		this.repaint();
	}
	public void setBackground(String backgroundImage){
		ImageIcon bgIcon = ChangeBgUIState.createImageIcon(backgroundImage, 1024, 768);
		Image bgImg = bgIcon.getImage();
		this.backgroundImg = bgImg;
		this.repaint();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D)g.create()).drawImage(backgroundImg, 0, 0, 1024, 768, this);
	}
}
