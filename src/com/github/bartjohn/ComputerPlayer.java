package com.github.bartjohn;

public class ComputerPlayer {
    Strategy strategy;

    public ComputerPlayer(Strategy strategy) {
        this.strategy = strategy;
    }

    public int makeSelection() {
        return strategy.execute();
    }
}
