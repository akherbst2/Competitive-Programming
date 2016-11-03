import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.lang.StringBuilder;

public class contest4
{
   static double DELT = 1e-6;

   static boolean eql(double a, double b) {
      return Math.abs(a - b) < DELT;
   }

   static boolean isInt(double n) {
      int i =(int) Math.round(n);
      return eql(n, i);

   }
   public static void main(String[] args)
   {
      Scanner scan = new Scanner(System.in);
      ArrayList<Integer> intList = new ArrayList<Integer>();
      double A = scan.nextInt();
      double B = scan.nextInt();
      double C = scan.nextInt();
      double D = scan.nextInt();
      scan.nextLine();
      while ((!eql(A, B)) || (!eql(B, C)) || (!eql(C, D)) || (!eql(A, -1)))
      {
         if (eql(A, -1))
         {
            if(eql(D - C, C - B))
            //if (D - C == C - B)
            {
               A = B + C - D;
            }
            else if(eql(D/C, C/B))
            //else if (D / C == C / B)
            {
               A = (B / D) * C;
               //A = B * C / D;
            }
            if (A < 0.5 || A > 1000000 || !isInt(A))
            {
               A = -1;
            }
            intList.add((int)Math.round( A));
         }
         else if(eql(B, -1))
         //else if (B == -1)
         {
            if(eql(D - C, (C - A) / 2))
            //if (D - C == (C - A) / 2)
            {
               B = A + D - C;
            }
            else if(eql((D /C)*(D/C), C/A    ))
            //else if (D * D / C / C == C / A)
            {
               B = A * D / C;
            }
            if (B < 0.5 || B > 1000000 || !isInt(B))
            {
               B = -1;
            }
            intList.add((int)Math.round(B));
         }
         else if(eql(C, -1))
         //else if (C == -1)
         {
            if( eql((D - B) / 2, B - A))
            //if ((D - B) / 2 == B - A)
            {
               C = D + A - B;
            }
            else if(eql(D/B, (B/A)*(B/A)))
            //else if (D / B == B * B / A / A)
            {
               C = (D / B) * A;
            }
            if (C < 0.5 || C > 1000000 || !isInt(C))
            {
               C = -1;
            }
            intList.add((int)Math.round(C));
         }
         else
         {
            if(eql(C - B, B - A))
            //if (C - B == B - A)
            {
               D = C + B - A;
            }
            else if(eql(C/B, B/A))
            //else if (C / B == B / A)
            {
               D = (C /A) * B;
            }
            if (D < 0.5 || D > 1000000 || !isInt(D))
            {
               D = -1;
            }
            intList.add((int)Math.round(D));
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