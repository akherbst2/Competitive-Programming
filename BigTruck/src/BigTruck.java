import java.util.List;
import java.util.*;
/**
 * Created by Alyssa on 9/24/2016.
 */
public class BigTruck {
    static int start, end;
    static List<List<Integer>> graph;
    static int[] items;
    static int[][] distance;
    static Path[] dp;
    static Path best = new Path(Integer.MAX_VALUE, -1);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        start = 0;
        end = n - 1;
        items = new int[n];
        graph = new ArrayList<>();
        distance = new int[n][n];
        dp = new Path[n];
        for(int i = 0; i < n; i++) {
            items[i] = sc.nextInt();
            graph.add(new ArrayList<>());
        }
        int roads = sc.nextInt();
        for(int i = 0; i < roads; i++) {
            int node1 = sc.nextInt() - 1;
            int node2 = sc.nextInt() - 1;
            int d = sc.nextInt();
            distance[node1][node2] = d;
            distance[node2][node1] = d;
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        dfs(start, new boolean[n], new Path(0, items[start]));
        if(best.items == -1) {
            System.out.print("impossible");
        }
        else {
            System.out.print(best.dist + " " + best.items);
        }
    }

    static Path dfs(int curr, boolean[] seen, Path path) {
        if(curr == end) {
            if((dp[0].dist > path.dist)||((dp[0].dist == path.dist)&&(dp[0].items < path.items))){
                dp[0] = path;
                return path;
            }
        }
        else if(dp[curr] != null) {
            return dp[curr];
        }
        else {
            if(!seen[curr]) {
                seen[curr] = true;
                List<Integer> nbrs = graph.get(curr);
                Path bestForCurr = new Path(Integer.MAX_VALUE, -1);
                for(Integer nbr:nbrs) {
                    Path newPath = new Path(path.dist + distance[curr][nbr], path.items + items[nbr]);
                    Path result = dfs(nbr, Arrays.copyOf(seen, seen.length), newPath);
                    if((result.dist < bestForCurr.dist)||((bestForCurr.dist == result.dist) && (result.items > bestForCurr.items))) {
                        bestForCurr = result;
                    }
                }
                dp[curr] =
            }
        }
    }

    static class Path {
        int dist, items;


        public Path(int dist, int items) {
            this.dist = dist;
            this.items = items;
        }
    }
}
