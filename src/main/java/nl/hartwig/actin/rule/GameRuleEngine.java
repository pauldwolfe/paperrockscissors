package nl.hartwig.actin.rule;

import nl.hartwig.actin.enums.PrsMove;
import nl.hartwig.actin.util.WinnerPredicate;

@FunctionalInterface
public interface GameRuleEngine {

    WinnerPredicate getRule(PrsMove move);

}
