package nl.hartwig.actin.enums;

import io.micrometer.common.util.StringUtils;
import nl.hartwig.actin.exception.InvalidMoveException;

public enum PrsMove {

    PAPER,
    ROCK,
    SCISSORS;

    public static PrsMove register(final String move) {
        if (StringUtils.isBlank(move)) {
            throw new InvalidMoveException("move cannot be blank");
        }
        return switch (move.trim().toUpperCase()) {
            case "PAPER", "P" -> PAPER;
            case "ROCK", "R" -> ROCK;
            case "SCISSORS", "S" -> SCISSORS;
            default -> throw new InvalidMoveException("Invalid move: " + move);
        };
    }

}
