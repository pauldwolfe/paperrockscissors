# Rock-Paper-Scissors

## Overview

This is a simple command line implementation of Rock Paper Scissors, following the specs that the game is played repeatedly until the player explicitly chooses to quit. The number of wins, losses, and ties are then displayed.

## Design choices

### Actions

The moves are determined by a set of `Action`s, in this case, `Rock`, `Paper`, and `Scissors`. An action is determined by an enumerated label and a set of actions which it beats.

Firstly, by programming to `Actions`, it is easy to add new `Action`s to the set. There are some edge cases handled and tested, for example, the ambiguous case where `ActionA` beats `ActionB` but `ActionB` also beats `ActionA`. I chose not to allow two distinct `Action`s to end in a tie. It's not difficult logic to handle, but there are a lot more edge cases, and I decided that it cluttered the code without really adding much useful functionality.

Secondly, the game logic can be handled by a pipeline of `Action`s, instead of by keeping state. Although the implementation is completely synchronous, the pipeline could be made asynchronous without any change in logic, and more importantly, without needing to lock any resources. (The technical term for this pattern is the Kleisli category). For example, if we were running the code in a browser window, and instead of playing against a computer, we were waiting for a remote human to submit a move, the pipeline would allow us to wait for the opponent's moves without blocking the browser thread. (I decided not to make the code asynchronous to keep the scope relatively simple, but I have put comments in the code where functions could be replaced with async ones). The pattern can be moved to any multi-threaded environment relatively easily, by just needing to await certain results, specifically the calls to `Game.getResultFromPipeline()`

### Players

A `PlayerFactory` determines whether a player is a Human Player or a Computer `Player`. For now, Player 1 will always be Human and Player 2 will always be a Computer (which chooses Actions randomly), since the game would not really work between two humans on the same computer when Player 1 always goes first. However, if Player 2 were a remote human player, the game would work Human vs Human, and this pattern allows that extension fairly simply.

### Record

The `Record` type is a simple triple of values: (`wins`, `losses`, `ties`), but you may notice that each round's output is encoded as a `Record` to be incremented, for example, a `LOSS` will return a record (0,1,0). This allow the pipeline to string together many `Action`s asynchronously, as the increments can be added on a specific thread, and only needs to be added to the "true" record when everything needs to be reconciled (for example, when a player quits). In technical terms, this turns the set of actions into a State Monad over the set of `Record`s. In practical terms, this means that the `Action`s can be processed asynchronously without ever needing to lock the running total `Record`. It also makes sure that the pipeline only processes a sequence of pure functions, without side-effects. 

## Testing

I was not familiar with Kotlin before doing this exercise, so I used the simplest setup in IntelliJ IDEA to run unit tests on the low-level components, and all pure functions have been automatically tested. A stretch goal would be to set up unit tests to automatically mock user inputs. However, given the time frame and the scope of the project, I decided to test the user inputs manually.