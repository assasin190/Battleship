package GameState;
import javax.swing.JFrame;

import Game.Main;
import GameState.GameStateManager;
import UserInterface.MainMenuUI;

public class MainGameState extends GameState {
	
	public MainGameState(Main main) {
		super(main);
	}

	@Override
	public void entered() {
		main.setBounds(100, 100, 1024, 768);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setCurrentPanel(new MainMenuUI(main));
	}

	@Override
	public void leaving() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return GameStateManager.MAIN_GAME_STATE;
				
	}

}
