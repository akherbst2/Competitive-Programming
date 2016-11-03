import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Alyssa on 9/19/2016.
 */
public class MarbleMadness {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int max = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            max += arr[i];
        }
        if(arr.length == 1) {
            max = 0;
        }
        else if (arr.length > 2) {
            max *= 2;
        }

    }

    static Pair recur(int idx, Pair p) {
        if (idx > p.arr.length - 1) {
            return p;
        }
        else {
            int move1 = p.moves;
            int[] arr1 = Arrays.copyOf(p.arr, p.arr.length);
            int move2 = p.moves;
            int[] arr2 = Arrays.copyOf(p.arr, p.arr.length);
            if(inBounds(idx + 1, p.arr)) {

            }

        }



    }

    static boolean inBounds(int idx, int[] arr) {
        return idx >= 0 && idx < arr.length;
    }

    static class Pair {
        int moves;
        int[] arr;


        public Pair(int moves, int[] arr) {
            this.moves = moves;
            this.arr = arr;
        }
    }
}
