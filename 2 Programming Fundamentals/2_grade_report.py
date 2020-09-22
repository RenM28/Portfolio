# Ren Gernes
# This program will read a tab-separated file of grades with blank space separating each semester
# It will then calc total number of credit hours and quality points earned per semester
# as well as gpa and whether or not they qualify for dean's list
# Also calc total hours and quality points, plus final gpa and academic standing of all semesters
# put together and prints everything in a neatly formatted display'

WIDTH = 65 # sets width I want to use for whole program

# Creates banner
def heading():
    print("*"*WIDTH)
    print("Grade Report Tool".center(WIDTH))
    print("*"*WIDTH)
    print()

# inputs: letter grade and number of credits earned in a course
# process: multiplies letter grade and credits to get quality points
# output: quality points for single course
def calc_quality_points(letter, credit_hrs):
    if letter == "A":
        points = 4
    elif letter == "A-":
        points = 3.7
    elif letter == "B+":
        points = 3.3
    elif letter == "B":
        points = 3
    elif letter == "B-":
        points = 2.7
    elif letter == "C+":
        points = 2.3
    elif letter == "C":
        points = 2
    elif letter == "C-":
        points = 1.7
    elif letter == "D+":
        points = 1.3
    elif letter == "D":
        points = 1
    elif letter == "D-":
        points = 0.7
    else:
        points = 0
    quality_points = points * credit_hrs
    return quality_points

# prints info for single semester
def print_per_semester_results(semester,credit,q_points,gpa,standing):
    print("%-20s%10d%10.2f%9.2f%16s" %(semester,credit_hours,total_q_points,gpa,standing))

# inputs: gpa for single semester
# process: if gpa is higher than 3.5, sets variable to reflect student made dean's list
# outputs: academic standing per single semester
def test_if_deans_list(gpa):
    if gpa >= 3.5:
        status = "DEAN'S LIST"
    else:
        status = ""
    return status

# inputs: cumulative gpa across all semesters
# process: tests gpa to see if student meets requirements to receive academic recognition
# outputs: academic standing across all semesters
def test_grad_status(gpa):
    if gpa >= 3.9:
        status = "HIGHEST HONORS"
    elif gpa >= 3.75:
        status = "HIGH HONORS"
    elif gpa >= 3.5:
        status = "HONORS"
    else:
        status = ""
    return status

heading() # prints banner using function

fname = input("Enter file name: ") # user enters file to be read
fvar = open(fname, "r")
print()

print("Grade summary: ")
print()

print("%-20s%10s%10s%9s%16s" %("Semester","Hours","Points","GPA","Standing")) # prints heading
print("*"*WIDTH)
# initialized variables
credit_hours = 0 # total semester credits
total_q_points = 0 # total semester quality points
credit_hours_final = 0 # overall credits
q_points_final = 0 # overall quality points
sum_gpa = 0 # gpa's per semester added together
num_gpa = 0 # number of gpa scores earned
gpa_final = 0 # overall gpa

for line in fvar:
    line = line.strip()
    if line == "":
        print_per_semester_results(semester,credit_hours,total_q_points,gpa,standing)
        # updates initialized variables based on what student earned that semester
        credit_hours_final += credit_hours
        q_points_final += total_q_points
        sum_gpa += gpa
        num_gpa += 1
    else:
        parts = line.split("\t")
        if len(parts) == 2:
            semester = parts[1].upper()
            # resets variables each time file reaches line with two tabs
            credit_hours = 0
            total_q_points = 0
        else:
            # labels important parts of each line
            credit = int(parts[2])
            grade = parts[3]
            # calc to keep a running sum of credits and quality points each line of file
            credit_hours += credit
            quality_points = calc_quality_points(grade, credit)
            total_q_points += quality_points
            gpa = total_q_points / credit_hours # calc to determine gpa
            standing = test_if_deans_list(gpa) # tests if student on dean's list

# Extra line of calculations and print line to account for last block of code (semester)            
print_per_semester_results(semester,credit_hours,total_q_points,gpa,standing)
credit_hours_final += credit_hours
q_points_final += total_q_points
sum_gpa += gpa
num_gpa += 1
gpa_final = sum_gpa/num_gpa # calc final gpa by averaging gpa earned each semester
grad_status = test_grad_status(gpa_final) # determines overall academic standing of student


print("*"*WIDTH)

# prints cumulative results
print("%-20s%10d%10.2f%9.2f%16s" %("Cumulative",credit_hours_final,q_points_final,gpa_final,grad_status))

fvar.close() # closes file - el fin
