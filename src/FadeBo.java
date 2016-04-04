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

public class FadeBo extends JPanel implements Runnable {

	Image imagem;
	Thread timer;
	private float alpha = 0f;
	private float alphaIn = 0f;
	private float alphaOut = 1.0f;
	boolean in = true;
	private String loading = "loading", name = "Magic Gold";
	private int count = 0;
	static Socket con = null;

	Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	public FadeBo() {

		this.setLayout(new GridLayout());
		imagem = new ImageIcon("bg.png").getImage();
		timer = new Thread(this);
		timer.start();

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
		main.add(new FadeBo());
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
		g2d.setColor(Color.red);
		if (count < 3) {
			loading += ".";
			count++;
		} else {
			loading = "loading";
			count = 0;
		}
		g2d.drawString(name, (int) d.getWidth() * 42 / 100,
				(int) d.getHeight() * 15 / 100);
		g2d.drawString(loading, (int) d.getWidth()*45/100, (int) d.getHeight() * 75 / 100);

	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * JFrame frame = new JFrame("Fade"); frame.add(new FadeBo());
	 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // frame.setSize(420,
	 * 330); // frame.pack(); frame.setUndecorated(true);
	 * frame.setLocationRelativeTo(null); frame.setVisible(true);
	 * insertBGM("intro_to_game.wav"); sleep(4000); //frame.dispose();
	 * checkingServer();
	 * 
	 * }
	 */

	/*
	 * public void actionPerformed(ActionEvent e) {
	 * 
	 * alpha += 0.05f; if (alpha >1) { alpha = 1; timer.stop(); } repaint();
	 * 
	 * 
	 * }
	 */

	public void run() {

		while (true) {
			try {
				timer.sleep(400);
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

	public static void progressBar(JDialog dlg) {

		JProgressBar dpb = new JProgressBar(0, 50);
		dlg.add(BorderLayout.CENTER, dpb);
		JLabel label = new JLabel("Connecting to Server...");
		Image img = null;
		try {
			img = ImageIO.read(new File("game/logo2.png"));
		} catch (IOException e) {
		}
		Image resizedImage = img.getScaledInstance(50, 50, 0);
		ImageIcon x = new ImageIcon(resizedImage);
		label.setIcon(x);
		dlg.add(BorderLayout.NORTH, label);
		dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dlg.setSize(400, 125);
		dlg.setLocationRelativeTo(null);
		dlg.setVisible(true);
		for (int i = 0; i <= 50; i++) {
			dpb.setValue(i);
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void checkingServer() {

		while (true) {
			JDialog dialog = new JDialog();
			progressBar(dialog);
			try {
				con = new Socket("128.199.235.83", 80);
				dialog.dispose();
				break;
			} catch (IOException e) {
				e.printStackTrace();
				dialog.dispose();
				int x = JOptionPane.showConfirmDialog(null,
						"Could not connect to Server.\n\tTry again?", "",
						JOptionPane.YES_NO_OPTION);
				if (x == JOptionPane.YES_NO_OPTION) {
					continue;
				} else {
					System.exit(0);
				}
			}
		}
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
