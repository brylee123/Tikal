package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndListener implements ActionListener {

	
	public EndListener(GUI gui) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Main.score();
		
	}

}
