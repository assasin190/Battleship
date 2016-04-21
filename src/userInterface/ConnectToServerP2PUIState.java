package userInterface;

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

import GameState.GameState;
import GameState.GameStateManager;
import game.Main;

public class ConnectToServerP2PUIState extends UI {
	public JTextField ipTextField;
	public JTextField portTextField;
	
	public ConnectToServerP2PUIState(Main main) {
		super(main);
		stateString = GameState.CONNECT_TO_SERVER_P2P_STATE;
		dialog = new JDialog(main, "Enter IP Address");
		dialog.setLocation(Main.getPopUpLocation(this)); 
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
		dialog.getContentPane().setLayout(new BorderLayout());
		
		//Inside Panel
		JPanel socketPanel = new JPanel();
		socketPanel.setPreferredSize(new Dimension(300, 60));
		dialog.getContentPane().add(socketPanel);
		socketPanel.setLayout(new BorderLayout());
		
		//North
		JPanel north = new JPanel();
		north.setLayout(new BorderLayout());
		ipTextField = new JTextField("localhost");
		ipTextField.setColumns(1);
		JLabel ipTextLabel = new JLabel("IP Address:");
		JPanel gapNorth = new JPanel();
		gapNorth.setPreferredSize(new Dimension(0, 0));
		north.add(gapNorth, BorderLayout.NORTH);
		JPanel ipLabel = new JPanel();
		ipLabel.setPreferredSize(new Dimension(100, 30));
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
				//OK -> Connect to the server
				main.connect(ipTextField.getText(), portTextField.getText());
				/*	Push UI state -> WAIT_FOR_CONNECTION_STATE
				   	Connect() will push WAIT_FOR_CONNECTION_STATE
				 */
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
		portTextField = new JTextField("8080");
		portTextField.setColumns(10);
		JLabel portTextLabel = new JLabel("Port:");
		portLabel.setLayout(new BorderLayout());
		portLabel.add(portTextLabel,BorderLayout.EAST);
		portField.setLayout(new BorderLayout());
		portField.add(portTextField,BorderLayout.CENTER);
		portLabel.setPreferredSize(new Dimension(100, 30));
		portField.setPreferredSize(new Dimension(300,30));
		south.add(portLabel,BorderLayout.WEST);
		south.add(portField,BorderLayout.CENTER);
		JButton cancelBtn = new JButton("Cancel");
		portField.add(cancelBtn, BorderLayout.EAST);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Cancel -> Return to previous state
				main.GSM.popState();
				/*
				GameState bufferedMainState = main.GSM.getBufferedState(GameStateManager.MAIN_MENU_STATE);
				main.GSM.changeState(bufferedMainState);
				*/
				
			}
		});
		
		JPanel gapSouth = new JPanel();
		gapSouth.setPreferredSize(new Dimension(0, 0));
		south.add(gapSouth, BorderLayout.SOUTH);
		socketPanel.add(north,BorderLayout.NORTH);
		socketPanel.add(south,BorderLayout.SOUTH);
		
		dialog.pack();
		dialog.setVisible(true);
		
	}
	@Override
	public void entered() {
		System.out.println("Main_thread: entered " + stateString);
		dialog.setVisible(true);
		
	}
	@Override
	public void leaving() {
		System.out.println("Main_thread: leaving " + stateString);
		dialog.dispose();
		main.setEnabled(true);
	}
	@Override
	public void obscuring() {
		System.out.println("Main_thread: " + stateString + " stacked");
		//Set the dialog's visibility to false
		dialog.setVisible(false);
	}
	@Override
	public void revealed() {
		System.out.println("Main_thread: " + stateString + " revealed");
		dialog.setVisible(true);
	}
}
