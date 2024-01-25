package main

import (
	"athmare/rockpaperscissors/helpers/assert"
	gamelogic "athmare/rockpaperscissors/lib"
	"testing"
)

// wanted to test most core scenarios to make sure the math checks out
func TestCalculateWinner(t *testing.T) {
	/*
	  Rock=1, Paper=2, Scissors=3
	  if 1 answer1 wins,
	  if 2 answer2 wins
	  if 0 tie
	  the +3 in the beginning is to prevent a negative value
	*/
	tests := []struct {
		name    string
		answer1 int
		answer2 int
		want    int
	}{
		{
			name:    "Player loses",
			answer1: 1,
			answer2: 2,
			want:    2,
		},
		{
			name:    "Player wins",
			answer1: 1,
			answer2: 3,
			want:    1,
		},
		{
			name:    "tie",
			answer1: 2,
			answer2: 2,
			want:    0,
		},
		{
			name:    "tie",
			answer1: 1,
			answer2: 1,
			want:    0,
		},
		{
			name:    "Player wins",
			answer1: 2,
			answer2: 1,
			want:    1,
		},
		{
			name:    "Player looses",
			answer1: 3,
			answer2: 1,
			want:    2,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {

			result := gamelogic.CalculateWinner(tt.answer1, tt.answer2)
			// Use the new assert.Equal() helper to compare the expected and
			// actual values.
			assert.Equal(t, result, tt.want)
		})
	}

}
func TestGetIntAnswerValue(t *testing.T) {
	tests := []struct {
		name   string
		answer string
		want   int
	}{
		{
			name:   "Rock",
			answer: "rock",
			want:   1,
		},
		{
			name:   "Scissors",
			answer: "scissors",
			want:   3,
		},
		{
			name:   "Invalid",
			answer: "doesnotexist",
			want:   0,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {

			result := gamelogic.GetIntAnswerValue(tt.answer)
			// Use the new assert.Equal() helper to compare the expected and
			// actual values.
			assert.Equal(t, result, tt.want)
		})
	}
}
func TestGetStringAnswerValue(t *testing.T) {
	tests := []struct {
		name   string
		answer int
		want   string
	}{
		{
			name:   "Rock",
			answer: 1,
			want:   "rock",
		},
		{
			name:   "Scissors",
			answer: 3,
			want:   "scissors",
		},
		{
			name:   "Invalid",
			answer: 0,
			want:   "",
		},
		{
			name:   "Invalid",
			answer: 4,
			want:   "",
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {

			result := gamelogic.GetStringAnswerValue(tt.answer)
			// Use the new assert.Equal() helper to compare the expected and
			// actual values.
			assert.Equal(t, result, tt.want)
		})
	}
}
