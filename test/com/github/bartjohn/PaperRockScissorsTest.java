package com.github.bartjohn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PaperRockScissorsTest {
    private static final int PAPER = 0;
    private static final int ROCK = 1;
    private static final int SCISSORS = 2;

    @ParameterizedTest
    @MethodSource("selectionAndExpectedOutcomeProvider")
    public void shouldProduceCorrectOutcomeForExpectedInputs(int userSelection, int computerSelection,
                                                      PaperRockScissors.Outcome outcome) throws Exception {
        PaperRockScissors game = getGameWithComputerSelection(computerSelection);
        game.play(userSelection);

        Assertions.assertEquals((PaperRockScissors.Outcome.USER == outcome) ? 1 : 0,
                game.getGameHistory().getGamesWon());
        Assertions.assertEquals((PaperRockScissors.Outcome.COMPUTER == outcome) ? 1 : 0,
                game.getGameHistory().getGamesLost());
        Assertions.assertEquals((PaperRockScissors.Outcome.TIE == outcome) ? 1 : 0, game.getGameHistory().getTies());
    }

    public static Stream<Arguments> selectionAndExpectedOutcomeProvider() {
        return Stream.of(
            arguments(PAPER, PAPER, PaperRockScissors.Outcome.TIE),
            arguments(PAPER, ROCK, PaperRockScissors.Outcome.USER),
            arguments(PAPER, SCISSORS, PaperRockScissors.Outcome.COMPUTER),
            arguments(ROCK, PAPER, PaperRockScissors.Outcome.COMPUTER),
            arguments(ROCK, ROCK, PaperRockScissors.Outcome.TIE),
            arguments(ROCK, SCISSORS, PaperRockScissors.Outcome.USER),
            arguments(SCISSORS, PAPER, PaperRockScissors.Outcome.USER),
            arguments(SCISSORS, ROCK, PaperRockScissors.Outcome.COMPUTER),
            arguments(SCISSORS, SCISSORS, PaperRockScissors.Outcome.TIE)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 3 })
    public void shouldThrowValidationExceptionForInvalidUserSelection(int userSelection) {
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> {
            PaperRockScissors game = getGameWithComputerSelection(PAPER);
            game.play(userSelection);
        });
        Assertions.assertEquals("Invalid selection: selections must be between 0 and 2", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 3 })
    public void shouldThrowValidationExceptionForInvalidComputerSelection(int computerSelection) {
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> {
            PaperRockScissors game = getGameWithComputerSelection(computerSelection);
            game.play(PAPER);
        });
        Assertions.assertEquals("Invalid selection: selections must be between 0 and 2", exception.getMessage());
    }

    @Test
    public void shouldKeepHistoryForMultipleGames() throws Exception {
        PaperRockScissors game = getGameWithComputerSelection(PAPER);
        int numWins = 10;
        int numLosses = 8;
        int numTies = 12;
        playGamesWithSelection(game, SCISSORS, numWins);
        playGamesWithSelection(game, ROCK, numLosses);
        playGamesWithSelection(game, PAPER, numTies);

        Assertions.assertEquals(numWins, game.getGameHistory().getGamesWon());
        Assertions.assertEquals(numLosses, game.getGameHistory().getGamesLost());
        Assertions.assertEquals(numTies, game.getGameHistory().getTies());
    }

    private void playGamesWithSelection(PaperRockScissors game, int userSelection, int numberOfTimes) throws Exception {
        for (int i = 0; i < numberOfTimes; i++) {
            game.play(userSelection);
        }
    }

    private PaperRockScissors getGameWithComputerSelection(int computerSelection) {
        return new PaperRockScissors(new ComputerPlayer(new ConstantStrategy(computerSelection)));
    }
}
