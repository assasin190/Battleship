package GameState;

import Game.Main;
import UserInterface.GameSetupUI;

public class GameSetupState extends GameState{

	public GameSetupState(Main main) {
		super(main);
	}

	@Override
	public void entered() {
		main.replaceCurrentPanel(new GameSetupUI());
		
	}

	@Override
	public void leaving() {
		// TODO Auto-generated method stub
		
	}

}
