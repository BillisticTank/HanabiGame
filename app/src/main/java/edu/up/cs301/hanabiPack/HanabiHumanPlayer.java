package edu.up.cs301.hanabiPack;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

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
	
	// The TextView the displays the current counter value
	private TextView testResultsTextView;
	
	// the most recent game state, as given to us by the CounterLocalGame
	private HanabiState state;
	
	// the android activity that we are running
	private GameMainActivity myActivity;

	//references to GUI elements we tinker with during play
	Button hintButton;
	Button playCardButton;
	Button discardButton;

	ImageView[] teammateCards = new ImageView[10];

	ImageView[] yourCards = new ImageView[5];
	//TODO:  add the other variables needed

	//A mostly transparent yellow
	int transYellow = 0x44FFFF00;

	//Currently selected teammate's card
	private int selectedTeammateCard = 0;
	//Currently selected card in your deck
	private int selectedYourCard = 0;
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
		return myActivity.findViewById(R.id.main); //hanabi_main);

	}
	
	/**
	 *It updates the whole display of the game based on the current state.
	 */

    protected void updateDisplay() {
		//set the hint button to reflect the most recent hint
		TextView hintView = myActivity.findViewById(R.id.hints);
		hintView.setText("Hints: " + state.getTotalHints());

		TextView deckView = myActivity.findViewById(R.id.cardsDeck);
		deckView.setText("Cards Left in Deck: " + state.getTotalCardsInDeck());

		TextView fuseView = myActivity.findViewById(R.id.fuseTokens);
		fuseView.setText("Fuse Tokens: " + state.getFuseTokens());

		//Update the currently selected teammate card
		for(int i = 0; i < teammateCards.length; ++i) {
			teammateCards[i].setColorFilter(0);  //fully transparent
		}
		teammateCards[selectedTeammateCard].setColorFilter(transYellow);

		//Update the currently selected card in your deck
		for(int i = 0; i < yourCards.length; ++i) {
			yourCards[i].setColorFilter(0);  //fully transparent
		}
		yourCards[selectedYourCard].setColorFilter(transYellow);


		//0 = yellow incorrect
		//1 = red correct
		//2 = white correct
		//3 = blue correct
		//4 = green incorrect

		//Red SubShow Cards has been played, ...
		ArrayList<Card> redSubShow = state.fireworkShow.get(1);
		ImageView redSubShowCard1 = myActivity.findViewById(R.id.red1);
		ImageView redSubShowCard2 = myActivity.findViewById(R.id.red2);
		ImageView redSubShowCard3 = myActivity.findViewById(R.id.red3);
		ImageView redSubShowCard4 = myActivity.findViewById(R.id.red4);
		ImageView redSubShowCard5 = myActivity.findViewById(R.id.red5);

		//White SubShow Cards has been played, ...
		ArrayList<Card> whiteSubShow = state.fireworkShow.get(2);
		ImageView whiteSubShowCard1 = myActivity.findViewById(R.id.white1);
		ImageView whiteSubShowCard2 = myActivity.findViewById(R.id.white2);
		ImageView whiteSubShowCard3 = myActivity.findViewById(R.id.white3);
		ImageView whiteSubShowCard4 = myActivity.findViewById(R.id.white4);
		ImageView whiteSubShowCard5 = myActivity.findViewById(R.id.white5);

		//Blue SubShow Cards has been played, ...
		ArrayList<Card> blueSubShow = state.fireworkShow.get(3);
		ImageView blueSubShowCard1 = myActivity.findViewById(R.id.blue1);
		ImageView blueSubShowCard2 = myActivity.findViewById(R.id.blue2);
		ImageView blueSubShowCard3 = myActivity.findViewById(R.id.blue3);
		ImageView blueSubShowCard4 = myActivity.findViewById(R.id.blue4);
		ImageView blueSubShowCard5 = myActivity.findViewById(R.id.blue5);

		//Green SubShow Cards has been played, ...
		ArrayList<Card> greenSubShow = state.fireworkShow.get(4);
		ImageView greenSubShowCard1 = myActivity.findViewById(R.id.green1);
		ImageView greenSubShowCard2 = myActivity.findViewById(R.id.green2);
		ImageView greenSubShowCard3 = myActivity.findViewById(R.id.green3);
		ImageView greenSubShowCard4 = myActivity.findViewById(R.id.green4);
		ImageView greenSubShowCard5 = myActivity.findViewById(R.id.green5);

		//Yellow SubShow Cards has been played, ...
		ArrayList<Card> yellowSubShow = state.fireworkShow.get(0);
		ImageView yellowSubShowCard1 = myActivity.findViewById(R.id.yellow1);
		ImageView yellowSubShowCard2 = myActivity.findViewById(R.id.yellow2);
		ImageView yellowSubShowCard3 = myActivity.findViewById(R.id.yellow3);
		ImageView yellowSubShowCard4 = myActivity.findViewById(R.id.yellow4);
		ImageView yellowSubShowCard5 = myActivity.findViewById(R.id.yellow5);

		// Updated Red Subshow Cards
		if (redSubShow.size() > 0) { redSubShowCard1.setImageResource(R.drawable.hanabi_red_1); }
		if (redSubShow.size() > 1) { redSubShowCard2.setImageResource(R.drawable.hanabi_red_2);}
		if (redSubShow.size() > 2) { redSubShowCard2.setImageResource(R.drawable.hanabi_red_3);}
		if (redSubShow.size() > 3) { redSubShowCard2.setImageResource(R.drawable.hanabi_red_4);}
		if (redSubShow.size() > 4) { redSubShowCard2.setImageResource(R.drawable.hanabi_red_5);}

		// Updated Blue Subshow Cards
		if (blueSubShow.size() > 0) { blueSubShowCard1.setImageResource(R.drawable.hanabi_blue_1); }
		if (blueSubShow.size() > 1) { blueSubShowCard2.setImageResource(R.drawable.hanabi_blue_2); }
		if (blueSubShow.size() > 2) { blueSubShowCard3.setImageResource(R.drawable.hanabi_blue_3); }
		if (blueSubShow.size() > 3) { blueSubShowCard4.setImageResource(R.drawable.hanabi_blue_4); }
		if (blueSubShow.size() > 4) { blueSubShowCard5.setImageResource(R.drawable.hanabi_blue_5); }

		// Updated White Subshow Cards
		if (whiteSubShow.size() > 0) { whiteSubShowCard1.setImageResource(R.drawable.hanabi_white_1); }
		if (whiteSubShow.size() > 1) { whiteSubShowCard2.setImageResource(R.drawable.hanabi_white_2); }
		if (whiteSubShow.size() > 2) { whiteSubShowCard3.setImageResource(R.drawable.hanabi_white_3); }
		if (whiteSubShow.size() > 3) { whiteSubShowCard4.setImageResource(R.drawable.hanabi_white_4); }
		if (whiteSubShow.size() > 4) { whiteSubShowCard5.setImageResource(R.drawable.hanabi_white_5); }

		//Updated Green Subshow Cards
		if (greenSubShow.size() > 0) { greenSubShowCard1.setImageResource(R.drawable.hanabi_green_1); }
		if (greenSubShow.size() > 1) { greenSubShowCard2.setImageResource(R.drawable.hanabi_green_2); }
		if (greenSubShow.size() > 2) { greenSubShowCard3.setImageResource(R.drawable.hanabi_green_3); }
		if (greenSubShow.size() > 3) { greenSubShowCard4.setImageResource(R.drawable.hanabi_green_4); }
		if (greenSubShow.size() > 4) { greenSubShowCard5.setImageResource(R.drawable.hanabi_green_5); }

		//Updated Yellow Subshow Cards
		if (yellowSubShow.size() > 0) { yellowSubShowCard1.setImageResource(R.drawable.hanabi_yellow_1); }
		if (yellowSubShow.size() > 1) { yellowSubShowCard2.setImageResource(R.drawable.hanabi_yellow_2); }
		if (yellowSubShow.size() > 2) { yellowSubShowCard3.setImageResource(R.drawable.hanabi_yellow_3); }
		if (yellowSubShow.size() > 3) { yellowSubShowCard4.setImageResource(R.drawable.hanabi_yellow_4); }
		if (yellowSubShow.size() > 4) { yellowSubShowCard5.setImageResource(R.drawable.hanabi_yellow_5); }

		//Display the subshow cards
		for(int color = 0; color < state.color.length; ++color) {
			//ArrayList<Card> RedSubShow = state.fireworkShow.get(color);
			//ArrayList<Card> WhiteSubShow = state.fireworkShow.get(color);
			//ArrayList<Card> YellowSubShow = state.fireworkShow.get(color);
			//ArrayList<Card> GreenSubShow = state.fireworkShow.get(color);
			//ArrayList<Card> BlueSubShow = state.fireworkShow.get(color);

			if (redSubShow.size() > 0) { redSubShowCard1.setImageResource(R.drawable.hanabi_red_1); }
			if (whiteSubShow.size() > 0) { whiteSubShowCard1.setImageResource(R.drawable.hanabi_white_1); }
			if (yellowSubShow.size() > 0) { yellowSubShowCard1.setImageResource(R.drawable.hanabi_yellow_1); }
			if (greenSubShow.size() > 0) { greenSubShowCard1.setImageResource(R.drawable.hanabi_green_1); }
			if (blueSubShow.size() > 0) { blueSubShowCard1.setImageResource(R.drawable.hanabi_blue_1); }

		}

		//TODO draw a little circle on com's cards to show which cards i've
		//TODO I've already given hints on


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
		//Announces every single move made by players in the game
		TextView announcer = myActivity.findViewById(R.id.announcer);

		// if we are not yet connected to a game, ignore
		if (game == null) return;

		//handle the hint button (TODO:  More code needed here)
		if (button == hintButton) {

			//send a hint action to the local game based on color
			//TODO colorHint isColor parameter needs to be changed to be something more flexible
			GiveHintAction colorHint = new GiveHintAction(this, true,
					state.getPlayer_Id(), selectedTeammateCard);

			//TODO feel like this also should be done in HanabiState

			int newHints = state.getTotalHints() - 1;
			state.setTotalHints(newHints);
			if(state.getTotalHints() < 0 || state.getTotalHints() == 0){

				state.setTotalHints(0);
				announcer.setText("Sorry, you have zero hints left.");
			}
			else if (state.getTotalCardsInDeck() == 0){announcer.setText("GAME OVER! All the cards have been played.");} //GAME OVER

			else {announcer.setText("Player"+state.getPlayer_Id() +" used a Hint.");}  // if the hints are negative.
			game.sendAction(colorHint);
			updateDisplay();

		}
			else if (button == discardButton) {

			DiscardCardAction discardCard = new DiscardCardAction(this, selectedYourCard);

			// TODO error? shouldn't this be done in HanabiState
			//  via the discardCardAction?
			//decrease the card in a player's deck by one
			int cardsLeft = state.getTotalCardsInDeck() - 1;
			state.setTotalCardsInDeck(cardsLeft);

			if (state.getTotalHints() < 8) {
				int newHints = state.getTotalHints() + 1;
				state.setTotalHints(newHints);
			}

			//Checks if Game Over conditions are met
			if(state.getTotalCardsInDeck() < 0 || state.getTotalCardsInDeck() == 0){

				state.setTotalCardsInDeck(0);
				announcer.setText("GAME OVER! All the cards have been played.");
				myActivity.setGameOver(true);
			} // if the hints are negative.

			else {announcer.setText("Player"+state.getPlayer_Id() +" discarded a Card.");}
			game.sendAction(discardCard);
			updateDisplay();

		}
		else if (button == playCardButton) {

			PlayCardAction playCard = new PlayCardAction(this, selectedYourCard);

			//TODO: This is just a test to see if the Fuse Tokens are working,
			// we'll change this when we actually try to implement the Play Card Action

			/**
			 * Suggestion:
			 * if(card color == firework show column) {
			 * 		if(card number = numCards in column + 1) {
			 * 			playCard
			 * 			announcer.setText("Player succesfully played a " +
			 * 			getCardColor + getCardNumber)
			 * 		}
			 * 	}
			 */



			int newFuse = state.getFuseTokens() - 1;
			state.setFuseTokens(newFuse);
			announcer.setText("Player Played the Wrong Card!");

			//Checks if Game Over conditions are met
			if(state.getFuseTokens() < 0 || state.getFuseTokens() == 0) {
				state.setFuseTokens(0);
				announcer.setText("GAME OVER! The Bomb Blew Up!");
				myActivity.setGameOver(true);
			}

			game.sendAction(playCard);
			updateDisplay();
		}

	}// onClick

	/** when the user touches a card, mark that card as selected */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		for(int i = 0; i < teammateCards.length; ++i) {
			if (v == teammateCards[i]) {
				this.selectedTeammateCard = i;
				updateDisplay();
				return true;
			}
		}


		for(int i = 0; i < yourCards.length; ++i) {
			if (v == yourCards[i]) {
				this.selectedYourCard = i;
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

		hintButton = activity.findViewById(R.id.giveHintButton);
		hintButton.setOnClickListener(this);
		playCardButton = activity.findViewById(R.id.playCardButton);
		playCardButton.setOnClickListener(this);
		discardButton = activity.findViewById(R.id.discardButton);
		discardButton.setOnClickListener(this);

		//init the references to the teammates' cards
		teammateCards[0] = activity.findViewById(R.id.GPT1);
		teammateCards[1] = activity.findViewById(R.id.GPT2);
		teammateCards[2] = activity.findViewById(R.id.GPT3);
		teammateCards[3] = activity.findViewById(R.id.GPT4);
		teammateCards[4] = activity.findViewById(R.id.GPT5);
		teammateCards[5] = activity.findViewById(R.id.Gemini1);
		teammateCards[6] = activity.findViewById(R.id.Gemini2);
		teammateCards[7] = activity.findViewById(R.id.Gemini3);
		teammateCards[8] = activity.findViewById(R.id.Gemini4);
		teammateCards[9] = activity.findViewById(R.id.Gemini5);

		//Setting updated cards;
		Random random = new Random();
		for (int i = 0; i < teammateCards.length; i++){
			int color = random.nextInt(5);
			int value = random.nextInt(5) + 1;

			// color 0: Blue color.
			// color 1: Red color.
			// color 2: Yellow color.
			// color 3: White color.
			// color 4: Green color.

			if (color == 0){ //Blue color
				if(value == 1){teammateCards[i].setImageResource(R.drawable.hanabi_blue_1);}
				else if(value == 2){teammateCards[i].setImageResource(R.drawable.hanabi_blue_2);}
				else if(value == 3){teammateCards[i].setImageResource(R.drawable.hanabi_blue_3);}
				else if(value == 4){teammateCards[i].setImageResource(R.drawable.hanabi_blue_4);}
				else if(value == 5){teammateCards[i].setImageResource(R.drawable.hanabi_blue_5);}
			}
			else if (color == 1){ //Red color
				if(value == 1){teammateCards[i].setImageResource(R.drawable.hanabi_red_1);}
				else if(value == 2){teammateCards[i].setImageResource(R.drawable.hanabi_red_2);}
				else if(value == 3){teammateCards[i].setImageResource(R.drawable.hanabi_red_3);}
				else if(value == 4){teammateCards[i].setImageResource(R.drawable.hanabi_red_4);}
				else if(value == 5){teammateCards[i].setImageResource(R.drawable.hanabi_red_5);}
			}
			else if (color == 2){ //Yellow color
				if(value == 1){teammateCards[i].setImageResource(R.drawable.hanabi_yellow_1);}
				else if(value == 2){teammateCards[i].setImageResource(R.drawable.hanabi_yellow_2);}
				else if(value == 3){teammateCards[i].setImageResource(R.drawable.hanabi_yellow_3);}
				else if(value == 4){teammateCards[i].setImageResource(R.drawable.hanabi_yellow_4);}
				else if(value == 5){teammateCards[i].setImageResource(R.drawable.hanabi_yellow_5);}
			}
			else if (color == 3){ //White color
				if(value == 1){teammateCards[i].setImageResource(R.drawable.hanabi_white_1);}
				else if(value == 2){teammateCards[i].setImageResource(R.drawable.hanabi_white_2);}
				else if(value == 3){teammateCards[i].setImageResource(R.drawable.hanabi_white_3);}
				else if(value == 4){teammateCards[i].setImageResource(R.drawable.hanabi_white_4);}
				else if(value == 5){teammateCards[i].setImageResource(R.drawable.hanabi_white_5);}
			}
			else if (color == 4){ //Green color
				if(value == 1){teammateCards[i].setImageResource(R.drawable.hanabi_green_1);}
				else if(value == 2){teammateCards[i].setImageResource(R.drawable.hanabi_green_2);}
				else if(value == 3){teammateCards[i].setImageResource(R.drawable.hanabi_green_3);}
				else if(value == 4){teammateCards[i].setImageResource(R.drawable.hanabi_green_4);}
				else if(value == 5){teammateCards[i].setImageResource(R.drawable.hanabi_green_5);}
			}

		}
		//



		//init the references to the teammates' cards
		yourCards[0] = activity.findViewById(R.id.playerCard1);
		yourCards[1] = activity.findViewById(R.id.playerCard2);
		yourCards[2] = activity.findViewById(R.id.playerCard3);
		yourCards[3] = activity.findViewById(R.id.playerCard4);
		yourCards[4] = activity.findViewById(R.id.playerCard5);


		for(int i = 0; i < yourCards.length; i++)
		{
			int color = random.nextInt(5);
			int value = random.nextInt(5) + 1;
			if (color == 0){ //blue color
				if(value == 1){yourCards[i].setImageResource(R.drawable.hanabi_blue_1);}
				else if(value == 2){yourCards[i].setImageResource(R.drawable.hanabi_blue_2);}
				else if(value == 3){yourCards[i].setImageResource(R.drawable.hanabi_blue_3);}
				else if(value == 4){yourCards[i].setImageResource(R.drawable.hanabi_blue_4);}
				else if(value == 5){yourCards[i].setImageResource(R.drawable.hanabi_blue_5);}
			}
			else if (color == 1){ //red color
				if(value == 1){yourCards[i].setImageResource(R.drawable.hanabi_red_1);}
				else if(value == 2){yourCards[i].setImageResource(R.drawable.hanabi_red_2);}
				else if(value == 3){yourCards[i].setImageResource(R.drawable.hanabi_red_3);}
				else if(value == 4){yourCards[i].setImageResource(R.drawable.hanabi_red_4);}
				else if(value == 5){yourCards[i].setImageResource(R.drawable.hanabi_red_5);}
			}
			else if (color == 2){ //yellow color
				if(value == 1){yourCards[i].setImageResource(R.drawable.hanabi_yellow_1);}
				else if(value == 2){yourCards[i].setImageResource(R.drawable.hanabi_yellow_2);}
				else if(value == 3){yourCards[i].setImageResource(R.drawable.hanabi_yellow_3);}
				else if(value == 4){yourCards[i].setImageResource(R.drawable.hanabi_yellow_4);}
				else if(value == 5){yourCards[i].setImageResource(R.drawable.hanabi_yellow_5);}
			}
			else if (color == 3){ //white color
				if(value == 1){yourCards[i].setImageResource(R.drawable.hanabi_white_1);}
				else if(value == 2){yourCards[i].setImageResource(R.drawable.hanabi_white_2);}
				else if(value == 3){yourCards[i].setImageResource(R.drawable.hanabi_white_3);}
				else if(value == 4){yourCards[i].setImageResource(R.drawable.hanabi_white_4);}
				else if(value == 5){yourCards[i].setImageResource(R.drawable.hanabi_white_5);}
			}
			else if (color == 4){ //green color
				if(value == 1){yourCards[i].setImageResource(R.drawable.hanabi_green_1);}
				else if(value == 2){yourCards[i].setImageResource(R.drawable.hanabi_green_2);}
				else if(value == 3){yourCards[i].setImageResource(R.drawable.hanabi_green_3);}
				else if(value == 4){yourCards[i].setImageResource(R.drawable.hanabi_green_4);}
				else if(value == 5){yourCards[i].setImageResource(R.drawable.hanabi_green_5);}
			}
		}




		//Make myself the touch listener for all the cards
		for(int i = 0; i < teammateCards.length; ++i) {
			teammateCards[i].setOnTouchListener(this);
		}

		for(int i = 0; i < yourCards.length; ++i) {
			yourCards[i].setOnTouchListener(this);
		}

	}

}// class HanabiHumanPlayer






