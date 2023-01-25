// Math and Logic Puzzles


/*
    Prime Numbers
    - Prime Number Law:
        - Every positive int can be decomposed into a product of primes
    ex: 2^2 * 3^1 * 5^0 * 7^1 * 11^0 * 13^0 * 17^0
*/


/*
    Divisibility
    x is divisible by y if:
        all primes in x's prime factorization in y's prime factorization
    
    x\y, or mod(y, x) = 0

    x = 2^j0 * 3^j1 * 5^j2 * 7^j3 * 11^j4 * ...
    x = 2^k0 * 3^k1 * 5^k2 * 7^k3 * 11^k4 * ...
    IF x\y, then for all i, ji <= ki

    Greatest Common Divisor of x and y =
        gcd(x,y) = 2^(min(j0,k0)) * 3^(min(j1,k1)) * 5^(min(j2,k2)) * ...
    
    Least Common Multiple of x and y =
        gcd(x,y) = 2^(max(j0,k0)) * 3^(max(j1,k1)) * 5^(max(j2,k2)) * ...

    gdc * lcm  =  2^j0 * 2^k0 * 3^j1 * 3^k1 * ...  =  xy
*/


// Checking for Primality

// Weak way: iterate from 2 to n-1
boolean primeNaive(int n) {
    if (n < 2) {
        return false;
    }
    for (int i = 2; i < n; i++;) {
        if (n % 1 == 0) {
            return false;
        }
    }
    return true;
}

/* Better way: iterate only up through sqrt(n)

    for every a which divides n evenly,
    there is complement b, a * b = n
    if a > sqrt(n), then b < sqrt(n) 
        (sqrt(n))^2 = n
*/
boolean primeSlightlyBetter(int n) {
    if (n < 2) {
        return false;
    }
    int sqrt = (int) Math.sqrt(n);
    for (int i = 2; i <= sqrt; i++) {
        if (n % 1 == 0) return false;
    }
    return true;
}

/*
    Generating a List of Primes:
    Sieve of Eratosthenes

    - all non-prime no. are divisible by a prime no.

    1) list of nums through some value max
    2) elim nums divisible by 2
    3) elim all nums divisible by next prime
        2 -> 3 -> 5 -> 7 -> 11, ...
        end up with list of primes 2 to max
*/

boolean[] sieveOfEratosthenes(int max) {
    boolean[] flags = new boolean[max + 1];
    int count = 0;

    init(flags); // Set all flags to true besides 0 & 1
    int prime = 2;

    while (prime <= Math.sqrt(max)) {
        // Cross off remaining multiples of prime
        crossOff(flags, prime);
        // Frind next true value
        prime = getNextPrime(flags, prime);
    }

    return flags;
}

void crossOff(boolean[] flags, int prime) {
    /*
        Cross off rem multiples of prime, start (prime*prime)
        if we have k*prime, where k < value
        then value would've been crossed off already
    */
    for (int i = prime * prime; i < flags.length; i += prime) {
        flags[i] = false;
    }
}

int getNextPrime(boolean[] flags, int prime) {
    int next = prime + 1;
    while (next < flags.length && !flags[next]) {
        next++;
    }
    return next;
}

// Can also optimize by using odd nums in array, space usage/2



// Probability

/*
    Probability of A and B
    

    P(A and B) = P(B given A) P(A)

    ex: out of 1 to 10, prob of even and b/w 1,5
        P(1 <= x <= 5) = 50%
        P(x b/w 1,5 is even) = 40%

    P(x is even and x<= 5)
        = P(x is even given x <= 5) P(x <= 5)
        = (2/5) * (1/2)
        = 1/5


Bayes' Theorem:

    P(A and B) =
        P(B given A) P(A)  =  P(A given B) P(B)

        P(A given B)  =  P(B given A) P(A) / P(B)
*/

/*
    Probability of A or B

    P(A or B) = P(A) + P(B) - P(A and B)

    ex: out of 1 to 10, prob of even or b/w 1,5
        P(1 <= x <= 5) = 50%
        P(x is even) = 50%
        P(both) = 20%

    P(x is even or x <= 5)
        = P(x is even) + P(x <= 5) - P(x is even and x <= 5)
        = 1/2 + 1/2 - 1/5
        = 4/5
*/

/*
    Independence
        - A or B happening tell nothing about other happening

        P(B given A) = P(B)
            which means:
        P(A and B) = P(A) P(B)
*/

/*    
    Mutual Exclusivity
        - A and B cannot both happen

    P(A and B) = 0
        which means:
    P(A or B) = P(A) + P(B)
*/

// Events cannot be both independent & ME unless P = 0

/*
    Rules and Patterns

    ex: 2 (uneven) ropes that take 1 hour to burn, time 15 minutes

    Rule 1: Given rope 1 takes x mins and rope 2 takes y mins:
        can time x + y mins
    Rule 2: Given rope takes x min:
        can time x/2 mins
    Rule 3: if rope takes x min, rope 2 takes y min:
        can turn rope 2 into rope that takes
        (y - x)  mins  or  (y - x/2) mins

    1) Light rope 1 at both ends, rope 2 at one end
    2) Rope 1 burnt up in 30 min, rope 2 has 30 min left
    3) Light rope 2 at other end
    4) Rope 2 burnt up in 15 min
*/

/*
    Worst Case Shifting
        - worst case minimization
        - minimize action or do task at most some x times
        - if early decision results in skeweing of worst case
            change decision to balance out worst case

        ex: 8 balls of same wight, 9th ball is heavier
            Given comparison balance, find heavy ball in only 2 uses

        3 tries:
            2 sets of 4, w/ 9th left out 
            -> if equal -> 9th is heavy one, else weigh 2 more times
        Imbalance of worst case: 9th takes one weighing, others take 3
            
        Worst case balancing: penalize 9th by putting more to side

        1. divide balls into 3 sets of 3 
        2. first weighing reveals set with heavy ball
        3. again remove one ball
        4. second weighing reveals heavy ball

        given x balls, where x is divisible by 3,
            one weighing gives set of x/3 balls with heavy ball
*/