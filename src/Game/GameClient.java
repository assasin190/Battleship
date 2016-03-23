package Game;

import java.net.Socket;
import UserInterface.MainGame;

public class GameClient {
		protected GameServer gameServer;
		protected Player player;
		protected BoardGame board;
		protected boolean isYourTurn;
		protected MainGame gameUI;
		
		public static void main(String [] args) {
			
		}
	
		protected GameClient() {
			player = new Player();
			board = new BoardGame();
		}
		
}