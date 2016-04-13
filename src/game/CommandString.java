package game;

public class CommandString {
	
	//Includes all the message sent between server and client
	public static final String CLIENT_GAME_SETUP_READY = "CLIENT_GAME_SETUP_READY";
	public static final String CLIENT_GAME_START_READY = "CLIENT_GAME_START_READY";
	public static final String CLIENT_START_GAME_SETUP = "CLIENT_START_GAME_SETUP";
	
	public static final String SERVER_OTHER_CLIENT_NOT_AVAILABLE = "SERVER_OTHER_CLIENT_NOT_AVAILABLE";
	public static final String SERVER_OTHER_CLIENT_AVAILABLE = "SERVER_OTHER_CLIENT_AVAILABLE";
	public static final String SERVER_START_GAME_SETUP = "SERVER_START_GAME_SETUP";
	
	/*	Validate a command string
		If the command string is valid, return true
		else return false
	*/
	public static boolean validateCommand(String command) {
		switch(command) {
			case CLIENT_GAME_SETUP_READY:	 	return true;
			case CLIENT_GAME_START_READY:		return true;
			default: 							return false;
		}
	}
}
