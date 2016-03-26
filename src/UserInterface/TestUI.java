package UserInterface;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.CardLayout;

public class TestUI extends JPanel{
	
	public JPanel panel;
	private JTextField textField;
	Image image;
	ImageIcon bg;
	JLabel screen;
	boolean start = true;
	
	public TestUI() {
		insertBGM("login.wav");
		start = false;
		setVisible(true);
		initialize();
	}
	
	private void initialize() {
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(1024,768));
		//panel.setBackground(Color.BLACK);
		bg = createImageIcon("bg.png",
				1024, 768);
		panel.setLayout(new BorderLayout(0, 0));
		screen = new JLabel (bg);
		panel.add(screen);
		
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(1024,200));
		p1.setBackground(new Color(0, 0, 0));
		p1.setOpaque(false);
		panel.add(p1, BorderLayout.NORTH);
	
	}

	public ImageIcon createImageIcon(String path, int width, int height) {
		Image img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
		}
		Image resizedImage = img.getScaledInstance(width, height, 0);
		return new ImageIcon(resizedImage);
	}
	public void insertBGM(String sound) {
		File soundFile = new File(sound);
		AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			if(start){
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}

