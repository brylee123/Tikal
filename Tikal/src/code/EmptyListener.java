package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;

/*This class is the action listener for empty tile buttons in the GUI. When empty tiles are clicked,
 * tiles must be placed. */
public class EmptyListener implements ActionListener{
	private Stack<Tile> _stk;
	private TButton _button;
	private GUI _gui;
	private JButton _rotateButton;
	private String _playerText;
	private Player _player;
	private int AP;

	public EmptyListener(Stack<Tile> stk, TButton emptyButton, GUI gui, JButton rotateButton, String playerText){
		_stk = stk;
		_button = emptyButton;
		_gui = gui;
		_rotateButton = rotateButton;
		_playerText = playerText;
	}

	/* This method determines what the button will do when clicked. If a tile is picked from the stack 
	 * and the button is either in a corner or is next to a tile that isn't empty, a tile will be placed
	 * on the button.*/
	@Override
	public void actionPerformed(ActionEvent e) {
		_player = Main.getPlayer(_gui.getTurn());
		//System.out.println("You pressed button number " + _button.getID());
		if(_gui.getTileEnabled()&&(_button.IsInACorner()||_button.hasPath())){
			Tile tile = _stk.pop();
			_button.setIcon(tile.getIcon());
			_rotateButton.setIcon(null);
			_rotateButton.setText("Draw");
			_button.setIsNotEmpty(true);
			_button.setText("Pyr:1"+_playerText);
			_button.removeActionListener(this);
			_button.addActionListener(new NonEmptyListener(_button, _gui,_playerText));
			_gui.setTileEnabled(false);
			
			_player.placeTile(Main.getBoardPieces().get(_button.getID()));
			AP = _player.getAP();
			_gui.getLabel().setText("Player "+ (_gui.getTurn()+1)+" has "+AP +" AP's" );
			//Main.getBoardPieces().get(_button.getID()).setTile();
		}
	}
}
