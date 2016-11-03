import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.lang.StringBuilder;

public class contest
{
   private static StringBuilder string;
   
   public static void main(String[] args)
   {
      FastScanner scan = new FastScanner();
      int A = scan.nextInt();
      int B = scan.nextInt();
      int C = scan.nextInt();
      int D = scan.nextInt();
      while (A != B || B != C || C != D || A != -1)
      {
         if (A == -1)
         {
            if (D - C == C - B)
            {
               A = B + C - D;
            }
            else if (D / C == C / B)
            {
               A = B * C / D;
            }
            if (A < 1 || A > 1000000)
            {
               A = -1;
            }
            System.out.println(A);
         }
         else if (B == -1)
         {
            if (D - C == (C - A) / 2)
            {
               B = A + D - C;
            }
            else if (D * D / C / C == C / A)
            {
               B = A * D / C;
            }
            if (B < 1 || B > 1000000)
            {
               B = -1;
            }
            System.out.println(B);
         }
         else if (C == -1)
         {
            if ((D - B) / 2 == B - A)
            {
               C = D + A - B;
            }
            else if (D / B == B * B / A / A)
            {
               C = D * A / B;
            }
            if (C < 1 || C > 1000000)
            {
               C = -1;
            }
            System.out.println(C);
         }
         else
         {
            if (C - B == B - A)
            {
               D = C + B - A;
            }
            else if (C / B == B / A)
            {
               D = C * B / A;
            }
            if (D < 1 || D > 1000000)
            {
               D = -1;
            }
            System.out.println(D);
         }
         A = scan.nextInt();
         B = scan.nextInt();
         C = scan.nextInt();
         D = scan.nextInt();
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