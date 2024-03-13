package com;

public class RuleRockScissors implements RuleInterface {
    public GameInputChoice playRule() {
        // Rock wins Scissors
        return GameInputChoice.ROCK;
    }
}
