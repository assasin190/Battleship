package GameState;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.JFrame;
import Game.Main;
import UserInterface.MainMenuUIState;

public class GameStateManager {
	
	//GameStateManager is accessible from all UI via instance main.GSM
	private Stack<GameState> stackedGameState;
	public HashMap<String, GameState> bufferedStateMap;
	
	public static String MAIN_MENU_STATE = "MAIN_MENU_STATE";
	public static String CONNECT_TO_SERVER_P2P_STATE = "CONNECT_TO_SERVER_P2P_STATE";
	public static String GAME_SETUP_STATE = "GAME_SETUP_STATE";
	
	public GameStateManager() {
		stackedGameState = new Stack<GameState>();
		//bufferedStateMap = new HashMap<String, GameState>();
	}
	
	public GameState getCurrentState() {
		return stackedGameState.peek();
	}
	
	public void popState() {
		//Leave current state
		stackedGameState.pop().leaving();
		//Reveal the state on the top of the stack
		stackedGameState.peek().revealed();
		//Note: the method does not return an instance of a GameState
	}
	
	public void changeState(GameState nextState) {
		//Leave current state
		stackedGameState.pop().leaving();
		//Set new state
		stackedGameState.push(nextState);
		//Enter new state
		nextState.entered();
		
		
		/* Switch string model
		switch(nextState) {
			case GameStateManager.MAIN_GAME_STATE:
				main.setBounds(100, 100, 1024, 768);
				main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				main.setCurrentPanel(new MainMenuUI(main));
				
			case GameStateManager.GAME_SETUP_STATE:
		*/
				
	}
	
	public void pushState(GameState stackingState) {
		//Call obscuring current state before leaveing
		stackedGameState.peek().obscuring();
		//Push new stacking state into the stack
		stackedGameState.push(stackingState);
		//Enter new state
		stackingState.entered();
	}
	
	//Used only the first time (When stackedCurrentState is empty)
	public void setState(GameState initialState) {
		//Set new state
		stackedGameState.push(initialState);
		//Enter new state
		initialState.entered();
	}
	
	public void storeBufferedState(String stateString, GameState stateObject) {
		bufferedStateMap.put(stateString, stateObject);
	}
	
	public GameState getBufferedState(String stateString) {
		return bufferedStateMap.get(stateString);
	}
}
