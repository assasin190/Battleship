package UserInterface;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Game.Main;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

public class MainMenuUI {
	Main main;
	public JPanel panel;
	private ModeSelectDialog popUpDialog;

	public MainMenuUI(Main main) {
		initialize(main);
	}
	
	private void initialize(Main main) {
		this.main = main;
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		
		panel.setLayout(new BorderLayout(0, 0));
		panel.setPreferredSize(new Dimension(1024,768));
		
		/*
		ImageIcon icon = new ImageIcon("bg.png"); 
		JLabel bg = new JLabel();
		bg.setIcon(icon);*/
		
		
		JPanel gapLeft = new JPanel();
		gapLeft.setPreferredSize(new Dimension(412,300));
		panel.add(gapLeft, BorderLayout.WEST);
		gapLeft.setOpaque(false);
		
		JPanel gapRight = new JPanel();
		gapRight.setPreferredSize(new Dimension(412,300));
		panel.add( gapRight, BorderLayout.EAST);
		gapRight.setOpaque(false);
		
		JPanel gapNorth = new JPanel();
		gapNorth.setPreferredSize(new Dimension(1024, 150));
		panel.add(gapNorth,BorderLayout.NORTH);
		gapNorth.setOpaque(false);

		JPanel gapSouth = new JPanel();
		gapSouth.setPreferredSize(new Dimension(1024, 318));
		panel.add(gapSouth,BorderLayout.SOUTH);
		gapSouth.setOpaque(false);
		
		JPanel center = new JPanel();
		panel.add(center, BorderLayout.CENTER);
		center.setPreferredSize(new Dimension(200, 300));
		center.setLayout(new BorderLayout(0, 0));
		center.setOpaque(false);
		
		JPanel modePanel = new JPanel();
		modePanel.setPreferredSize(new Dimension(200, 80));
		modePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("Mode");
		label.setFont(new Font("Avenir", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		modePanel.add(label);
		center.add(modePanel, BorderLayout.NORTH);
		//modePanel.setOpaque(false);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(200,200));
		center.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setOpaque(false);
		
		buttonPanel.setLayout(new BorderLayout(0, 0));
		JButton clientBtn = new JButton("Client");
		clientBtn.setFont(new Font("Avenir", Font.PLAIN, 16));
		buttonPanel.add(clientBtn, BorderLayout.NORTH);
		clientBtn.setPreferredSize(new Dimension(200, 80));
	
		JButton serverBtn = new JButton("Server");
		serverBtn.setFont(new Font("Avenir", Font.PLAIN, 16));
		buttonPanel.add(serverBtn,BorderLayout.SOUTH);
		serverBtn.setPreferredSize(new Dimension(200, 80));

	
		clientBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame theFrame = (JFrame) SwingUtilities.windowForComponent(panel);
				popUpDialog = new ModeSelectDialog((JFrame) SwingUtilities.windowForComponent(panel), "Select Mode");
				
			}
		});
	}
}

class ModeSelectDialog extends JDialog{
	private JTextField ipTextField;
	private JTextField portTextField;
	
	protected ModeSelectDialog(JFrame parent, String title) {
		super(parent, title);
		initialize();
	}
	private void initialize() {
		ipTextField = new JTextField();
		portTextField = new JTextField();
		ipTextField.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				String ipText = ((JTextField) input).getText();
				return false;
			}
		});
		portTextField.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				String portText = ((JTextField) input).getText();
				return false;
			}
		});
		getContentPane().setLayout(new BorderLayout());
		//Inside Panel
		//North
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		ipTextField = new JTextField();
		JLabel ipTextLabel = new JLabel("IP Address:");
		north.add(ipTextLabel);
		north.add(ipTextField);
		
		//South
		JPanel south = new JPanel();
		south.setLayout(new FlowLayout());
		portTextField = new JTextField();
		JLabel portTextLabel = new JLabel("Port:");
		south.add(portTextLabel);
		south.add(portTextField);
		
		getContentPane().add(north, BorderLayout.PAGE_START);
		getContentPane().add(south, BorderLayout.PAGE_END);
		pack();
		setVisible(true);
		
	}
}
