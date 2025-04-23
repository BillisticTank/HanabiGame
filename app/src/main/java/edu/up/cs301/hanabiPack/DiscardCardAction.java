package edu.up.cs301.hanabiPack;

import java.io.Serializable;
import java.util.ArrayList;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class DiscardCardAction extends GameAction implements Serializable {

    public int _cardIndex;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
     * @version February 2025 l.
     */
    public DiscardCardAction(GamePlayer player, int cardIndex) {
        super(player);
        _cardIndex = cardIndex;
    }
}
