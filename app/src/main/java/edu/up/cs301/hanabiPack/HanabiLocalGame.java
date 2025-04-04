package edu.up.cs301.hanabiPack;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import android.util.Log;

/**
 * A class that represents the state of a game. In our counter game, the only
 * relevant piece of information is the value of the game's counter. The
 * CounterState object is therefore very simple.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
 * @version February 2025
 */
public class HanabiLocalGame extends LocalGame {

	// When a counter game is played, any number of players. The first player
	// is trying to get the counter value to TARGET_MAGNITUDE; the second player,
	// if present, is trying to get the counter to -TARGET_MAGNITUDE. The
	// remaining players are neither winners nor losers, but can interfere by
	// modifying the counter.
	public static final int TARGET_MAGNITUDE = 10;

	// the game's state
	HanabiState gameState;

	/**
	 * default ctor that makes the game state
	 */
	public HanabiLocalGame()
	{
		gameState = new HanabiState();
	}
	
	/**
	 * can this player move
	 * 
	 * @return
	 * 		true, because all player are always allowed to move at all times,
	 * 		as this is a fully asynchronous game
	 */
	@Override
	protected boolean canMove(int playerIdx) {
		if(playerIdx == gameState.getPlayer_Id()){
			return true;
		};
		return false;
	}

	/**
	 * This ctor should be called when a new counter game is started
	 */
	public HanabiLocalGame(GameState state) {
		// initialize the game state, with the counter value starting at 0
		if (! (state instanceof HanabiState)) {
			state = new HanabiState();
		}
		this.gameState = (HanabiState)state;
		super.state = state;
	}

	/**
	 * The only type of GameAction that should be sent is CounterMoveAction
	 */
	@Override
	protected boolean makeMove(GameAction action) {
		Log.i("action", action.getClass().toString());
		
		if (action instanceof GiveHintAction) {

			GiveHintAction mouth = (GiveHintAction) action;
			nextTurn();
			return gameState.makeGiveHintAction(mouth);
		}

		if(action instanceof PlayCardAction)
		{
			PlayCardAction eyes = (PlayCardAction) action;
			nextTurn();
			return gameState.makePlayCardAction(eyes);
		}

		if(action instanceof DiscardCardAction)
		{
			DiscardCardAction ears = (DiscardCardAction) action;
			nextTurn();
			return gameState.makeDiscardCardAction(ears);
		}
		return false;
	}//makeMove

	protected void nextTurn(){
		if(gameState.getPlayer_Id() == 0){
			gameState.setPlayer_Id(1);
		}
		else if(gameState.getPlayer_Id() == 1){
			gameState.setPlayer_Id(2);
		}
		else if(gameState.getPlayer_Id() == 2){
			gameState.setPlayer_Id(0);
		}
	}
	
	/**
	 * send the updated state to a given player
	 */
	@Override
	protected void sendUpdatedStateTo(GamePlayer p) {
		// this is a perfect-information game, so we'll make a
		// complete copy of the state to send to the player
		p.sendInfo(new HanabiState(this.gameState));
		
	}//sendUpdatedSate
	
	/**
	 * Check if the game is over. It is over, return a string that tells
	 * who the winner(s), if any, are. If the game is not over, return null;
	 * 
	 * @return
	 * 		a message that tells who has won the game, or null if the
	 * 		game is not over
	 */
	@Override
	protected String checkIfGameOver() {

		/**
		 * // get the value of the counter
		 * 		int counterVal = this.gameState.getCounter();
		 *
		 * 		if (counterVal >= TARGET_MAGNITUDE) {
		 * 			// counter has reached target magnitude, so return message that
		 * 			// player 0 has won.
		 * 			return playerNames[0]+" has won.";
		 * 		        }
		 * 		else if (counterVal <= -TARGET_MAGNITUDE) {
		 * 			// counter has reached negative of target magnitude; if there
		 * 			// is a second player, return message that this player has won,
		 * 			// otherwise that the first player has lost
		 * 			if (playerNames.length >= 2) {
		 * 				return playerNames[1]+" has won.";
		 *            }
		 * 			else {
		 * 				return playerNames[0]+" has lost.";
		 *            }
		 *        }else {
		 * 			// game is still between the two limit: return null, as the game
		 * 			// is not yet over
		 * 			return null;
		 *        }
		 */
		return null;
	}

}// class CounterLocalGame
