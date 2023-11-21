package ClientCommunications;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import OCSF.GameClient;

public class BoardControl implements ActionListener{
	
	private JPanel container;
	private GameClient client;
	
	//this boolean will be used to see whether or not a piece was already selected before
	private boolean selected;
	
	public BoardControl(JPanel container, GameClient client) {
		this.container = container;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Leave Game") {
			//leave game
		}
		
		//I'm assuming there wouldn't be any other buttons to be interacted with other than the leave button and the checkers board
		else {
			//creates an object that acts like the JButton class that was clicked
			Object Obj = e.getSource();
			
		}
	}

}
