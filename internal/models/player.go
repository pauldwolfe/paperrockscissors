package models

type Player struct {
	baseUserModel
}

func NewPlayer(username string) Player {
	return Player{baseUserModel{username: username, score: 0}}
}
