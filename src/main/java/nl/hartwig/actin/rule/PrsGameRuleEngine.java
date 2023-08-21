package nl.hartwig.actin.rule;

import nl.hartwig.actin.enums.PrsMove;
import nl.hartwig.actin.exception.RuleNotFoundException;
import nl.hartwig.actin.util.WinnerPredicate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PrsGameRuleEngine implements GameRuleEngine {

    private final Map<PrsMove, WinnerPredicate> winRules;

    public WinnerPredicate getRule(final PrsMove move) {
        if (!winRules.containsKey(move)) {
            throw new RuleNotFoundException("There is no rule for move: " + move);
        }
        return winRules.get(move);
    }

    public PrsGameRuleEngine() {
        winRules = new ConcurrentHashMap<>(3);
        winRules.put(PrsMove.ROCK, opponent -> opponent == PrsMove.SCISSORS);
        winRules.put(PrsMove.PAPER, opponent -> opponent == PrsMove.ROCK);
        winRules.put(PrsMove.SCISSORS, opponent -> opponent == PrsMove.PAPER);
    }

}
