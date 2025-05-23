package edu.up.cs301.hanabiPack;

import java.io.Serializable;
import java.util.ArrayList;

import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.gameConfiguration.*;

/**
 * this is the primary activity for Hanabi
 *
 * @author Andrew M. Nuxoll
 * @author Steven R. Vegdahl
 * @author Derric Smith, Alexander Leah, Hassinullah Niazy, Carter Chan
 * @version April 2025
 */
public class HanabiMainActivity extends GameMainActivity implements Serializable {

    // the port number that this game will use when playing over the network
    private static final int PORT_NUMBER = 2234;

    /**
     * Create the default configuration for this game
     */
    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // a human player player type (player type 0)
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new HanabiHumanPlayer(name);
            }
        });

        // a computer player type (player type 1)
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new HanabiComputerPlayer1(name);
            }
        });

        // a computer player type (player type 2)
        playerTypes.add(new GamePlayerType("Computer Player (GUI)") {
            public GamePlayer createPlayer(String name) {
                return new HanabiComputerPlayer2(name);
            }
        });

        // Create a game configuration class for Hanabi:
        // - player types as given above
        // - 3 players
        // - name of game is "Hanabi Game"
        // - port number as defined above

        GameConfig defaultConfig = new GameConfig(playerTypes, 3, 3, "Hanabi",
                PORT_NUMBER);

        // Add the default players to the configuration
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Gemini", 1); // player 2: a computer player
        defaultConfig.addPlayer("ChatGPT", 2); // player 3: a computer player

        // Set the default remote-player setup:
        // - player name: "Remote Player"
        // - IP code: (empty string)
        // - default player type: human player
        defaultConfig.setRemoteData("Remote Player", "", 0);

        // return the configuration
        return defaultConfig;
    }//createDefaultConfig




    /**
     * create a local game
     *
     * @return the local game (Hanabi)
     */
    @Override
    public LocalGame createLocalGame(GameState state) {
        if (state == null) {
            state = new HanabiState();
        }
        return new HanabiLocalGame(state);

    }


}
