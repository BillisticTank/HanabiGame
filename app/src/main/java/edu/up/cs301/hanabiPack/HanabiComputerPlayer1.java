package edu.up.cs301.hanabiPack;

import java.io.Serializable;
import java.util.Random;

import edu.up.cs301.GameFramework.players.GameComputerPlayer;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.GameFramework.utilities.Tickable;

/**
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
 * @version February 2025
 */
public class HanabiComputerPlayer1 extends GameComputerPlayer implements Tickable, Serializable {

    /**
     * Constructor for objects of class CounterComputerPlayer1
     *
     * @param name the player's name
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
     * @param info the information (presumably containing the game's state)
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
        double decide = Math.random();
        // See if it's "my" turn
            if (state.getPlayer_Id() != this.playerNum) {
                return;
            }
            else if (decide < 0.3) {
                GiveHintAction hint = new GiveHintAction(this, isColor, receiverId, cardIndex);
                this.sleep(10);
                this.game.sendAction(hint);


            } else if (decide > 0.3 && decide < 0.6) {
                DiscardCardAction discard = new DiscardCardAction(this, cardIndex);
                this.sleep(10);
                this.game.sendAction(discard);
                state.setPlayer_Id(state.getPlayer_Id() + 1);


            }
            else
            {
                PlayCardAction play = new PlayCardAction(this, cardIndex);
                this.sleep(10);
                this.game.sendAction(play);
                state.setPlayer_Id(state.getPlayer_Id() + 1);

            }


    }

}
