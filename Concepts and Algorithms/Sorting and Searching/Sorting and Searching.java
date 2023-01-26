// Sorting and Searching


/*
    Bubble Sort  |  runtime = O(n^2), memory = O(1) 

    - start at beginning of array
    - swap first 2 elements if 1st > 2nd
    - move to next pair, making sweeps of array till sorted
*/


/*
    Selection Sort  |  runtime = O(n^2), memory = O(1) 

    - simple but inefficient
    - find smallest element using linear scan
    - move smallest to front (swap with front element)
    - find 2nd smallest and swap with 2nd, and so on
*/


/*
    Merge Sort  |  runtime = O(n Logn), memory = depends/O(n) 

    - divide array in half
    - sort each half
    - merge sorted halves

    - merge method copies elements from segment into helper array
    - keep track of start of left & right halves (helperLeft, helperRight)
    - iterate through helper, copy smaller element from halves into array

    - remaining elements from left half copied (right half already there)

    ex: [1, 4, 5 || 2, 8, 9]
        copy over (1, 4, 5, 2)
        [8, 9] still in place in both arrays, 
            so don't need to copy over

    - Space Complexity = O(n) 
        - due to aux space used to merge parts of array
*/
void mergesort(int[] array) {
    int[] helper = new int[array.length];
    mergesort(array, helper, 0, array.length - 1);
}

void mergesort(int[] array, int[] helper, int low, int high) {
    if (low < high) {
        int middle = low + (high - low) / 2;
        mergesort(array, helper, low, middle); // sort left half
        mergesort(array, helper, middle+1, high); // sort right half
        merge(array, helper, low, middle, high); // merge halves
    }
}

void merge(int[] array, int[] helper, int low, int middle, int high) {
    // Copy both halves into a helper array
    for (int i = low; i <= high; i++) {
        helper[i] = array[i];
    }

    int helperLeft = low;
    int helperRight = middle + 1;
    int current = low;

    // Iterate through helper array, compare halves
    // copy back smaller element from 2 halves
    while (helperLeft <= middle && helperRight <= high) {
        if (helper[helperLeft] <= helper[helperRight]) {
            array[current] = helper[helperRight];
            helperLeft++;
        } else { // if right element < left element
            array[current] = helper[helperRight];
            helperRight++;
        }
        current;
    }

    // Copy left side to target array
    int remaining = middle - helperLeft;
    for (int i = 0; i <= remaining; i++) {
        array[current + i] = helper[helperLeft + i];
    }
}


/*
    Quick Sort  |  runtime = O(n Logn) avg, O(n^2) worst, memory = O(1) 

    - pick random element
    - partition array st all nums < partition
        come before all elements > partition
    - partition done efficiently through series of swaps

    - WC RT = O(n^2)
        - if partitioned element is not near median
            -> sorting will be slow
*/
void quickSort(int[] arr, int left, int right) {
    int index = partition(arr, left, right);
    if (left < index - 1) { // sort left half
        quickSort(arr, left, index - 1);
    }
    if (index < right) { // sort right half
        quickSort(arr, index, right);
    }
}

int partition(int[] arr, int left, int right) {
    int pivot = arr[left + (right - left) / 2]; // pick pivot
    while(left <= right) {
        // find element on left that should be right
        while(arr[left] < pivot) left++;

        // find element on right that should be left
        while (arr[right] > pivot) right--;

        // swap elements, move left and right indeces
        if (left <= right) {
            swap(arr, left, right); // swap
            left++;
            right--;
        }
    }
    return left;
}

/*
    Radix Sort  |  runtime = O(kn)

    - for ints, since ints have finite # bits

    - iterate through each digit, grouping #s by each digit
    - first, sort by 1st digit, w/ 0s together
    - next, sort each group by next digit

    O(kn), where n = # elements, k = # passes of sorting algo

    ex: Given large array of Persons, sort inc by age
    - large array -> efficiency important
    - sorting based on ages, so values are in small range
    - buckets of 1 year each -> O(n) runtime
*/


// Searching Algorithms

/*
    Binary Search
    - look for element x in sorted array
    - first, compare x to midpoint
        if x < mp, search left half
        else, search right half
    - repeat until find x or subarray has size 0
*/
int binarySearch(int[a], int x) {
    int low = 0;
    int high = a.length - 1;
    int mid;

    while (low <= high) {
        mid = low + (high - low) / 2;
        if (a[mid] < x) {
            low = mid + 1;
        } else if (a[mid] > x) {
            high = mid - 1;
        } else {
            return mid;
        }
    }
    return -1; // Error
}

int binarySearchRecursive(int[a], int x, int low, int high) {
    if(low > high) return -1; // Error

    int mid = low + ((high - low) / 2);
    if (a[mid] < x) {
        return binarySearchRecursive(a, x, mid + 1, high);
    } else if (a[mid] > x) {
        return binarySearchRecursive(a, x, low, mid - 1);
    } else {
        return mid;
    }
}