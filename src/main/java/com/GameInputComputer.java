package com;

import java.io.IOException;
import java.io.InputStream;

public class GameInputComputer extends GameInputAbstract {

    GameInputComputer(InputStream sourceInput) {
        super(sourceInput);
    }

    public GameInputChoice requestGameChoiceInput() {

        int inputInt;
        try {
            inputInt = sourceInput.read();
        } catch (IOException e) {
            e.printStackTrace();
            return GameInputChoice.NONE;
        }

        GameInputChoice input = GameInputChoice.NONE;
        char inputChar = (char)inputInt;
        switch (inputChar) {
            case 'r': input = GameInputChoice.ROCK; break;
            case 'p': input = GameInputChoice.PAPER; break;
            case 's': input = GameInputChoice.SCISSORS; break;
        }

        return input;
    }

    /**
     * To request a string input. The request string is not used here, it can be a blank string.
     * 
     * @param request   the request printed to request the user's input.
     * @return          the string that was entered.
     */
    public String requestString(String request) {
        return "R2D2";
    }

}
