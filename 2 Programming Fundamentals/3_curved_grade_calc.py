# Ren Gernes
# This program reads a text file separated by tabs where the first line
# will be skipped. Each student's average will be displayed in a table.
# A summary of results for all students will also be displayed at end.

from stats_lib import * # imports statistics file for calculations

# initialized lists and variables
test_scores = []
names_list = []
count = 0
avg_list = []
WIDTH = 50 # width for main section
SM_WIDTH = 25 # width for summary section

# prints heading that will be displayed to user
def heading():
    print("*"*WIDTH)
    print("Curved Grade Calculator".center(WIDTH))
    print("*"*WIDTH)

# printing criteria for categories displayed in summary section
# inputs: label and numerical value of desired statistic 
def print_sum(name, value):
    print("%-10s%15.2f" %(name, value))

# calculates letter grade of each student based on given curve
# inputs: average of each student, mean of whole class, standard deviation
# of student averages
# output: list of curved grades for each student
def calc_letter_grade(student_avg, class_avg, std):
    letters_list = [] # initializes list
    # runs through student averages to find which letter category each
    # belongs to
    for i in range(len(student_avg)):
        if student_avg[i] >= class_avg + 1.25*std:
            letter_grade = "A"
        elif student_avg[i] >= class_avg + 0.25*std:
            letter_grade = "B"
        elif student_avg[i] >= class_avg - 1*std:
            letter_grade = "C"
        elif student_avg[i] >= class_avg - 2*std:
            letter_grade = "D"
        else:
            letter_grade = "F"
        letters_list.append(letter_grade) # adds all grades to list
    return letters_list

# determines number of each letter grade a class of students receives
# input: list of grades
# output: frequency dictionary where key = letter grade and value = number of
# times that letter grade appeared (frequency)
def find_amount_of_each_letter(grades):
    freqs = {} # initializes frequency dictionary
    for grade in grades:
        if grade in freqs:
            freqs[grade] += 1 # if grade appears again, adds 1 to total
        else:
            freqs[grade] = 1 # if grade is yet to appear, starts at 1
    return freqs # returns dictionary

heading() # calls function to print heading
fname = input("Enter file name: ")
fvar = open(fname, "r")
print() # formatting stuff
print("*Course Final Grades Report*".center(WIDTH))
print("%-10s%15s%25s" %("Name", "Average","Grade"))
print("-"*WIDTH)

first_line = True # sets up first line to be skipped
for line in fvar:
    if first_line == False:
        count += 1 # keeps track of number of times program runs through loop
        parts = line.split("\t") # split lines by tab
        name = parts[0]
        names_list.append(name) # adds each student name to list
        for part in parts[1:]: 
            test_scores.append(int(part)) # casts grades as integers
        avg = find_mean(test_scores) # finds individual student means
        avg_list.append(avg) # compiles student means into list
        test_scores = [] # resets test score list after line ends
    first_line = False # starts calculations for proceeding lines


# following lines perform calculations based on functions from stats file
# or current file
mean = find_mean(avg_list) # class mean
minimum = find_min(avg_list) # minimum grade of class
maximum = find_max(avg_list) # maximum grade of class
var = find_variance(avg_list) # variance of student grades
stdev = find_stdev(avg_list) # standard deviation of student grades
# calls function to determine curved grades for students
grade_list = calc_letter_grade(avg_list, mean, stdev)
mode = find_mode(grade_list) # mode of class grades (most received letter grade)

# prints name, average, and grade received for each student in class,
# for the amount of lines in the text file
for i in range(count):
    print("%-10s%15.2f%25s" %(names_list[i], avg_list[i], grade_list[i]))

# calls function to determine the amount of times each letter grade was
# received; outputs as dictionary
num_grades = find_amount_of_each_letter(grade_list)
avg_list.sort() # sorts student averages
median = find_median(avg_list) # median of class grades (middle value)

# formatting and print statements for summary section
print() 
print("Summary".center(SM_WIDTH))
print("-"*SM_WIDTH)
print_sum("Mean", mean)
print_sum("Median", median)
print_sum("StDev", stdev)
print_sum("Minimum", minimum)
print_sum("Maximum", maximum)
print("%-10s%7s" %("Most common grade:", mode[0]))
print("%-10s%15d" %("# of A's", num_grades["A"]))
print("%-10s%15d" %("# of B's", num_grades["B"]))
print("%-10s%15d" %("# of C's", num_grades["C"]))
print("%-10s%15d" %("# of D's", num_grades["D"]))
print("%-10s%15d" %("# of F's", num_grades["F"]))
print("Thank you for using this program.")


