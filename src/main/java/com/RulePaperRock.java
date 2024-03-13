package com;

public class RulePaperRock implements RuleInterface {
    public GameInputChoice playRule() {
        // Paper wins Rock
        return GameInputChoice.PAPER;
    }
}
