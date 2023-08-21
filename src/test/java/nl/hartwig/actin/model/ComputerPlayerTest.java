package nl.hartwig.actin.model;

import nl.hartwig.actin.enums.PrsMove;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class ComputerPlayerTest {

    @InjectMocks
    private ComputerPlayer computerPlayer;

    @Test
    void verifyComputerPlayerName() {
        Assertions.assertEquals(ComputerPlayer.getComputerName(), computerPlayer.getName());
    }

    @Test
    void verifyComputerPlayerReturnsValidMoveAndLastMove() {
        Assertions.assertTrue(Arrays.asList(PrsMove.values()).contains(computerPlayer.getMove()));
        Assertions.assertNotNull(computerPlayer.getLastMove());
    }
}
