import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.lang.StringBuilder;

public class contest3
{
   private static StringBuilder string;
   
   public static void main(String[] args)
   {
      Scanner scan = new Scanner(System.in);
      ArrayList<Integer> intList = new ArrayList<Integer>();
      int A = scan.nextInt();
      int B = scan.nextInt();
      int C = scan.nextInt();
      int D = scan.nextInt();
      scan.nextLine();
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
            //System.out.println(A);
            intList.add(A);
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
            //System.out.println(B);
            intList.add(B);
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
            //System.out.println(C);
            intList.add(C);
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
            //System.out.println(D);
            intList.add(D);
         }
         A = scan.nextInt();
         B = scan.nextInt();
         C = scan.nextInt();
         D = scan.nextInt();
      }
      for (int i: intList)
      {
         System.out.println(i);
      }
   }
}