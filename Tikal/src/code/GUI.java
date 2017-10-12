package code;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

/* This class describes the graphical user interface of the board game.*/
public class GUI {

	private int _numPlayers;
	private ImageIcon _image;
	private ArrayList<ArrayList<TButton>> _buttonList;
	private ArrayList<ArrayList<int[]>> _al2;
	private Stack<Tile> _stk;
	private JButton _rotateButton;
	private Boolean _tileEnabled;
	private Boolean _placeEnabled;
	private Boolean _moveEnabled;
	private Boolean _placePyramidEnabled;
	private int[] _bounds;
	private HashMap<String, BufferedImage> _imBase;
	private Dimension _screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int _turn;
	private int[] _turnList;
	private JLabel APDisplay;

	public GUI(ArrayList<Player> playerNames) {

		_imBase = new HashMap<String, BufferedImage>();
		loadBaseImages();
		_numPlayers = playerNames.size();
		_image = new ImageIcon("Images/blank.png");
		_buttonList = new ArrayList<ArrayList<TButton>>();
		_al2 = new ArrayList<ArrayList<int[]>>();
		_tileEnabled = false;
		_placeEnabled = false;
		_moveEnabled = false;
		_placePyramidEnabled = false;
		_bounds = new int[] { 6, getNumberofColumns(_numPlayers) }; // {y-bound,x-bound}
		_turn = 0;
		_turnList = new int[Main.getPlayerList().size()];
		for (int j = 0; j < _turnList.length; j++) {
			_turnList[j] = j;
		}

		_stk = new Stack<Tile>();// list of all tiles
		for (int i = 0; i < Main.getBoardPieces().size(); i++) {
			_stk.push(new Tile(_imBase, i));
		}


		
		JFrame f = new JFrame("CSE116 - Tikal - Stage 2");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel statusPanel1 = new JPanel();
		statusPanel1.add(Box.createHorizontalStrut((int) (_screenSize
				.getWidth() * .7)));
	

		Container cp = f.getContentPane();
		cp.setLayout(new SpringLayout());

		Box box = Box.createVerticalBox();
		Box boxScore = Box.createHorizontalBox();

		_rotateButton = new JButton("Draw");
		// _rotateButton.setPreferredSize(new Dimension(100, 100));
		_rotateButton.addActionListener(new RotateListener(_stk, _rotateButton,
				this));
		box.add(_rotateButton);

		JButton expoPlaceButton = new JButton("Place");
		// expoPlaceButton.setPreferredSize(new Dimension(100,100));
		expoPlaceButton.addActionListener(new PlaceListener(this));
		box.add(expoPlaceButton);

		JButton expoMoveButton = new JButton("Move");
		// expoMoveButton.setPreferredSize(new Dimension(100,100));
		expoMoveButton.addActionListener(new MoveListener(this));
		box.add(expoMoveButton);

		JButton placePyramidButton = new JButton("Pyramid");
		// placePyramidButton.setPreferredSize(new Dimension(100,100));
		placePyramidButton.addActionListener(new PyramidListener(this));
		box.add(placePyramidButton);

		JButton changeTurnButton = new JButton("End Turn");
		changeTurnButton.addActionListener(new TurnListener(this));
		box.add(changeTurnButton);
		
		JButton saveButton = new JButton("Save Game");
		saveButton.addActionListener(new SaveListener());
		boxScore.add(saveButton);
		
		JButton scoreButton = new JButton("End Game");
		scoreButton.addActionListener(new EndListener(this));
		boxScore.add(scoreButton);
		
		int AP = Main.getPlayer(_turn).getAP();
		APDisplay = new JLabel("Player "+ (getTurn()+1)+" has "+AP +" AP's" );
		box.add(APDisplay);

		// JPanel statusPanel2 = new JPanel();
		// statusPanel2.add(Box.createVerticalStrut(1900));
		// JLabel statusLabel2 = new
		// JLabel("Player 1's Score: 0      Player 2's Score: 0 ");
		// statusPanel2.add(statusLabel2);

		statusPanel1.add(box);
		statusPanel1.add(boxScore);

		JPanel mainpanel = new JPanel();
		mainpanel
				.setPreferredSize(new Dimension(
						(int) ((1 + (Main.getPlayerList().size() - 2.0) / 8.0) * 250 * _bounds[1]),
						1000));
		JScrollPane scroller = new JScrollPane(mainpanel);
		scroller.setPreferredSize(new Dimension(
				(int) (_screenSize.getWidth() * .7), (int) (_screenSize
						.getHeight() * 0.9)));
		String pyramidText = "Pyr:0";
		String playerText = "";
		for (int i = 0; i < _numPlayers; i++) {
			playerText = playerText + " P" + (i + 1) + ":0";
		}

		int count = 0;
		for (int i = 0; i < _bounds[1]; i++) {

			_buttonList.add(new ArrayList<TButton>());
			_al2.add(new ArrayList<int[]>()); // What is this???

			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
			mainpanel.add(p);
			for (int j = 0; j < _bounds[0] + i % 2; j++) {
				TButton button = new TButton(_image, new int[] { j, i },
						_bounds, count, _buttonList);
				// button.setPreferredSize(new Dimension(100, 100));
				button.setText(pyramidText + playerText);
				button.addActionListener(new EmptyListener(_stk, button, this,
						_rotateButton, playerText));
				_buttonList.get(i).add(button);
				_al2.get(i).add(new int[_numPlayers]);
				p.add(button);
				count++;
			}
		}

		// cp.add(statusPanel2);
		// cp.add(mainpanel);
		cp.add(scroller);
		cp.add(statusPanel1);
		f.setExtendedState(Frame.MAXIMIZED_BOTH);
		f.setSize(f.getWidth() + 1, f.getHeight());
		f.pack();
		f.setVisible(true);
	}

	/* This is an accessor method for the _placeEnabled instance variable. */
	public Boolean getPlaceEnabled() {
		return _placeEnabled;
	}

	/* This is a mutator method for the _placeEnabled instance variable. */
	public void setPlaceEnabled(Boolean placeEnabled) {
		_placeEnabled = placeEnabled;
	}

	/* This is an accessor method for the _moveEnabled instance variable. */
	public Boolean getMoveEnabled() {
		return _moveEnabled;
	}

	/* This is a mutator method for the _moveEnabled instance variable. */
	public void set_moveEnabled(Boolean moveEnabled) {
		_moveEnabled = moveEnabled;
	}

	/*
	 * This is an accessor method for the _placePyramidEnabled instance
	 * variable.
	 */
	public Boolean getPlacePyramidEnabled() {
		return _placePyramidEnabled;
	}

	/* This is a mutator method for the _placePyramidEnabled instance variable. */
	public void setPlacePyramidEnabled(Boolean placePyramidEnabled) {
		_placePyramidEnabled = placePyramidEnabled;
	}

	/*
	 * This method passes in an image and a number, then rotates the image 90
	 * degrees clockwise times the number passed in, and then returns the
	 * rotated image. For example, passing in the number 2 will rotate the image
	 * 180 degrees clockwise.
	 */
	public static BufferedImage rotate(BufferedImage im, int rot) {
		if (rot % 4 == 0)
			return im;
		BufferedImage im2 = new BufferedImage(im.getHeight(), im.getWidth(),
				im.getType());
		Graphics2D graphics = (Graphics2D) im2.getGraphics();
		graphics.rotate(Math.PI / 2 * rot, im2.getWidth() / 2,
				im2.getHeight() / 2);
		graphics.drawImage(im, 0, 0, im.getWidth(), im.getHeight(), null);
		return im2;
	}

	/*
	 * This method passes in a maximum and a minimum value, and then returns an
	 * random integer between both values (and including them).
	 */
	public static int randInt(int max, int min) {
		Random r = new Random();
		int rand = r.nextInt(max - min + 1) + min;
		return rand;
	}

	/*
	 * This method loads all images so that they can be used later on in the
	 * program.
	 */
	private void loadBaseImages() {
		for (String s : new String[] { "1", "1b", "2", "2b", "3b", "6b",
				"empty", "blank" }) {
			BufferedImage im;
			try {
				im = ImageIO.read(new FileInputStream("Images/" + s + ".png"));
				_imBase.put(s, im);
			} catch (IOException e) {
				System.exit(1);
			}
		}
	}

	/* This is an accessor method for the _tileEnabled instance variable. */
	public Boolean getTileEnabled() {
		return _tileEnabled;
	}

	/*
	 * This method passes in the number of players of the game and calculates
	 * (and returns) the number of columns needed in the GUI.
	 */
	private int getNumberofColumns(int p) {
		for (int i = 1;; i++) {
			if ((2 * i - 1) * i > 2 * p * 15) {
				return i;
			}
		}
	}

	/* This is a mutator method for the _tileEnabled instance variable. */
	public void setTileEnabled(Boolean b) {
		_tileEnabled = b;
	}

	//Accesor method for who's turn it is
	public int getTurn() {
		return _turn;
	}

	/* changes who's turn it is */
	public void changeTurn() {
		if (_turn == _turnList.length - 1) {
			_turn = 0;
		} else {
			_turn++;
		}
		System.out.println("It is now player " + (_turn + 1) + "'s turn");
	}
	
	public JLabel getLabel(){
		return APDisplay;
	}
}
