import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Alyssa on 6/4/2016.
 */
public class NPalindromes {
    static int T;
    static HashMap<Integer, ArrayList<Integer>> dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        dp = new HashMap<Integer, ArrayList<Integer>>();
        runPalindromes();
        for(int k = 0; k < T; k++) {
            int next = sc.nextInt();
            palindrome(next);
            ArrayList<Integer> numEachLetter = dp.get(next);
            String s = stringFor(numEachLetter);
            System.out.println(s);
        }
    }

    static void runPalindromes() {
        int n = 1;
        int result = 0;
        while(n <= 10000) {
            result+=n;
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(n);
            dp.put(result, list);
            n++;
        }
    }

    static void palindrome(int test) {
        if(!dp.containsKey(test)) {
            int nextLargest = test;
            while (!dp.containsKey(nextLargest)) {
                nextLargest--;
            }
            ArrayList<Integer> newList = new ArrayList<>(dp.get(nextLargest));
            int remainder = test - nextLargest;
            palindrome(remainder);
            newList.addAll(dp.get(remainder));
            dp.put(test, newList);
        }
    }

    static String stringFor(ArrayList<Integer> nums) {
        if (nums.size() > 26) {
            return "-1";
        }
        else {
            int charNum = 97;
            StringBuilder bob = new StringBuilder();
            for (Integer num:nums) {
                for (int k = 0; k < num; k++) {
                    bob.append((char) charNum);
                }
                charNum++;
            }
            return bob.toString();
        }
    }


}
