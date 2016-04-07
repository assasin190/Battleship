package GameState;
import java.util.HashMap;

import javax.swing.JFrame;

import Game.Main;
import UserInterface.MainMenuUIState;

public class GameStateManager {
	
	public GameState currentState;
	public HashMap<String, GameState> bufferedStateMap;
	
	public static String MAIN_MENU_STATE = "MAIN_MENU_STATE";
	public static String CONNECT_TO_SERVER_P2P_STATE = "CONNECT_TO_SERVER_P2P_STATE";
	public static String GAME_SETUP_STATE = "GAME_SETUP_STATE";
	
	public GameStateManager() {
		currentState = null;
		bufferedStateMap = new HashMap<String, GameState>();
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
	
	public void storeBufferedState(String stateString, GameState stateObject) {
		bufferedStateMap.put(stateString, stateObject);
	}
	
	public GameState getBufferedState(String stateString) {
		return bufferedStateMap.get(stateString);
	}
}
