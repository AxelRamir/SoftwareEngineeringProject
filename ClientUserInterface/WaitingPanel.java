package ClientUserInterface;

import javax.swing.*;

import ClientCommunications.WaitingControl;

public class WaitingPanel extends JPanel{
	private WaitingControl waitingControl;

	public WaitingPanel(WaitingControl waitingControl) {
		super();
		this.waitingControl = waitingControl;
		JPanel main = new JPanel();
		JLabel label = new JLabel("Waiting for another player to join");
		main.add(label);
		this.add(main);
	}
}
