import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Alyssa on 4/5/2016.
 */
public class PrimeCombinations {
    static ArrayList<Long> subset = new ArrayList<Long>();
    static int n;
    static long[] list;

    public static void makeSubsets(long[] arr, int ind, long prod) {
        if (ind == arr.length) {
            subset.add(prod);
            return;
        }
        makeSubsets(arr, ind + 1, prod);
        prod = prod * arr[ind];
        makeSubsets(arr, ind + 1, prod);
        //prod = prod / arr[ind];
    }

    public static void main(String[] args) {
        readInput();
        makeSubsets(list, 0, 1);
        subset.sort(Comparator.naturalOrder());
        for (int k = 0; k < subset.size(); k++) {
            System.out.print(subset.get(k));
            if (k != subset.size() - 1) {
                System.out.print(" ");
            }
        }
    }
    public static void readInput() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        list = new long[n];
        for (int k = 0; k < n; k++) {
            list[k] = sc.nextInt();
        }
    }
}

