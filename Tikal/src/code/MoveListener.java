package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* This class is the action listener for the Move button in the GUI.*/
public class MoveListener implements ActionListener{
	private GUI _gui;

	public MoveListener(GUI gui){
		_gui = gui;
	}

	/* This method defines what happens when the Place button is pushed. Pushing the
	 * button enables the user to place explorers on the tiles.*/
	@Override
	public void actionPerformed(ActionEvent e) {
		_gui.setPlaceEnabled(false);
		_gui.set_moveEnabled(true);
		_gui.setPlacePyramidEnabled(false);
	}
}