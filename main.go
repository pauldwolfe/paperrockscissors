package main

import (
	"bufio"
	"fmt"
	"os"

	gamelogic "athmare/rockpaperscissors/lib"
	"athmare/rockpaperscissors/models"
)

func main() {
	NewGameRoom()
}

func NewGameRoom() {

	fmt.Println("Let's play  Paper Rock Scissors..")
	reader := bufio.NewReader(os.Stdin)

username:
	fmt.Println("Please input your username..")

	username, err := reader.ReadString('\n')
	if err != nil || len(username) == 0 {
		fmt.Println("Username not valid or empty please try again")
		goto username
	}

	player := models.NewPlayer(username)
	bot := models.NewBot()
	//TODO
	//validates the gameloop
	//create unitests
	// create scala comparisons
	//create documentation
	//create binaries
	//commit to github

	//game loop
	fmt.Printf("Player: %s is going up against computer: %s \n", player.GetUsername(), bot.GetUsername())
gameloop:
	for {
		fmt.Println("Type in 'paper', 'rock' or 'scissors")

		output, err := reader.ReadString('\n')
		answer := gamelogic.CleanAnswer(output)

		if answer == "exit" {

			fmt.Println("Bye Bye")
			os.Exit(0)
		}

		intPlayerAnswer := gamelogic.GetIntAnswerValue(answer)
		if err != nil || intPlayerAnswer == 0 {
			fmt.Println("The Answer is invalid please try again")
			goto gameloop
		}

		intBotAnswer := bot.Play()
		fmt.Printf("%s played %s\n", bot.GetUsername(), gamelogic.GetStringAnswerValue(intBotAnswer))

		winnerInt := gamelogic.CalculateWinner(intPlayerAnswer, intBotAnswer)

		//if 1 answer1 wins,
		//if 2 answer2 wins
		//if 0 tie
		if winnerInt == 1 {
			player.IncrementScore()
			fmt.Printf("Player %s wins this round!\n", player.GetUsername())
		} else if winnerInt == 2 {
			bot.IncrementScore()
			fmt.Printf("Bot %s wins this round! \n", bot.GetUsername())
		} else {
			fmt.Println("No winners its a tie")
		}
		displayCurrentScore(player, bot)
	}

}

func displayCurrentScore(player models.Player, bot models.Bot) {
	fmt.Printf("Current score is '%s: %d' vs '%s: %d'\n", player.GetUsername(), player.GetScore(), bot.GetUsername(), bot.GetScore())
}
