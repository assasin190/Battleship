package GameState;
import Game.Main;

public interface GameState {
	
	public abstract void entered(); //TODO implements function called after entered the state
	public abstract void leaving(); //TODO implements function called right before leaving the state
	
}
