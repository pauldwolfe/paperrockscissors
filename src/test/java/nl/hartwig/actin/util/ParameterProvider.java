package nl.hartwig.actin.util;

import nl.hartwig.actin.enums.PrsMove;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public final class ParameterProvider {

    public static Stream<Arguments> winningMovePairProvider() {
        return Stream.of(
                Arguments.of(PrsMove.ROCK, PrsMove.SCISSORS),
                Arguments.of(PrsMove.PAPER, PrsMove.ROCK),
                Arguments.of(PrsMove.SCISSORS, PrsMove.PAPER)
        );
    }

    public static Stream<Arguments> losingMovePairProvider() {
        return Stream.of(
                Arguments.of(PrsMove.ROCK, PrsMove.PAPER),
                Arguments.of(PrsMove.PAPER, PrsMove.SCISSORS),
                Arguments.of(PrsMove.SCISSORS, PrsMove.ROCK)
        );
    }

    public static Stream<Arguments> moveProvider() {
        return Stream.of(
                Arguments.of(PrsMove.ROCK),
                Arguments.of(PrsMove.PAPER),
                Arguments.of(PrsMove.SCISSORS)
        );
    }

    public static Stream<Arguments> notWinningMovePairProvider() {
        return Stream.of(
                Arguments.of(PrsMove.ROCK, PrsMove.PAPER),
                Arguments.of(PrsMove.ROCK, PrsMove.ROCK),
                Arguments.of(PrsMove.PAPER, PrsMove.SCISSORS),
                Arguments.of(PrsMove.PAPER, PrsMove.PAPER),
                Arguments.of(PrsMove.SCISSORS, PrsMove.ROCK),
                Arguments.of(PrsMove.SCISSORS, PrsMove.SCISSORS)
        );
    }

    public static Stream<Arguments> userInputMoveProvider() {
        return Stream.of(
                Arguments.of("paper", PrsMove.PAPER),
                Arguments.of("p", PrsMove.PAPER),
                Arguments.of("rock", PrsMove.ROCK),
                Arguments.of("r", PrsMove.ROCK),
                Arguments.of("scissors", PrsMove.SCISSORS),
                Arguments.of("s", PrsMove.SCISSORS)
        );
    }

    private ParameterProvider() {

    }
}
