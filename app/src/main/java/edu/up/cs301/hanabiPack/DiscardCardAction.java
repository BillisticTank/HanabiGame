package edu.up.cs301.hanabiPack;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class DiscardCardAction extends GameAction
{

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public DiscardCardAction(GamePlayer player) {
        super(player);
    }
}
