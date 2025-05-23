package edu.up.cs301.hanabiPack;

import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;


/**
 * Not currently being used, will update if it is implemented!
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Derric Smith, Alexander Leah, Hassin Niazy, Carter Chan
 * @version April 2025
 */
public class HanabiComputerPlayer2 extends HanabiComputerPlayer1 implements Serializable {

    /*
     * instance variables
     */

    // the most recent game state, as given to us by the CounterLocalGame
    private HanabiState currentGameState = null;

    // If this player is running the GUI, the activity (null if the player is
    // not running a GUI).
    private Activity activityForGui = null;

    // If this player is running the GUI, the widget containing the counter's
    // value (otherwise, null);
    private final TextView counterValueTextView = null;

    // If this player is running the GUI, the handler for the GUI thread (otherwise
    // null)
    private Handler guiHandler = null;

    /**
     * constructor
     *
     * @param name the player's name
     */
    public HanabiComputerPlayer2(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // perform superclass behavior
        super.receiveInfo(info);

        Log.i("computer player", "receiving");

        // if there is no game, ignore
        if (game == null) {
        } else if (info instanceof HanabiState) {
            // if we indeed have a counter-state, update the GUI
            currentGameState = (HanabiState) info;
            updateDisplay();
        }
    }


    /**
     * sets the counter value in the text view
     */
    private void updateDisplay() {
        // if the guiHandler is available, set the new counter value
        // in the counter-display widget, doing it in the Activity's
        // thread.
        if (guiHandler != null) {
            guiHandler.post(
                    new Runnable() {
                        public void run() {
                            if (counterValueTextView != null && currentGameState != null) {
                                counterValueTextView.setText("" );
                            }
                        }
                    });
        }
    }

    /**
     * Tells whether we support a GUI
     *
     * @return true because we support a GUI
     */
    public boolean supportsGui() {
        return true;
    }

    /**
     * callback method--our player has been chosen/rechosen to be the GUI,
     * called from the GUI thread.
     *
     * @param a the activity under which we are running
     */
    @Override
    public void setAsGui(GameMainActivity a) {

        // remember who our activity is
        this.activityForGui = a;

        // remember the handler for the GUI thread
        this.guiHandler = new Handler();

        // Load the layout resource for the our GUI's configuration
        activityForGui.setContentView(R.layout.hanabi_human_player);

        // if the state is non=null, update the display
        if (currentGameState != null) {
            updateDisplay();
        }
    }

}
