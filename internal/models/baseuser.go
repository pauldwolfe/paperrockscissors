package models

// interfaces are different in golang, you don't specify the inheritance part
// on the class/struct but rather implement the same functions as the interface
// as they describe it: 'if it quacks like a duck and acts like a duck its a duck'
type BaseUserInterface interface {
	IncrementScore()
	GetUsername() string
	GetScore() int
}

// props
// using lower case makes baseuser private
type baseUserModel struct {
	username string
	score    int
}

// use a pointer to our struct to modify the value and not create a new one
func (base *baseUserModel) IncrementScore() {
	base.score++
}

func (base baseUserModel) GetUsername() string {
	return base.username
}

func (base baseUserModel) GetScore() int {
	return base.score
}
