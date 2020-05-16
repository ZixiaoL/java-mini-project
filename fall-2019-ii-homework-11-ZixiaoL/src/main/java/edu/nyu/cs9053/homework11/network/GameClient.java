package edu.nyu.cs9053.homework11.network;

import edu.nyu.cs9053.homework11.game.Difficulty;
import edu.nyu.cs9053.homework11.game.GameProvider;
import edu.nyu.cs9053.homework11.game.screen.InputMove;

import java.io.*;
import java.net.Socket;
/**
 * User: blangel
 *
 * A blocking IO implementation of a client which requests moves from a remote server implementing the
 * {@linkplain edu.nyu.cs9053.homework11.network.NetworkGameProvider}
 */
public class GameClient implements GameProvider {

    public static GameClient construct(Difficulty difficulty) {
        try {
            Socket socket = new Socket(GameServer.SERVER_HOST, GameServer.SERVER_PORT);
            socket.setKeepAlive(true);
            return new GameClient(difficulty, socket.getInputStream(), socket.getOutputStream());
        } catch (IOException ioe) {
            return null;
        }
    }

    private final Difficulty difficulty;
    private final InputStream serverInput;
    private final OutputStream serverOutput;

    public GameClient(Difficulty difficulty, InputStream serverInput, OutputStream serverOutput) {
        this.difficulty = difficulty;
        this.serverInput = serverInput;
        this.serverOutput = serverOutput;
    }

    @Override public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override public int getRandomNumberOfNextFoes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("foes ").append(getDifficulty());
        try {
            serverOutput.write(stringBuilder.toString().getBytes());
            serverOutput.flush();
            return Integer.valueOf(readFromServer());
        } catch (IOException ioe) {
            return -1;
        }
    }

    @Override public InputMove getRandomNextMove() {
        try {
            String move = "move";
            serverOutput.write(move.getBytes());
            serverOutput.flush();
            switch (readFromServer()) {
                case "Up":
                    return InputMove.Up;
                case "Down":
                    return InputMove.Down;
                case "Left":
                    return InputMove.Left;
                case "Right":
                    return InputMove.Right;
                default:
                    return null;
            }

        } catch (IOException ioe) {
            return null;
        }
    }

    private String readFromServer() throws IOException {
        int availableAmount = 0;
        while (availableAmount == 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(ie);
            }
            availableAmount = serverInput.available();
        }
        byte[] into = new byte[availableAmount];
        int read = serverInput.read(into);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < read; i++) {
            stringBuilder.append((char) into[i]);
        }
        return stringBuilder.toString();
    }

}
