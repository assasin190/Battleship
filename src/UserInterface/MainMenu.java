package UserInterface;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.InputVerifier;

public class MainMenu {
	public JPanel panel;
	private ModeSelectDialog popUpDialog;

	/**
	 * Create the panel.
	 */
	public MainMenu() {
		initialize();
	}
	
	private void initialize() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Mode");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setAlignmentX(0.5f);
		panel.add(label);
		JButton clientBtn = new JButton("Client");
		clientBtn.setAlignmentX(0.5f);
		clientBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame theFrame = (JFrame) SwingUtilities.windowForComponent(panel);
				popUpDialog = new ModeSelectDialog((JFrame) SwingUtilities.windowForComponent(panel), "Select Mode");
				
			}
		});
		panel.add(clientBtn);
		JButton serverBtn = new JButton("Server");
		serverBtn.setAlignmentX(0.5f);
		panel.add(serverBtn);
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

class IPTextField extends JTextField {
	String inputText;
	
	private IPTextField() {
		super();
		
	}
	@Override
	public void validate() {
		
	}
}