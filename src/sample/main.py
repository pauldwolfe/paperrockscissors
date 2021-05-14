
import random
from func.logic import logic

class Games:
  def __init__(myobj, num_of_games):
    myobj.num_of_games = num_of_games

num_of_games = int(input("\n\nEnter the number of games you want to play: "))
p1 = Games(num_of_games)
logic(num_of_games)

