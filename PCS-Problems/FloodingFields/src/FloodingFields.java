import java.util.*;
import static java.lang.Math.*;
/**
 * Created by Alyssa Herbst on 10/8/2016.
 *
 * Max Flow Problem.
 * This solution contains comments about the code
 *
 *  n = Size of land (n*n)
 *  t = Hours
 *  k = # Cows
 *
 *  Total Problem Complexity:  n + k + 2n^2 + tn^2 + (Algorithm Complexity)
 *
 *  m = # edges
 *  N = # nodes
 *
 *  +----------------------------------------------------------+
 *  |    Algorithm       | Runtime (seconds) |   Complexity    |
 *  |    ----------------+-------------------|-----------------|
 *  |    Ford Fulkerson  |   1.70 sec        |   O(m|k|)       |
 *  |    Dinic's         |   1.02 sec        |   O(N*N*m)      |
 *  |    Push-Relabel    |   1.47 sec        |   O(N*N*N)      |
 *  +----------------------------------------------------------+
 *
 * Problem Source: https://pcs.spruett.me/problems/121
 */
public class FloodingFields {
    static int n, k, h;
    static Node S, K;

    public static void main(String[] args) {
        MaxFlowSolver mf = new PushRelabel();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        h = sc.nextInt();

        //Allows me to access the altitude of the land at a certain location
        int[][] altitude = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                altitude[i][j] = sc.nextInt();
            }
        }

        //Locations that initially have cows
        boolean[][] hasCow = new boolean[n][n];
        for(int i = 0; i < k; i++) {
            hasCow[sc.nextInt()][sc.nextInt()] = true;
        }

        //Source Node
        S = mf.addNode();
        //Sink Node
        K = mf.addNode();

        //used for quick access the surrounding locations that I can travel to (including the starting node).
        int[][] nbrs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}, {0, 0}};

        //txy = time, x location, y location.
        //Nodes that track cows at a certain time and location. 1 node per unique time, x, and y.
        Node[][][] txy = new Node[h + 1][n][n];

        //intertxy = intermediate nodes at a time, x, and y
        //1 edge travels from a txy node to a intermediate node.  This represents the fact that only 1 cow can stay at
        //a location at each time.  That way, if multiple cows arrive at the same location, only 1 may continue.
        //Multiple edges travel from the intermediate node to the next txy node (t + 1 time, same x y location),
        //representing the passing of an hour.  The surrounding nodes are only added if the cow can stay at that height,
        //given the water level at that time. The cow may also remain in the same place and not move as time passes.
        Node[][][] intertxy = new Node[h][n][n];

        //Links the source nodes to the nodes that have cows at t = 0.  (A max of k cows can be "pushed" to the sink
        //node at the end)
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                Node node = mf.addNode();
                txy[0][r][c] = node;
                if(hasCow[r][c]) {
                    mf.link(S, node, 1);
                }
            }
        }

        //Sets up the main maxflow graph
        for(int t = 1; t <= h; t++) {
            int waterHeight = sc.nextInt();
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    //If a cow is able to exist at that row and col at a certain time, we add it to our graph.
                    if ((altitude[x][y] - waterHeight) > 0) {
                        Node node = mf.addNode();
                        txy[t][x][y] = node;
                        //For each of the current Node's neighbors, we go back an hour and see if that location was
                        //accessible by a cow.  If so, we connect the two (using intermediate nodes to represent problem restrictions)
                        //to signify that it is possible to travel to that node from the neighbor of that node in the
                        //previous hour.
                        for (int i = 0; i < nbrs.length; i++) {
                            int moveToX = x + nbrs[i][0];
                            int moveToY = y + nbrs[i][1];
                            //setup for intermediate node
                            if (isValid(moveToX, moveToY) && (txy[t - 1][moveToX][moveToY] != null)) {
                                if (intertxy[t - 1][moveToX][moveToY] == null) {
                                    intertxy[t - 1][moveToX][moveToY] = mf.addNode();
                                    mf.link(txy[t - 1][moveToX][moveToY], intertxy[t - 1][moveToX][moveToY], 1);
                                }
                                //link the previous time with the current time if it is possible for a cow to travel here.
                                mf.link(intertxy[t - 1][moveToX][moveToY], txy[t][x][y], 1);
                            }
                        }
                    }
                }
            }
        }

        //Link the locations that are accessible at t = h to the sink.  If cows have made it to the sink, they
        //survived!! :)
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(txy[h][r][c] != null) {
                    mf.link(txy[h][r][c], K, 1);
                }
            }
        }

        //Do the max flow magic and print it B)
        System.out.println(mf.getMaxFlow(S, K));
    }

    /**
     * Checks to make sure that the x and y values are within bounds of the array
     */
    static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < n;
    }

//*************Virginia Tech ICPC Handbook Code follows this statement**************************************************

    public static class Node {
        private Node() {}
        String name;
        List<Edge> edges = new ArrayList<>();
        int index;
    }

    public static class Edge {
        boolean forward;

        Node from, to;
        int flow;
        final int capacity;
        Edge dual;
        long cost;

        protected Edge(Node s, Node d, int c, boolean f) {
            forward = f;
            from = s;
            to = d;
            capacity = c;
        }

        int remaining() { return capacity - flow;  }

        void addFlow(int amount) {
            flow += amount;
            dual.flow -= amount;
        }
    }

    public static abstract class MaxFlowSolver {
        List<Node> nodes = new ArrayList<>();

        public void link(Node n1, Node n2, int capacity) {
            Edge e12 = new Edge(n1, n2, capacity, true);
            Edge e21 = new Edge(n2, n1, 0, false);
            e12.dual = e21;
            e21.dual = e12;
            n1.edges.add(e12);
            n2.edges.add(e21);
        }

        public void link(Node n1, Node n2, int capacity, long cost) {
            Edge e12 = new Edge(n1, n2, capacity, true);
            Edge e21 = new Edge(n2, n1, 0, false);
            e12.dual = e21;
            e21.dual = e12;
            n1.edges.add(e12);
            n2.edges.add(e21);
            e12.cost = cost;
            e21.cost = -cost;
        }

        public void link(int n1, int n2, int capacity){
            link(nodes.get(n1), nodes.get(n2), capacity);
        }

        protected MaxFlowSolver(int n) {
            for(int i = 0; i < n; i++) {
                addNode();
            }
        }

        protected MaxFlowSolver() {this(0);}

        public abstract int getMaxFlow(Node src, Node snk);

        public Node addNode() {
            Node n = new Node();
            n.index = nodes.size();
            nodes.add(n);
            return n;
        }
    }

    static class PushRelabel extends MaxFlowSolver {
        PushRelabel() {this(0);}
        PushRelabel(int n) { super(n); }
        int[] count;
        int[] dist;
        int[] excess;
        boolean[] active;
        Queue<Integer> Q;
        int N;

        void Enqueue(int v) {
            if(!active[v] && excess[v] > 0) {
                active[v] = true;
                Q.offer(v);
            }
        }
        void Push(Edge e) {
            int amt = min(excess[e.from.index], e.remaining());
            if(dist[e.from.index] <= dist[e.to.index] || amt == 0)
                return;
            e.addFlow(amt);

            excess[e.to.index] += amt;
            excess[e.from.index] -= amt;
            Enqueue(e.to.index);
        }
        void Gap(int k) {
            for(int v = 0; v < N; v++) {
                if(dist[v] < k) continue;
                count[dist[v]]--;
                dist[v] = max(dist[v], N+1);
                count[dist[v]]++;
                Enqueue(v);
            }
        }

        void Relabel(int v) {
            count[dist[v]]--;
            dist[v] = 2*N;
            for(Edge e:nodes.get(v).edges) {
                if(e.remaining() > 0)
                    dist[v] = min(dist[v], dist[e.to.index] + 1);
            }
            count[dist[v]]++;
            Enqueue(v);
        }

        void Discharge(int v) {
            for(Edge e: nodes.get(v).edges) {
                if(excess[v] > 0)
                    Push(e);
                else
                    break;
            }

            if(excess[v] > 0) {
                if(count[dist[v]] == 1) {
                    Gap(dist[v]);
                }
                else {
                    Relabel(v);
                }
            }
        }

        @Override
        public int getMaxFlow(Node src, Node snk) {
            N = nodes.size();
            count = new int[2*N];
            dist = new int[N];
            excess = new int[N];
            active = new boolean[N];
            Q = new ArrayDeque<Integer>();

            count[0] = N - 1;
            count[N] = 1;

            dist[src.index] = N;
            active[src.index] = active[snk.index] = true;

            for(Edge e: src.edges) {
                excess[src.index] += e.capacity;
                Push(e);
            }

            //FIFO selection rule
            while(!Q.isEmpty()) {
                int v = Q.poll();
                active[v] = false;
                Discharge(v);
            }

            int totflow = 0;
            for(Edge e:src.edges) {
                totflow += e.flow;
            }
            return totflow;
        }
    }

    public static class Dinic extends MaxFlowSolver {
        int[ ]dist;
        boolean[] blocked;

        int BlockingFlow(Node src, Node snk) {
            int N = nodes.size();
            dist = new int[N];
            Arrays.fill(dist, -1);
            int[ ]Q = new int[N];
            int s = src.index;
            int t = snk.index;

            dist[s] = 0;
            int head = 0, tail = 0;
            Q[tail++] = s;
            while(head < tail) {
                int x = Q[head++];
                List<Edge> succ = nodes.get(x).edges;
                for(Edge e:succ) {
                    if(dist[e.to.index] == -1 && e.remaining() > 0) {
                        dist[e.to.index] = dist[e.from.index] + 1;
                        Q[tail++] = e.to.index;
                    }
                }
            }

            if(dist[t] == -1) {
                return 0;
            }

            int flow, totflow = 0;
            blocked = new boolean[N];
            do {
                flow = dfs(src, snk, Integer.MAX_VALUE);
                totflow += flow;
            } while (flow > 0);
            return totflow;
        }

        int dfs(Node v, Node snk, int mincap) {
            if(v == snk)
                return mincap;
            for(Edge e: v.edges) {
                if(!blocked[e.to.index]
                        &&dist[e.from.index] + 1 == dist[e.to.index]
                        && e.remaining() > 0) {
                    int flow = dfs(e.to, snk, min(mincap, e.remaining()));
                    if(flow > 0) {
                        e.addFlow(flow);
                        return flow;
                    }
                }
            }

            blocked[v.index] = true;
            return 0;
        }

        @Override
        public int getMaxFlow(Node src, Node snk) {
            int flow, totflow = 0;
            while ((flow = BlockingFlow(src, snk)) != 0)
                totflow += flow;
            return totflow;
        }

        public Dinic () { this(0); }
        public Dinic (int n) { super(n); }
    }


    static class FordFulkerson extends MaxFlowSolver {
        FordFulkerson() {this(0);}
        FordFulkerson(int n) { super(n);}


        @Override
        public int getMaxFlow(Node src, Node snk) {
            int total = 0;
            for(;;) {
                Edge[] prev = new Edge[nodes.size()];
                int addedFlow = findAugmentingPath(src, snk, prev);
                if(addedFlow == 0) break;
                total += addedFlow;

                for(Edge edge = prev[snk.index]; edge != null; edge = prev[edge.dual.to.index]) {
                    edge.addFlow(addedFlow);
                }
            }
            return total;
        }


        int findAugmentingPath(Node src, Node snk, Edge[] from) {
            Deque<Node> queue = new ArrayDeque<>();
            queue.offer(src);
            int N = nodes.size();
            int[] minCapacity = new int[N];
            boolean[] visited = new boolean[N];
            visited[src.index] = true;
            Arrays.fill(minCapacity, Integer.MAX_VALUE);
            while(queue.size() > 0) {
                Node node = queue.poll();
                if(node == snk)
                    return minCapacity[snk.index];
                for(Edge edge : node.edges) {
                    Node dest = edge.to;
                    if(edge.remaining() > 0 && !visited[dest.index]) {
                        visited[dest.index] = true;
                        from[dest.index] = edge;
                        minCapacity[dest.index] = min(minCapacity[node.index],
                                edge.remaining());
                        if(dest == snk)
                            return minCapacity[snk.index];
                        queue.push(dest);
                    }
                }
            }
            return 0;
        }

    }

}