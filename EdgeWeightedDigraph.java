package Assignment_3;

public class EdgeWeightedDigraph {
   int V;
    int E;
    Bag<DirectedEdge>[] adj;
    double[] K= new double[100];
  
    public EdgeWeightedDigraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }

    public EdgeWeightedDigraph(int V, int E) {
        this(V);
        if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = (int) (Math.random() * V);
            int w = (int) (Math.random() * V);
            double weight = Math.round(100 * Math.random()) / 100.0;
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    public EdgeWeightedDigraph() {
    	this(StdIn.readInt());
    	for(int i=0;i<this.V;i++){
			K[i] = StdIn.readDouble();
			//StdOut.println(K[i]);
		}
    	int V =StdIn.readInt();
        int E =StdIn.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            if (v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
            if (w < 0 || w >= V) throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V-1));
            double weight = StdIn.readDouble();
            addEdge(new DirectedEdge(v, w, weight));
        }
        
    }

    public EdgeWeightedDigraph(EdgeWeightedDigraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<DirectedEdge> reverse = new Stack<DirectedEdge>();
            for (DirectedEdge e : G.adj[v]) {
                reverse.push(e);
            }
            for (DirectedEdge e : reverse) {
                adj[v].add(e);
            }
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        adj[v].add(e);
        E++;
    }

    public double vertexWeight(int v) {
        return K[v];
    }
 
    public Iterable<DirectedEdge> adj(int v) {
        if (v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    } 

    public int outdegree(int v) {
        if (v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
        return adj[v].size();
    }

    public String toString() {
        String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        s.append(V+NEWLINE);
        s.append(E+NEWLINE);
        for (int v = 0; v < V; v++) {
           // s.append(v + ":");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
                s.append(NEWLINE);
            }
            //s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        //In in = new In(args[0]);
       // EdgeWeightedDigraph G = new EdgeWeightedDigraph();
       //StdOut.println(G);
    }

}