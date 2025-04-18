package edu.up.cs301.hanabiPack;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class GiveHintAction extends GameAction {
    public boolean _isColor;
    public int _reciverId;
    public int _aboutCard; //the card you pick.

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
     * @version February 2025
     */
    public GiveHintAction(GamePlayer player, boolean isColor,
                          int reciverId, int aboutCard) {
        super(player);
        _isColor = isColor;
        _reciverId = reciverId;
        _aboutCard = aboutCard;

    }
}
