package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 /*
 * This class is a Listener for the Turn button,
 * it will change who's turn it is in the game
 */

public class TurnListener implements ActionListener {
	
	private GUI _gui;
	private int AP;
	
	public TurnListener(GUI gui){
		_gui = gui;
		AP = Main.getPlayer(_gui.getTurn()).getAP();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Main.getPlayer(_gui.getTurn()).resetAP();
		_gui.changeTurn();
		_gui.getLabel().setText("Player "+ (_gui.getTurn()+1)+" has "+AP +" AP's" );
		
		
	}

}
