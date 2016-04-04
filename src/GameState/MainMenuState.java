package GameState;

import javax.swing.JFrame;

import Game.Main;
import UserInterface.MainMenuUI;

public class MainMenuState extends GameState{

	public MainMenuState(Main main) {
		super(main);
	}

	@Override
	public void entered() {
		main.setBounds(100, 100, 1024, 768);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.replaceCurrentPanel(new MainMenuUI(main));
		
	}

	@Override
	public void leaving() {
		// TODO Auto-generated method stub
		
	}

}
