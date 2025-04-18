package edu.up.cs301.hanabiPack;

/**
 * class Card
 *
 * represents a single card in a player's hand
 */
public class Card {
    public int _color;  //the color of the card
    public int _number; //the number of the card

    public String _cardUniqueId;

    public boolean _colorKnown = false;
    public boolean _numberKnown = false;

    public Card(int color, int number) {
        _color = color;
        _number = number;
    }

    public Card(Card that) {
        this._color = that._color;
        this._number = that._number;
    }

    @Override
    public String toString() {
        String numName = "" + _number;
        if (_color == HanabiState.BLUE) return "Blue " + numName;
        if (_color == HanabiState.RED) return "Red " + numName;
        if (_color == HanabiState.GREEN) return "Green " + numName;
        if (_color == HanabiState.YELLOW) return "Yellow " + numName;
        if (_color == HanabiState.WHITE) return "White " + numName;

        return "";
    }
}
