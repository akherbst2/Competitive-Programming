/**
 * Created by Alyssa on 10/1/2016.
 */
import java.util.*;

public class PyroTubes {
    public static void main(String[] args) {
        ArrayList<Long> nums = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLong()) {
            long n = sc.nextLong();
            if (n == -1) break;
            nums.add(n);
        }
        out: for (int i = 0; i < nums.size(); i++){
            int count = 0;
            in: for (int j = i; j < nums.size(); j++){
                if (isValid(nums.get(i), nums.get(j)) <= 2)
                    count++;
            }
            sb.append(nums.get(i)).append(":").append(count - 1).append('\n');
        }
        System.out.println(sb.toString());
    }
    public static long isValid(long n1, long n2){
        long comp = n1 ^ n2;
        return countOnes(comp);
    }
    public static int countOnes(long in){
        int count = 0;
        while (in != 0){
            in = in & (in-1);
            count++;
        }
        return count;
    }
}
