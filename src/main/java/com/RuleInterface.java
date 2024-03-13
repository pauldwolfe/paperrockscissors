package com;

/**
 * Interface representing a game rule.
 * The rule represents a game input of 2 choices and the rule that compares the 2 choices to find the winning input.
 */
public interface RuleInterface {
    /**
     * Applies the rule and returns the winning GameInput.
     * 
     * @return  returns the winning input
     */
    GameInputChoice playRule();
}
