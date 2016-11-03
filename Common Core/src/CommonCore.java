import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alyssa on 8/21/2016.
 */
public class CommonCore {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        sc.next();
        int b = sc.nextInt();

        List<Integer> nums = new ArrayList<>();
        nums.add(a);
        nums.add(b);
        System.out.println(a + " + " + b);
        while(nums.size() > 1) {
            if(!isSorted(nums)) {
                swap(nums);
            }
            else if(!isSplit(nums)){
                nums = split(nums);
            }
            else {
                if (nums.get(0) > 9 &&(nums.get(1) > 9)) {
                    int k = nums.remove(1);
                    int j = nums.remove(0);
                    nums.add(0, k + j);
                }
                else if(nums.get(nums.size() - 1) < 10 &&(nums.get(nums.size() - 2) < 10)) {
                    int k = nums.remove(nums.size() - 1);
                    int j = nums.remove(nums.size() - 1);
                    nums.add(k + j);
                }
                else {
                    int k = nums.remove(1);
                    int j = nums.remove(0);
                    nums.add(0, k + j);
                }
            }

            System.out.println(asString(nums));
        }
    }

    public static List<Integer> split(List<Integer> nums) {
        List<Integer> newList = new ArrayList<>(nums);
        int idx = 0;
        for (int i = 0; i < nums.size(); i++) {
            int in = nums.get(i);
            if((in % 10 != 0)&&(in / 10 != 0)) {
                newList.remove(i + idx);
                newList.add(i + idx, (in / 10) * 10);
                newList.add( i + 1 + idx, in % 10);
                idx++;
            }
        }
        return newList;
    }

    public static boolean isSplit(List<Integer> nums) {
        for (Integer in: nums) {
            if ((in % 10 != 0)&&(in / 10 != 0)) {
                return false;
            }
        }
        return true;
    }

    public static String asString(List<Integer> nums) {
        StringBuilder bob = new StringBuilder();
        bob.append("= ");
        for(int i = 0; i < nums.size(); i++) {
            bob.append(nums.get(i));
            if (i != nums.size() -1) {
                bob.append(" + ");
            }
        }
        return bob.toString();
    }

    public static boolean isSorted(List<Integer> nums) {
        int prev = Integer.MAX_VALUE;
        for (Integer in: nums) {
            if (in > prev) {
                return false;
            }
            prev = in;
        }
        return true;
    }

    public static void swap(List<Integer> nums) {
        nums.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0 - Integer.compare(o1, o2);
            }
        });

    }
}