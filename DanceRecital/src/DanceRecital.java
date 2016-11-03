import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DanceRecital {
    static int r;
    static int bestLength;
    static int[][] dist;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = Integer.parseInt(sc.next());
        List<String> rtns = new ArrayList<>();
        for(int i = 0; i < r; i++) {
            rtns.add(sc.next());
        }
        dist = new int[r][r];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < r; j++) {
                dist[i][j] = -1;
            }
        }
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < r; j++) {
                if(i == j) {
                    dist[i][j] = 0;
                }
                else if(dist[i][j] == -1){
                    int len = 0;
                    int i1 = 0;
                    int i2 = 0;
                    String a = rtns.get(i);
                    String b = rtns.get(j);
                    while((i1 < rtns.get(i).length())&&(i2 < rtns.get(j).length())) {
                        if(a.charAt(i1) == b.charAt(i2)) {
                            i1++;
                            i2++;
                            len++;
                        }
                        else if(a.charAt(i1) < b.charAt(i2)) {
                            i1++;
                        }
                        else {
                            i2++;
                        }
                    }
                    dist[i][j] = len;
                    dist[j][i] = len;
                }
            }
        }
        bestLength = Integer.MAX_VALUE;
        //start the recursion
        recur(0, new boolean[r], 0, 0);
        System.out.print(bestLength);
    }


    //returns length
    static void recur(int numSeen, boolean[] seen, int idx, int currLen) {
        if(numSeen == seen.length) {
            if (bestLength > currLen) {
                bestLength = currLen;
            }
        }
        for(int i = 0; i < r; i++) {
            if(!seen[i]) {
                boolean[] newSeen = Arrays.copyOf(seen, seen.length);
                newSeen[i] = true;
                int newLen = currLen;
                if(numSeen != 0) {
                    newLen += dist[idx][i];
                }
                recur(numSeen + 1, newSeen, i, newLen);
            }
        }
    }
}