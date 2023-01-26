/*
    Recursion and Dynamic Programming

    - can be built off of subproblems

    - "design algo to compute the nth ..."
    - "write code to list the first n ..."
    - "implement method to compute all ..."
*/


/* 
    Approaches:

    Bottom-Up Approach
    - start with solution for simple case (list with 1 element)
    - find solution for next case (2 elements), and so on
    - Build solution from one case off previous case(s)

     Top-Down Approach
    - divide problem for case N into subproblems
    - look out for overlap b/w cases

     Half-and-Half Approach
     - divide data set in half
     - binary search: find half w/ val in sorted array
     - merge sort: sort halves, then merge sorted halves
*/

// Recursive algorithms can be space inefficient 
//  -> O(n) memory at recursion depth of n



/*
    Dynamic Programming and Memoization

    - find overlapping subproblems in recursive algorithm,
        cache repeated calls for future recursive calls
*/


/*
    Fibonacci Numbers
    - compute the nth Fibonacci number:
*/

/*
    Recursive nth Fibonacci number

    - leaves on tree are all fib(1) or fib(0) -> base cases
    - total # nodes = O(2^n), which is the runtime (upper bound)
*/
int fibonacci(int i) {
    if (i == 0) return 0;
    if (i == 1) return 1;
    return fibonacci(i - 1) + fibonacci(i - 2);
}

/*
    Top-Down Dynamic Programming (Memoization)
    - cache computed results for identical nodes

    - only call fib(n) O(n) times, with O(n) possible falues for fib
    - each time fib(i) computed, cache result

    - leaves only on left branches
    - each node has 1 child, with 2n children in tree
        = runtime of O(n)
*/
int fibonacci(int n) {
    return fibonacci(n, new int[n + 1]);
}

int fibonacci(int i, int[] memo) {
    if (i == 0 || i == 1) return i;

    if (memo[i] == 0) {
        memo[i] = fibonacci(i - 1, memo) + fibonacci(i - 2, memo);
    }
    return memo[i];
}

/*
    Bottom-Up Dynamic Programming
    - compute base cases first, then build on
*/
int fibonacci(int n) {
    if (n == 0) return 0;
    else if (n == 1) return 1;

    int[] memo = new int[n];
    memo[0] = 0;
    memo[1] = 1;
    for (int i = 2; i < n; i++) {
        memo[i] = memo[i - 1] + memo[i - 2];
    }
    return memo[n - 1] + memo[n - 2];
}

/*
    Without memo table:
    - only use memo[i] for memo[i+1] and memo[i+2]
    - can remove table and store vars

    store results from last 2 Fibs into a and b
        for each iteration:
        - compute next val (c = a + b)
        - move (b, c = a + b) into (a, b)
*/
int fibonacci(int n) {
    if (n == 0) return 0;
    int a = 0;
    int b = 1;
    for (int i = 2; i < n; i++) {
        int c = a + b;
        a = b;
        b = c;
    }
    return a + b;
}