package com;

import java.util.Dictionary;
import java.util.Hashtable;

public class RulesEngineRockPaperScissors implements RulesEngineInterface {

    Dictionary<String, RuleInterface> rulesMap = new Hashtable<>();

    RulesEngineRockPaperScissors() {
        /*
         * The 2 inputs can be received in any order. Use a map to map an input 1 and an input 2 to the same rule 
         * comparing a combination of the 2 inputs in any order in other words
         * "input1=rock, input2=scissors" should map to the same rule as "input1=scissors, input2=rock".
         */

        rulesMap.put(GameInputChoice.TwoChoicesToString(GameInputChoice.SCISSORS, GameInputChoice.ROCK), new RuleRockScissors());
        rulesMap.put(GameInputChoice.TwoChoicesToString(GameInputChoice.ROCK, GameInputChoice.SCISSORS), new RuleRockScissors());
        rulesMap.put(GameInputChoice.TwoChoicesToString(GameInputChoice.ROCK, GameInputChoice.PAPER), new RulePaperRock());
        rulesMap.put(GameInputChoice.TwoChoicesToString(GameInputChoice.PAPER, GameInputChoice.ROCK), new RulePaperRock());
        rulesMap.put(GameInputChoice.TwoChoicesToString(GameInputChoice.PAPER, GameInputChoice.SCISSORS), new RuleScissorsPaper());
        rulesMap.put(GameInputChoice.TwoChoicesToString(GameInputChoice.SCISSORS, GameInputChoice.PAPER), new RuleScissorsPaper());
        
    }


    /**
     * Applies the rule for the 2 game inputs and returns the winning input.
     * 
     * @param input1
     * @param input2
     * @return
     * @throws GameRulesException
     */
    public GameInputChoice applyRule(GameInputChoice input1, GameInputChoice input2) throws GameRulesException {

        // If the two inputs are the same we ahve a tie then retunr NONE for the GameInputChoice.
        if (input1 == input2) {
            return GameInputChoice.NONE;
        }
        
        RuleInterface rule = rulesMap.get(GameInputChoice.TwoChoicesToString(input1, input2));
        if (rule == null) {
            throw new GameRulesException("The game rules could not be applied.");
        }

        return rule.playRule();
    }

}
