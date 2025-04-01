package edu.up.cs301.hanabiPack;

import java.util.ArrayList;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class DiscardCardAction extends GameAction
{

    Card _selection;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     *
     * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
     * @version February 2025 l.
     */
    public DiscardCardAction(GamePlayer player, Card selection)
    {
        super(player);
        _selection = selection;
    }
}
