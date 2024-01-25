package gamelogic

import (
	maphelper "athmare/rockpaperscissors/helpers"
	"strings"
)

type GameLogic struct {
}

func CalculateWinner(answer1 int, answer2 int) int {
	/*
	  Rock=1, Paper=2, Scissors=3
	  if 1 answer1 wins,
	  if 2 answer2 wins
	  if 0 tie
	  the +3 in the beginning is to prevent a negative value
	*/
	winner := (3 + answer1 - answer2) % 3
	return winner
}

func GetIntAnswerValue(answer string) int {
	possibleAnswers := getConstantMap()
	return possibleAnswers[answer]
}

func GetStringAnswerValue(value int) string {
	key, ok := maphelper.Mapkey(getConstantMap(), value)
	if !ok {
		return ""
	}
	return key
}

func CleanAnswer(answerToClean string) string {
	return strings.TrimSpace(strings.ToLower(answerToClean))
}

// golang does not support immutable maps
// so i'm using a function that represents one
func getConstantMap() map[string]int {
	return map[string]int{
		"rock":     1,
		"paper":    2,
		"scissors": 3,
	}
}
