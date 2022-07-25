************************************************************************
file with basedata            : md340_.bas
initial value random generator: 2013815531
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  22
horizon                       :  151
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     20      0       21        2       21
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          3           5   6  13
   3        3          3           8  13  17
   4        3          3           6   7  14
   5        3          2          12  14
   6        3          3           9  10  12
   7        3          3          11  13  21
   8        3          3           9  10  20
   9        3          1          19
  10        3          1          18
  11        3          2          12  16
  12        3          2          15  19
  13        3          1          20
  14        3          2          15  16
  15        3          1          17
  16        3          2          17  19
  17        3          1          20
  18        3          1          21
  19        3          1          22
  20        3          1          22
  21        3          1          22
  22        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     3       0    7    4    0
         2     3       8    0    0   10
         3     6       7    0    0   10
  3      1     2       4    0    0    4
         2     2       0    7    1    0
         3     8       4    0    1    0
  4      1     2       0    4    5    0
         2     3       0    4    0   10
         3    10       2    0    5    0
  5      1     1       0    5    4    0
         2     4       6    0    4    0
         3     5       5    0    2    0
  6      1     1       0    5    0    8
         2     7       5    0    5    0
         3    10       4    0    0    6
  7      1     3       0    6    0    9
         2     3       7    0    0    9
         3     8       5    0    0    9
  8      1     1       0    7    0    8
         2     8       0    5    0    7
         3     9       7    0    0    7
  9      1     4       0    7    0    8
         2     5      10    0    0    5
         3     6      10    0    0    4
 10      1     2       7    0    8    0
         2     4       2    0    6    0
         3     6       0    6    4    0
 11      1     6       9    0    0    7
         2     9       0    8    0    4
         3    10       0    4    9    0
 12      1     6       0    6    0    7
         2     7       0    5    3    0
         3    10       0    4    0    7
 13      1     1       0    7    9    0
         2     5       1    0    0    8
         3     6       0    4    9    0
 14      1     1      10    0    0    6
         2     3       0    8    9    0
         3     4       9    0    0    6
 15      1     2       8    0    9    0
         2     5       0    7    0    3
         3     6       6    0    6    0
 16      1     2       7    0    6    0
         2     8       6    0    0    3
         3     9       5    0    5    0
 17      1     1       0    6    0   10
         2     3       0    4    0    8
         3     9       0    2    0    5
 18      1     2       0    8    8    0
         2     8       0    1    7    0
         3     8       0    4    0    5
 19      1     3      10    0    0    6
         2     4       4    0    0    5
         3     6       0    5    0    5
 20      1     1       0    2    0    5
         2     4       0    2    9    0
         3     5       6    0    7    0
 21      1     4       7    0    0   10
         2     7       0    7    0    9
         3    10       3    0    4    0
 22      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   29   36   71  103
************************************************************************
