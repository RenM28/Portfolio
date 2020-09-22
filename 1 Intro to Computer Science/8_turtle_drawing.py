# Imports important packages
from turtle import Turtle, Screen
import math

# The following functions change sam the turtle from leaving a trail
# or not and assign his status to a variable
def line_status_up(status):
    sam.penup()
    status = "up"
    return status

def line_status_down(status):
    sam.pendown()
    status = "down"
    return status

# Sets up the screen aka sam's habitat
win = Screen()
win.setup(450,450,100,50)
win.bgcolor("White")

# Settings that bring sam to life
sam = Turtle()
sam.hideturtle()
sam.speed(0)

# Initializes sam to not leave a trail
status = "up"
line_status_up(status)

# Initializes variables
total = 0
x1 = 0
y1 = 0
x2 = x1
y2 = y1

# Asks user for file name that code will read
fname = input("Enter the name of the drawing file: ")
fvar = open(fname, "r")

#main code
for line in fvar:
    # Gets rid of white space and breaks up parts by spaces
    line = line.strip()
    parts = line.split(" ")
    if (len(parts) == 1):
        line_status_up(status) # Sets line status to up when text file reads "stop"
        status = line_status_up(status) # Updates the status variable
    else:
        # Breaks text file into parts
        line_color = parts[0].upper() 
        x1 = int(parts[1])
        y1 = int(parts[2])
        sam.color(line_color)
        # Calculates distance traveled when sam is leaving a trail only
        if status == "down":
            d = math.sqrt((x2-x1)**2+(y2-y1)**2)
            total = total + d
        # Updates values of x2 and y2
        x2=x1
        y2=y1
        sam.goto(x1,y1)
        line_status_down(status) # Sets line status to up when text file does not read "stop"
        status = line_status_down(status) # Updates status variable
        
# Creates second turtle who writes the total distance of sam's trail like the true pal he is
score = Turtle()
score.hideturtle()
score.color("Maroon")
score.penup()
score.goto(20,-150)
score.write("Total distance of lines drawn: %.2f" %total)

# Closes file
fvar.close()

# Ends program when user hits enter
input("Press enter to end the program por favor: ")
win.bye()
