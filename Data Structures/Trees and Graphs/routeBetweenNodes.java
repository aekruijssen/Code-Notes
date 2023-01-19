/*
    Route between nodes:
    Given a directed graph and two nodes S and E,
    design algorithm to find if there is a route from S to E
*/

/*
    Graph Traversal
    - DFS or BFS
    - Start with one of two nodes, check if other is found
    - Mark any node found as visited to avoid cycles & repitition
*/

// Iterative BFS
enum State { Unvisited, Visited, Visiting; }

boolean search(Graph g, Node start, Node end) {
    if (start == end) return true;

    // operates as Queue
    LinkedList<Node> q = new LinkedList<Node>();

    for (Node u : g.getNodes()) {
        u.state = State.Unvisited;
    }
    start.state = State.Visiting;
    q.add(start);
    Node u;
    while (!q.isEmpty()) {
        u = q.removeFirst(); // dequeue
        if (u != null) {
            for (Node v : u.getAdjacent()) {
                if (v.state == State.Unvisited) {
                    if (v == end) {
                        return true;
                    } else {
                        v.state = State.Visiting;
                        q.add(v);
                    }
                }
            }
            u.state = State.Visited;
        }
    }
    return false;
}