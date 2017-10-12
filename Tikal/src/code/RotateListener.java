package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;

/* This class is the action listener for the rotate button in the GUI.*/
public class RotateListener implements ActionListener {
	private Stack<Tile> _stk;
	private JButton _rotateButton;
	private GUI _gui;
	private int AP;
	private Player _player;

	public RotateListener(Stack<Tile> stk, JButton rotateButton, GUI gui) {
		_stk = stk;
		_rotateButton = rotateButton;
		_gui = gui;
	}

	/*
	 * This method describes what happens when the rotate button is clicked. A
	 * tile is taken from the stack and is displayed on the button when first
	 * clicked. All later clicks rotate the image 180 degrees. After the tile is
	 * placed on an empty button, this process starts all over again.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		_player = Main.getPlayer(_gui.getTurn());
		AP = _player.getAP();
		if (AP > 3) {
			_gui.setTileEnabled(true);
			Tile tile = _stk.pop();
			_rotateButton.setText("Rotate");
			tile.rotate();
			_rotateButton.setIcon(tile.getIcon());
			_stk.push(tile);
			tile.setAllowRotation(true);
		}
		else{
			System.out.println("You are out of APs");
		}
	}
}
