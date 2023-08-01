# Rock Paper Scissors in Kotlin

This is a simple interactive game of [Paper Rock Scissors](https://en.wikipedia.org/wiki/Rock_paper_scissors)

## What it Does
* The user is presented with a CLI to play the game. 
* A user can enter one of 3 inputs: paper, rock or scissors.
* The computer will choose one input at random.
* The game rules will be applied and a winner is chosen: 
  - Paper beats Rock
  - Rock beats Scissors
  - Scissors beats Paper. 
  - The same input is a tie. 
* The game will repeat until the user explictly chooses to exit: by entering "exit" in the CLI.
* On exit a summary is displayed of games won, lost, and tied.

## How to Run
* `Main.kt` is the main program. Simply executing it with `./gradlew run` will start the game in the console.
* You can run `./gradlew jar` to get a compiled JAR under `/build/libs`
* You can also import this Gradle project into IDE such as IntelliJ and run `Main.kt` in IDE

## Enjoy the game!
