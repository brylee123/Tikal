package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* This class is the action listener for the Place button in the GUI.*/
public class PlaceListener implements ActionListener{
	private GUI _gui;

	public PlaceListener(GUI gui){
		_gui = gui;
	}

	/* This method defines what happens when the Place button is pushed. Pushing the
	 * button enables the user to place explorers on the tiles.*/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		_gui.setPlaceEnabled(true);
		_gui.set_moveEnabled(false);
		_gui.setPlacePyramidEnabled(false);
		
	}
}
