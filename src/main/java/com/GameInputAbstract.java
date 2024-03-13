package com;

import java.io.InputStream;

/**
 * The abstract class that represents a game input source, the derived class can for example get input
 * from the commandline interface, or from a computer via random generation of the input.
 */
public abstract class GameInputAbstract {

    InputStream sourceInput = null;

    GameInputAbstract(InputStream sourceInput) {
        this.sourceInput = sourceInput;
    }

    /**
     * To request the input of a a game choice.
     * @return the GameInputChoice enum value of the choice that was entered.
     */
    abstract GameInputChoice requestGameChoiceInput();

    /**
     * To request a string input. The request that asks the user for the input msut be supplied.
     * 
     * @param request   the request printed to request the user's input.
     * @return          the string that was entered.
     */
    abstract String requestString(String request);
}
