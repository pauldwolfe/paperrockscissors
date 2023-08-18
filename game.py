"""
Rock-Paper-Scissors command-line game.

Requires python>=3.11 installed, in a terminal change
to a directory containing this script and type:

  $ python game.py

and follow the instructions.
"""

from dataclasses import dataclass
from enum import Enum
import random
from typing import Callable

HELP_MESSAGE = """Let's play a game of Rock-Paper-Scissors!

Type one of 'rock', 'paper', or 'scissors' at the prompt,
I'll pick one too and tell you who won. 

When you are done type 'exit' and I'll summarize the scores, 
or type 'help' at any time to get these instructions again.

Good luck, and I promise I won't cheat!
"""
UNKNOWN_INPUT_MESSAGE = "Oops, I don't understand. Try again or type 'help'"
PROMPT = "What's your guess? > "
PROFUSE_APOLOGY = "You didn't do anything wrong, the code has a bug! Please contact the developer."

class Item(Enum):
    """All possible hand-shapes that can be played"""
    ROCK = 1
    PAPER = 2
    SCISSORS = 3

    @staticmethod
    def random():
        """Return an item at random"""
        return random.choice(list(Item.__members__.values()))

class Result(Enum):
    """Logical outcomes of any single round of the game"""
    PLAYER_WINS = 1
    COMPUTER_WINS = 2
    TIE = 3

class Action(Enum):
    """Possible game actions, e.g. display help, play or exit"""
    HELP = 1
    PLAY_ROUND = 2
    EXIT = 3
    UNKNOWN_INPUT = 4


class Rules:
    """Encapsulates the game play logic,
    by returning the winner of all possible
    valid games.
    """

    @staticmethod
    def evaluate(player_item: Item, computer_item: Item) -> Result:
        """Given a throw of hands for player and computer
        return the winner.

        Parameters
        ----------
        player_item : Item
            hand shape thrown by player
        computer_item : Item
            hand shape thrown by computer

        Returns
        -------
        Result
            The winner or tie
        """

        if player_item == computer_item:
            return Result.TIE
        elif (player_item, computer_item) in [  # winning cases for player
            (Item.ROCK, Item.SCISSORS), 
            (Item.SCISSORS, Item.PAPER),
            (Item.PAPER, Item.ROCK)
            ]:
            return Result.PLAYER_WINS
        else:
            return Result.COMPUTER_WINS

@dataclass
class GameInput:
    '''Logically, input is an action and an item,
    for example PLAY & ROCK. Item may be None as
    for HELP, and EXIT actions.'''
    action: Action
    item: Item

class InputHandler:
    """Handle all user data cleaning."""

    @staticmethod
    def handle(data: str) -> GameInput:
        """Given a string of user input, determine what
        action to take, and the corresponding Item if any.

        Whitespace and character case is ignored.

        Parameters
        ----------
        data : str
            user input

        Returns
        -------
        GameInput
            Returns GameInput indicating action to take and 
            corresponding item.
        """
        data = data.strip()
        data = data.lower()

        if data in ('help', 'h'):
            return GameInput(Action.HELP, None)
        elif data == 'exit':
            return GameInput(Action.EXIT, None)
        elif data == 'rock':
            return GameInput(Action.PLAY_ROUND, Item.ROCK)
        elif data == 'paper':
            return GameInput(Action.PLAY_ROUND, Item.PAPER)
        elif data == 'scissors':
            return GameInput(Action.PLAY_ROUND, Item.SCISSORS)
        else:
            return GameInput(Action.UNKNOWN_INPUT, None)


def random_strategy() -> Item:
    """A computer player that returns a random Item 

    Returns
    -------
    Item
        One of the hand shapes selected at random
    """
    return Item.random()

@dataclass
class GameResponse():
    '''Response from the game engine with a message to
    display to the user, and flag indicating if the game
    is done.'''
    message: str
    done: bool

class Game():
    """Main driver for the Rock-Paper-Scissors game."""

    def __init__(self, strategy: Callable[[], Item] = random_strategy):
        """Main driver for the Rock-Paper-Scissors game.

        Parameters
        ----------
        strategy : Callable[[], Item], optional
            a function that returns the hand shapes thrown by the computer player,
            by default random_strategy
        """
        self.strategy = strategy

        self.player_score = 0
        self.computer_score = 0
        self.num_ties = 0

    @staticmethod
    def intro() -> str:
        """Text to display on game startup."""
        return HELP_MESSAGE
    
    @staticmethod
    def prompt() -> str:
        """Input prompt text"""
        return PROMPT
        
    def play(self, data: str) -> GameResponse:
        """Play a round of the game. Given user
        input string, process and return response with 
        text for user, and boolean indicating if we are done.

        Parameters
        ----------
        data : str
            Input string from user

        Returns
        -------
        GameResponse
            Response with user message and done flag.
        """

        gameInput = InputHandler.handle(data)

        if gameInput.action == Action.HELP:
            return GameResponse(HELP_MESSAGE, False)
        elif gameInput.action == Action.PLAY_ROUND:
            computer_item = self.strategy()
            message = self.score_round(gameInput.item, computer_item)
            return GameResponse(message, False)
        elif gameInput.action == Action.EXIT:
            return GameResponse(self.scoreboard(), True)
        elif gameInput.action == Action.UNKNOWN_INPUT:
            return GameResponse(UNKNOWN_INPUT_MESSAGE, False)
        else:  # should never happen
            assert False, PROFUSE_APOLOGY
        
    def score_round(self, player_item: Item, computer_item: Item) -> str:
        """Update scores for a round of game play, given
        the item played by each player. Return a message
        for the user indicating who won.

        Parameters
        ----------
        player_item : Item
            Hand shape played by player, the user
        computer_item : Item
            Hand shape played by computer, the computer

        Returns
        -------
        str
            Text message indicating the winner of the round.
        """

        result = Rules.evaluate(player_item, computer_item)

        player_item_name = player_item.name.lower()
        computer_item_name = computer_item.name.lower()

        if result == Result.PLAYER_WINS:
            self.player_score += 1
            return f"You played {player_item_name}, I played {computer_item_name}, you win!"
        elif result == Result.COMPUTER_WINS:
            self.computer_score += 1
            return f"You played {player_item_name}, I played {computer_item_name}, I win!"
        elif result == Result.TIE:
            self.num_ties += 1
            return f"You played {player_item_name}, I played {computer_item_name}, its a tie!"
        else:  # should never happen
            assert False, PROFUSE_APOLOGY


    def scoreboard(self) -> str:
        """Produce a text message with total game scores.

        Returns
        -------
        str
            Message summarizing the results of all the 
            rounds of the games played.
        """
        return f"You won {self.player_score}, I won {self.computer_score}, and we tied {self.num_ties} times. Let's play again sometime!"


if __name__ == '__main__':
    '''Main loop'''

    game = Game()
    print(game.intro())

    try:
        while True:
            user_data = input(game.prompt())
            response = game.play(user_data)
            print(response.message)
            if response.done:
                break
    except EOFError:
        pass # its ok, user typed control-D
    except KeyboardInterrupt:
        pass # its ok, user hit control-C
