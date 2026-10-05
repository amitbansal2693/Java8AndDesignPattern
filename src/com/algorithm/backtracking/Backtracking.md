When should you think "Backtracking"?
Look for problems asking you to:
generate all possibilities
generate all combinations
generate all permutations
find all valid arrangements
choose elements where there are multiple possible choices
solve a constraint problem

Most backtracking solutions use recursion.
But remember the distinction:
Recursion = a function calls itself.
Backtracking = make a choice → explore → undo the choice → try another choice.
So recursion is usually the mechanism we use to implement backtracking.

Backtracking template

***
backtrack(current state):

    if solution is complete:
        save solution
        return

    for each possible choice:

        make choice

        backtrack(next state)

        undo choice

***

Backtracking vs Recursion
Don't confuse them.
Recursion is a technique:
function calls itself
Backtracking is a problem-solving strategy:
make choice
→ recursively explore
→ undo choice
→ try another choice



The problems we'll do
I recommend this progression:
Level 1 — Learn the pattern
Subsets
Subsets II — duplicates
Permutations
Permutations II
Level 2 — Choice + target
Combination Sum
Combination Sum II
Letter Combinations of a Phone Number
Level 3 — Constraints
Generate Parentheses
Palindrome Partitioning
Word Search
Level 4 — Classic hard backtracking
N-Queens
Sudoku Solver

The most important mental model
Suppose:
[1, 2, 3]
You want all subsets.
At 1, you have two choices:
[]
/    \
choose 1   skip 1
[1]       []
Then from [1]:
[1]
/   \
[1,2]   [1]
And so on.
You're building a decision tree.


