# Ren Gernes
# Creates a randomized list of passwords with specified format

import random

# defines a greeting the user will receive upon opening the program
def welcome():
    print("*" * 50)
    print("Welcome to the Password Generator".center(50))
    print("*" * 50)
    print()

# chooses a random word from the text file the user imports
def gen_random_word(word_list):
    for word in fvar:
        word = word.strip()
        word_list.append(word)
    random_word = random.choice(word_list)
    return random_word

# capitalizes every other letter in a word, starting with the first
def cap_every_other(first_word):
    result = ""
    for i in range(len(first_word)):
        if i % 2 == 0:
            result = result + first_word[i].upper()
        else:
            result = result + first_word[i]
    return result

# replaces specified letters in a word with certain characters
# leaves other letters as is 
def replace_letters(second_word):
    result = ""
    for ch in second_word:
        if ch in "iI":
            result = result + "!"
        elif ch in "eE":
            result = result + "3"
        elif ch in "sS":
            result = result + "$"
        elif ch in "oO":
            result = result + "0"
        else:
            result = result + ch
    return result

# keeps every other letter in a word, starting with the first letter
# capitalizes each letter that is kept
def keep_odd_and_cap(third_word):
    result = ""
    for i in range(len(third_word)):
        if i % 2 == 0:
            result = result + third_word[i].upper()
    return result

# generates random number from 1000 to 9999
def gen_random_num(num):
    num = random.randint(1000,9999)
    number.append(num)
    return num

# main code

welcome() # calls welcome function

file = input("Enter name of file: ") # user chooses file to import
fvar = open(file, "r") # reads specified file

# initialized values
word_list = []
number = []
total_pass = 0
do_again = "y"

while do_again == "y":
    num_pass = int(input("How many passwords would you like to create? "))
    print("Here are your passwords: ")
    for i in range(num_pass):
        # generates three random words
        rand_word_1 = gen_random_word(word_list)
        rand_word_2 = gen_random_word(word_list)
        rand_word_3 = gen_random_word(word_list)
        # calls above functions on random words and stores values to a variable
        r_1 = cap_every_other(rand_word_1)
        r_2 = replace_letters(rand_word_2)
        r_4 = keep_odd_and_cap(rand_word_3)
        r_5 = gen_random_num(number)
        # prints completed password
        print("%s%s*%s%d" % (r_1, r_2, r_4, r_5))
    print() 
    total_pass = total_pass + num_pass # keeps track of number of passwords generated
    # allows while loop to keep running as long as user inputs "y"
    do_again = input("Create another list of passwords? ").lower().strip()

print("Thank you for using this password generator.") # thanks user
print("%d passwords have been generated." % total_pass) # states number of passwords generated




