/**
 * Created by Alyssa on 10/31/2016.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.copyOf;

public class Sleep {
    static int[] isEven;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        char[] ch = sc.next().toCharArray();
        isEven = new int[n];
        for(int i = 0; i < n; i++) {
            if(ch[i] == 'U') {
                isEven[i] = 1;
            }
            else {
                isEven[i] = -1;
            }
        }

        for(int i = 0; i < n; i++) {
            HashSet<int[]> seen = new HashSet<>();
            int[] arr = Arrays.copyOf(isEven, n + 1);
            arr[n] = i;
            int curr = i;
            int t = 0;
            while((curr < n)&&
                    (curr >= 0) &&
                    !seen.contains(arr)) {

                t++;
                seen.add(arr);
                arr[n] = getNext(curr, arr);
                arr[curr] = arr[curr] * -1;
                curr = arr[n];
            }

            if( curr >= n || curr < 0) {
                if(!sb.toString().isEmpty()) {
                    sb.append(' ');
                }
                sb.append(t + 1);
            }
            else {
                if(!sb.toString().isEmpty()) {
                    sb.append(' ');
                }
                sb.append(-1);
            }
        }
    }
    static class



    static int getNext(int prev, int[] currArr) {
        return (currArr[prev]) + prev;
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