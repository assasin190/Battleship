package UserInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Game.Main;

public class ModeSelectDialog extends JDialog{
	public JTextField ipTextField;
	public JTextField portTextField;
	
	public ModeSelectDialog(JFrame parent, String title) {
		super(parent, title);
		setLocation(350,200); //262
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
		JPanel socketPanel = new JPanel();
		socketPanel.setPreferredSize(new Dimension(500,400));
		getContentPane().add(socketPanel);
		socketPanel.setLayout(new BorderLayout());
		
		//North
		JPanel north = new JPanel();
		north.setLayout(new BorderLayout());
		ipTextField = new JTextField();
		ipTextField.setColumns(1);
		JLabel ipTextLabel = new JLabel("IP Address:");
		JPanel gapNorth = new JPanel();
		gapNorth.setPreferredSize(new Dimension(250,140));
		north.add(gapNorth, BorderLayout.NORTH);
		JPanel ipLabel = new JPanel();
		ipLabel.setPreferredSize(new Dimension(200, 30));
		JPanel ipField = new JPanel();
		ipField.setPreferredSize(new Dimension(300, 30));
		ipLabel.setLayout(new BorderLayout());
		ipField.setLayout(new BorderLayout());
		ipLabel.add(ipTextLabel,BorderLayout.EAST);
		ipField.add(ipTextField,BorderLayout.CENTER);
		north.add(ipLabel,BorderLayout.WEST);
		north.add(ipField,BorderLayout.CENTER);
		JButton okBtn = new JButton("OK");
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModeSelectDialog.this.dispose();
				
				//Connect to a server
			}
			
		});
		north.add(okBtn, BorderLayout.EAST);
		
		
		
		//ipTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		//ipTextField.setHorizontalAlignment(SwingConstants.LEFT);
		
		//South
		JPanel south = new JPanel();
		south.setLayout(new BorderLayout());
		JPanel portLabel = new JPanel();
		JPanel portField = new JPanel();
		portTextField = new JTextField();
		portTextField.setColumns(10);
		JLabel portTextLabel = new JLabel("Port:");
		portLabel.setLayout(new BorderLayout());
		portLabel.add(portTextLabel,BorderLayout.EAST);
		portField.setLayout(new BorderLayout());
		portField.add(portTextField,BorderLayout.WEST);
		portLabel.setPreferredSize(new Dimension(200, 30));
		portField.setPreferredSize(new Dimension(300,30));
		south.add(portLabel,BorderLayout.WEST);
		south.add(portField,BorderLayout.CENTER);
		
		JPanel gapSouth = new JPanel();
		gapSouth.setPreferredSize(new Dimension(250,200));
		south.add(gapSouth, BorderLayout.SOUTH);
		gapSouth.add(okBtn);
		JButton cancelBtn = new JButton("Cancel");
		gapSouth.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModeSelectDialog.this.dispose();
			}
		});
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModeSelectDialog.this.dispose();
				//Connect to a server
			}
			
		});
		
		socketPanel.add(north,BorderLayout.NORTH);
		socketPanel.add(south,BorderLayout.SOUTH);
		
		
		pack();
		setVisible(true);
		
	}
}
