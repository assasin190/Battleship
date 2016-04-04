package GameState;
import javax.swing.JFrame;

import Game.Main;
import UserInterface.MainMenuUI;

public class GameStateManager {
	Main main;
	GameState currentState;
	
	public static final String MAIN_MENU_STATE = "MAIN_MENU_STATE";
	public static final String MAIN_GAME_STATE = "MAIN_GAME_STATE";
	
	
	public GameStateManager(Main main) {
		this.main = main;
	}
	
	public GameState getcurrentGameState() {
		return currentState;
	}
	
	public void changeState(String nextState) {
		switch(nextState) {
			case GameStateManager.MAIN_GAME_STATE:
				main.setBounds(100, 100, 1024, 768);
				main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				main.setCurrentPanel(new MainMenuUI(main));
				
		}
		
	}

}
