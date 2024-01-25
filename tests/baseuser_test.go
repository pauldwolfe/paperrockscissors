package main

import (
	"athmare/rockpaperscissors/helpers/assert"
	"athmare/rockpaperscissors/internal/models"
	"testing"
)

// BaseUser is a private type so we use one of its  children instead
// to make sure that everything works correctly
func TestScoreIncrement(t *testing.T) {
	player := models.NewPlayer("testPlayer")
	player.IncrementScore()
	assert.Equal(t, player.GetScore(), 1)
}

// func TestIfInterfaceIsImplemented(t *testing.T) {
// 	val := models.NewPlayer("test")
// 	_, ok := interface{}(val).(models.BaseUserInterface)
// 	assert.Equal(t, ok, true)
// }
