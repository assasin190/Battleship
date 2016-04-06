package GameState;

import javax.swing.JPanel;

import Game.Main;
import UserInterface.GameSetupUI;

public class GameSetupState implements GameState{
	GameSetupUI gameSetupUI;
	
	@Override
	public void entered() {
		gameSetupUI = new GameSetupUI();
		GameStateManager.getMain().replaceCurrentPanel(new GameSetupUI());
		
	}

	@Override
	public void leaving() {
		// TODO Auto-generated method stub
		
	}

}
