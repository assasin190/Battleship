package GameState;
import Game.Main;

public interface GameState {
	
	public void entered();   //TODO implements function called after entered the state
	public void leaving();   //TODO implements function called right before leaving the state
	public void obscuring(); //TODO implements function called right before the state is stacked
	public void revealed();  //TODO implements function called after the state is revealed
	
}
