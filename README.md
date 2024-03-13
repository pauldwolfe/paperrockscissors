# Paper Rock Scissors Exercise

The following is a small exercise to get an idea of your coding and design skills. We would like you to develop a simple interactive game of [Paper Rock Scissors](https://en.wikipedia.org/wiki/Rock_paper_scissors)

It's intentionally not an algorithmically complex problem, so we're looking more at modelling and structure. We are trying to get a feel for how you code in a professional setting.

## Functional Requirements
* The user is presented with a CLI to play the game. 
* A user can enter one of 3 inputs: paper, rock or scissors.
* The computer will choose one input at random.
* The game rules will be applied and a winner is chosen: 
  - Paper beats Rock
  - Rock beats Scissors
  - Scissors beats Paper. 
  - The same input is a tie. 
* The game will repeat until the user explictly chooses to exit.
* On exit a summary is displayed of games won, lost, and tied.

## Playing the game
I created a Maven project and used Visual Studio Code for the IDE. 
The game starts by asking the player's name.
After each round the result of the round is printed, when the player chooses to end the game, by entering the 'x' key, a summary is printed and the final winner out of all the rounds is printed.

## Notes on the solution
The non-functional requirements stated "Don't go overboard", in the light of that I may have gone slightly overboard. I do think it may be over-engineered, in a real life situation I would dial it down and would not straight away implement many layers of abstraction but would rather choose to keep it simple, I always prefer keeping it simple and not implement what I POSSIBLY MAY need but only what I ABSOLUTELY need, and later add more if required, implementing more layers of abstraction and more lines of code leads to code that is harder to maintain and follow. But I implemented what I think you may want to see me demonstrate my understanding of interfaces, dependency injection, abstraction, and general OO principles.

My process of finding a solution will usually start with analysis and a design of some kind, depending on what kind of problem I am solving, a flow diagram, UML sequence diagram, class diagram, or ERD. For this solution I created a flow diagram as well as a class diagram which I supply below.


**Flow Diagram**

![Flow Diagram](./RockPaperScissors-flow%20diagram.png)

**Class Diagram**

![Class Diagram](./RockPaperScissors-class%20diagram.png)


