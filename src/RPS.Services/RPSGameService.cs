using System;

namespace RPS.Service
{
    /// <summary>
    /// Main game service
    /// </summary>
    public class RPSGameService
    {
        public Game game;

        /// <summary>
        /// Game service constructor. Creates Player1 based on the provided name, auto-creates the computer user as Player2
        /// </summary>
        /// <param name="playerName">Human player's name</param>
        public RPSGameService(string playerName) {
            game = new Game();
            game.Player1 = new Player(playerName);
            game.Player2 = new Computer();
        }

        /// <summary>
        /// Plays one round of the game
        /// </summary>
        /// <param name="player1Selection">Human player's selection</param>
        /// <returns>Round object</returns>
        public Round PlayRound(Selection player1Selection) {
            game.GamesPlayed++;
            game.Player1.Selection = player1Selection;
            ((Computer)game.Player2).SetRandomSelection();
            int winner = DetermineWin();
            Round round = new Round(game.Player1, game.Player2, winner);
            if (winner == 1)
                game.Player1.AddWin();
            else if (winner == 2)
                game.Player2.AddWin();
            else
                game.Ties++;
            return round;
        }

        /// <summary>
        /// Ends the game and returns the game results to the client
        /// </summary>
        /// <returns>Game object</returns>
        public Game EndGame() {
            return game;
        }

        /// <summary>
        /// Helper method to assist CLI client with translating key input to Selection enum
        /// </summary>
        /// <param name="key"></param>
        /// <returns>Equivalent Selection enum value</returns>
        public Selection TranslateEntry(char key) {
            switch(key)
            {
                case 'R': case 'r':
                    return Selection.Rock;
                case 'P': case 'p':
                    return Selection.Paper;
                case 'S': case 's':
                    return Selection.Scissors;
                default:
                    throw new Exception("Invalid key");
            }
            
        }

        /// <summary>
        /// Determines which selection is the winner
        /// </summary>
        /// <param name="player1Selection">Player 1's selection</param>
        /// <param name="player2Selection">Player 2's selection</param>
        /// <returns>Returns 1 if player 1 wins, 2 if player 2 wins, 0 if tie--></returns>
        public int DetermineWin() {
            const int _player1 = 1;
            const int _player2 = 2;
            const int _tie = 0;
            int _winner = _tie;

            if (game.Player1.Selection == game.Player2.Selection)
                return _tie;

            switch(game.Player1.Selection)
            {
                case Selection.Rock:
                    _winner = (game.Player2.Selection == Selection.Scissors) ? _player1 : _player2;
                    break;
                case Selection.Paper:
                    _winner = (game.Player2.Selection == Selection.Rock) ? _player1 : _player2;
                    break;
                case Selection.Scissors:
                    _winner = (game.Player2.Selection == Selection.Paper) ? _player1 : _player2;
                    break;
            }
            return _winner;
        }
        
    }
}
