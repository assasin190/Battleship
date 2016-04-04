package GameState;
import Game.Main;

public abstract class GameState {
	
	public Main main;
	
	public GameState(Main main){
		this.main = main;
	}
	
	public abstract void entered(); //TODO implements function called after entered the state
	public abstract void leaving(); //TODO implements function called right before leaving the state
	
}
