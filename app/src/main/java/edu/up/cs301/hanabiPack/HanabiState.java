package edu.up.cs301.hanabiPack;

import android.graphics.Color;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the Hanabi game. The state consist of simply
 * the value of the counter.
 *
 * start to end move sequence: 1.Player 1 gives hint to player 2 --- 2.player 2 plays a card (red 2) -
 * -- 3.player 2 gives hint to player 3 --- 4.player three discards a card (added to discard pile) -
 * -- 5.player three gives hint to player 4 --- 5. player four plays card (green 3). Game is won due to cards
 * in correct order with no faliures.
 * 
 * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
 * @version February 2025
 */
public class HanabiState extends GameState {


	public static final int BLUE = 0;
	public static final int RED = 1;
	public static final int WHITE = 2;
	public static final int YELLOW = 3;
	public static final int GREEN = 4;




	Random rand = new Random();
	// instance variables,
	private int player_Id; //three players (1...3)
	private int totalHints; // total hints.
	private int fuseTokens; // Number of failures, more than 3 lose.;
	private int cardsInHand = 5; //cards in a player hand;
	private int totalCardsInDeck = 50; //cards in the deck
	private boolean cardVisibility = true;

	HanabiMainActivity mainActivity;


	int count = rand.nextInt(5);


	//TODO:  should this be public or should there be a getter?  You decide.
	public int[] color = new int[5];

	{
		color[BLUE] = Color.BLUE;
		color[RED] = Color.RED;
		color[WHITE] = Color.WHITE;
		color[YELLOW] = Color.YELLOW;
		color[GREEN] = Color.GREEN;
	}

	//TODO:  Nuxoll thinks a 3x5 2D array of Card objects would be a good way to track
	//		 what's in each player's hand.  Each Card object contains:
	//		 color, number, whether player knows the color, whether player knowns the number
	//       i.e., int, int, boolean, boolean

	/**
	 * The cards_value is a 2D array. The 3 represents the 3 players, and the 5 represents the
	 * 5 cards each player has
	 */
	private Card[][] cards_Value = new Card[3][5]; // Array of Object Card Type;
	private int discardAmount; // how many cards are discarded

	ArrayList<Card> drawPile = new ArrayList<Card>(totalCardsInDeck);
	{
		//for each color we have 5 cards;
		for (int i = 0; i < color.length; ++i) {
			for (int j = 0; j < 5; j++) {

				drawPile.add(new Card(i, j));
				drawPile.add(new Card(i, j));
			}
		}
		// This shuffles the deck to make sure every game, players start with different cards
		Collections.shuffle(drawPile);
	}

	/**\
	 *
	 * Firework Show on the table
	 */
	ArrayList< ArrayList<Card> > fireworkShow = new ArrayList<>();

	/**
	 * An Array of Cards in your hand, this will be used for the hint action in gamestate
	 */
	private Card[][] hints = new Card[3][5];

	private int finalScore; // score for firework show.

	/**
	 * default constructor;
	 * intitalizes all the variables for a new game of Hanabi
	 */

	public HanabiState() {
		this.player_Id = 0; // human player
		this.totalHints = 8;
		this.fuseTokens = 3;
		this.cardsInHand = 5;
		for (int i = 0; i < color.length; i++) {
			this.color[i] = color[count];
		}

		//add an empty list for each color
		for (int j = 0; j < color.length; j++)
		{
			fireworkShow.add(new ArrayList<Card>());
		}

		// this.recentCardPlayed = 0;
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 5; j++){
				cards_Value[i][j] = drawPile.remove(0) ;
			}
		}

		//hints array
		for (int i = 0; i < hints.length; i++) {
			for (int j = 0; j < hints[i].length; j++) {
				hints[i][j] = new Card(-1, -1);
			}
		}
		this.discardAmount = 0;
		this.finalScore = 0;
		for(int i = 0; i < 5; i++)
		{

		}

	}

	/**
	 * deep copy constructor; makes a copy of the original object
	 *
	 * @param orig the object from which the copy should be made
	 */
	public HanabiState(HanabiState orig) {
		this.player_Id = orig.player_Id;
		this.totalHints = orig.totalHints;
		this.fuseTokens = orig.fuseTokens;
		this.cardsInHand = orig.cardsInHand;
		//TODO this array needs to be a deep copy, currently it is a shallow copy
		//this.cards_Value = orig.cards_Value;
		for (int i = 0; i < cards_Value.length; i++) {
			for (int j = 0; j < cards_Value[i].length; j++) {
				this.cards_Value[i][j] = orig.cards_Value[i][j];
			}
		}

		for (int i = 0; i < color.length; i++) {
			this.color[i] = orig.color[i];
		}
		//TODO we need to deep copy the firework show
		//This is a shallow copy, we'll fix it later
		this.fireworkShow = orig.fireworkShow;

		//TODO we need to deep copy the hints show
		for (int i = 0; i < hints.length; i++) {
			this.hints[i] = orig.hints[i];
		}

		this.discardAmount = orig.discardAmount;
		this.finalScore = orig.finalScore;
		for (int i = 0; i < drawPile.size(); i++) {
			//TODO Add Cards after we created card class;
		}
	}

	/**
	 * getter methods
	 */
	public int getPlayer_Id() {return player_Id;}
	public int getTotalHints() {return totalHints;}
	public int getFuseTokens() {return fuseTokens;}
	public Card[] getCardsInHand(int player_Id) {return cards_Value[player_Id];}
	public int getTotalCardsInDeck(){return totalCardsInDeck;}
	public boolean getCardVisibility(){return cardVisibility;}


	/**
	 * setter methods
	 */
	public void setPlayer_Id(int player_Id) {this.player_Id = player_Id;}
	public void setTotalHints(int totalHints) {this.totalHints = totalHints;}
	public void setFuseTokens(int fuseTokens) {this.fuseTokens = fuseTokens;}
	public void setCardsInHand(int cardsInHand) {this.cardsInHand = cardsInHand;}
	public void setTotalCardsInDeck(int cardsInDeck) {this.totalCardsInDeck = cardsInDeck;}
	public void setCardVisibility(boolean visible){this.cardVisibility = visible;};
	/**
	 * this toString method describes the state of the game as a string
	 */

	@Override
	public String toString() {

		return "Clock Tokens: " + totalHints
				+ "Fuse Tokens: " + fuseTokens
				+ "Discarded Cards: " + discardAmount
				+ "Final Score: " + finalScore
				+ "Turn: Player " + player_Id;
	}

	/**
	 * these three methods correspond to each action class and check if the action is viable
	 * then they modify the game state accordingly
	 */
	public boolean makePlayCardAction(PlayCardAction action)
	{
		/**
		 * cardColor is the selected card's color
		 * subShow arraylist is the firework show's column that matches the selected card
		 * cardNumber is the selected card's number
		 */

		//get a reference to the card, at this index
		Card toPlay = cards_Value[player_Id][action._cardIndex];
		//get the array for the selected card's respective color
		ArrayList<Card> subShow = fireworkShow.get(toPlay._color);
		//remove the card from the player's hand and replace it with a new card
		cards_Value[player_Id][action._cardIndex] = drawPile.remove(0);

		//if the card is appropriate to play
		if(toPlay._number == subShow.size() + 1) {
			subShow.add(toPlay);
			finalScore++;
		}
		//if card isn't a valid play
		else {
			fuseTokens--;
		}
		return true;
	}

	public boolean makeDiscardCardAction(DiscardCardAction action) {
	if(totalCardsInDeck > 0) {

		totalCardsInDeck--;

	if (totalHints < 8) {
		totalHints++;
		return true;
	}
	else if (totalHints == 8) {
		return true;
	}

	else if (totalCardsInDeck == 0) {
		TextView announce = mainActivity.findViewById(R.id.announcer);
		announce.setText("GAME OVER! You have no cards left in deck!");
	}
	}
		return false;
	}

	public boolean makeGiveHintAction(GiveHintAction action) {
		if(totalHints > 0){
			/**
			 * give hint action should question you on who you want to give the hint to
			 * and then question whether you want to hint them on color or number of their cards
			 * and then check to make sure if total hints is not equal to 0
			 * and then display that information
			 */
			if(player_Id == action._reciverId){
				return false;
				/**
				 * you can't give a hint to yourself
				 */
			}
			//get the card the hint is about
			Card hintCard = cards_Value[action._reciverId][action._aboutCard];
			if(action._byColor)
			{
				hints[action._reciverId][action._aboutCard]._color = hintCard._color;
			}
			else{
				hints[action._reciverId][action._aboutCard]._number = hintCard._number;
			}

			if(getTotalHints() < 0 || getTotalHints() == 0){
				setTotalHints(0);
			}

			return true;
		}

		return false;

	}
}
