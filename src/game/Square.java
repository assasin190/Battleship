package game;

import java.io.Serializable;

import javax.swing.JLabel;
import userInterface.SquareLabel;

public class Square implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7539706428845824277L;
	protected int x;
	protected int y;
	protected boolean Marked;
	protected boolean occupied;
	protected Ship occupyingShip;
	//Non-serializable
	protected transient SquareLabel label;
	
	public Square(int y, int x) {
		this.y = y;
		this.x = x;
		Marked = false;
		occupied = false;
	}
	public void setOccupyingShip(Ship ship) {
		occupyingShip = ship;
		occupied = true;
	}
	
	public void setSquareLabel(SquareLabel squareLabel) {
		label = squareLabel;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
}
