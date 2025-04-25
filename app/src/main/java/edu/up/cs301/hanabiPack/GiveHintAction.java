package edu.up.cs301.hanabiPack;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;
/**
 * The setup class for the Give Hint Action
 *
 * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
 * @version April 2025
 */
public class GiveHintAction extends GameAction {
    public boolean _byColor; //The hint is defaulted to color for simplicity, but can be changed
    public int _reciverId; //Who is receiving your hint
    public int _aboutCard; //the card you pick.
    public GiveHintAction(GamePlayer player, boolean isColor,
                          int reciverId, int aboutCard) {
        super(player);
        _byColor = isColor;
        _reciverId = reciverId;
        _aboutCard = aboutCard;

    }
}
