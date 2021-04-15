import { Choice } from '../src/models/enums/choice';
import { getRoundScore, initRockPaperScissorsScore } from './../src/helpers/score-helpers';


describe('Mocked Game', () => {
    let scorings = initRockPaperScissorsScore();
    it('should properly determine score given a user input (Rock)', () => {
       const { roundScore, computerAnswer } = getRoundScore(scorings, Choice.ROCK);
       if(computerAnswer === Choice.ROCK) {
         expect(roundScore).toBe(0);
       }
       if(computerAnswer === Choice.SCISSORS) {
         expect(roundScore).toBe(1);
       }
       if(computerAnswer === Choice.PAPER) {
         expect(roundScore).toBe(-1);
       }
    })
});