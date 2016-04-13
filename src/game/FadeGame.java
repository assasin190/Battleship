package game;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class FadeGame extends JPanel implements Runnable {

	Image imagem;
	Thread timer;
	private float alpha = 0f;
	private float alphaIn = 0f;
	private float alphaOut = 1.0f;
	boolean in = true;
	private String loading = "loading", name = "Magic Gold";
	private int count = 0;
	static Socket con = null;
	boolean start;

	Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	public FadeGame() {

		this.setLayout(new GridLayout());
		imagem = new ImageIcon("bg.png").getImage();
		timer = new Thread(this);
		timer.start();
		insertBGM("fade.wav");
		start = false;

	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// here you define alpha 0f to 1f

	
	public static void main(String[] args) {
		JFrame main = new JFrame();
		main.setSize(1024, 768);
		main.add(new FadeGame());
		main.setVisible(true);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		if (in) {
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, alphaIn));
		} else {
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, alphaOut));
		}
		g2d.drawImage(imagem, 0, 0, (int) d.getWidth(), (int) d.getHeight(),
				null);
		g2d.setFont(new Font("Arial", Font.BOLD, 30));
		g2d.setColor(Color.WHITE);
		if (count < 3) {
			loading += ".";
			count++;
		} else {
			loading = "loading";
			count = 0;
		}
		g2d.drawString(name, (int) d.getWidth() * 35 / 100,
				(int) d.getHeight() * 30 / 100);
		g2d.drawString(loading, (int) d.getWidth()*38/100, (int) d.getHeight() * 50 / 100);

	}


	public void run() {

		while (true) {
			try {
				timer.sleep(200);
				repaint();
				if (in) {
					alphaIn += 0.05f;
					if (alphaIn > 1) {
						in = false;
					}
				} else {
					alphaOut -= 0.05f;
					if (alphaOut < 0) {
						alphaOut = 0;
						break;
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static ImageIcon reachingServer(ImageIcon logo) {

		ImageIcon icon = logo;
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(img.getWidth(null),
				img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		ImageIcon newIcon = new ImageIcon(bi);
		return newIcon;

	}

	
	

	public void insertBGM(String sound) {
		File soundFile = new File(sound);
		AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();

			/*
			 * if(start){ clip.loop(Clip.LOOP_CONTINUOUSLY); } else {
			 * clip.start(); }
			 */
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
