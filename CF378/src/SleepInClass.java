/**
 * Created by Alyssa on 10/31/2016.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class SleepInClass {
    //pairity, stairity
    static int[][] dpps;
    static int[] evenTime;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        evenTime = new int[n];
        dpps = new int[2][n];
        String str = sc.next();
        char[] chs = str.toCharArray();
        for(int i = 0; i < n; i++) {
            if(chs[i] == 'U') {
                evenTime[i] = 1;
            }
            else {
                evenTime[i] = -1;
            }
        }
        //for every stair
        for(int i = 0; i < n; i++) {
            Stack<Pair> stack = new Stack<>();
            int t = 0;
            int curr = i;

            stack.push(new Pair((t % 2), i));
            boolean[][] seenps = new boolean[2][n];
            int next = getNext(i, t);
            while((next >= 0)&&
                    (next < n)&&
                    !seenps[t % 2][next]&&
                    (dpps[t % 2][next] == 0)){


                seenps[t % 2][curr] = true;
                stack.push(new Pair(t % 2, curr));
                curr = next;
                next = getNext(next, t + 1);
                t++;
            }

            //if we've seen this space done before
            if ((next >= 0) &&
                    (next < n)&&
                    dpps[(t + 1) % 2][next] != 0) {
                if(!sb.toString().isEmpty()) {
                    sb.append(" ");
                }
                if(dpps[(t + 1) % 2][next] == -1) {
                    sb.append(-1);
                }
                else {
                    //add current time, plus time from next space to falling, plus time to get to next space
                    sb.append(t + dpps[(t + 1) % 2][next] + 1);
                }
            }
            else {
                int out = 0;
                if((next < 0)||(next >= n)) {
                    if(!sb.toString().isEmpty()) {
                        sb.append(" ");
                    }

                    t++;
                    //to fall
                    sb.append(t);
                    out = t;
                }
                //if this space has been done before
                else {
                    if (!sb.toString().isEmpty()) {
                        sb.append(" ");
                    }
                    //goes on forever
                    sb.append(-1);
                    out = -1;
                }

                while(!stack.isEmpty()) {
                    Pair pair = stack.pop();
                    dpps[pair.parity][pair.stair] = out;
                }
            }

        }
        System.out.println(sb);

    }

    static int getNext(int prev, int time) {
        return ((int) Math.pow(-1, time) * evenTime[prev]) + prev;
    }

    static class Pair {
        int parity, stair;

        public Pair(int parity, int stair) {
            this.parity = parity;
            this.stair = stair;
        }
    }

    public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(Reader in) {
            br = new BufferedReader(in);
        }

        public FastScanner() {
            this(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String readNextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int[] readIntArray(int n) {
            int[] a = new int[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextInt();
            }
            return a;
        }

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextLong();
            }
            return a;
        }
    }
}