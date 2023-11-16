package Main;

import java.awt.Color;

import Database.Database;
import OCSF.GameClient;
import OCSF.GameServer;
import ServerCommunication.LoginData;

public class Main {
	public static void main(String[] args) {
		GameServer server = new GameServer();
		GameClient client = new GameClient();
		
		LoginData loginData = new LoginData("jjones@yahoo.com", "hello1234");
		
		
	}	
}