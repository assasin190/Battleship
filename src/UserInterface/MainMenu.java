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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.InputVerifier;

public class MainMenu {
	public JPanel panel;
	private JDialog popUpDialog;

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
				popUpDialog = new ModeSelectDialog(Main.frame, "Select Mode");
			}
			
		});
		panel.add(clientBtn);
		
		JButton serverBtn = new JButton("Server");
		serverBtn.setAlignmentX(0.5f);
		panel.add(serverBtn);
	}

}

class ModeSelectDialog extends JDialog implements ActionListener{
	private JTextField inputText;
	private JTextField ipField;
	private JTextField portField;
	
	protected ModeSelectDialog(JFrame parent, String title) {
		super(parent, title);
		initialize();
	}
	private void initialize() {
		ipField = new JTextField();
		portField = new JTextField();
		inputText.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				String ipText = ((JTextField) input).getText();
				return false;
			}
		});
		portField.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				String portText = ((JTextField) input).getText();
				return false;
			}
		});
		getContentPane().add(ipField);
		getContentPane().add(portField);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
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