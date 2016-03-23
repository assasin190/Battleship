package Game;

import java.net.Socket;

public class GameClient {
		protected GameServer gameServer;
		protected Player player;
		protected BoardGame board;
		protected boolean isYourTurn;
		
		public static void main(String [] args) {
			
		}
	
		protected GameClient() {
			player = new Player();
			board = new BoardGame();
		}
		
}