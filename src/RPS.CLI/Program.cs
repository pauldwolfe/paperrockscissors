using System;
using RPS.Service;

namespace CLI
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                string validKeys = "rRpPsS";
                Console.WriteLine("Welcome to Rock, Paper, Scissors! Please enter your name:");
                var name = Console.ReadLine();

                RPSGameService gameService = new RPSGameService(name);

                Console.WriteLine("(R)ock, (P)aper, or (S)cissors? [(X) to exit]");
                ConsoleKeyInfo key = Console.ReadKey(true);
                while (key.KeyChar != 'x' && key.KeyChar != 'X')
                {
                    if (validKeys.IndexOf(key.KeyChar) > -1)
                    {
                        var round = gameService.PlayRound(gameService.TranslateEntry(key.KeyChar));
                        Console.Write($"{round.Player1.Name} selected {round.Player1.Selection}, {round.Player2.Name} selected {round.Player2.Selection}. RESULT:");
                        switch (round.Winner)
                        {
                            case (0):
                                Console.WriteLine("Tie!");
                                break;
                            case (1):
                                Console.WriteLine($" {round.Player1.Name} Wins!");
                                break;
                            case (2):
                                Console.WriteLine($" {round.Player2.Name} Wins!");
                                break;

                        }
                        Console.WriteLine($"{Environment.NewLine}(R)ock, (P)aper, or (S)cissors? [(X) to exit]");
                    }
                    key = Console.ReadKey(true);
                }
                var result = gameService.EndGame();

                Console.Write($"Game over! {result.Player1.Name} {result.Player1.Wins} wins, {result.Player2.Name} {result.Player2.Wins} wins, {result.Ties} ties. ");
                if (result.Winner == null)
                    Console.WriteLine("It's an overall tie!");
                else
                    Console.WriteLine($"{result.Winner.Name} is the overall winner!");
            }
            catch (Exception e) {
                Console.WriteLine($"Unexpected error occurred: {e.Message}");
            }
        }
    }
}
