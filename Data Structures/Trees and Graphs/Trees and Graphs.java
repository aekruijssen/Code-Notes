  /*
    Tree is composed of nodes
    - Each tree has a root node
    - Root node has zero or more child nodes
    - Each child node has zero or more child nodes

    - A tree cannot contain cycles
    - Nodes may or may not be in a paricular order
    - Nodes could have any data type
    - Nodes may or may not have links back to their parent nodes
  */

  class Node {
    public String name;
    public Node[] children;
  }

// Optional Tree wrapper class
  class Tree {
    public Node root;
  }


/*
    Binary Tree
    - Each node has up to two children
    - Ex of when not to use binary tree:
        - Storing phone numbers -> use 10-ary tree

    Leaf node = has no children
*/


/*
    Binary Search Tree
    - Binary tree where every node fits ordering property:
        all left descendents  <=  n  <  all right descendents

    * sometimes tree cannot have duplicates, other times dups on riht or on either side -> clarify
*/


/*
    Balanced Trees
    - ensure O(Log n) for insert and find
    - Red-black trees, AVL trees
*/


/*
    Complete Binary Tree
    - every level is fully filled (except maybe last, which is filled left to right)

    Full Binary Tree
    - every node has either zero or two children aka no nodes with one child

    Perfect Binary Tree
    - all interior nodes have two children, all leaf nodes are at same level
    (pyramid/triangle)
    - Rare, with (2^k - 1) nodes (k = number of levels)
*/



// Binary Tree Traversal:

/*
    In-Order Traversal
    -"visit"/print left branch, then curr node, and finally right branch
    - when performed on BST, it visits nodes in ascending order
*/

void inOrderTraversal(TreeNode node) {
    if (node != null) {
        inOrderTraversal(node.left);
        visit(node);
        inOrderTraversal(node.right);
    }
}

/*
    Pre-Order Traversal
    - visits curr node before its child nodes
    - root is always first node visited
*/

void preOrderTraversal(TreeNode node) {
    if (node != null) {
        visit(node);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }
}


/*
    Post-Order Traversal
    - visits curr node after its child nodes
    - root is always last node visited
*/

void postOrderTraversal(TreeNode node) {
    if(node != null) {
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        visit(node);
    }
}


/*
    Binary Heaps

    Min-heap (max heap is equivalent but elements are in descending order)
    - complete binary tree (totally filled besides rightmost elements on last level)
        where each node is smaller than its children
    - root is minimum element in tree

    insert
    - start by inserting element at bottom
    - insert at next available spot (left to right on bottom level)
      (maintain complete tree property)
    - "fix" tree by swapping new element with its parent until appropriate spot found
      "bubble up" minimum element
    - O(Log n) time, n = no. nodes in heap

    extract_min
    - Minimum element always at top
    - first, remove min element and swap with last element in heap
    - bubble down element by swapping with one of its children until min-heap prop restored
    - O(Log n) time, n = no. nodes in heap

    (no order within level, but need to swap with smaller to maintain upper level)
*/


/*
    Tries (Prefix Trees)
    - variant of n-ary tree where chars are stored at each node
    - each path down the tree may represent a word

    The * nodes ("null nodes") often indicate complete words

    Implementation could be special type of child (TerminatingTrieNode)
    or could use boolean flag 'terminates' within "parent" node

    A node in a trie can have [1, ALPHABET_SIZE + 1] children
    (or 0, through ALPHABET_SIZE if boolean flag used instead of a * node)

    A hash table can quickly look up if string is valid word, but
    cannot determine if string is prefix of any valid words

    - Trie checks if string is valid prefix in O(K) time, k = length of string
    (technically hash table runtime, as HT lookups are considered O(1),
    but must read through all chars in input -> O(K) for word lookup)

    - Tries useful for problems involving lists of valid words
    - Tree would require multiple searches (M -> MA -> MAN -> MANY)
        pass reference to current node in the tree
        Trie -> check if Y is a child of MAN, no need to restart from root
*/


/*
    Graphs
    - Tree is a connected graph without cycles

    - Graph is a collection of nodes with edges between some of them
    - Directed (one-way street edges) 
        or Undirected (two-way street edges)
    - Connected (path exists b/w every pair of vertices) 
        or Unconnected (consist of multiple isolated subgraphs)
    - Can have cycles or be Acyclic
*/

/*
    Adjacency List
    - Every vertex stores a list of adjacent vertices
    - In undirected, edges stored twice
        (a,b) -> b stores in a's list, a stored in b's list

    - Unlike a tree, cannot necessarily reach all nodes from a single node
    
    Adjacency list
    - array (or hash table) of lists (arrays, arraylists, linked lists)
    - Tend to use node classes instead
*/

class Graph {
    public Node[] nodes;
}

class Node {
    public String name;
    public Node[] children;
}

/*
    Adjacency Matrix
    - N x N boolean matrix (N = no. nodes)
    - true/1 value at matrix[i][j] indicates edge from i to j
    - Undirected graph -> adjacency matrix will be symmetric
    - Directed graph -> may not be symmetric

    Same graph algorithms used on adj lists (ex. BFS) can be used w/ adj matrices,
        - may be less efficient
        - Adj list can easily iterate through neighbors of node
        - Adj matrix needs to iterate through all nodes to identify node's neighbors
*/


// Graph Search

/*
    Depth-first Search
    - start at node, explore each branch before moving to next branch
    - preffered if want to visit every node in graph
    - Slightly simpler

    visit a node and iterate through each of a's neighbors
    when visiting node b (neighbor of a), visit b's neighbors
    THEN go on to visit a's other neighbors

    Exhaustively search b's branch before its other neighbors

    * pre-order and other forms of traversal are DFS
        but for DFS, must check if node has been visited,
        or risk getting stuck in infinite loop
*/

void dfs(Node root):
    if (root == null) return
    visit(root)
    root.visited = true
    for each Node n in root.adjacent:
        if n.visited == false:
            search(n)



/*
    Breadth-first Search
    - start at node, explore each neighbor before any of their children
    - Useful for finding (shortest) path between two nodes
        (rep all friendships in world, find path b/w 2 people)
        don't have to go far degrees out, will be like growing pop circle

    BFS is NOT recursive; it uses a queue

    node a visits each of a's neighbors before any of their own neighbors
    searching level by level out from a
*/

void bfs(Node root):
    Queue queue = new Queue()
    root.marked = true
    queue.enqueue(root) // Add to end of queue

    while !queue.isEmpty():
        Node r = queue.dequeue() // Remove from front of queue
        visit(r)
        for each Node n in r.adjacent:
            if n.marked == false:
                n.marked = true
                queue.enqueue(n)


/*
    Bidirectional Search
    - find the shortest path between a source and destination node
    - run two BFS searchs, one form each node
    - if graph directed: searches forward from s and backwards from t (opposite of edges)


    Consider graph where every node has <= k adjacent nodes
    and shortest path from node s to node t has length d

    - BFS: search >= k nodes in first level, 
        then >= k for each of those k nodes -> k^2, done d times
        O(k^d)
    - Bidirectional: two searches that collide after d/2 levels (path midpoint)
        each search visits about k^(d/2) -> 2K^(d/2) -> O(k^(d/2))
        faster by factor of k^(d/2)  (times itself = k^d)

        "friend of friend" now upped degree to "friend of friend of friend of friend"
        - Can support paths twice as long
*/
