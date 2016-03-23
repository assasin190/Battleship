package Game;

import Game.Square;

public class BoardGame {
	protected Square [][] board;
	protected Ship [] ships;
	
	public BoardGame() {
		this.board = new Square[8][8];
	}
	// TODO implements a boardgame's actions
}
