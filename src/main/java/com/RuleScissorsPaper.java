package com;

public class RuleScissorsPaper implements RuleInterface {
    public GameInputChoice playRule() {
        // Scissors winds Paper
        return GameInputChoice.SCISSORS;
    }
}
