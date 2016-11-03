/**
 * Created by Alyssa on 11/2/2016.
 */

import java.util.*;

public class HouseRobbing {
    static int[] house;
    static long[] MV;
    static HashMap<Integer, List<Integer>> map;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of house: ");
        map = new HashMap<>();
        int n = sc.nextInt();
        house = new int[n];
        MV = new long[n];
        Arrays.fill(MV, -1);
        System.out.println("Enter the values at each house, separated by a space");
        for(int k = 0; k < n; k++) {
            house[k] = sc.nextInt();
        }
        System.out.println("Max value = " + rob(n - 1));
        System.out.println("Rob from houses:");
        List<Integer> houses = map.get(n - 1);
        for(Integer h:houses) {
            System.out.print(h);
            if (h != houses.get(houses.size() - 1)) {
                System.out.print(" ");
            }
        }

    }

    static long rob(int k) {
        if (k < 0) {
            map.put(k, new ArrayList<>());
            return 0;
        }
        else if (MV[k] != -1) {
            return MV[k];
        } else {
            long dontRob = rob(k - 1);
            long rob = rob(k - 2) + house[k];
            if (dontRob > rob) {
                map.put(k, map.get(k - 1));
                MV[k] = dontRob;
            } else {
                List<Integer> prevList = map.get(k - 2);
                List<Integer> currList = new ArrayList<>(prevList);
                currList.add(k);
                map.put(k, currList);
                MV[k] = rob;
            }
            return MV[k];
        }
    }

}