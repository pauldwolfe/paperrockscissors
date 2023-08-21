package nl.hartwig.actin.service;

import lombok.RequiredArgsConstructor;
import nl.hartwig.actin.enums.PrsMove;
import nl.hartwig.actin.model.ComputerPlayer;
import nl.hartwig.actin.model.Player;
import nl.hartwig.actin.model.UserPlayer;
import nl.hartwig.actin.rule.GameRuleEngine;
import nl.hartwig.actin.util.LogUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaperRockScissorsGameService implements GameService {

    private final UserPlayer user;
    private final ComputerPlayer computer;
    private final GameRuleEngine prsRuleEngine;

    @Override
    public Optional<Player> start() {
        PrsMove userMove = user.getMove();
        PrsMove computerMove = computer.getMove();

        LogUtil.logPlayerNameAndMove(user.getName(), userMove.name());
        LogUtil.logPlayerNameAndMove(computer.getName(), computerMove.name());

        Optional<Player> roundWinner = Optional.empty();

        if (prsRuleEngine.getRule(userMove).isWinner(computerMove)) {
            roundWinner = Optional.of(user);
        } else if (prsRuleEngine.getRule(computerMove).isWinner(userMove)) {
            roundWinner = Optional.of(computer);
        }

        if (roundWinner.isPresent()) {
            LogUtil.congratsWinner(roundWinner.get());
        } else {
            LogUtil.itsDraw();
        }

        return roundWinner;
    }
}
