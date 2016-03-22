package UserInterface;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;

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
		
		JButton button_2 = new JButton("Client");
		button_2.setAlignmentX(0.5f);
		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				popUpDialog = new JDialog();
				
			}
			
		});
		panel.add(button_2);
		
		JButton button_3 = new JButton("Server");
		button_3.setAlignmentX(0.5f);
		panel.add(button_3);
	}

}

class ModeSelectDialog extends JDialog implements ActionListener{
	private String inputText;
	private JTextField ipField;
	private JTextField portField;
	
	private ModeSelectDialog(JFrame parent, String title) {
		super(parent, title);
		initialize();
	}
	private void initialize() {
		//TODO implements mode selection dialog
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