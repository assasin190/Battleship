package GameState;
import Game.Main;

public class GameStateManager {
	Main main;
	GameState currentState;
	
	public static final String MAIN_MENU_STATE = "MAIN_MENU_STATE";
	public static final String MAIN_GAME_STATE = "MAIN_GAME_STATE";
	
	
	public GameState getcurrentGameState() {
		return currentState;
	}
	
	public void changeState(String nextState) {
		switch(nextState) {
			case GameStateManager.MAIN_GAME_STATE:
				
		}
		
	}

}
