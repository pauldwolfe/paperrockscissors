using System;

namespace RPS.Service
{
    /// <summary>
    /// Represents one round of the game
    /// </summary>
    public struct Round {
        public int Winner;
        public Player Player1;
        public Player Player2;

        public Round(Player player1, Player player2, int winner) {
            Winner = winner;
            Player1 = player1;
            Player2 = player2;
        }
    }
}