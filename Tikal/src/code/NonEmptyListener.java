package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* This class is the action listener for tile buttons that already have tiles placed
 * on them (i.e. nonempty tiles).*/
public class NonEmptyListener implements ActionListener{
	private TButton _button;
	private GUI _gui;
	private String _playerText;
	private int AP;
	
	
	public NonEmptyListener(TButton NonEmptyButton, GUI gui, String playerText){
		_button = NonEmptyButton;
		_gui = gui;
		_playerText = playerText;
		
		
	}
	
	/* This method uses the game's state to determine what a TButton's text should show at the
	 * time that this method is called.*/
	public String getButtonText(TButton button){
		String text = "Pyr:"+button.getPyr().getSize();
		for(int i=0;i<Main.getPlayerList().size();i++){
			
			
			text = text+"P"+(i+1)+":" + Main.getBoardPieces().get(button.getID()).getExpoCount()[i];
			button.setText(text);
		}
		return text;
	}

	/* This method defines what the NonEmptyButton will do when clicked. What happens depends on which
	 * button was previously pressed (Place, Move or Pyramid).*/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Player player = Main.getPlayer(_gui.getTurn());
		if(_gui.getPlaceEnabled()){
			
				/* For this line of code: Get whoever's turn it is's number of explorers on this button
				 * and increment it by one.*/
				player.placeExplorer(Main.getBoardPieces().get(_button.getID()));
				//Main.getBoardPieces().get(_button.getID()).setExpo(_gui.getTurn());
				_button.setText(getButtonText(_button));
				AP = player.getAP();
				_gui.getLabel().setText("Player "+ (_gui.getTurn()+1)+" has "+AP +" AP's" );
		
		}
		else if(_gui.getMoveEnabled()){
			
		}
		else if(_gui.getPlacePyramidEnabled()){
			AP = player.getAP();
			if(AP>0){
				
			_button.getPyr().incPyramid();
			_button.setText(getButtonText(_button));
			player.placePyramid(Main.getBoardPieces().get(_button.getID()));
			AP = player.getAP();
			_gui.getLabel().setText("Player "+ (_gui.getTurn()+1)+" has "+AP +" AP's" );
				
				
			}
			else{
				System.out.println("You are out of Action Points");
			}
		}
	}
}
