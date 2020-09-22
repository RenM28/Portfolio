# Ren Gernes

'''
This program will calculate our compatibility
based on your responses to several questions
'''

print("***********************************************")
print("             Marvelous Matchmaker")
print("        Looking for marriage material")
print("                Puppylove, Inc.")
print("***********************************************")

instructions = """
This program will calculate our compatibility using a very
advanced and scientifically accurate process. Therefore, it
is imperative you read these instructions VERY carefully. Or
else you may ruin your chances of marrying me, and that would
be a tragedy. Please answer the next 5 questions truthfully.
Type 5 to indicate you strongly disagree, 4 if you disagree,
3 if you have no preference, 2 if you agree, and 1 if you
strongly agree. The lower your total score at the end,
the better.

Good luck good fellow ;) """

print(instructions)

total = 0 #initializes total score at 0

#Each chunk of code will compare a user's response to the ideal response
#Questions are weighted based on importance to me

print() # empty line

print("Question 1")
print("----------")
Q_1 = int(input("Cats are the best animal in the universe: "))
if Q_1 == 1:
    total = total
else:
    total = abs(1 - Q_1) * 5 + total

print() # empty line

print("Question 2")
print("----------")
Q_2 = int(input("Staying in a fancy hotel is better than camping: "))
if Q_2 == 4:
    total = total
else:
    total = abs(4 - Q_2) + total

print() # empty line

print("Question 3")    
print("----------")
Q_3 = int(input("I am capable of laughing at myself: "))
if Q_3 == 2:
    total = total
else:
    total = abs(2 - Q_3) * 4 + total

print() # empty line

print("Question 4")
print("----------")
Q_4 = int(input("Country is the absolute worst: "))
if Q_4 == 5:
    total = total
else:
    total = abs(5 - Q_4) * 2 + total

print() # empty line

print("Question 5")
print("----------")
Q_5 = int(input("I wanna be the very best like no one ever was: "))
if Q_5 == 1:
    total = total
else:
    total = abs(1 - Q_5) * 10 + total

print() #empty line

print("Your final score is %d." % total) #prints total score

#Scale that determines if whether the user is a good match for me
if total == 0:
    a = """
WOOOOOO!! You win! You must be my soul mate; my one and only. Or maybe
you're me from a parallel universe. The world may never know. """
    print(a)
elif total <= 20:
    b = """
Hmm I see some potential in you to be my partner. Let's go on a date.
If all goes well, we'll be married within the week. """
    print(b)
elif total <= 40:
    c = """
I dunno how else to say this, bro. Romantically, we just ain't compatible.
But dude, we could be like bff's. Let's shoot some hoops sometime. """
    print(c)
else:
    d = """
Uhhhhh... please stay far far away from me. I have a ticket for the first
flight to Mars that's got your name on it. So long sucker! """
    print(d)

print() #empty line
