package edu.up.cs301.hanabiPack;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class GiveHintAction extends GameAction
{
    //TODO:  Add instance variables here to specify:  which, which type of hint (num or color), and the hint value itself
    public int _color;
    public int _num;
    public int _numOfTokens;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
     * @version February 2025
     */
    public GiveHintAction(GamePlayer player, int color, int num, int numOfTokens)
    {
        super(player);
        _color = color;
        _num = num;
        _numOfTokens = numOfTokens;
    }
}
