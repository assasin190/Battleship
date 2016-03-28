import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import UserInterface.GameUI;
import UserInterface.MainGame;
import UserInterface.TestUI;

public class TestRunUI {

	public static void main(String[] args) {
		//Create a class object
		
		JFrame frame = new JFrame();
		frame.setResizable(true);
		
		//Add class panels
		ImageIcon bgIcon = createImageIcon("bg.png",1024, 768);
		Image img = bgIcon.getImage();
		ImageIcon img1 = createImageIcon("avatar.png",200,200);
		JLabel lb1 = new JLabel(img1);
		//lb1.setBounds(10,10, 200,200);
		
		
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawImage(img, 0, 0, 1024, 768, this);
				
			}
		};
		
		/*
		JPanel p1=new JPanel();
		JLabel l1 = new JLabel("hi help");
		p1.setLayout(new BorderLayout());
		p1.add(l1, BorderLayout.CENTER);
		p1.setPreferredSize(new Dimension(200,200));
		panel.add(p1,BorderLayout.WEST);
		*/
		JLabel l1 = new JLabel();
		l1.setText("Hi");
		frame.setPreferredSize(new Dimension(1024,768));
		panel.setLayout(new BorderLayout());
		panel.add(l1, BorderLayout.NORTH);
		panel.add(lb1, BorderLayout.CENTER);
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(1024,768));
		frame.setResizable(false);
		
		
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
