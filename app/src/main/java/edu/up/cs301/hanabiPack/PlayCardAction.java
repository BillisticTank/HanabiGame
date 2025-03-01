package edu.up.cs301.hanabiPack;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class PlayCardAction extends GameAction
{

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     *
     * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
     * @version February 2025
     */
    public PlayCardAction(GamePlayer player) {
        super(player);
    }
}
