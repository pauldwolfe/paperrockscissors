"""
Run tests with `python game_test.py`
"""

import unittest
import game as g
from game import Rules, Item, Result, InputHandler, Action, GameInput

class TestRules(unittest.TestCase):
    def test_rules(self):

        throws = [Item.ROCK, Item.PAPER, Item.SCISSORS]

        # all matching hand throws are ties
        for t in throws:
            self.assertEqual(Rules.evaluate(t, t), Result.TIE, f"Expected tie for {t} vs {t}")

        # list of winning cases for player 1, [(winning item, losing item), ...]
        cases = [
            (Item.ROCK, Item.SCISSORS),
            (Item.SCISSORS, Item.PAPER),
            (Item.PAPER, Item.ROCK)
        ]

        for case in cases:
            # player 1 wins
            self.assertTrue(Rules.evaluate(case[0], case[1]), Result.PLAYER_WINS)

            # reverse case is player 2 wins
            self.assertTrue(Rules.evaluate(case[1], case[0]), Result.COMPUTER_WINS)

class TestInputHandler(unittest.TestCase):
    def test_input(self):
        h = InputHandler()

        # regular inputs, spaces and case shouldn't matter
        self.assertEqual(h.handle("help"), GameInput(Action.HELP, None))
        self.assertEqual(h.handle("Help"), GameInput(Action.HELP, None))
        self.assertEqual(h.handle(" help    "), GameInput(Action.HELP, None))

        self.assertEqual(h.handle("exit"), GameInput(Action.EXIT, None))
        self.assertEqual(h.handle("rock"), GameInput(Action.PLAY_ROUND, Item.ROCK))
        self.assertEqual(h.handle("paper"), GameInput(Action.PLAY_ROUND, Item.PAPER))
        self.assertEqual(h.handle("scissors"), GameInput(Action.PLAY_ROUND, Item.SCISSORS))

        # some bad inputh
        self.assertEqual(h.handle("shoe"),GameInput(Action.UNKNOWN_INPUT, None))
        self.assertEqual(h.handle(""), GameInput(Action.UNKNOWN_INPUT, None))
        self.assertEqual(h.handle(" "), GameInput(Action.UNKNOWN_INPUT, None))
        self.assertEqual(h.handle("幸"), GameInput(Action.UNKNOWN_INPUT, None))


class TestGame(unittest.TestCase):

    def test_init(self):
        # game state on initialization
        game = g.Game()
        self.assertEqual(0, game.player_score)
        self.assertEqual(0, game.computer_score)
        self.assertEqual(0, game.num_ties)

    def test_intro(self):
        game = g.Game()
        self.assertAlmostEqual(game.intro(), g.HELP_MESSAGE)

    def test_prompt(self):
        game = g.Game()
        self.assertEqual(game.prompt(), g.PROMPT)

    def test_score_round(self):
        
        # a tie
        game = g.Game()
        response = game.score_round(Item.ROCK, Item.ROCK)
        self.assertEqual(response, "You played rock, I played rock, its a tie!")
        self.assertEqual(0, game.player_score)
        self.assertEqual(0, game.computer_score)
        self.assertEqual(1, game.num_ties)

        # player 1 loses
        game = g.Game()
        response = game.score_round(Item.ROCK, Item.PAPER)
        self.assertEqual(response, "You played rock, I played paper, I win!")
        self.assertEqual(0, game.player_score)
        self.assertEqual(1, game.computer_score)
        self.assertEqual(0, game.num_ties)

        # player 1 wins
        game = g.Game()
        response = game.score_round(Item.ROCK, Item.SCISSORS)
        self.assertEqual(response, "You played rock, I played scissors, you win!")
        self.assertEqual(1, game.player_score)
        self.assertEqual(0, game.computer_score)
        self.assertEqual(0, game.num_ties)

    def test_scoreboard(self):
        game = g.Game()
        game.player_score = 1
        game.computer_score = 2
        game.num_ties = 3

        expected = "You won 1, I won 2, and we tied 3 times. Let's play again sometime!"

    def test_play(self):
        """this is a bit of an integration test, plays a full game, since
        we have coverage of various cases we only play one round and make
        sure overall flow holds together"""
        
        def rock_lover():
            return Item.ROCK

        game = g.Game(strategy = rock_lover)
        response = game.play("shoe")
        self.assertEqual(response.message, g.UNKNOWN_INPUT_MESSAGE)
        self.assertEqual(response.done, False)

        response = game.play("paper")
        self.assertEqual(response.message, "You played paper, I played rock, you win!")
        self.assertEqual(response.done, False)
  
        # this is a bit fragile and hardcodes the formatting used in the game
        response = game.play("exit")
        expected = "You won 1, I won 0, and we tied 0 times. Let's play again sometime!"
        self.assertEqual(response.message, expected)
        self.assertEqual(response.done, True)


if __name__ == '__main__':
    unittest.main()