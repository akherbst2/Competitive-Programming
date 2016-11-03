/**
 * Created by Alyssa on 11/1/2016.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class test2505 {
    static StringBuilder sb;

    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        int top = 2147483647;
        int bottom = 2147483647;
        for(int i = 2; i <= top; i++) {
            if(((top % i) == 0)&&((bottom % i ) == 0)) {
                top = top / i;
                bottom = bottom / i;
            }
        }
        sb.append(top + '\t' + bottom);
        System.out.println(sb);
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