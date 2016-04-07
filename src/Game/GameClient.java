package Game;

import java.net.Socket;

import UserInterface.GameSetupUIState;
import UserInterface.MainGameUI;

public class GameClient implements Runnable {
		//P2P case field
		protected transient GameServer gameServer;
		//Local, Non-serializable field
		protected Socket socket;
		protected transient GameSetupUIState setupGameUI;
		//Non-serializable field
		protected transient MainGameUI gameUI;
		//Global serializable field
		protected Player player;
		protected BoardGame board;
		protected boolean isYourTurn;
		protected boolean isWithLocalServer = false;
		
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
			//Setup the game
			gameSetup();
			if(isWithLocalServer) {
				
			}
			else {
				
			}
		}
		
		public void setLocalServer(GameServer gameServer) {
			this.gameServer = gameServer;
			isWithLocalServer = true;
		}
		
		public void gameSetup() {
			
		}
		
}