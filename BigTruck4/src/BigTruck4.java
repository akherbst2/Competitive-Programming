import java.util.*;

/**
 * Created by Alyssa on 9/28/2016.
 */
public class BigTruck4 {
    static int[] items4;
    static int[][] distance2;
    static List<List<Integer>> nbrs;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        items4 = new int[n];
        distance2 = new int[n][n];
        nbrs = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            items4[i] = sc.nextInt();
            nbrs.add(new ArrayList<>());
        }
        int k = sc.nextInt();
        for(int i = 0; i < k; i++) {
            int n1 = sc.nextInt() - 1;
            int n2 = sc.nextInt() - 1;
            int d = sc.nextInt();
            distance2[n1][n2] = d;
            distance2[n2][n1] = d;
            nbrs.get(n1).add(n2);
            nbrs.get(n2).add(n1);
        }
        dijkstra(n, 0, n - 1);
    }

    static final long INF = Long.MAX_VALUE;

    //Lazy Dijkstras
    static class Entry implements Comparable {
        public int index;
        public long distance;
        public int items;
        public Entry(long d, int i, int it) {
            distance = d;
            index = i;
            items = it;
        }
        public int compareTo(Object o ) {
            Entry e = (Entry) o;
            if(distance != e.distance) {
                return (int) (distance - e.distance);
            }
            return index - e.index;
        }
    }

   static void dijkstra(int N, int start, int goal) {
        final long[] dist = new long[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;
       Entry best = new Entry(INF, -1, -1);

        int[] prev = new int[N];
        int bestItems = 0;
        Arrays.fill(prev, -1);
        Queue<Entry> frontier = new PriorityQueue<>(dist.length);
        frontier.offer(new Entry(0, start, items4[start]));

        while(!frontier.isEmpty()) {
            Entry e = frontier.poll();
            int u = e.index;
            if (u == goal) {
                if(e.distance < dist[u]) {
                    best = e;
                } else if((e.distance == dist[u])&&(e.items > best.items)) {
                    best = e;
                }
            }
            if(dist[u] < e.distance) {
                continue;
            }
            for(int v:nbrs.get(u)) {
                long uv = distance2[u][v];
                if(dist[u] + uv <= dist[v]) {
                    prev[v] = u;
                    dist[v] = dist[u] + uv;
                    int items = e.items + items4[v];
                    frontier.offer(new Entry(dist[v], v, items));
                }
            }
        }

        if(prev[goal] == -1) {
            System.out.print("impossible");
        }
        else {
            System.out.print(dist[goal] + " " + best.items);
        }

    }

}
