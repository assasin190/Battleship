package game;

import game.Square;
import userInterface.GameSetupUIState.SquareLabel;

public class Ship {
	public Square [] occupation;
	public boolean isSunk;
	public int shipNumber;
	
	public Ship(int shipNumber) {
		occupation = new Square[4];
		this.shipNumber = shipNumber;
	}
	
	public void setOccupation(Square[] occupation) {
		this.occupation = occupation;
	}
	
	
}
