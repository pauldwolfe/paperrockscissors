package models

import (
	"fmt"
	"math/rand"
)

type Bot struct {
	baseUserModel
}

func NewBot() Bot {
	//create a random bot name e.g. bot#34
	username := fmt.Sprint("bot#", rand.Intn(100))
	return Bot{baseUserModel{username: username, score: 0}}
}

func (bot Bot) Play() int {
	return rand.Intn(2) + 1
}
