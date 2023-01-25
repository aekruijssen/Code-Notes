// Bit Manipulation

/*
    Bit Manipulation by hand:
    - can convert to base 10, then back to binary
    - ^ is XOR, ~ is NOT

    0110 + 0010 = 1000
    0011 + 0010 = 0101
    0110 - 0011 = 0011
    1000 - 0110 = 0010
    0011 * 0101 = 1111
    0011 * 0011 = 1001
    1101 >> 2 = 0011
    1101 ^ 1010 = 1000
    0110 + 0110 = 1100  // equivalient to (0110 * 2) = (0110 << 1) = 1100
    0100 * 0011 = 1100  // 0100 = 4 -> (0011 * 4) = (0011 << 2) = 1100
    1101 ^ (~1101) = 1111  // (a^(~a)) = 1       aka   x XOR !x = 1
    1011 & (~0 << 2) = 1000  // ~0 = 1111, (1111 << 2) = 1100, (1011 & 1100) = 1000
        // ANDing 1100 with another value clears last two bits
*/

/*
    Bit Facts and Tricks

    x ^ 0s = x
    x ^ 1s = ~x
    x ^ x = 0s

    x & 0s = 0s
    x & 1s = x
    x & x = x

    x | 0s = x
    x | 1s = 1s
    x | x = x
*/

/*
    Two's Complement and Negative Numbers

    - Computers typically store ints in two's complement
    - positive number represented as itself
    - negative number represented as two's complement of abs value
        - 1 in sign bit to indicate negative
    - two's complement of N-bit no. = complement of no. wrt 2^N

    ex: -4 -> 2^3 = 8, comp(3) wrt 8 = 5, 5 = 101 -> -3 = 1101

    -K as N-bit no. = concat( 1, (2^(N-1) - K) )

    Invert bits in positive representation, then add 1
        ex: 3 = 011, flip bits -> 100, (100 + 1) = 101  ->  -3 = 1101
    

    7  ->  0 111  ->  -1 = 1 111  
    6  ->  0 110  ->  -2 = 1 110
    5  ->  0 101  ->  -3 = 1 101
    4  ->  0 100  ->  -4 = 1 100
    3  ->  0 011  ->  -5 = 1 011
    2  ->  0 010  ->  -6 = 1 010
    1  ->  0 001  ->  -7 = 1 001
    0  ->  0 000
*/


/*
    Arithmetic vs Logical Right Shift


    - Arithmetic right shift >>
        - shift values to right but fill new bits with sign bit
        - divides roughly in 2
            10110101 >> 1 = 11011010
            -75 -> -38

    - Logical right shift >>>
        - shift bits and insert 0 in most significant bit
        - on 8-bit no. where sign bit is most significant:
            10110101 >>> 1 = 01011010
            -75 -> 90
*/


/*
    Arithmetic right shift >>
    - shift values to right but fill new bits with sign bit
    - divides roughly in 2
        10110101 >> 1 = 11011010  aka  -75 -> -38
*/
int repeatedArithmeticShift(int x, int count) {
    for (int i = 0; i < count; i++) {
        x >>= 1; // Arithmetic shift by 1
    }
    return x;
}


/*
    Logical right shift >>>
    - shift bits and insert 0 in most significant bit
    - on 8-bit no. where sign bit is most significant:
        10110101 >>> 1 = 01011010  aka  -75 -> 90
*/
int repeatedLogicalShift(int x, int count) {
    for (int i = 0; i < count; i++) {
        x >>>= 1;
    }
    return x;
}




// Bit Tasks


/*
    Get Bit
    1) shift 1 over by i bits (ex: 00010000)
    2) perform AND with num, clearing all bits but bit i
    3) if new value != 0, bit i must be 1, else 0
*/
boolean getBit(int num, int i) {
    return ((num & (1 << i)) != 0);
}


/*
    SetBit
    1) shift 1 over by i bits (ex: 00010000)
    2) perform OR with num
    - only value at bit i changes, other bits of mask are 0
*/
int setBit(int num, int i) {
    return num | (1 << i);
}



/*
    Clear Bit
    1) create reverse & negate (ex: 00010000 -> ~00010000 = 11101111)
    2) AND with num, clearing ith bit and leaving remainder unchanged
*/
int clearBit(int num, int i) {
    int mask = ~(1 << i);
    return num & mask;
}


/*
    Clear Bits from most significant bit through i (inclusive)
    1) create mask with 1 at ith bit (1 << i)
    2) subtract 1 from mask, giving 0s followed by i 1s
    3) AND num with mask, leaving just last i bits
*/
int clearBitsMCBthroughI(int num, int i) {
    int mask = (1 << i) - 1;
    return num & mask;
}


/*
    Clear Bits from i through 0 (inclusive)
    1) sequence of 1s (= -1)
    2) shift left by (i + 1) bits
        - giving 1s followed by i+1 0s
*/
int clearBitsIthrough0(int num, int i) {
    int mask = (-1 << (i + 1));
    return num & mask;
}



/*
    Update Bit
    - set ith bit to value v
    1) clear bit i with mask
    2) shift value left by i bits
        - giving num with bit i = v, other bits 0
    3) OR numbers
        - updating ith bit if v = 1, else 0
*/
int updateBit(int num, boolean bitIs1) {
    int value = bitIs1 ? 1 : 0;
    int mask = ~(1 << i);
    return (num & mask) | (value << i);
}
