import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Alyssa on 9/17/2016.
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
        if (arr.length == 1) {
            max = 0;
        }
        else if (arr.length > 2) {
            max *= 2;
        }
        Pair p = recur(0, new Pair(0, arr));
        System.out.print(max + " " + p.moves);
    }

    static Pair recur(int idx, Pair p) {
        if(idx > p.arr.length - 1) {
            return p;
        }
        else {
            int move1 = p.moves;
            int[] arr1 = Arrays.copyOf(p.arr, p.arr.length);
            int move2 = p.moves;
            int[] arr2 = Arrays.copyOf(p.arr, p.arr.length);
            if(inBounds(idx - 1, p.arr)) {
                arr1[idx] = arr1[idx] + arr1[idx - 1];
                move1 += arr1[idx - 1];
                arr1[idx - 1] = 0;
            }
            if(inBounds(idx + 1, p.arr)) {
                arr1[idx] = arr1[idx] + arr1[idx + 1];
                move1 += arr1[idx + 1];
                arr1[idx + 1] = 0;

                arr2[idx + 1] = arr2[idx + 1] + arr2[idx];
                move2 += arr2[idx];
                arr2[idx] = 0;
            }
            if(inBounds(idx + 2, p.arr)) {
                arr2[idx + 1] = arr2[idx + 1] + arr2[idx + 2];
                move2 += arr2[idx + 2];
                arr2[idx + 2] = 0;
            }
            Pair pair1 = recur(idx + 2, new Pair(move1, arr1));
            Pair pair2 = recur(idx + 3, new Pair(move2, arr2));
            if((idx == 0)&&(p.arr.length > 2)){
                return pair2;
            }
            if((idx == p.arr.length - 1)&&(p.arr.length > 2)) {
                return pair1;
            }
            if(pair1.moves < pair2.moves) {
                return pair1;
            }
            return pair2;
        }
    }

    static boolean inBounds(int idx, int[] arr) {
        return idx >= 0 && idx < arr.length;

    }

    static class Pair{
        int moves;
        int[] arr;

        public Pair(int moves, int[] arr) {
            this.moves = moves;
            this.arr = arr;
        }
    }
}
