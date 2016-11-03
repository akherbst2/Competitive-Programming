import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by Alyssa on 8/5/2016.
 */
public class Problem1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int s1 = sc.nextInt();
        int s2 = sc.nextInt();
        int[] C = new int[s1];
        int[] D = new int[s2];
        for(int i = 0; i < s1; i++) {
            C[i] = sc.nextInt();
        }
        for(int i = 0; i < s2; i++) {
            D[i] = sc.nextInt();
        }
        System.out.print(solution(K, C, D));

    }

    public static int solution(int K, int[] C, int[] D) {
        int pairs = 0;
        HashMap<Integer, Integer> s = new HashMap<>();
        for (int i = 0; i < C.length; i++) {
            if(s.containsKey(C[i])) {
                int num = s.get(C[i]);
                if (num == 1)
                {
                    pairs++;
                    s.put(C[i], 0);
                }
                else {
                    s.put(C[i], 1);
                }
            }
            else {
                s.put(C[i], 1);
            }
        }

        //id, num
        HashMap<Integer, Integer> dirty = new HashMap<>();
        PriorityQueue<Sock> socks = new PriorityQueue<>();
        int i = 0;
        while((i < D.length) && (K > 0)) {
            if(s.containsKey(D[i])) {
                if(s.get(D[i]) == 1) {
                    K--;
                    pairs++;
                    s.put(D[i], 0);
                }
                else if(s.get(D[i]) == 0) {
                    if(!dirty.containsKey(D[i])) {
                        dirty.put(D[i], 1);
                    }
                    else {
                        dirty.put(D[i], dirty.get(D[i]) + 1);
                    }
                }
            }
            else {
                if(!dirty.containsKey(D[i])) {
                    dirty.put(D[i], 1);
                }
                else {
                    dirty.put(D[i], dirty.get(D[i]) + 1);
                }
            }
            i++;
        }


        for(Integer key : dirty.keySet()) {
            if(K < 2) {
                break;
            }
            else {
                int pairsCanWash;
                int dp = dirty.get(i) / 2;
                if(K < (dp * 2)) {
                    pairsCanWash = K / 2;
                }
                else {
                    pairsCanWash = dp;
                }
                K -= pairsCanWash * 2;
                pairs += pairsCanWash;
            }
        }

        return pairs;
    }

    public class Sock {
        int kUsed;
        int id;
        public Sock(int id, int kUsed) {
            this.id = id;
            this.kUsed = kUsed;
        }

        public int compareTo(Object o) {
            Sock other = (Sock) o;
            return this.kUsed - other.kUsed;
        }
    }
}
