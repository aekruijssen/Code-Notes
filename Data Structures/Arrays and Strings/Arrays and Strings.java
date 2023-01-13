// - Implement ArrayList
// - Implement StringBuilder
// - Implement HashTable



// ArrayList - doubles in size when full. Each resize = O(n), each access = O(1)
// Amortized runtime = O(1) 
// # copies to insert N elements  =  (N/2 + N/4 + N/8 + ... + 2 + 1)  =  < N
ArrayList<String> merge(String[words, String[] more) {
    ArrayList<String> sentence = new ArrayList<String>();
    for (String w : words) sentence.add(w):
    for (String w : more) sentence.add(w);
    return sentence;
}



// StringBuilder - resizable array of strings, copying them back to a String only when neccessary
String joinWords(String[] words) {
    StringBuilder sentence = new StringBuilder();
    for (String w : words) {
        sentence.append(w);
    }
    return sentence.toString();
}



// HashTable - maps keys to values for efficient lookup
// Worst case (many collisions) runtime = O(N), generally O(1) lookup
// If implemented with balanced binary search tree, lookup = O(logN)
/*
    1) Compute key's hash code (int or long). Multiple keys can have same hash code (nonunique)
    2) Map hash code to index in array. Ex: hash(key) % array_length. Again, multiple hash codes can map to same index (nonunique)
    3) Store the key and value in the Linked List at the computed index in the array. Linked list is used due to collisions
    To retrieve key, repeat process; Compute hash code from key, compute index from hash code, search linked list for value with given key
*/