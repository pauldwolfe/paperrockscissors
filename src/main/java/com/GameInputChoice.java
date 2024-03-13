package com;

/**
 * Enumerator that represent the 3 choices that can be made in this game.
 */
public enum GameInputChoice {
    NONE,
    ROCK,
    PAPER,
    SCISSORS;

    /**
     * Convert the user's input character to the correpsonding GameInputChoice enum value.
     * @param inputChar the character the user entered.
     * @return          the GameInputChoice enum value the represents the character that was entered.
     */
    public static GameInputChoice convertCharToGameInputChoice(char inputChar) {
        GameInputChoice input = GameInputChoice.NONE;
        switch (inputChar) {
            case 'r': input = GameInputChoice.ROCK; break;
            case 's': input = GameInputChoice.SCISSORS; break;
            case 'p': input = GameInputChoice.PAPER; break;
            case 'x': input = GameInputChoice.NONE; break;
        }
        return input;
    }

    /**
     * Creates a concatenated string of 2 game choice inputs in the pattern "<GameInputChoice1>-<GameInputChoice2>""
     * for example: ROCK-SCISSORS
     * 
     * @param input1    the first GameInputChoice
     * @param input2    the second GameInputChoice
     * @return          the two game choice enum values concatenated with a hyphen; e.g. ROCK-SCISSORS
     */
    public static String TwoChoicesToString(GameInputChoice input1, GameInputChoice input2) {
        return input1 + "-" + input2;
    }
}
