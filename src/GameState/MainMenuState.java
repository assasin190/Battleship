package GameState;

import javax.swing.JFrame;

import game.Main;
import userInterface.MainMenuUIState;

public class MainMenuState implements GameState{
	
	public MainMenuState(Main main) {
		
	}

	@Override
	public void entered() {
		/*
		System.out.println("Main_thread: MAIN_MENU_STATE");
		main.setBounds(100, 100, 1024, 768);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.replaceCurrentPanel(new MainMenuUIState());
		*/
		
	}

	@Override
	public void leaving() {
		// TODO Auto-generated method stub
		
	}

}
