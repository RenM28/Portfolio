# Ren Gernes
# This library contains several functions for analyzing lists of numbers

import math

# the find_min function will return the minimum number in a list of numbers
# MUST CONTAIN AT LEAST ONE VALUE
# input: list of numbers
# output: minimum in that list
def find_min(vals):
    mini = vals[0]
    for val in vals:
        if val < mini:
            mini = val
    return mini

# the find_max function will return the maximum number in a list of numbers
# input: the list of numbers (MUST CONTAIN AT LEAST ONE VALUE)
# output: the maximum in the list
def find_max(vals):
    maxi = vals[0]
    for val in vals:
        if val > maxi:
            maxi = val
    return maxi

# the find_spread function returns the difference between the min and
# max values in the list
# input: list of numbers (MUST CONTAIN AT LEAST ONE)
# output: the difference between the max and min
def find_spread(vals):
    return find_max(vals) - find_min(vals)

# the find_mean function finds the traditional average of list of nums
# input: list of nums (MUST CONTAIN AT LEAST ONE VALUE)
# output: arithmetic mean of list of nums
def find_mean(vals):
    total = 0
    for val in vals:
        total += val
    avg = total/len(vals)
    return avg

# the find_median function finds middle value in the list
# list need not be sorted first, and will not be sorted
# when we return. Instead, a new list that is a copy of
# the old one will be created, and that is what will be sorted.
# So, original values will be preserved
# input: list of nums (MUST CONTAIN AT LEAST ONE VALUE)
# output: median - middle value - of list
def find_median(vals):
    copy = list(vals) # makes brand new list with values in vals
    copy.sort()
    # distinguish between even-sized lists and odd-sized lists
    # odd-sized: 1 2 3 4 5   len is 5   5//2 -> 2
    # even-sized: 1 2 3 4 5 6   len is 6   6//2 -> 3
    if len(vals) % 2 == 1: # odd-sized list
        return vals[len(vals) // 2]
    else: # even-sized list
        pos = len(vals) // 2
        hi = vals[pos]
        lo = vals[pos-1]
        return (hi + lo) / 2

# find_variance finds the population variance for the values in the list
# input: the list of numbers (MUST BE AT LEAST ONE VALUE)
# output: the variance
def find_variance(vals):
    mean = find_mean(vals)
    total = 0
    for val in vals:
        total += (val-mean)**2
    variance = total/len(vals)
    return variance

# find_stdev finds the population standard deviation for values in list
# input: list of numbers (MUST BE AT LEAST ONE VALUE)
# output: standard deviation
def find_stdev(vals):
    return math.sqrt(find_variance(vals))

# find_mode function - to be copied to stats.py
# input: list of numbers
# output: list that lists items that appeared most (i.e. the mode)

def find_mode(vals):
    mode = [] # will eventually return this
    freqs = {} # will store nums and corresponding freqs
    # key will come from vals, and values will be frequencies
    most = 0 # largest frequency so far
    for val in vals:
        if val in freqs:
            freqs[val] = freqs[val] + 1
        else:
            freqs[val] = 1
        if freqs[val] > most:
            most = freqs[val] # newly updated frequency is highest so far
    # we now have dictionary of all our original vals and how frequently
    # they appear, also know highest frequency. Just don't know which vals
    # have that frequency. We'll now determine that and add those entries to
    # our mode list
    for val in freqs:
        if freqs[val] == most:
            mode.append(val) # add val to mode list
    return mode


    
    








































