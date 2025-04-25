package edu.up.cs301.hanabiPack;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;

import android.util.Log;

import java.io.Serializable;

/**
 * A class that represents the local game. In Hanabi, knowing whose turn it is, which actions
 * Are being taken, and if the game is over are important details to keep track of.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
 * @version April 2025
 */
public class HanabiLocalGame extends LocalGame implements Serializable {

    public static final int TARGET_MAGNITUDE = 10;

    // the game's state
    HanabiState gameState;

    /**
     * default ctor that makes the game state
     */
    public HanabiLocalGame() {
        gameState = new HanabiState();
    }

    /**
     * can this player move
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if (playerIdx == gameState.getPlayer_Id()) {
            return true;
        }
        return false;
    }

    /**
     * This ctor should be called when a new game of Hanabi starts
     */
    public HanabiLocalGame(GameState state) {
        // initialize the game state, with the counter value starting at 0
        if (!(state instanceof HanabiState)) {
            state = new HanabiState();
        }
        this.gameState = (HanabiState) state;
        super.state = state;
    }

    /**
     * The different actions in Hanabi: GiveHint, PlayCard, and DiscardCard
     */
    @Override
    protected boolean makeMove(GameAction action) {
        Log.i("action", action.getClass().toString());

        //If the player's action is a GiveHintAction
        if (action instanceof GiveHintAction) {

            GiveHintAction mouth = (GiveHintAction) action;
            nextTurn();
            return gameState.makeGiveHintAction(mouth);
        }

        //If the player's action is a PlayCardAction
        if (action instanceof PlayCardAction) {
            PlayCardAction eyes = (PlayCardAction) action;
            nextTurn();
            return gameState.makePlayCardAction(eyes);
        }

        //If the player's action is a DiscardCardAction
        if (action instanceof DiscardCardAction) {
            DiscardCardAction ears = (DiscardCardAction) action;
            nextTurn();
            return gameState.makeDiscardCardAction(ears);
        }
        return false;
    }//makeMove

    /**
     * Progresses the turn to the next person
     */
    protected void nextTurn() {
        //Player 0's turn ends
        if (gameState.getPlayer_Id() == 0) {
            gameState.setPlayer_Id(1);
        }

        //Player 1's turn ends
        else if (gameState.getPlayer_Id() == 1) {
            gameState.setPlayer_Id(2);
        }

        //Player 2's turn ends
        else if (gameState.getPlayer_Id() == 2) {
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
     * @return a message that tells who has won the game, or null if the
     * game is not over
     */
    @Override
    protected String checkIfGameOver() {

        if (gameState.getFuseTokens() < 0) {
            return "The bomb blew up! Game Over!\n";
        }

        if (gameState.drawPile.isEmpty()) {
            return "No more cards in the draw pile! Game Over!\n";
        }
        return null;
    }

}// class HanabiLocalGame
