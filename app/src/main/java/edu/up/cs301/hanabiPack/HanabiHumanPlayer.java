package edu.up.cs301.hanabiPack;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * A GUI of a counter-player. The GUI displays the current value of the counter,
 * and allows the human player to press the '+' and '-' buttons in order to
 * send moves to the game.
 * 
 * Just for fun, the GUI is implemented so that if the player presses either button
 * when the counter-value is zero, the screen flashes briefly, with the flash-color
 * being dependent on whether the player is player 0 or player 1.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
 * @version February 2025
 */
public class HanabiHumanPlayer extends GameHumanPlayer implements OnClickListener, View.OnTouchListener {

	/* instance variables */
	
	// the most recent game state, as given to us by the CounterLocalGame
	private HanabiState state;
	
	// the android activity that we are running
	private GameMainActivity myActivity;

	//references to GUI elements we tinker with during play
	Button hintButton;

	ImageView[] teammateCards = new ImageView[8];
	//TODO:  add the other variables needed

	//A mostly transparent yellow
	int transYellow = 0x44FFFF00;

	//Currently selected teammate's card
	private int selectedCard = 0;

	/**
	 * constructor
	 * @param name
	 * 		the player's name
	 */
	public HanabiHumanPlayer(String name) {
		super(name);
	}

	/**
	 * Returns the GUI's top view object
	 * 
	 * @return
	 * 		the top object in the GUI's view heirarchy
	 */
	public View getTopView() {
		return myActivity.findViewById(R.id.main);
	}
	
	/**
	 * sets the counter value in the text view
	 */
	protected void updateDisplay() {
		//Temporary:  set the hint button's background color to reflect the most recent hint
		hintButton.setBackgroundColor(this.state.color[0]);

		//Update the currently selected card
		for(int i = 0; i < teammateCards.length; ++i) {
			teammateCards[i].setColorFilter(0);  //fully transparent
		}
		teammateCards[selectedCard].setColorFilter(transYellow);

		//TODO: more code needed here!
	}

	/**
	 * this method gets called when the user clicks the '+' or '-' button. It
	 * creates a new CounterMoveAction to return to the parent activity.
	 * 
	 * @param button
	 * 		the button that was clicked
	 */
	public void onClick(View button) {
		// if we are not yet connected to a game, ignore
		if (game == null) return;

		//handle the hint button (TODO:  More code needed here)
		if (button == hintButton) {
			//send a hint action to the local game
			GiveHintAction gha = new GiveHintAction(this, Color.GREEN);
			game.sendAction(gha);
		}


	}// onClick

	/** when the user touches a card, mark that card as selected */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		for(int i = 0; i < teammateCards.length; ++i) {
			if (v == teammateCards[i]) {
				this.selectedCard = i;
				updateDisplay();
				return true;
			}
		}
		return false;
	}


	/**
	 * callback method when we get a message (e.g., from the game)
	 * 
	 * @param info
	 * 		the message
	 */
	@Override
	public void receiveInfo(GameInfo info) {
		// ignore the message if it's not a CounterState message
		if (!(info instanceof HanabiState)) return;
		
		// update our state; then update the display
		this.state = (HanabiState)info;
		updateDisplay();
	}
	
	/**
	 * callback method--our game has been chosen/rechosen to be the GUI,
	 * called from the GUI thread
	 * 
	 * @param activity
	 * 		the activity under which we are running
	 */
	public void setAsGui(GameMainActivity activity) {
		
		// remember the activity
		this.myActivity = activity;
		
	    // Load the layout resource for our GUI
		activity.setContentView(R.layout.hanabi_human_player);

		//setup this class as a listener for button click events
		hintButton = activity.findViewById(R.id.giveHintButton);
		hintButton.setOnClickListener(this);
		//TODO:  the other buttons

		//init the references to the teammates' cards
		teammateCards[0] = activity.findViewById(R.id.imageView);
		teammateCards[1] = activity.findViewById(R.id.imageView2);
		teammateCards[2] = activity.findViewById(R.id.imageView3);
		teammateCards[3] = activity.findViewById(R.id.imageView4);
		teammateCards[4] = activity.findViewById(R.id.imageView6);
		teammateCards[5] = activity.findViewById(R.id.imageView7);
		teammateCards[6] = activity.findViewById(R.id.imageView8);
		teammateCards[7] = activity.findViewById(R.id.imageView9);

		//Make myself the touch listener for all the cards
		for(int i = 0; i < teammateCards.length; ++i) {
			teammateCards[i].setOnTouchListener(this);
		}


	}

}// class CounterHumanPlayer






