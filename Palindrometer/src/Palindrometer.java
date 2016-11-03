import java.util.Scanner;

/**
 * Created by Alyssa on 9/11/2016.
 */
public class Palindrometer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        while(!s.equals("0")) {
            int[] p = new int[s.length()];
            int x = Integer.parseInt(s);
            int idx = p.length - 1;
            while(x > 0) {
                p[idx] = x % 10;
                x = x / 10;
                idx--;
            }
            int total = 0;
            while (!isPalindrome(p)) {
                boolean done = false;
                while (!done) {
                    idx = p.length - 1;
                    p[idx]++;
                    total++;
                    if (p[idx] / 10 > 0) {
                        p[idx] = p[idx] % 10;
                        p[idx - 1]++;
                    } else {
                        done = true;
                    }
                }
            }
            System.out.println(total);
            s = sc.next();
        }
    }

    static boolean isPalindrome(int[] arr) {
        for(int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - (1 + i)]) {
                return false;
            }
        }
        return true;

    }
}
