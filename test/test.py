#Here I will test the main logic of the game, which will let us know who get a point
#First test on the tie result
comp_input = 'S'
user_input = 'S'

print("TEST TIE")
if ( user_input=='R' and comp_input=='P' ) or ( user_input=='P' and comp_input=='S' ) or ( user_input=='S' and comp_input=='R' ):
	print("LOSE")
elif ( user_input=='P' and comp_input=='R' ) or ( user_input=='S' and comp_input=='P' ) or ( user_input=='R' and comp_input=='S' ):
	print("WIN")
else:
	print("TIE")


#Win result
print("TEST WIN")
comp_input = 'P'
user_input = 'S'


if ( user_input=='R' and comp_input=='P' ) or ( user_input=='P' and comp_input=='S' ) or ( user_input=='S' and comp_input=='R' ):
	print("LOSE")
elif ( user_input=='P' and comp_input=='R' ) or ( user_input=='S' and comp_input=='P' ) or ( user_input=='R' and comp_input=='S' ):
	print("WIN")
else:
	print("TIE")


#Test on the lose result
print("TEST LOSE")
comp_input = 'R'
user_input = 'S'


if ( user_input=='R' and comp_input=='P' ) or ( user_input=='P' and comp_input=='S' ) or ( user_input=='S' and comp_input=='R' ):
	print("LOSE")
elif ( user_input=='P' and comp_input=='R' ) or ( user_input=='S' and comp_input=='P' ) or ( user_input=='R' and comp_input=='S' ):
	print("WIN")
else:
	print("TIE")