package Game;

import java.io.Serializable;
import java.net.Socket;
import UserInterface.MainGameUI;

public class GameClient implements Runnable, Serializable{
		//Local game client field
		protected transient GameServer gameServer;
		//Non-serializable field
		protected transient MainGameUI gameUI;
		//Global serializable field
		protected Player player;
		protected BoardGame board;
		protected boolean isYourTurn;
		protected boolean isWithLocalServer;
		
		public static void main(String [] args) {
			
		}
		
		protected GameClient() {
			player = new Player();
			board = new BoardGame();
		}
		
		protected GameClient(GameServer gameServer) {
			this.gameServer = gameServer;
			player = new Player();
			board = new BoardGame();
			isWithLocalServer = true;
		}

		@Override
		public void run() {
			//Case local client
			if(isWithLocalServer) {
				
			}
			else {
				
			}
		}
		
		public void gameSetup() {
			
		}
		
}