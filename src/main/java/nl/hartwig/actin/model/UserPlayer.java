package nl.hartwig.actin.model;

import nl.hartwig.actin.enums.PrsMove;
import nl.hartwig.actin.exception.ExitCommandException;
import nl.hartwig.actin.service.AuditService;
import nl.hartwig.actin.service.InputService;
import nl.hartwig.actin.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPlayer extends Player {

    private final InputService inputService;
    private final AuditService auditService;

    @Autowired
    public UserPlayer(InputService inputService, AuditService auditService) {
        this.inputService = inputService;
        this.auditService = auditService;
        LogUtil.areYouReady();
        this.setName(inputService.getNextString());
    }

    @Override
    public PrsMove getMove() {
        LogUtil.whatIsYourMove();
        final String userInput = this.inputService.getNextString();
        if (isExist(userInput)) {
            LogUtil.reportSummaryAndExit();
            auditService.summarize();
            throw new ExitCommandException();
        } else {
            PrsMove move = PrsMove.register(userInput);
            this.lastMove = move;
            return move;
        }
    }

    public PrsMove getLastMove() {
        return this.lastMove;
    }
}
