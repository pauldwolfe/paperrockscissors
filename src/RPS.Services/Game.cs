using System;

namespace RPS.Service
{
    /// <summary>
    /// Game class, tracking player stats
    /// </summary>
    public class Game {
        /// <summary>
        /// The number of games played
        /// </summary>
        public int GamesPlayed { get; set; }
        
        /// <summary>
        /// The number of tied games
        /// </summary>
        public int Ties { get; set; }
        
        
        public Player Player1 { get; set; }
        public Player Player2 { get; set; }
        
        /// <summary>
        /// Returns the current overall winner
        /// </summary>
        public Player Winner {
            get {
                if (Player1.Wins > Player2.Wins)
                    return Player1;
                else if (Player2.Wins > Player1.Wins)
                    return Player2;
                else
                    return null; //tie
            }}
    }
    
}