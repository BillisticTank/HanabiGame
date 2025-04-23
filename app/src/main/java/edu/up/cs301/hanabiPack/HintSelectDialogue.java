package edu.up.cs301.hanabiPack;

import static android.app.PendingIntent.getActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import edu.up.cs301.GameFramework.Game;


/**
 * This class should create a popup box that prompts the user to tell the other players
 * about their card's numbers or colors.
 */

/**
 *
 */

public class HintSelectDialogue extends DialogFragment implements Runnable {

    //Boolean flag that gets set to true after the user answers
    private boolean answer = false;

    public boolean getAnswer() {
        return answer;
    }

    GiveHintAction hint = null;
    //this is the incomplete hint that we're going to send to the game
    Game game = null;
    //game variable that we send hint to.
    AppCompatActivity myActivity = null;

    public HintSelectDialogue(GiveHintAction hint, Game game, AppCompatActivity myActivity) {
        this.hint = hint;
        this.game = game;
        this.myActivity = myActivity;
    }

    /**
     * create an Alert dialog box
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to give a hint about the color or number of a Card")
                .setPositiveButton("Color", new DialogInterface.OnClickListener() {
                    //modify the hint to match the user's choice
                    public void onClick(DialogInterface dialog, int id) {
                        hint._byColor = true;
                        answer = true;
                    }
                })
                .setNegativeButton("Number", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        hint._byColor = false;
                        answer = true;
                    }
                });
        // Create the AlertDialog object and return it.
        return builder.create();
    }

    @Override
    public void run() {
        this.show(myActivity.getSupportFragmentManager(), "GAME_DIALOG");

        while (!(answer)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                //nothing;
            }
        }
        game.sendAction(hint);
    }
}
