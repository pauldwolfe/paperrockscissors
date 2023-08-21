package nl.hartwig.actin.rule;

import nl.hartwig.actin.enums.PrsMove;
import nl.hartwig.actin.util.WinnerPredicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class PrsGameRuleEngineTest {

    @InjectMocks
    private PrsGameRuleEngine ruleEngine;

    @Test
    @SuppressWarnings("unchecked")
    void ruleEngineCreated_verifyAllRulesExist() {
        Arrays.stream(PrsMove.values()).forEach(move -> Assertions.assertNotNull(ruleEngine.getRule(move)));
        Map<PrsMove, WinnerPredicate> winRules = (Map<PrsMove, WinnerPredicate>) ReflectionTestUtils.getField(ruleEngine, "winRules");
        Assertions.assertNotNull(winRules);
        Assertions.assertEquals(PrsMove.values().length, winRules.size());
    }

    @ParameterizedTest
    @MethodSource("nl.hartwig.actin.util.ParameterProvider#winningMovePairProvider")
    void getRule_whenWinningPrsMovePairProvided_thenMoveWins(final PrsMove winnerMove, final PrsMove loserMove) {
        Assertions.assertTrue(ruleEngine.getRule(winnerMove).test(loserMove));
    }

    @ParameterizedTest
    @MethodSource("nl.hartwig.actin.util.ParameterProvider#notWinningMovePairProvider")
    void getRule_whenNotWinningPrsMovePairProvided_thenMoveNotWins(final PrsMove notWinnerMove, final PrsMove winnerMove) {
        Assertions.assertFalse(ruleEngine.getRule(notWinnerMove).test(winnerMove));
    }

}
