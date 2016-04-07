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
import GameState.GameState;
import GameState.GameStateManager;

public class ConnectToServerP2PUIState extends UI implements GameState {
	public JDialog dialog;
	public JTextField ipTextField;
	public JTextField portTextField;
	
	public ConnectToServerP2PUIState(Main main) {
		super(main);
		dialog = new JDialog(main, "Enter IP Address");
		dialog.setLocation(350,200); //262
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
		socketPanel.setPreferredSize(new Dimension(500,400));
		dialog.getContentPane().add(socketPanel);
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
				//OK -> Connect to the server
				main.Connect(ipTextField.getText(), portTextField.getText());
				//Change UI state -> WAIT_FOR_CONNECTION_STATE
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
				//Cancel -> Return to buffered state -> MAIN_MENU_STATE
				GameState bufferedMainState = main.GSM.getBufferedState(GameStateManager.MAIN_MENU_STATE);
				main.GSM.changeState(bufferedMainState);
				
			}
		});
		
		socketPanel.add(north,BorderLayout.NORTH);
		socketPanel.add(south,BorderLayout.SOUTH);
		
		
		dialog.pack();
		dialog.setVisible(true);
		
	}
	@Override
	public void entered() {
		System.out.println("Main_thread: entered CONNECT_TO_SERVER_P2P_STATE");
		main.setEnabled(false);
		ConnectToServerP2PUIState.this.dialog.setVisible(true);
		
	}
	@Override
	public void leaving() {
		System.out.println("Main_thread: leaving CONNECT_TO_SERVER_P2P_STATE");
		ConnectToServerP2PUIState.this.dialog.dispose();
		main.setEnabled(true);
	}
}
