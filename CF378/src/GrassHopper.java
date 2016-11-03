/**
 * Created by Alyssa on 10/31/2016.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class GrassHopper {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);

        String s = sc.next();
        char[] arr = s.toCharArray();
        HashSet<Character> v = new HashSet<>();
        v.add('A');
        v.add('E');
        v.add('I');
        v.add('O');
        v.add('U');
        v.add('Y');

        int max = 1;
        int curr = 0;
        for(int i = 0; i < arr.length; i++) {
            curr++;
            if(v.contains(arr[i])) {
                max = Math.max(curr, max);
                curr = 0;
            }
        }
        curr++;
        max = Math.max(curr, max);
        System.out.println(max);
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