package edu.up.cs301.hanabiPack;

import java.io.Serializable;
import java.util.ArrayList;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class PlayCardAction extends GameAction implements Serializable {

    public int _cardIndex;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
     * @version February 2025 l.
     */
    public PlayCardAction(GamePlayer player, int cardIndex) {
        super(player);
        this._cardIndex = cardIndex;
    }
}
