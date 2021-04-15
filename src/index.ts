import readline from 'readline-promise';
import { getRoundScore, initRockPaperScissorsScore } from './helpers/score-helpers';
import { ChoicesLabels } from './models/enums/choice';
import { GameScore } from './models/game-score.model';

//Initializing object for prompting text at console and wait for user input
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: true
});

// function for ending game / program execution
function endGame(gameScore: GameScore) {
    console.log(gameScore.toString());
    console.log('Thanks for playing! Exiting application...');
    process.exit();
}

const validAnswers = 'RPSX';
// init game score tracking
let gameScore = new GameScore();
// init scoring equivalences
let scorings = initRockPaperScissorsScore();

export async function main() {
    
    while(true) {
        try {
            const answer : string = await rl.questionAsync('Type one of the following choices: R = Rock, P = Paper, S = Scissors, X = Exit \n');
            const upperCasedAnswer = answer.toUpperCase();
            // verify a valid character was input by the user
            if(upperCasedAnswer.length === 1 && validAnswers.includes(upperCasedAnswer)) {
                // user has elected to exit application
                if(upperCasedAnswer === 'X') {
                    endGame(gameScore);
                }
                // determine round score based on user input
                let {roundScore, computerAnswer} = getRoundScore(scorings, upperCasedAnswer);
                console.log('Your choice: ', ChoicesLabels[upperCasedAnswer]);
                console.log('Computer choice: ', ChoicesLabels[computerAnswer]);
                // display messsage depending on score and update "global" score
                if(roundScore === 1) {
                    console.log('You Win! :)');
                    gameScore.userWins++;
                }
                else if(roundScore === -1) {
                    console.log('You Lose! :(');
                    gameScore.computerWins++;
                }
                else{
                    console.log('It\'s a tie! :O');
                    gameScore.ties++;
                } 
            }
            else {
                console.log('Invalid Input Provided. Please try again');
            } 
        }
        catch(err) {
            console.error('An error occured', err);
        }
    }
};

process.on('SIGINT', () => {
    endGame(gameScore);
});

main();