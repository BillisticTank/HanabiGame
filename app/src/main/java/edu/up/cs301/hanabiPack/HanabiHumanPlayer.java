package edu.up.cs301.hanabiPack;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.GameFramework.players.GamePlayer;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Random;

/**
 * A GUI of a counter-player. The GUI displays the current value of the counter,
 * and allows the human player to press the '+' and '-' buttons in order to
 * send moves to the game.
 * <p>
 * Just for fun, the GUI is implemented so that if the player presses either button
 * when the counter-value is zero, the screen flashes briefly, with the flash-color
 * being dependent on whether the player is player 0 or player 1.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
 * @version February 2025
 */
public class HanabiHumanPlayer extends GameHumanPlayer implements OnClickListener, View.OnTouchListener {

    /* instance variables */
    //CHEATING MODE
    Boolean CHEATING = true;


    // The TextView the displays the current counter value
    private TextView testResultsTextView;

    // the most recent game state, as given to us by the HanabiLocalGame
    private HanabiState state = new HanabiState();

    // the android activity that we are running
    private GameMainActivity myActivity;

    //references to GUI elements we tinker with during play
    Button hintButton;
    Button playCardButton;
    Button discardButton;

    ImageView[] teammateCards = new ImageView[10];

    ImageView[] yourCards = new ImageView[5];

    //A mostly transparent yellow
    int transYellow = 0x44FFFF00;

    //Currently selected teammate's card
    private int selectedTeammateCard = 0;
    //Currently selected card in your deck
    private int selectedYourCard = 0;

    /**
     * constructor
     *
     * @param name the player's name
     */
    public HanabiHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.main); //hanabi_main);

    }

    public void OnResume() {
        updateDisplay();
    }

    /**
     * It updates the whole display of the game based on the current state.
     */

    protected void updateDisplay() {

        setAsGui(myActivity);


        //set the hint button to reflect the most recent hint
        TextView hintView = myActivity.findViewById(R.id.hints);
        hintView.setText("Hints: " + state.getTotalHints());

        TextView deckView = myActivity.findViewById(R.id.cardsDeck);
        deckView.setText("Cards Left in Deck: " + state.getTotalCardsInDeck());

        TextView fuseView = myActivity.findViewById(R.id.fuseTokens);
        fuseView.setText("Fuse Tokens: " + state.getFuseTokens());

        //Player Cards
        PlayerCards();

        //TeammatesCards
        TeammateCards();

        //Update the currently selected teammate card
        for (int i = 0; i < teammateCards.length; ++i) {
            teammateCards[i].setColorFilter(0);  //fully transparent
        }
        teammateCards[selectedTeammateCard].setColorFilter(transYellow);

        //Update the currently selected card in your deck
        for (int i = 0; i < yourCards.length; ++i) {
            yourCards[i].setColorFilter(0);  //fully transparent
        }
        yourCards[selectedYourCard].setColorFilter(transYellow);


        //Yellow SubShow Cards has been played, ...
        ArrayList<Card> yellowSubShow = state.fireworkShow.get(HanabiState.YELLOW);
        ImageView yellowSubShowCard1 = myActivity.findViewById(R.id.yellow1);
        ImageView yellowSubShowCard2 = myActivity.findViewById(R.id.yellow2);
        ImageView yellowSubShowCard3 = myActivity.findViewById(R.id.yellow3);
        ImageView yellowSubShowCard4 = myActivity.findViewById(R.id.yellow4);
        ImageView yellowSubShowCard5 = myActivity.findViewById(R.id.yellow5);

        //Red SubShow Cards has been played, ...
        ArrayList<Card> redSubShow = state.fireworkShow.get(HanabiState.RED);
        ImageView redSubShowCard1 = myActivity.findViewById(R.id.red1);
        ImageView redSubShowCard2 = myActivity.findViewById(R.id.red2);
        ImageView redSubShowCard3 = myActivity.findViewById(R.id.red3);
        ImageView redSubShowCard4 = myActivity.findViewById(R.id.red4);
        ImageView redSubShowCard5 = myActivity.findViewById(R.id.red5);

        //White SubShow Cards has been played, ...
        ArrayList<Card> whiteSubShow = state.fireworkShow.get(HanabiState.WHITE);
        ImageView whiteSubShowCard1 = myActivity.findViewById(R.id.white1);
        ImageView whiteSubShowCard2 = myActivity.findViewById(R.id.white2);
        ImageView whiteSubShowCard3 = myActivity.findViewById(R.id.white3);
        ImageView whiteSubShowCard4 = myActivity.findViewById(R.id.white4);
        ImageView whiteSubShowCard5 = myActivity.findViewById(R.id.white5);

        //Blue SubShow Cards has been played, ...
        ArrayList<Card> blueSubShow = state.fireworkShow.get(HanabiState.BLUE);
        ImageView blueSubShowCard1 = myActivity.findViewById(R.id.blue1);
        ImageView blueSubShowCard2 = myActivity.findViewById(R.id.blue2);
        ImageView blueSubShowCard3 = myActivity.findViewById(R.id.blue3);
        ImageView blueSubShowCard4 = myActivity.findViewById(R.id.blue4);
        ImageView blueSubShowCard5 = myActivity.findViewById(R.id.blue5);

        //Green SubShow Cards has been played, ...
        ArrayList<Card> greenSubShow = state.fireworkShow.get(HanabiState.GREEN);
        ImageView greenSubShowCard1 = myActivity.findViewById(R.id.green1);
        ImageView greenSubShowCard2 = myActivity.findViewById(R.id.green2);
        ImageView greenSubShowCard3 = myActivity.findViewById(R.id.green3);
        ImageView greenSubShowCard4 = myActivity.findViewById(R.id.green4);
        ImageView greenSubShowCard5 = myActivity.findViewById(R.id.green5);


        // Updated Red Subshow Cards
        if (redSubShow.size() > 0) {
            redSubShowCard1.setImageResource(R.drawable.hanabi_red_1);
        }
        if (redSubShow.size() > 1) {
            redSubShowCard2.setImageResource(R.drawable.hanabi_red_2);
        }
        if (redSubShow.size() > 2) {
            redSubShowCard2.setImageResource(R.drawable.hanabi_red_3);
        }
        if (redSubShow.size() > 3) {
            redSubShowCard2.setImageResource(R.drawable.hanabi_red_4);
        }
        if (redSubShow.size() > 4) {
            redSubShowCard2.setImageResource(R.drawable.hanabi_red_5);
        }

        // Updated Blue Subshow Cards
        if (blueSubShow.size() > 0) {
            blueSubShowCard1.setImageResource(R.drawable.hanabi_blue_1);
        }
        if (blueSubShow.size() > 1) {
            blueSubShowCard2.setImageResource(R.drawable.hanabi_blue_2);
        }
        if (blueSubShow.size() > 2) {
            blueSubShowCard3.setImageResource(R.drawable.hanabi_blue_3);
        }
        if (blueSubShow.size() > 3) {
            blueSubShowCard4.setImageResource(R.drawable.hanabi_blue_4);
        }
        if (blueSubShow.size() > 4) {
            blueSubShowCard5.setImageResource(R.drawable.hanabi_blue_5);
        }

        // Updated White Subshow Cards
        if (whiteSubShow.size() > 0) {
            whiteSubShowCard1.setImageResource(R.drawable.hanabi_white_1);
        }
        if (whiteSubShow.size() > 1) {
            whiteSubShowCard2.setImageResource(R.drawable.hanabi_white_2);
        }
        if (whiteSubShow.size() > 2) {
            whiteSubShowCard3.setImageResource(R.drawable.hanabi_white_3);
        }
        if (whiteSubShow.size() > 3) {
            whiteSubShowCard4.setImageResource(R.drawable.hanabi_white_4);
        }
        if (whiteSubShow.size() > 4) {
            whiteSubShowCard5.setImageResource(R.drawable.hanabi_white_5);
        }

        //Updated Green Subshow Cards
        if (greenSubShow.size() > 0) {
            greenSubShowCard1.setImageResource(R.drawable.hanabi_green_1);
        }
        if (greenSubShow.size() > 1) {
            greenSubShowCard2.setImageResource(R.drawable.hanabi_green_2);
        }
        if (greenSubShow.size() > 2) {
            greenSubShowCard3.setImageResource(R.drawable.hanabi_green_3);
        }
        if (greenSubShow.size() > 3) {
            greenSubShowCard4.setImageResource(R.drawable.hanabi_green_4);
        }
        if (greenSubShow.size() > 4) {
            greenSubShowCard5.setImageResource(R.drawable.hanabi_green_5);
        }

        //Updated Yellow Subshow Cards
        if (yellowSubShow.size() > 0) {
            yellowSubShowCard1.setImageResource(R.drawable.hanabi_yellow_1);
        }
        if (yellowSubShow.size() > 1) {
            yellowSubShowCard2.setImageResource(R.drawable.hanabi_yellow_2);
        }
        if (yellowSubShow.size() > 2) {
            yellowSubShowCard3.setImageResource(R.drawable.hanabi_yellow_3);
        }
        if (yellowSubShow.size() > 3) {
            yellowSubShowCard4.setImageResource(R.drawable.hanabi_yellow_4);
        }
        if (yellowSubShow.size() > 4) {
            yellowSubShowCard5.setImageResource(R.drawable.hanabi_yellow_5);
        }

        //Display the subshow cards BRWYG
        for (int color = 0; color < state.color.length; ++color) {
            blueSubShow = state.fireworkShow.get(color);
            redSubShow = state.fireworkShow.get(color);
            whiteSubShow = state.fireworkShow.get(color);
            yellowSubShow = state.fireworkShow.get(color);
            greenSubShow = state.fireworkShow.get(color);

            //Red Subshow
            switch (redSubShow.size()) {
                case 1:
                    redSubShowCard1.setImageResource(R.drawable.hanabi_red_1);
                    break;
                case 2:
                    redSubShowCard1.setImageResource(R.drawable.hanabi_red_2);
                    break;
                case 3:
                    redSubShowCard1.setImageResource(R.drawable.hanabi_red_3);
                    break;
                case 4:
                    redSubShowCard1.setImageResource(R.drawable.hanabi_red_4);
                    break;
                case 5:
                    redSubShowCard1.setImageResource(R.drawable.hanabi_red_5);
                    break;
                default:
                    redSubShowCard1.setImageResource(R.drawable.hanabi_red);
                    break;
            }

            //Blue Subshow
            switch (blueSubShow.size()) {
                case 1:
                    blueSubShowCard1.setImageResource(R.drawable.hanabi_blue_1);
                    break;
                case 2:
                    blueSubShowCard2.setImageResource(R.drawable.hanabi_blue_2);
                    break;
                case 3:
                    blueSubShowCard3.setImageResource(R.drawable.hanabi_blue_3);
                    break;
                case 4:
                    blueSubShowCard4.setImageResource(R.drawable.hanabi_blue_4);
                    break;
                case 5:
                    blueSubShowCard5.setImageResource(R.drawable.hanabi_blue_5);
                    break;
                default:
                    blueSubShowCard1.setImageResource(R.drawable.hanabi_blue);
                    break;
            }

            //White Subshow
            switch (whiteSubShow.size()) {
                case 1:
                    whiteSubShowCard1.setImageResource(R.drawable.hanabi_white_1);
                    break;
                case 2:
                    whiteSubShowCard2.setImageResource(R.drawable.hanabi_white_2);
                    break;
                case 3:
                    whiteSubShowCard3.setImageResource(R.drawable.hanabi_white_3);
                    break;
                case 4:
                    whiteSubShowCard4.setImageResource(R.drawable.hanabi_white_4);
                    break;
                case 5:
                    whiteSubShowCard5.setImageResource(R.drawable.hanabi_white_5);
                    break;
                default:
                    whiteSubShowCard1.setImageResource(R.drawable.hanabi_white);
                    break;
            }

            //Yellow Subshow
            switch (yellowSubShow.size()) {
                case 1:
                    whiteSubShowCard1.setImageResource(R.drawable.hanabi_yellow_1);
                    break;
                case 2:
                    whiteSubShowCard2.setImageResource(R.drawable.hanabi_yellow_2);
                    break;
                case 3:
                    whiteSubShowCard3.setImageResource(R.drawable.hanabi_yellow_3);
                    break;
                case 4:
                    whiteSubShowCard4.setImageResource(R.drawable.hanabi_yellow_4);
                    break;
                case 5:
                    whiteSubShowCard5.setImageResource(R.drawable.hanabi_yellow_5);
                    break;
                default:
                    whiteSubShowCard1.setImageResource(R.drawable.hanabi_yellow);
                    break;
            }

            //Green Subshow
            switch (greenSubShow.size()) {
                case 1:
                    greenSubShowCard1.setImageResource(R.drawable.hanabi_green_1);
                    break;
                case 2:
                    greenSubShowCard2.setImageResource(R.drawable.hanabi_green_2);
                    break;
                case 3:
                    greenSubShowCard3.setImageResource(R.drawable.hanabi_green_3);
                    break;
                case 4:
                    greenSubShowCard4.setImageResource(R.drawable.hanabi_green_4);
                    break;
                case 5:
                    greenSubShowCard5.setImageResource(R.drawable.hanabi_green_5);
                    break;
                default:
                    greenSubShowCard1.setImageResource(R.drawable.hanabi_green);
                    break;
            }
        }


        LinearLayout mainLL = myActivity.findViewById(R.id.main);
        mainLL.invalidate();
    }


    /**
     * this method gets called when the user clicks the '+' or '-' button. It
     * creates a new CounterMoveAction to return to the parent activity.
     *
     * @param button the button that was clicked
     */
    public void onClick(View button) {
        //Announces every single move made by players in the game
        TextView announcer = myActivity.findViewById(R.id.announcer);

        // if we are not yet connected to a game, ignore
        if (game == null) return;

        if (state.getPlayer_Id() == 0)
            //handle the hint button
            if (button == hintButton) {

                //send a hint action to the local game based on color
                //create a hint action with a temporary value for the byColor boolean
                GiveHintAction colorHint = new GiveHintAction(this,
                        false, state.getPlayer_Id(), selectedTeammateCard);

                if (state.getTotalHints() < 0 || state.getTotalHints() == 0) {

                    state.setTotalHints(0);
                    announcer.setText("Sorry, you have zero hints left.");
                } else if (state.getTotalCardsInDeck() == 0) {
                    announcer.setText("GAME OVER! All the cards have been played.");
                } //GAME OVER

                else {
                    announcer.setText("Player" + state.getPlayer_Id() + " gave a hint about "
                            + colorHint._reciverId + "'s Card");
                }

                //launch a thread to ask the user what type of hint this is
                //then send the thread to game

                HintSelectDialogue hint = new HintSelectDialogue(colorHint, game, myActivity);
                Thread hintThread = new Thread(hint);
                hintThread.start();


                int newHints = state.getTotalHints() - 1;
                state.setTotalHints(newHints);

                // next turn
                //state.setPlayer_Id(state.getPlayer_Id() + 1);
            } else if (button == discardButton) {
                if (state.getPlayer_Id() == 0) {


                    DiscardCardAction discardCard = new DiscardCardAction(this, selectedYourCard);

                    //decrease the card in a player's deck by one
                    int cardsLeft = state.getTotalCardsInDeck() - 1;
                    state.setTotalCardsInDeck(cardsLeft);

                    if (state.getTotalHints() < 8) {
                        int newHints = state.getTotalHints() + 1;
                        state.setTotalHints(newHints);
                    }

                    //Checks if Game Over conditions are met
                    if (state.getTotalCardsInDeck() < 0 || state.getTotalCardsInDeck() == 0) {

                        state.setTotalCardsInDeck(0);
                        announcer.setText("GAME OVER! All the cards have been played.");
                        myActivity.setGameOver(true);
                    } // if the hints are negative.

                    else {
                        announcer.setText("Player" + state.getPlayer_Id() + " discarded a Card.");
                    }
                    game.sendAction(discardCard);
                    // next turn
                    state.setPlayer_Id(state.getPlayer_Id() + 1);
                }
                else
                {
                    announcer.setText("Hey player 0, its not your turn!");
                }

            } else if (button == playCardButton) {
                if (state.getPlayer_Id() == 0) {
                    PlayCardAction playCard = new PlayCardAction(this, selectedYourCard);

                    int newFuse = state.getFuseTokens() - 1;
                    state.setFuseTokens(newFuse);
                    announcer.setText("Player Played the Wrong Card!");

                    //Checks if Game Over conditions are met
                    if (state.getFuseTokens() < 0 || state.getFuseTokens() == 0) {
                        state.setFuseTokens(0);
                        announcer.setText("GAME OVER! The Bomb Blew Up!");
                        myActivity.setGameOver(true);
                    }

                    game.sendAction(playCard);
                    // next turn
                    state.setPlayer_Id((state.getPlayer_Id() + 1));
                }
                else
                {
                    announcer.setText("Hey player 0, its not your turn!");
                }
            }
        updateDisplay();

    }// onClick

    /**
     * when the user touches a card, mark that card as selected
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        for (int i = 0; i < teammateCards.length; ++i) {
            if (v == teammateCards[i]) {
                this.selectedTeammateCard = i;
                updateDisplay();
                return true;
            }
        }


        for (int i = 0; i < yourCards.length; ++i) {
            if (v == yourCards[i]) {
                this.selectedYourCard = i;
                updateDisplay();
                return true;
            }
        }
        return false;
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        // ignore the message if it's not a CounterState message
        if (!(info instanceof HanabiState)) return;

        // update our state; then update the display
        this.state = (HanabiState) info;
        updateDisplay();


    }

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        this.myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.hanabi_human_player);


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //PlayerCards();
        //TeammateCards();


        //updateDisplay();
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Setting Listeners for Buttons;
        hintButton = activity.findViewById(R.id.giveHintButton);
        hintButton.setOnClickListener(this);
        playCardButton = activity.findViewById(R.id.playCardButton);
        playCardButton.setOnClickListener(this);
        discardButton = activity.findViewById(R.id.discardButton);
        discardButton.setOnClickListener(this);

        //init the references to the teammates' cards
        teammateCards[0] = myActivity.findViewById(R.id.GPT1);
        teammateCards[1] = myActivity.findViewById(R.id.GPT2);
        teammateCards[2] = myActivity.findViewById(R.id.GPT3);
        teammateCards[3] = myActivity.findViewById(R.id.GPT4);
        teammateCards[4] = myActivity.findViewById(R.id.GPT5);
        teammateCards[5] = myActivity.findViewById(R.id.Gemini1);
        teammateCards[6] = myActivity.findViewById(R.id.Gemini2);
        teammateCards[7] = myActivity.findViewById(R.id.Gemini3);
        teammateCards[8] = myActivity.findViewById(R.id.Gemini4);
        teammateCards[9] = myActivity.findViewById(R.id.Gemini5);

        //Make myself the touch listener for all the cards
        for (int i = 0; i < teammateCards.length; ++i) {
            teammateCards[i].setOnTouchListener(this);
        }

        //init the references to the teammates' cards
        yourCards[0] = myActivity.findViewById(R.id.playerCard1);
        yourCards[1] = myActivity.findViewById(R.id.playerCard2);
        yourCards[2] = myActivity.findViewById(R.id.playerCard3);
        yourCards[3] = myActivity.findViewById(R.id.playerCard4);
        yourCards[4] = myActivity.findViewById(R.id.playerCard5);

        for (int i = 0; i < yourCards.length; ++i) {
            yourCards[i].setOnTouchListener(this);
        }

    }

    public void PlayerCards() {


        if (CHEATING) {
            for (int i = 0; i < yourCards.length; i++) {
                int color = state.getCardsInHand(state.getPlayer_Id())[i]._color;
                int value = state.getCardsInHand(state.getPlayer_Id())[i]._number + 1;

                if (color == HanabiState.BLUE) { //blue color
                    if (value == 1) {
                        yourCards[i].setImageResource(R.drawable.hanabi_blue_1);
                    } else if (value == 2) {
                        yourCards[i].setImageResource(R.drawable.hanabi_blue_2);
                    } else if (value == 3) {
                        yourCards[i].setImageResource(R.drawable.hanabi_blue_3);
                    } else if (value == 4) {
                        yourCards[i].setImageResource(R.drawable.hanabi_blue_4);
                    } else if (value == 5) {
                        yourCards[i].setImageResource(R.drawable.hanabi_blue_5);
                    }
                } else if (color == HanabiState.RED) { //red color
                    if (value == 1) {
                        yourCards[i].setImageResource(R.drawable.hanabi_red_1);
                    } else if (value == 2) {
                        yourCards[i].setImageResource(R.drawable.hanabi_red_2);
                    } else if (value == 3) {
                        yourCards[i].setImageResource(R.drawable.hanabi_red_3);
                    } else if (value == 4) {
                        yourCards[i].setImageResource(R.drawable.hanabi_red_4);
                    } else if (value == 5) {
                        yourCards[i].setImageResource(R.drawable.hanabi_red_5);
                    }
                } else if (color == HanabiState.YELLOW) { //yellow color
                    if (value == 1) {
                        yourCards[i].setImageResource(R.drawable.hanabi_yellow_1);
                    } else if (value == 2) {
                        yourCards[i].setImageResource(R.drawable.hanabi_yellow_2);
                    } else if (value == 3) {
                        yourCards[i].setImageResource(R.drawable.hanabi_yellow_3);
                    } else if (value == 4) {
                        yourCards[i].setImageResource(R.drawable.hanabi_yellow_4);
                    } else if (value == 5) {
                        yourCards[i].setImageResource(R.drawable.hanabi_yellow_5);
                    }
                } else if (color == HanabiState.WHITE) { //white color
                    if (value == 1) {
                        yourCards[i].setImageResource(R.drawable.hanabi_white_1);
                    } else if (value == 2) {
                        yourCards[i].setImageResource(R.drawable.hanabi_white_2);
                    } else if (value == 3) {
                        yourCards[i].setImageResource(R.drawable.hanabi_white_3);
                    } else if (value == 4) {
                        yourCards[i].setImageResource(R.drawable.hanabi_white_4);
                    } else if (value == 5) {
                        yourCards[i].setImageResource(R.drawable.hanabi_white_5);
                    }
                } else if (color == HanabiState.GREEN) { //green color
                    if (value == 1) {
                        yourCards[i].setImageResource(R.drawable.hanabi_green_1);
                    } else if (value == 2) {
                        yourCards[i].setImageResource(R.drawable.hanabi_green_2);
                    } else if (value == 3) {
                        yourCards[i].setImageResource(R.drawable.hanabi_green_3);
                    } else if (value == 4) {
                        yourCards[i].setImageResource(R.drawable.hanabi_green_4);
                    } else if (value == 5) {
                        yourCards[i].setImageResource(R.drawable.hanabi_green_5);
                    }
                }

                yourCards[i].invalidate();
            }
        }


    }

    public void TeammateCards() {


        //Setting updated cards;

        for (int i = 0; i < teammateCards.length; i++) {
            int teammateId1 = (state.getPlayer_Id() + 1) % 3;
            int teammateId2 = (teammateId1 + 1) % 3;
            Card[] playerHand = state.getCardsInHand(teammateId1);

            if (i >= playerHand.length) {
                playerHand = state.getCardsInHand(teammateId2);
            }
            int color = playerHand[i % 5]._color;
            int value = playerHand[i % 5]._number;

            if (color == HanabiState.BLUE) { //Blue color
                if (value == 1) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_blue_1);
                } else if (value == 2) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_blue_2);
                } else if (value == 3) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_blue_3);
                } else if (value == 4) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_blue_4);
                } else if (value == 5) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_blue_5);
                }
            } else if (color == HanabiState.RED) { //Red color
                if (value == 1) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_red_1);
                } else if (value == 2) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_red_2);
                } else if (value == 3) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_red_3);
                } else if (value == 4) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_red_4);
                } else if (value == 5) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_red_5);
                }
            } else if (color == HanabiState.YELLOW) { //Yellow color
                if (value == 1) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_yellow_1);
                } else if (value == 2) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_yellow_2);
                } else if (value == 3) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_yellow_3);
                } else if (value == 4) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_yellow_4);
                } else if (value == 5) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_yellow_5);
                }
            } else if (color == HanabiState.WHITE) { //White color
                if (value == 1) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_white_1);
                } else if (value == 2) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_white_2);
                } else if (value == 3) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_white_3);
                } else if (value == 4) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_white_4);
                } else if (value == 5) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_white_5);
                }
            } else if (color == HanabiState.GREEN) { //Green color
                if (value == 1) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_green_1);
                } else if (value == 2) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_green_2);
                } else if (value == 3) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_green_3);
                } else if (value == 4) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_green_4);
                } else if (value == 5) {
                    teammateCards[i].setImageResource(R.drawable.hanabi_green_5);
                }
            }

        }
    }//TeammateCards

}// class HanabiHumanPlayer






