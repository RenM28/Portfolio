# Ren Gernes
# This program will determine the health risk of potential clients

# defines a welcome statement to be printed to the user
def welcome():
    print("*" * 80)
    print("Health Risk Calculator".center(80))
    print("*" * 80)
    print()
    plug = """This lovely program will help determine a potential
client's health risk level so you don't empty your pockets quite
so fast. After all, insuring people is all about the money. This
refined program measures health in terms of body-mass index, blood
pressure, age, and family history of a select few diseases. """
    print(plug)
    print()

# returns category of blood pressure as a string based on systolic and diastolic blood pressure
def rank_blood_pressure(systolic,diastolic):
    if systolic < 120 and diastolic < 80:
        bp_category = "normal"
    elif systolic < 130 and diastolic < 80:
        bp_category = "elevated"
    elif systolic < 140 or diastolic < 90:
        bp_category = "stage 1"
    elif systolic <= 180 or diastolic <= 120:
        bp_category = "stage 2"
    else:
        bp_category = "crisis"
    return bp_category

# returns blood pressure risk as an integer based on the category the user falls under
def calc_blood_pressure_risk():
    if bp_category == "normal":
        bp_risk = 0
    if bp_category == "elevated":
        bp_risk = 15
    if bp_category == "stage 1":
        bp_risk = 30
    if bp_category == "stage 2":
        bp_risk = 75
    if bp_category == "crisis":
        bp_risk = 100
    return bp_risk

# returns the user's bmi as a float with inputs weight and height
def calc_bmi(weight,height):
    weight = weight/2.2046
    height = height*0.0254
    bmi = weight/(height*height)
    return bmi

# returns category as a string based on bmi score
def rank_bmi(bmi):
    if bmi < 25:
        bmi_category = "normal"
    elif bmi < 30:
        bmi_category = "overweight"
    else:
        bmi_category = "obese"
    return bmi_category

# returns risk factor based on bmi as an integer based on bmi category 
def calc_bmi_risk():
    if bmi_category == "normal":
        bmi_risk = 0
    if bmi_category == "overweight":
        bmi_risk = 30
    if bmi_category == "obese":
        bmi_risk = 75
    return bmi_risk

# returns risk factor as an integer based on age of user 
def calc_age_risk(age):
    if age < 30:
        age_risk = 0
    elif age < 45:
        age_risk = 10
    elif age < 60:
        age_risk = 20
    else:
        age_risk = 30
    return age_risk

# returns risk factor as an integer based on family history of diabetes, cancer, and alzheimer's
def calc_fam_disease_risk(diabetes, cancer, alzheimers):
    fam_risk = 0
    if diabetes == "y":
        fam_risk = fam_risk + 10
    if cancer == "y":
        fam_risk = fam_risk + 10
    if alzheimers == "y":
        fam_risk = fam_risk + 10
    return fam_risk

# returns overall risk category based on the risks associated with user's blood pressure, bmi, age, and family history of disease
def rank_overall_risk(bp_risk, bmi_risk, age_risk, fam_risk):
    total_risk = bp_risk + bmi_risk + age_risk + fam_risk
    if total_risk <= 20:
        insurance_cat = "low-risk"
    elif total_risk <= 50:
        insurance_cat = "moderate-risk"
    elif total_risk <= 75:
        insurance_cat = "high-risk"
    else:
        insurance_cat = "uninsurable"
    return insurance_cat

# prints end results with the risk factors right-justified
def print_results():
    print("Age:            %5d" %age_risk)
    print("Body-Mass:      %5d (index = %.2f)" %(bmi_risk,bmi))
    print("Blood Pressure: %5d (%s)" %(bp_risk,bp_category))
    print("Family History: %5d" %fam_risk)
    print()
    print("Overall, this potential client is %s." %insurance_cat)

# main section of code

welcome() # calls welcome function

#while loop runs until user inputs "n," which happens when the user runs out of potential clients to check on
do_again = "y"
while do_again == "y":
    # user inputs information that will be used to determine risk factors
    print("Please enter the following information about your potential client:")
    print()
    age = int(input("Age in years: "))
    height = float(input("Height in inches: "))
    weight = float(input("Weight in pounds: "))
    systolic = float(input("Systolic blood pressure: "))
    diastolic = float(input("Diastolic blood pressure: "))
    print("Family history: ")
    diabetes = input("Diabetes (y or n): ").lower().strip()
    cancer = input("Cancer (y or n): ").lower().strip()
    alzheimers = input("Alzheimer's (y or no): ").lower().strip()

    # calls functions that calc category and integer value of blood pressure risk
    bp_category = rank_blood_pressure(systolic,diastolic)
    bp_risk = calc_blood_pressure_risk()

    #calls functions that calc bmi and category and integer value of bmi risk
    bmi = calc_bmi(weight,height)
    bmi_category = rank_bmi(bmi)
    bmi_risk = calc_bmi_risk()

    age_risk = calc_age_risk(age) # calls age risk function

    fam_risk = calc_fam_disease_risk(diabetes, cancer, alzheimers) # calls history of family disease function

    insurance_cat = rank_overall_risk(bp_risk, bmi_risk, age_risk, fam_risk) # calls total risk function

    print_results() #calls function to print results

    # Has user determine if they'd like to run the code again with "y" or "n"
    do_again = input("Would you like to determine the health risk of another potential client? (y or n): ").lower().strip()

    print()
    print("*" * 80)
    print()

print("Au revoir. Please use the info you have gained from this program with discretion.") # sign off
                     

