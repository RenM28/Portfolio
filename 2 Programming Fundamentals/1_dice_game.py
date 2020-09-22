# Ren Gernes
# This program will simulate a dice roll game using two six-sided die

import random

do_again = "y" # sets up first while condition

# greeting for users
print()
print("Welcome! Come one come all to the dice game of the century!")
print()

while do_again == "y":

    # initialized variables
    target = 0
    condition = 0
    # initial dice roll
    roll_1 = random.randint(1,6)
    roll_2 = random.randint(1,6)
    roll_t = roll_1 + roll_2
    
    input("Press Enter to roll.")

    print("You rolled a %d and a %d, for a total of %d." %(roll_1,roll_2,roll_t))

    '''
    If dice roll is 7 or 11, player will win and game ends or starts over
    If dice roll is 2, 3, or 12, player loses and game ends or starts over
    If dice roll is anything else, the game continues with new target
    as whatever the user just rolled
    '''
    if roll_t == 7:
        condition = 0 # sets up condition for game to end
        print("You win! Now's the time to buy a lotto ticket you lucky duck!")
    elif roll_t == 11:
        condition = 0
        print("You win! Now's the time to buy a lotto ticket you lucky duck!")
    elif roll_t == 2 or roll_t == 3 or roll_t == 12:
        condition = 0
        print("Big oof mate, ya lost. Talk about bad luck...")
    else:
        target = roll_t
        condition = roll_t # sets up condition for game to continue
        print("Your target is now %d. Don't roll a 7 or else!" % roll_t)
        print()

    # game will continue until the condition is no longer equal to the total roll
    while roll_t == condition:
        
        # sets up new dice roll
        roll_1 = random.randint(1,6)
        roll_2 = random.randint(1,6)
        roll_t = roll_1 + roll_2

        input("Press Enter to roll.")

        print("You rolled a %d and a %d, for a total of %d." %(roll_1,roll_2,roll_t))

        '''
        If dice roll is equal to previous roll, player loses and game ends or starts over
        If dice roll is equal to 7, player wins and game ends or starts over
        If dice roll is anything else, new target number is created and player keeps rolling
        '''
        if roll_t == target:
            condition = 0
            print("Oh snap son! Look at you; a winner in the flesh!")
        elif roll_t == 7:
            condition = 0
            print("Aww looks like 7 just ain't ya lucky number... better luck next time!")
        else:
            condition = roll_t
            target = roll_t
            print()
            
    # asks user if they'd like to replay game                    
    print()
    do_again = input("Would you like to play again good compatriot? (y or n)?").strip().lower()
    print() 

# obligatory polite sign off
print("Have a grand ol' day and thanks for playing!")
print()
