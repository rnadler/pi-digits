import math
from timeit import default_timer as timer

def pi():
  r = 4*(4*arccot(5) - arccot(239))
  return str(r)[0] + '.' + str(r)[1:-5]

def arccot(x):
  total = power = 10**319 // x
  divisor = 1
  while abs(power) >= divisor:
    power = -power // x**2
    divisor += 2
    total += power // divisor
  return total

def timed_pi():
  start = timer()
  rv = pi()
  end = timer()
  print(end - start)
  return rv
  
print("314 digits of Pi " + timed_pi())

