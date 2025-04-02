package edu.up.cs301.hanabiPack;

import java.util.ArrayList;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class PlayCardAction extends GameAction
{
    //TODO fix it so this checks the Card ID of your selected card

    Card _cardId;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     *
     * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
     * @version February 2025 l.
     */
    public PlayCardAction(GamePlayer player, Card cardId)
    {
        super(player);
        _cardId = cardId;
    }
}
