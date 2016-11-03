import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alyssa on 9/24/2016.
 */
public class BigTruck3 {
    static int start, end;
    static Path[] dp;
    static int[] items;
    static int[][] distance;
    static List<List<Integer>> nbrs;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        start = 0;
        end = n - 1;
        items = new int[n];
        distance = new int[n][n];
        nbrs = new ArrayList<>();
        dp = new Path[n];
        for(int i = 0; i < n; i++) {
            items[i]= sc.nextInt();
            nbrs.add(new ArrayList<>());
        }
        int k = sc.nextInt();
        for(int i = 0; i < k; i++) {
            int node1 = sc.nextInt() - 1;
            int node2 = sc.nextInt() - 1;
            int d = sc.nextInt();
            distance[node1][node2] = d;
            distance[node2][node1] = d;
            nbrs.get(node1).add(node2);
            nbrs.get(node2).add(node1);
        }
        dfs(0, new boolean[n]);
        Path best = dp[0];
        if((best == null)||(best.dist > 10000)||(best.items < 0)) {
            System.out.print("impossible");
        }
        else {
            System.out.print(best.dist + " " + best.items);
        }
    }

    static Path dfs(int curr, boolean[] seen) {
        if(curr == end) {
            dp[end] = new Path(0, items[end]);
            return dp[end];
        }
        else if(dp[curr] != null) {
            return dp[curr];
        }
        Path best = new Path(100000, -1000000);
        if(!seen[curr]) {
            seen[curr] = true;
            for(Integer nbr:nbrs.get(curr)) {
                if(!seen[nbr]) {
                    dfs(nbr, Arrays.copyOf(seen, seen.length));
                    Path r = dp[nbr];
                    if(r == null) {
                        continue;
                    }
                    Path result = new Path(r.dist + distance[curr][nbr], r.items + items[curr]);
                    if ((result.dist < best.dist) || ((result.dist == best.dist) && (result.items > best.items))) {
                        best = result;
                    }
                }
            }
            if((best.dist < 1000000)&&(best.items > -1)) {
                dp[curr] = best;
            }
        }
        return null;
    }



    static class Path{
        long dist, items;

        public Path(long dist, long items) {
            this.dist = dist;
            this.items = items;
        }
    }



}
