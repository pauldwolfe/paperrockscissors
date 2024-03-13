package com;

public interface RulesEngineInterface {

    /**
     * ApplyRule uses input1 and input2, applies the game rules, then returns the winning input.
     * 
     * @param input1 the input from player 1.
     * @param input2 the input from player 2.
     * @return       the winning input after the rule was applied.
     */
    GameInputChoice applyRule(GameInputChoice input1, GameInputChoice input2) throws GameRulesException;
}
