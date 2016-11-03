import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://pcs.spruett.me/problems/38
 * Instead of incrementing by letters, we increment by delta angle
 * We built 2 tables of delta angles
 * Looks at the angles between each hand
 * Created by Alyssa on 4/26/2016.
 */
public class ClockPictures {
    static ArrayList<Double> search;
    static ArrayList<Double> target;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        search = new ArrayList<Double>();
        target = new ArrayList<Double>();
        N = sc.nextInt();
        for (int k = 0; k < N; k++) {
            search.add(sc.nextDouble());
        }
        for (int k = 0; k < N; k++) {
            target.add(sc.nextDouble());
        }

        System.out.println(KMP(search, target) >= 0 ? "yes":"no");
    }



    public static int[] failureTable(ArrayList<Double> target) {
        int[] table = new int[target.size() + 1];
        table[0] = -1;
        table[1] = 0;

        int left = 0;
        int right = 2;

        while(right < table.length) {
            if (target.get(right-1) == target.get(left)) {
                left++;
                table[right] = left;
                right++;
            }
            else if(left > 0)
            {
                left = table[left];
            }
            else {
                table[right] = left;
                right++;
            }
        }
        return table;
    }

    public static int KMP(ArrayList<Double> search, ArrayList<Double> target) {
        int[] failTable = failureTable(target);

        int tP = 0; //Pointer into Target
        int sP = 0; //Pointer into Search
        while (sP < search.size()) {
            if (search.get(sP) == target.get(tP)) {
                tP++;
                //Returns the index of the start of the string
                if (tP == target.size()) {
                    return sP + 1 - target.size();
                }
                sP++;
            }
            else if (tP > 0){
                tP = failTable[tP];
            }
            else {
                sP++;
            }
        }
        return -1;
    }
}

