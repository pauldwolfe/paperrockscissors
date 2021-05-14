import random
from scores import scores

def logic(num_of_games):
    games=(num_of_games)
    my_dict={'R':"Rock",'P':"Paper",'S':"Scissors"}
    user_count=0
    comp_count=0
    tie_count=0

    while(tie_count+comp_count+user_count<games):
	#LOOP STARTS UNTIL YOU REACH NUMBER OF GAMES CHOSEN;

		flag=0

		user_input=raw_input("\nSelect Rock, Paper or Scissors: ")[0]
		user_input=user_input.upper()
		#The [0] after the input() will assign the first charcter of input to the variable;
		#So no much misspelling happens; 

		for i in my_dict.keys():
			if(user_input==i):		
				flag=1						#Will check if the input is Rock, Paper or Scissors;
				break						#If not, run the loop again;
		if(flag!=1):				
			print("INVALID INPUT")
			continue



		comp_input=random.choice(list(my_dict.keys()))	#Random choice from the dictionary to get computer's input;

		#Will determine who gets the points, user input vs computer input
		print("Computer's Input: ", my_dict[comp_input])
		if ( user_input=='R' and comp_input=='P' ) or ( user_input=='P' and comp_input=='S' ) or ( user_input=='S' and comp_input=='R' ):
			comp_count+=1
		elif ( user_input=='P' and comp_input=='R' ) or ( user_input=='S' and comp_input=='P' ) or ( user_input=='R' and comp_input=='S' ):
			user_count+=1
		else:
			tie_count+=1
			print("TIE")

	#This will get the scores	
    scores(user_count,comp_count)
		#ENDS;