package edu.up.cs301.hanabiPack;

import java.util.Random;

import edu.up.cs301.GameFramework.players.GameComputerPlayer;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.GameFramework.utilities.Tickable;

/**
 * A computer-version of a counter-player.  Since this is such a simple game,
 * it just sends "+" and "-" commands with equal probability, at an average
 * rate of one per second. 
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
 * @version February 2025
 */
public class HanabiComputerPlayer1 extends GameComputerPlayer implements Tickable {
	
    /**
     * Constructor for objects of class CounterComputerPlayer1
     * 
     * @param name
     * 		the player's name
     */
    public HanabiComputerPlayer1(String name) {
        // invoke superclass constructor
        super(name);
        
        // start the timer, ticking 20 times per second
        getTimer().setInterval(50);
        getTimer().start();
    }
    
    /**
     * callback method--game's state has changed
     * 
     * @param info
     * 		the information (presumably containing the game's state)
     */
	@Override
	protected void receiveInfo(GameInfo info) {
        //deciding factors
        Random rand = new Random();

        Boolean isColor = rand.nextBoolean();
        int cardIndex = rand.nextInt(5);
        int receiverId = rand.nextInt(3);

        while (receiverId == this.playerNum) {
            receiverId = rand.nextInt(3);
        }

		// Make sure that 'info' is a HanabiGameState
        if (!(info instanceof HanabiState)) {
            return;
        }

        HanabiState state = ((HanabiState) info);

        // See if it's "my" turn
        if (state.getPlayer_Id() != this.playerNum) {
            return;
        }

        // Calculate what move to make


        //send the move to the local game

            double decide = Math.random();

            if (decide < 0.3) {
                GiveHintAction hint = new GiveHintAction(this, isColor, receiverId, cardIndex);
                this.sleep(1000);
                this.game.sendAction(hint);
            }

            else if (decide > 0.3 && decide < 0.6) {
                DiscardCardAction discard = new DiscardCardAction(this, cardIndex);
                this.sleep(1000);
                this.game.sendAction(discard);
            }

            else {
                PlayCardAction play = new PlayCardAction(this, cardIndex);
                this.sleep(1000);
                this.game.sendAction(play);
            }

	}

}
