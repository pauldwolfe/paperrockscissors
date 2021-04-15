export class GameScore {
    userWins: number;
    computerWins: number;
    ties: number;
    constructor() {
        this.userWins = 0;
        this.computerWins = 0;
        this.ties = 0;
    }
    toString() {
        return `
            You won: ${this.userWins} rounds
            Computer won: ${this.computerWins} rounds
            Ties: ${this.ties}
        `;
    }
}