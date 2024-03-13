package com;

/**
 * Class that represents a move made by a player.
 */
public class PlayerMove {
    private Player player;
    private GameInputChoice gameInputChoice;

    PlayerMove(Player player, GameInputChoice gameInputChoice) {
        this.player = player;
        this.gameInputChoice = gameInputChoice;
    }

    public Player getPlayer() {
        return this.player;
    }

    public GameInputChoice getGameInputChoice() {
        return this.gameInputChoice;
    }
}
