package Game;

import java.net.Socket;

public class GameClient {
		protected GameServer gameServer;
		protected Player self;
		protected Player opponent;
		protected BoardGame board;
		protected boolean isYourTurn;
		protected boolean isLocal;
	
		protected GameClient(boolean isLocal) {
			if(isLocal) {
				
			}
		}
}