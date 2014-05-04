package Assignment_3;

public class ApplyDijkstraAllPairs {
    private ApplyDijkstra[] all;

    public ApplyDijkstraAllPairs(EdgeWeightedDigraph G) {
        all  = new ApplyDijkstra[G.V()];
        for (int v = 0; v < G.V(); v++)
            all[v] = new ApplyDijkstra(G, v);
    }

    public Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }

    public double dist(int s, int t) {
        return all[s].distTo(t);
    }
    
    public static void main(String[] args){
        //In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph();
        //StdOut.println(G);
        //G has all edge weights +ve, Dijkstra all pair can be applied
        ApplyDijkstraAllPairs allpairs = new ApplyDijkstraAllPairs(G);
        
        for(int s = 0; s < G.V(); s++) {
        	for(DirectedEdge e: G.adj[s]){
    			G.addEdge(new DirectedEdge(e.from(),e.to(),e.weight()+G.vertexWeight(e.to())-G.vertexWeight(e.from())));
    		}
    	
           for (int t = 0; t < G.V(); t++) {
               if (allpairs.path(s,t) != null) {
    
            	   if(allpairs.dist(s,t)+G.vertexWeight(t)-G.vertexWeight(s)>=0.00)
            		   StdOut.printf("%d to %d ( %.2f)  ", s, t, allpairs.dist(s,t)+G.vertexWeight(t)-G.vertexWeight(s));
            	   else
            		   StdOut.printf("%d to %d (%.2f)  ", s, t, allpairs.dist(s,t)+G.vertexWeight(t)-G.vertexWeight(s));
            	   if (allpairs.path(s,t) != null) {
                       for (DirectedEdge e : allpairs.path(s,t)) {
                    	   e.weight=e.weight+G.vertexWeight(e.to())-G.vertexWeight(e.from());
                           StdOut.print(e + "   ");
                           e.weight=0;
                       }
                   }
                   StdOut.println();
               }
               else {
                   StdOut.printf("%d to %d          no path\n", s, t);
               }
           }
           StdOut.println();
        }
    }
}