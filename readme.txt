/* *****************************************************************************
 *  Operating system: Windows
 *  Compiler: IntelliJ
 *  Text editor / IDE: IntelliJ
 *
 *  Have you taken (part of) this course before: No
 *  Have you taken (part of) the Coursera course Algorithms, Part I or II: No
 *
 *  Hours to complete assignment (optional): 5-6
 *
 **************************************************************************** */

Programming Assignment 1: Percolation


/* *****************************************************************************
 *  Describe the data structures (i.e., instance variables) you used to
 *  implement the Percolation API.
 **************************************************************************** */
1. int
- int n (input n to generate a n-by-n grid)
- int virtualTop (index referring to the virtual top site)
- int virtualBottom (index referring to the virtual bottom site)
- int numberOfOpenSites (keep track of number of open sites)

2. weighted quick-union:
- WeightedQuickUnionUF site (union-find of sites within n-by-n grid)

3. boolean[]
- boolean[] isOpen (boolean array to check if a particular site is open)

/* *****************************************************************************
 *  Briefly describe the algorithms you used to implement each method in
 *  the Percolation API.
 **************************************************************************** */
open(): First, I validate the input indices (row, col) with the validate helper
method and throw an exception for invalid indices. Then, I convert the
(row, col) site into a single integer with the index helper method. After that,
if the site is not opened yet, the site will be marked as open in the isOpen
boolean array while the number of open sites will increase by one. Following
that, I perform union find operations that link the site to its open neighbors
with the union() API.

isOpen(): First, I validate the input indices (row, col) with the validate
helper method and throw an exception for invalid indices. Then, I convert the
(row, col) site into a single integer with the index helper method. isOpen()
will return true if the site is open and false if the site is close. This can
be achieved by creating a boolean array isOpen to track the state (open/close)
of each individual site.

isFull(): First, I validate the input indices (row, col) with the validate
helper method and throw an exception for invalid indices. Then, I convert the
(row, col) site into a single integer with the index helper method. Then, I
check if the root of the site is connected to the virtual top site using the
find() API for union-find data type. isFull() will return true if the site is
connected to the top virtual site and vice versa.

numberOfOpenSites(): Since the number of open sites is always updated in open()
and store in a global instance variable, numberOfOpenSites() will just return
the value of the global instance variable.

percolates(): I check if the root of the virtual top site is connected/share the
same root to the virtual bottom site using the find() API for union-find data
type.

/* *****************************************************************************
 *  First, implement Percolation using QuickFindUF.
 *  What is the largest values of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
100         0.204
200         1.133
400         7.925
800         44.839
850         59.26
900         67.035
1000        98.024

Answer: n = 850 approx.

/* *****************************************************************************
 *  Describe the strategy you used for selecting the values of n.
 **************************************************************************** */
I start from n = 100 and continue doubling n until a value of n that runs more
than one minute. Then, I reduce n by smaller amount to narrow down the range of
n that runs less than one minute.


/* *****************************************************************************
 *  Next, implement Percolation using WeightedQuickUnionUF.
 *  What is the largest values of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
100         0.085
200         0.49
400         0.989
800         5.045
1600        35.267
1900        54.272
1950        59.178
2000        62.009

Answer: n = 1950 approx.


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
The backwash problem



/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
None

/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
None



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
I really love this assignment. It seems intimidating at first, but it is
actually pretty fun and doable. Thank you for marking my assignment and I
appreaciate any comments/feedback to improve my code. Thanks!
