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
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.CardLayout;

public class TestUI extends JPanel{
	
	private JTextField textField;
	boolean start = true;
	private JPanel top;
	private JPanel leftGap;
	private JPanel rightGap;
	ImageIcon img;
	JLabel screen;
	JFrame frame;
	
	public TestUI(JFrame frame) {
		this.frame = frame;
		insertBGM("login.wav");
		start = false;
		setVisible(false);
		initialize();
	}
	
	public static void main(String [] args) {
		JFrame frame = new JFrame();
		TestUI game = new TestUI(frame);
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(1024,768));
		frame.setResizable(false);
	}
	
	private void initialize() {
		
		setPreferredSize(new Dimension(1024,768));
		setBounds(0,0, 1024,768);
		setLayout(new BorderLayout());
		
		
		img = null;
		img = createImageIcon("bg.png",1024, 768);
		JPanel top = new JPanel();
		screen = new JLabel(img);
	//	screen.setBounds(0,0, 1024,768);
	//	screen.setHorizontalAlignment(JLabel.CENTER);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(screen, BorderLayout.CENTER);
		
		ImageIcon img1 = createImageIcon("avatar.png",200,200);
		JLabel lb1 = new JLabel(img1);
		lb1.setBounds(10,10, 1024,768);
		top.add(lb1);
		add(top, BorderLayout.PAGE_START);
		
		

	}
	/*
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
	}
	*/

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

