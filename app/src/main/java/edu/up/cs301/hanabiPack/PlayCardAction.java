package edu.up.cs301.hanabiPack;

import java.io.Serializable;
import java.util.ArrayList;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

/**
 * The setup class for the Play Card Action
 *
 * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
 * @version April 2025
 */
public class PlayCardAction extends GameAction implements Serializable {

    public int _cardIndex;
    public PlayCardAction(GamePlayer player, int cardIndex) {
        super(player);
        this._cardIndex = cardIndex;
    }
}
