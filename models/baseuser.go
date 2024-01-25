package models

// create unitests to make sure interface is implemented
// interfaces are different in golang, you don't specify it
// on the object but rather implement the same functions as the interface
// or rather if it quicks like a duck and acts like a duck its a duck
type BaseUser interface {
	GetScore()
	GetUsername()
	IncrementScore()
}

// using lower case makes baseuser private
type baseUserModel struct {
	username string
	score    int
}

//use a pointer to modify the value and not create a new one
func (base *baseUserModel) IncrementScore() {
	base.score++ //test this
}

func (base baseUserModel) GetUsername() string {
	return base.username
}

func (base baseUserModel) GetScore() int {
	return base.score
}
