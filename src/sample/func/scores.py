	#Get and show the score;
def scores(user_count,comp_count):
  print("\n\t\tFINAL SCORE:")
  print("User Score: ",user_count)
  print("Computer Score: ",comp_count)
  if user_count>comp_count:
    print("\n\n\t\tYOU WON!!!!")
  elif user_count<comp_count:
    print("\n\n\t\tYOU LOST!!")
  else:
  	print("\n\n\t\tIT'S A TIE!!")