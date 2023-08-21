package nl.hartwig.actin.model;

import lombok.Getter;
import nl.hartwig.actin.enums.PrsMove;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class ComputerPlayer extends Player {

    @Getter
    private static final String computerName = "machine";

    public ComputerPlayer() {
        this.name = computerName;
    }

    @Override
    public PrsMove getMove() {
        List<PrsMove> values = Arrays.asList(PrsMove.values());
        PrsMove move = values.get(new Random().nextInt(values.size()));
        this.lastMove = move;
        return move;
    }

    @Override
    public PrsMove getLastMove() {
        return this.lastMove;
    }

}
