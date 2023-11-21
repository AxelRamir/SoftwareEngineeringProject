package ServerCommunication;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import ocsf.server.ConnectionToClient;

public class PlayerQueue implements Serializable{
	private Queue<ConnectionToClient>clientConnections;
	private int size;
	
	public PlayerQueue() {
		clientConnections = new LinkedList<ConnectionToClient>();
		size = 0;
	}
	
	public void addPlayer(ConnectionToClient client) {
		clientConnections.add(client);
		size++;
	}
	
	public ConnectionToClient removePlayer() {
		size--;
		return clientConnections.remove();
	}
	public int size() {
		return size;
	}
}
