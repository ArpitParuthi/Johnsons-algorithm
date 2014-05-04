package Assignment_3;

public class CreateAuxiliaryGraph {
    private int V;
    private int E;
    Bag<DirectedEdge>[] adj;
    double[] K= new double[100];

    public CreateAuxiliaryGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }
    public CreateAuxiliaryGraph() {
        this(StdIn.readInt());
        int E = StdIn.readInt();
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
    public CreateAuxiliaryGraph(CreateAuxiliaryGraph G) {
        this(G.V()+1);
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
    public double vertexWeight(int v){
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
        s.append(E + NEWLINE);
        for (int v = 0; v < V; v++) {
            //s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "");
                s.append(NEWLINE);
            }
           // s.append(NEWLINE);
        }
        return s.toString();
    }

  
    public static void main(String[] args) {
        CreateAuxiliaryGraph G = new CreateAuxiliaryGraph();
        CreateAuxiliaryGraph H= new CreateAuxiliaryGraph(G);
        for(int i=0;i<H.V-1;i++)
        	H.addEdge(new DirectedEdge(H.V-1,i,0));
        StdOut.print(H); //This graph is equivalent to G'
      
    }

}

