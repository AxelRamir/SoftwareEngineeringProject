package ServerCommunication;

import java.util.LinkedList;
import java.util.Queue;

import ocsf.server.ConnectionToClient;

public class PlayerQueue {
	Queue<ConnectionToClient>clientConnections;
	
	public PlayerQueue() {
		clientConnections = new LinkedList<ConnectionToClient>();
	}
	
	public void addPlayer(ConnectionToClient client) {
		clientConnections.add(client);
	}
	
	public ConnectionToClient removePlayer() {
		return clientConnections.remove();
	}
	public int size() {
		return clientConnections.size();
	}
}
