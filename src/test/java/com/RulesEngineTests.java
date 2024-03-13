package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RulesEngineTests {

    @ParameterizedTest
    @MethodSource("inputChoice")
    void testRulesEngine(GameInputChoice input1, GameInputChoice input2, GameInputChoice expectedWinningChoice) {
        RulesEngineInterface rulesEngine = new RulesEngineRockPaperScissors();

        try {
            GameInputChoice winningChoice = rulesEngine.applyRule(input1, input2);
            assertEquals(expectedWinningChoice, winningChoice);
        } catch (GameRulesException e) {
            fail("Apply rule.");
        }
    }

    private static Stream<Arguments> inputChoice() {
        return Stream.of(
            Arguments.of(GameInputChoice.ROCK, GameInputChoice.PAPER, GameInputChoice.PAPER),
            Arguments.of(GameInputChoice.ROCK, GameInputChoice.SCISSORS, GameInputChoice.ROCK),
            Arguments.of(GameInputChoice.SCISSORS, GameInputChoice.PAPER, GameInputChoice.SCISSORS)
        );
    }
}
