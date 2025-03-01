package edu.up.cs301.hanabiPack;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the Hanabi game. The state consist of simply
 * the value of the counter.
 * 
 * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
 * @version February 2025
 */
public class HanabiState extends GameState {

	Random rand = new Random();
	// instance variables,
	private int player_Id; //four players (1...4)
	private int totalHints; // total hints.
	private int fuseTokens; // Number of failures, more than 3 lose.;
	private int cardsInHand = 4; //cards in a player hand;

	int count = rand.nextInt(5) + 1;
	private int[] color = new int[5];

	{
		color[0] = Color.BLUE;
		color[1] = Color.RED;
		color[2] = Color.WHITE;
		color[3] = Color.YELLOW;
		color[4] = Color.GREEN;
	}

	;


	private int[] cards_Value = new int[cardsInHand]; // Array of Object Card Type;
	private int discardAmount; // how many cards are discarded

	ArrayList<Integer> drawPileAmount = new ArrayList<>(50);

	{
		for (int i = 0; i < 50; ++i) {
			drawPileAmount.add(i);
		}
	}

	// private Cards recentCardPlayed; // latest card played from hand.
	private int finalScore; // score for firework show.

	/**
	 * default constructor;
	 * constructor, initializing the counter value from the parameter
	 */

	public HanabiState() {
		this.player_Id = 1;
		this.totalHints = 8;
		this.fuseTokens = 0;
		this.cardsInHand = 4;
		for (int i = 0; i < color.length; i++) {
			this.color[i] = color[count];
		}

		// this.recentCardPlayed = 0;
		this.cards_Value = null;
		this.discardAmount = 0;
		this.finalScore = 0;
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
		this.cards_Value = orig.cards_Value;
		for (int i = 0; i < color.length; i++) {
			this.color[i] = orig.color[i];
		}
		this.discardAmount = orig.discardAmount;
		this.finalScore = orig.finalScore;
		for (int i = 0; i < drawPileAmount.size(); i++) {
			// Add Cards after we created card class;
		}
	}

	//this toString method describes the state of the game as a string
	@Override
	public String toString() {

		return "Clock Tokens: " + totalHints
				+ " Fuse Tokens: " + fuseTokens
				+ "Discarded Cards: " + discardAmount
				+ "Final Score: " + finalScore
				+ "Turn: Player " + player_Id;
	}

	//these three methods corraspond to each action class and check if the action is viable
	public boolean makePlayCardAction(PlayCardAction action) {

		return true;
	}

	public boolean makeDiscardCardAction(DiscardCardAction action) {
		if(totalHints == 8) {
			return false;
		}
		else {
			totalHints++;
			discardAmount++;
			return true;
		}

	}

	public boolean makeGiveHintAction(GiveHintAction action) {
		if(totalHints == 0)
		{
			return false;
		}
		else
		{
			totalHints--;

			return true;
		}

	}
}
