package GameState;
import javax.swing.JFrame;

import Game.Main;
import UserInterface.MainMenuUI;

public class GameStateManager {
	GameState currentState;
	
	public static final String MAIN_MENU_STATE = "MAIN_MENU_STATE";
	public static final String GAME_SETUP_STATE = "GAME_SETUP_STATE";
	public static final String MAIN_GAME_STATE = "MAIN_GAME_STATE";
	
	public GameStateManager() {
		
	}
	
	public GameState getcurrentGameState() {
		return currentState;
	}
	
	public void changeState(GameState nextState) {
		//Leave current state
		currentState.leaving();
		//Set new state
		currentState = nextState;
		//Enter new state
		currentState.entered();
		
		
		/* Switch string model
		switch(nextState) {
			case GameStateManager.MAIN_GAME_STATE:
				main.setBounds(100, 100, 1024, 768);
				main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				main.setCurrentPanel(new MainMenuUI(main));
				
			case GameStateManager.GAME_SETUP_STATE:
		*/
				
	}
	
	//Used only the first time (When currentState is null)
	public void setState(GameState state) {
		//Set new state
		currentState = state;
		//Enter new state
		currentState.entered();
	}

}
