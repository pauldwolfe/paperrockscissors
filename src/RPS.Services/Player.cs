using System;

namespace RPS.Service
{
    /// <summary>
    /// Regular player class
    /// </summary>
    public class Player
    {
        private int _wins = 0;
        private Selection _selection;

        public Player(string name) {
            Name = name;
        }

        public string Name {get;set;}

        public Selection Selection { 
            get 
            {
                return _selection;
            } 
            set {
                _selection = value;
            }
        }

        public int Wins {
            get { return _wins; } 
        }


        public void AddWin() {
            _wins++;
        }

    }

    /// <summary>
    /// Computer player class
    /// </summary>
    public class Computer : Player
    {
        public Computer(): base("Computer") {

        }

        public void SetRandomSelection() {
            base.Selection = GetRandomSelection();
        }

        private Selection GetRandomSelection() {
            Random random = new Random();
            return (Selection)random.Next(3);
        }
    }

}
