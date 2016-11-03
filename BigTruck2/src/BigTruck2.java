import java.util.List;
import java.util.*;

/**
 * Created by Alyssa on 9/24/2016.
 */
public class BigTruck2 {
    static int start, end;
    static List<List<Integer>> graph;
    static int[] items;
    static int[][] distance;
    static Path[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        start = 0;
        end = n - 1;
        items = new int[n];
        graph = new ArrayList<>();
        distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        dp = new Path[n];
        for (int i = 0; i < n; i++) {
            items[i] = sc.nextInt();
            graph.add(new ArrayList<>());
        }
        int roads = sc.nextInt();
        for (int i = 0; i < roads; i++) {
            int node1 = sc.nextInt() - 1;
            int node2 = sc.nextInt() - 1;
            int d = sc.nextInt();
            distance[node1][node2] = d;
            distance[node2][node1] = d;
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        dfs(start, new boolean[n]);
        if ((dp[0] == null) || dp[0].items <= -1) {
            System.out.print("impossible");
        } else {
            System.out.print(dp[0].dist + " " + dp[0].items);
        }
    }

    static void dfs(int curr, boolean[] seen) {
        if (curr == end) {
            dp[end] = new Path(0, items[end]);
        }
        else if ((dp[curr] == null) && (!seen[curr])) {
            seen[curr] = true;
            Path bestForCurr = new Path(Integer.MAX_VALUE, Integer.MIN_VALUE);
            List<Integer> nbrs = graph.get(curr);
            for (Integer nbr : nbrs) {
                dfs(nbr, Arrays.copyOf(seen, seen.length));
                Path r = dp[nbr];
                if(r == null) {
                    continue;
                }
                long newDistance = Integer.MAX_VALUE;
                long newItems = Integer.MIN_VALUE;
                if (r.items >= 0) {
                    newItems = r.items + items[curr];
                }
                if (r.dist + distance[curr][nbr] < Integer.MAX_VALUE) {
                    newDistance = r.dist + distance[curr][nbr];
                }
                final Path result = new Path(newDistance, newItems);
                if ((result.dist < bestForCurr.dist) || ((bestForCurr.dist == result.dist) && (result.items > bestForCurr.items))) {
                    bestForCurr = result;
                }

            }
            if (bestForCurr.items >= 0) {
                dp[curr] = bestForCurr;
            }
        }
    }


static class Path {
    long dist, items;


    public Path(long dist, long items) {
        this.dist = dist;
        this.items = items;
    }
}
}
