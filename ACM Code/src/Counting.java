import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Alyssa on 4/5/2016.
 *
 * Info on counts here:
 * https://docs.google.com/presentation/d/1PEMuMCZC3tHFVdbaj7K2T55HEucpVNQ7_79PrvH98EU/edit#slide=id.p
 */
public class Counting {

//    ArrayList<String> subset = new ArrayList<String>();
//
//    public static void makeSubsets(char[] arr, int ind, StringBuilder bob) {
//        if (ind == arr.length) {
//            subset.add(bob.toString());
//        }
//        makeSubsets(arr, ind + 1, bob);
//        bob.append(arr[ind]);
//        makeSubsets(arr, ind + 1, bob);
//        bob.deleteCharAt(bob.length() - 1);
//    }
//
//
//
//
//    public static void main(String[] args) {
//        makeSubsets("ABC".toCharArray(), 0, new StringBuilder());
//        System.out.println(subset.toString());
//    }

//    public ArrayList<Integer> primeCombinations(ArrayList<Integer> nums) {
//
//    }


    /**
     * Sets up code by itself.  Doesn't need a method to be called.  Good for lookup tables.
     */
    static long[] factorial = new long[21];
    static {
        factorial[0] = 1;
        for (int i = 1; i < 21; i++) {
            factorial[i] = i * factorial[i - 1];
        }
    }


    public long factorial(long n) {
        if (n ==0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * n people, chosen k at a time
     * n!/(k1!*k2!*k3!...)
     * @param n
     * @param k
     * @return
     */
    public long multinomialCount(int n, int[] k) {
        long nProd = factorial(n);
        //for each of the ks in the array
        int kProd = 1;
        for (int i = 0; i < k.length; i++) {
            //Factorial of each k
            kProd *= factorial(k[i]);
        }
        return nProd/kProd;
    }

    /**
     * Counting sorts for characters in a word
     * @param word
     * @return
     */
    public long arrangements(String word) {
        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();
        for (char c: word.toCharArray()) {
            if (!charCount.containsKey(c)) {
                charCount.put(c, 0);
            }
            charCount.put(c, charCount.get(c) + 1);
        }

        long nFac = factorial[word.length()];
        for (Integer i : charCount.values()) {
            nFac /= factorial[i];
        }
        return nFac;
    }



    /**
     * Order matters.
     * n!/(n - k)!
     * @return
     */
    public long groups(long n, long k) {
        return factorial(n)/(factorial(n-k));
    }

    /**
     * Order doesn't matter.  "n choose k"
     * n!/(k!(n-k)!)
     * @param n number elements
     * @ param k number of ways to order
     */
    public long combination(int n, int k) {
        return factorial(n)/(factorial(k) * factorial(n-k));
    }

}

/***********************************************************************************************************************
/***********************************************************************************************************************
/***********************************************************************************************************************
/***********************************************************************************************************************
/***********************************************************************************************************************

 import java.util.Scanner;
 /**
 * Total time:  6:18
 * https://pcs.spruett.me/problems/26
 * Created by Alyssa on 4/6/2016.
 */
public class RandomElections {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.print(factorial[n]/factorial[n-k]);

    }

    /**
     * Sets up code by itself.  Doesn't need a method to be called.  Good for lookup tables.
     */
    static long[] factorial = new long[21];
    static {
        factorial[0] = 1;
        for (int i = 1; i < 21; i++) {
            factorial[i] = i * factorial[i - 1];
        }
    }
}
