package game;

import javax.swing.JLabel;

import userInterface.GameSetupUIState.SquareLabel;

public class Square {
	
	protected int x;
	protected int y;
	protected boolean isMarked;
	protected boolean isOccupied;
	protected SquareLabel label;
	//A square can only have one ship
	protected Ship occupyingShip;
	
	public Square(int y, int x) {
		this.y = y;
		this.x = x;
		isMarked = false;
		isOccupied = false;
	}
	public void setOccupyingShip(Ship ship) {
		occupyingShip = ship;
		isOccupied = true;
	}
	
	public void setSquareLabel(SquareLabel squareLabel) {
		label = squareLabel;
	}
	
}
