package nl.hartwig.actin.model;

import nl.hartwig.actin.enums.PrsMove;
import nl.hartwig.actin.exception.ExitCommandException;
import nl.hartwig.actin.exception.InvalidMoveException;
import nl.hartwig.actin.exception.InvalidUsernameException;
import nl.hartwig.actin.service.AuditService;
import nl.hartwig.actin.service.InputService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserPlayerTest {

    private UserPlayer player;
    @Mock
    private InputService inputService;
    @Mock
    private AuditService auditService;

    @Test
    void createUserPlayer_invalidNameProvided_verifyValidUserPlayerCreated() {
        final String mockUserName = "mock-valid-user-name";
        Mockito.when(inputService.getNextString()).thenReturn(mockUserName);
        this.createUserPlayer();

        Assertions.assertEquals(mockUserName, player.getName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void createUserPlayer_nullOrBlankNameProvided_thenExceptionThrown(final String nullOrBlankUserName) {
        Mockito.when(inputService.getNextString()).thenReturn(nullOrBlankUserName);

        Assertions.assertThrows(InvalidUsernameException.class, this::createUserPlayer);
    }

    @ParameterizedTest
    @MethodSource("nl.hartwig.actin.util.ParameterProvider#userInputMoveProvider")
    void getMove_validGameMoveInputProvided_verifyUserMove(final String userInput, final PrsMove expectedMove) {
        final String mockUserName = "mock-valid-user-name";
        Mockito.when(inputService.getNextString()).thenReturn(mockUserName);
        this.createUserPlayer();
        Mockito.when(inputService.getNextString()).thenReturn(userInput);

        PrsMove userMove = player.getMove();

        Assertions.assertEquals(expectedMove, userMove);
        Mockito.verify(auditService, Mockito.never()).summarize();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = "hammer")
    void getMove_invalidGameMoveInputProvided_exceptionThrown(final String invalidUserInput) {
        final String mockUserName = "mock-valid-user-name";
        Mockito.when(inputService.getNextString()).thenReturn(mockUserName);
        this.createUserPlayer();
        Mockito.when(inputService.getNextString()).thenReturn(invalidUserInput);

        Assertions.assertThrows(InvalidMoveException.class, () -> player.getMove());
        Mockito.verify(auditService, Mockito.never()).summarize();
    }

    @ParameterizedTest
    @ValueSource(strings = {"quit", "q"})
    void getMove_exitMoveInputProvided_exitCommandExceptionThrown(final String quitCommand) {
        final String mockUserName = "mock-valid-user-name";
        Mockito.when(inputService.getNextString()).thenReturn(mockUserName);
        this.createUserPlayer();
        Mockito.when(inputService.getNextString()).thenReturn(quitCommand);

        Assertions.assertThrows(ExitCommandException.class, () -> player.getMove());
        Mockito.verify(auditService).summarize();
    }

    private void createUserPlayer() {
        player = new UserPlayer(inputService, auditService);
    }
}
