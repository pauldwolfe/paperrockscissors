import { Choice, ChoicesArray } from "../models/enums/choice";
import { Scoring } from "../models/scoring.model";

// initialize equivalences according to a user input. 1 represents pick wins, -1 pick losses, 0 tie
export function initRockPaperScissorsScore() {
    let scorings : Scoring[] = [];
    scorings.push(new Scoring(Choice.ROCK, { [Choice.ROCK] : 0, [Choice.PAPER]: -1, [Choice.SCISSORS] : 1}));
    scorings.push(new Scoring(Choice.PAPER, { [Choice.ROCK] : 1, [Choice.PAPER]: 0, [Choice.SCISSORS] : -1}));
    scorings.push(new Scoring(Choice.SCISSORS, { [Choice.ROCK] : -1, [Choice.PAPER]: 1, [Choice.SCISSORS] : 0}));
    return scorings;
}

// calculate random pick for computer and return score
export function getRoundScore(scorings: Scoring[], userAnswer: string): { roundScore: number, computerAnswer?: string } {
    let random = Math.floor(Math.random() * 3);
    let computerAnswer = ChoicesArray[random];
    let targetScoring = scorings.find(s => s.selection === userAnswer);
    return {
        roundScore: targetScoring.possibleResults[computerAnswer],
        computerAnswer
    }
}