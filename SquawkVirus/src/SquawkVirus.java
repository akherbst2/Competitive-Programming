import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Alyssa Herbst
 */
public class SquawkVirus {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        int t = sc.nextInt();

        List<Integer>[] nbrs = new List[n];
        for(int i = 0; i < n; i++) {
            nbrs[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            nbrs[x].add(y);
            nbrs[y].add(x);
        }

        long[] prev = new long[n];
        prev[s] = 1;
        long total = 1;

        for(int i = 0; i < t; i++) {
            total = 0;
            long[] curr = new long[n];
            for(int j = 0; j < n; j++) {
                if(prev[j] != 0) {
                    for(Integer k:nbrs[j]) {
                        curr[k] = curr[k] + prev[j];
                        if(i == t - 1) total += prev[j];
                    }
                }
            }
            prev = curr;
        }
        if(t < 0) {
            total = 0;
        }
        System.out.print(total);
    }
}
