package nl.hartwig.actin.service;


import nl.hartwig.actin.enums.PrsMove;
import nl.hartwig.actin.model.ComputerPlayer;
import nl.hartwig.actin.model.Player;
import nl.hartwig.actin.model.UserPlayer;
import nl.hartwig.actin.rule.GameRuleEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaperRockScissorsGameServiceTest {

    @InjectMocks
    private PaperRockScissorsGameService prsGame;
    @Mock
    private UserPlayer user;
    @Mock
    private ComputerPlayer computer;
    @Mock
    private GameRuleEngine ruleEngine;

    @BeforeEach
    void init() {
        when(user.getName()).thenReturn("mock-user-name");
        when(computer.getName()).thenReturn("mock-computer-name");
    }

    @ParameterizedTest
    @MethodSource("nl.hartwig.actin.util.ParameterProvider#winningMovePairProvider")
    void start_whenUserPlayerWins_verifyUserPlayerAsWinner(final PrsMove winnerMove, final PrsMove loserMove) {
        when(user.getMove()).thenReturn(winnerMove);
        when(computer.getMove()).thenReturn(loserMove);
        when(ruleEngine.getRule(Mockito.eq(winnerMove))).thenReturn((move) -> true);

        Optional<Player> winner = prsGame.start();

        Assertions.assertTrue(winner.isPresent());
        Assertions.assertEquals(user, winner.get());
    }

    @ParameterizedTest
    @MethodSource("nl.hartwig.actin.util.ParameterProvider#losingMovePairProvider")
    void start_whenUserPlayerLoses_verifyUserPlayerAsLoser(final PrsMove loserMove, final PrsMove winnerMove) {
        when(user.getMove()).thenReturn(loserMove);
        when(computer.getMove()).thenReturn(winnerMove);
        when(ruleEngine.getRule(Mockito.eq(loserMove))).thenReturn((move) -> false);
        when(ruleEngine.getRule(Mockito.eq(winnerMove))).thenReturn((move) -> true);

        Optional<Player> winner = prsGame.start();

        Assertions.assertTrue(winner.isPresent());
        Assertions.assertEquals(computer, winner.get());
    }

    @ParameterizedTest
    @MethodSource("nl.hartwig.actin.util.ParameterProvider#moveProvider")
    void start_whenRoundDraws_verifyDraw(final PrsMove drawMove) {
        when(user.getMove()).thenReturn(drawMove);
        when(computer.getMove()).thenReturn(drawMove);
        when(ruleEngine.getRule(Mockito.eq(drawMove)))
                .thenReturn((move) -> false)
                .thenReturn((move) -> false);

        Optional<Player> winner = prsGame.start();

        Assertions.assertFalse(winner.isPresent());
    }

}
