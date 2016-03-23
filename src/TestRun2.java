import java.awt.Dimension;

import javax.swing.JFrame;
import UserInterface.GameUI;
import UserInterface.MainGame;

public class TestRun2 {

	public static void main(String[] args) {
		//Create a class object
		MainGame game = new MainGame();
		
		JFrame frame = new JFrame();
		
		//Add class panel
		frame.add(game.panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(1024,768));
		frame.setResizable(false);
	}

}
