package Assignment_3;

public class ApplyDijkstra {
    private double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
    private IndexMinPQ<Double> pq;    // priority queue of vertices

    public ApplyDijkstra(EdgeWeightedDigraph G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }

        // check optimality conditions
        assert check(G, s);
    }

    // relax edge e and update pq if changed
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

    // length of shortest path from s to v
    public double distTo(int v) {
        return distTo[v];
    }

    // is there a path from s to v?
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    // shortest path from s to v as an Iterable, null if no such path
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }


    // check optimality conditions:
    // (i) for all edges e:            distTo[e.to()] <= distTo[e.from()] + e.weight()
    // (ii) for all edge e on the SPT: distTo[e.to()] == distTo[e.from()] + e.weight()
    private boolean check(EdgeWeightedDigraph G, int s) {

        // check that edge weights are nonnegative
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (v == s) continue;
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < G.V(); w++) {
            if (edgeTo[w] == null) continue;
            DirectedEdge e = edgeTo[w];
            int v = e.from();
            if (w != e.to()) return false;
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
    
       EdgeWeightedDigraph G = new EdgeWeightedDigraph();
       //	StdOut.println(G);
       //This graph has all edge weights positive, so Dijkstra can be applied
        int s = Integer.parseInt(args[0]);
       // int[]w = new int[10];
        //int[]x= new int[10];
        // compute shortest paths
        ApplyDijkstra sp = new ApplyDijkstra(G, s);

        // print shortest path
        for (int t = 0; t < G.V(); t++) {
        	//G.addEdge(new DirectedEdge(,G.vertexWeight(t)));
        		for(DirectedEdge e: G.adj[t]){
        			G.addEdge(new DirectedEdge(e.from(),e.to(),e.weight()+G.vertexWeight(e.to())-G.vertexWeight(e.from())));
        		}
        	
            if (sp.hasPathTo(t)) {
            	//w[t]=sp.distTo(t);
            	if(sp.distTo(t)+G.vertexWeight(t)-G.vertexWeight(s)>=0.00){
            		StdOut.printf("%d to %d ( %.2f) ", s, t, sp.distTo(t)+G.vertexWeight(t)-G.vertexWeight(s));
            	}
            	else{
            		StdOut.printf("%d to %d (%.2f) ", s, t, sp.distTo(t)+G.vertexWeight(t)-G.vertexWeight(s));
            	}
            	if (sp.hasPathTo(t)) {
                    for (DirectedEdge e : sp.pathTo(t)) {
                        	
                        	e.weight=e.weight+G.vertexWeight(e.to())-G.vertexWeight(e.from());
                        	//G.addEdge(new DirectedEdge(e.from(),e.to(),e.weight));
                        		
                      StdOut.print(e + "   ");
                      	e.weight=0;
                    }
                }
               StdOut.println();
            }
        	
            else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
            
            
       
            }
        	
        }

}