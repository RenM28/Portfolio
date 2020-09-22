# Ren Gernes
# This program will allow you to analyze the sentiment behind a single user entered
# tweet or a selected file. The sentiment will be judged based on the ratio values
# of each adjective. Positive ratios are associated with positive sentiments and
# vice versa
# Certain words will also have a multiplier effect on the ratios of the adjectives

# Credit to SentiWords (used to determine sentiment scores):
# Gatti L., Guerini M. & Turchi M. "SentiWords: Deriving a High Precision and High Coverage Lexicon for Sentiment Analysis". 
# IEEE Transactions on Affective Computing 7.4 (2016): 409-421.

# prints title/description of each component being printed
def print_banner():
    print()
    print("*** Analysis ***".center(45))
    print("%-15s%10s%10s%10s" %("Word", "Score", "Mult", "Contrib"))
    print("-" * 45)

# prints menu of choices for user and returns number chosen
# outputs: number user chooses from menu
def print_menu_and_return_choice():
        print("What would you like to do?")
        print("1. Analyze a single tweet")
        print("2. Analyze a file")
        print("3. Exit")
        user_choice = int(input("Enter the number of your choice: "))
        return user_choice

# opens text file entered by user and converts all line breaks to spaces
# in order to turn the text into a single string
# outputs: text as string
def open_and_convert_file_to_string():
    fname = input("Enter name of file to analyze: ")
    fvar = open(fname, "r")
    text = fvar.read()
    text = text.replace("\n", " ")
    fvar.close()
    return text
    
# Reads in a tab delimited file entered by the user and creates dictionary
# with keys as adjectives and values as sentiment scores
# Note: skips over comment lines that start with #
# Input: file name
# Output: dictionary of adjectives and associated sentiment scores
def read_sentiments(fname):
    fvar = open(fname, "r")
    result = {}
    for line in fvar:
        line = line.strip()
        if line[0] != "@" and line != "": # skips commented lines
            if "#a" in line: # keeps track of only adjectives
                parts = line.split("\t")
                if float(parts[1]) != 0:
                    # stores name of each adjective up to the #
                    adjective = parts[0][:parts[0].index("#")].lower()
                    score = float(parts[1])
                    result[adjective] = score
    fvar.close()
    return result

# Takes in what user entered and returns all words without punctuation
# Input: word user entered
# Output: word without punctuation
def remove_punct(word):
    # if word ends in a nonnumeric or alphabetic char, that char is removed
    if word[-1:] not in "abcdefghijklmnopqrstuvwxyz0123456789":
        word = word[:-1]
    else:
        word = word # returns original word if no punctuation
    return word

# Takes in line of text and dictionary of sentiment scores created by
# read_sentiments function and computes total sentiment score for line
# of text
# Inputs: a line of text either from a file or entered by the user and
# a dictionary of adjectives and their corresponding sentiment values
# Output: total sentiment score of line of text
def analyze_text_and_print(adjectives, ratios):
    adjectives = adjectives.strip().lower()
    parts = adjectives.split(" ")
    score_total = 0
    list_of_scores = [] # sets up list of scores so total sentiment can be calc later
    for i,part in enumerate(parts):
        found = False
        mult = 1 # initializes mult
        if part in ratios:
            found = True
            orig_score = ratios[part]
            mult_score = orig_score # sets mult = original score to start
            # the following if statements deal with multipliers based on
            # previous words
            if i > 0:
                previous_word = remove_punct(parts[i-1])
                if previous_word == "not":
                    mult = -1
                    mult_score *= mult
                elif previous_word == "really" or previous_word == "very" or previous_word == "totally" or previous_word == "extremely" or previous_word == "super":
                    mult = 2
                    mult_score *= mult
                elif previous_word == "slightly" or previous_word == "pretty" or previous_word == "mildly" or previous_word == "somewhat":
                    mult = 0.5
                    mult_score *= mult
                elif previous_word == "too":
                    mult = -0.5
                    mult_score *= mult
                else:
                    mult = 1
                    mult_score = orig_score
            if i > 1:
                word_before_previous = parts[i-2] 
                if word_before_previous == "not":
                    mult *= -1
                    mult_score *= -1
        else:
            part = remove_punct(part) # removes punctutation with function
            if part in ratios:
                found = True
                orig_score = ratios[part]
                mult_score = orig_score # initializes multiplier as original score
                # if statements look at 2 previous words for each current word
                # in order to determine necessary multiplier
                if i > 0:
                    previous_word = remove_punct(parts[i-1])
                    if previous_word == "not":
                        mult = -1
                        mult_score *= mult
                    elif previous_word == "really" or previous_word == "very" or previous_word == "totally" or previous_word == "extremely" or previous_word == "super":
                        mult = 2
                        mult_score *= mult
                    elif previous_word == "slightly" or previous_word == "pretty" or previous_word == "mildly" or previous_word == "somewhat":
                        mult = 0.5
                        mult_score *= mult
                    elif previous_word == "too":
                        mult = -0.5
                        mult_score *= mult
                    else:
                        mult = 1
                        mult_score = orig_score
                if i > 1:
                    word_before_previous = parts[i-2] 
                    if word_before_previous == "not":
                        mult *= -1
                        mult_score *= mult
        if found == True:
            score_total += mult_score
            list_of_scores.append(mult_score) # appends each score to list
            print("%s%-14s%10.4f%10.4f%10.4f" %(part[0].upper(),part[1:],orig_score,mult,mult_score))
    return score_total, list_of_scores

# Takes in total sentiment score from analyze_text function and returns
# a string that displays how positive or negative it is
# Input: sentiment score from analyze_text function
# Output: string detailing how positive/negative score is
def judge_sentiment(sentiment_score):
    total_score = 0
    for i in range(len(sentiment_score)):
        total_score += sentiment_score[i]
    if total_score > 0.5:
        sentiment = "positive"
    elif total_score > 0:
        sentiment = "slightly positive"
    elif total_score == 0:
        sentiment = "neutral"
    elif total_score >= -0.5:
        sentiment = "slightly negative"
    else:
        sentiment = "negative"
    return total_score, sentiment
    
# main
fname = input("Enter name of sentiment words file: ")
ratios = read_sentiments(fname)
num_of_choice = 0
while num_of_choice != 3: # as long as user does not quit execute code
    num_of_choice = print_menu_and_return_choice() # brings up menu
    if num_of_choice == 1: # lets user enter any string
        phrase = input("Enter text to analyze: ")
        print_banner()
        phrase_score, list_of_scores = analyze_text_and_print(phrase, ratios)
        total_score, sentiment = judge_sentiment(list_of_scores)
        print()
        print("With a total score of %.4f, the text is judged %s." % (total_score, sentiment))
        print()
    elif num_of_choice == 2: # lets user enter any text file
        phrase = open_and_convert_file_to_string()
        print_banner()
        phrase_score, list_of_scores = analyze_text_and_print(phrase, ratios)
        total_score, sentiment = judge_sentiment(list_of_scores)
        print()
        print("With a total score of %.4f, the text is judged %s." % (total_score, sentiment))
        print()

print("Thank you for using this program.") # Yeehaw, that'd be all pardner!
