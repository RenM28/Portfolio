import math

# Ren Gernes
'''
This program will figure out the number of gallons of paint and primer
the user needs in order to paint all the rooms of their house
'''

# constants

WINDOW_DIM = 5*3*144 # converts window dim to sq in
DOOR_DIM = 7*3*144 # converts door dim to sq in
PAINT_COV = 350*144 # converts paint coverage to sq in 
PRIMER_COV = 200*144 # converts primer coverage to sq in

print() # empty line

greeting = """Ahoy matey! If ye 'ave a room t' paint, yer in luck.
Welcome t' th' paint cost calculator me crew 'n I
drew up while sailin' th' seven seas. """

print("****************************************************")
print(greeting)
print("****************************************************")

print()

# 1 gallon of paint: 350 sq ft
# 1 gallon of primer: 200 sq ft

# Asks user for number of rooms and cost of paint and primer
rooms = int(input("How many rooms arr ye wishin' t' paint matey? "))
paint = float(input("1 gallon o' paint'll cost ye: "))
primer = float(input("1 gallon o' primer'll cost ye: "))


print() # empty line

# loop will run for as many rooms as the user inputs
for i in range (rooms):
    
    # prints which room the user is inputing info for
    print ("*** Room %d ***" % (i + 1))
    
    # asks the user to input details about their room that will be used in calculations
    height = int(input("What be th' height o' yer room in inches? "))
    width = int(input("What be th' width o' yer room in inches? "))
    length = int(input("What be th' length o' yer room in inches? "))
    initial_coat = input("This be th' first time yer paintin' yer room (y o' n)? ").lower().strip()
    ceiling = input("Do ye wish t' paint yer ceiling? (y o' n)? ").lower().strip()
    doors = int(input("How many doors do ye 'ave? "))
    windows = int(input("How many windows do ye 'ave? "))

    # calculates the total surface area that needs to be painted
    room_sa = ((2*height*width) + (2*height*length)) - (windows*WINDOW_DIM) - (doors*DOOR_DIM)

    # adds surface area of ceiling if user should want it painted
    if ceiling == "y":
        room_sa = room_sa + (length*width)

    # determines how many gallons of paint and primer are necessary
    paint_gal = math.ceil(room_sa/PAINT_COV)

    #initializes amount of primer needed
    primer_gal = 0

    # calculates primer needed if the room is being painted for the first time
    if initial_coat == "y":
        primer_gal = math.ceil(room_sa/PRIMER_COV)

    # calculates total cost of paint and primer needed
    total_cost = (paint_gal*paint) + (primer_gal*primer)

    # Tells user gallons of paint/primer needed and amount user will have to spend
    print("It be necessary t' buy %d gallons o' paint an' %d gallons of primer." % (paint_gal, primer_gal))
    print("Ye be needin' t' fork over $%.2f." % total_cost)
    
    print() #empty line

print("'Ave a good day matey! Thank ye fer yer time!")
    
