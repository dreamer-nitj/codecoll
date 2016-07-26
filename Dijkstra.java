import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.Stack;

/**
 * This method is my sample implementation of weighted
 * graph.
 *
 * @author ankitk
 */
public class Dijkstra {
    public static int[] dijkstra(WeightedDirectedGraph graph, int source) {
        final float[] dist = new float[graph.V()];
        final int[] predecessor = new int[graph.V()];
        final boolean[] visited = new boolean[graph.V()];

        for (int i = 0 ; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;

        for (int i = 0 ; i < dist.length; i++) {
            final int next = minVertex(dist, visited);
            visited[next] = true;

            // The shortest path to next is dist[next] and via predecessor[next].
            for (WeightedDirectedGraph.Edge edge : graph.adj(next)) {
                final int vertex = edge.vertex;
                final float d = dist[next] + edge.weight;
                if (dist[vertex] > d) {
                    dist[vertex] = d;
                    predecessor[vertex] = next;
                }
            }
        }

        return predecessor;
    }

    private static int minVertex(float[] dist, boolean[] v) {
        float x = Integer.MAX_VALUE;
        int y = -1;

        for (int i = 0 ; i < dist.length; i++) {
            if (!v[i] && dist[i] < x) {
                y = i;
                x = dist[i];
            }
        }

        return y;
    }

    public static void printPath(int[] predecessor, int s, int e) {
        Stack<Integer> path = new Stack<Integer>();
        for (int x = e; x != s; x = predecessor[x]) {
            path.push(x);
        }

        path.push(s);

        System.out.println(path);
    }

    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        Scanner scanner = new Scanner(inputStream);
        int numOfVertex = scanner.nextInt(), numOfEdges = scanner.nextInt();
        WeightedDirectedGraph graph = new WeightedDirectedGraph(numOfVertex);

        for (int i = 0; i < numOfEdges; i++) {
            graph.addEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextFloat());
        }

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.printPath(dijkstra.dijkstra(graph, 0), 0, 221);
    }

    private static class WeightedDirectedGraph {
        class Edge {
            Integer vertex;
            float weight;

            public Edge(int vertex, float weight) {
                this.vertex = vertex;
                this.weight = weight;
            }
        }

        private final int V;
        private Bag<Edge>[] adj;

        public WeightedDirectedGraph(int V) {
            this.V = V;
            adj = (Bag<Edge>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Edge>();
            }
        }

        public void addEdge(int v, int w, float weight) {
            adj[v].add(new Edge(w, weight));
        }

        public Iterable<Edge> adj(int v) {
            return adj[v];
        }

        public int V() {
            return V;
        }

        public int E() {
            int edges = 0;
            for (int v = 0; v < V(); v++) {
                for (Edge edge : adj(v)) {
                    edges++;
                }
            }

            return edges / 2;
        }
    }
}
