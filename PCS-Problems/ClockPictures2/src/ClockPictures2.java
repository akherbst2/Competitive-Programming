import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Alyssa on 8/2/2016.
 */
public class ClockPictures2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] in = new int[n];
        for (int i = 0; i < n; i++) {
            in[i] = sc.nextInt();
        }
        int[] in2 = new int[n];
        for(int i = 0; i < n; i++) {
            in2[i] = sc.nextInt();
        }
        Arrays.sort(in);
        Arrays.sort(in2);
        int[] nxtIfFail = new int[n];
        int c = 0;
        int[] diff = new int[n];
        int total = 360000;
        for (int i = 0; i < n; i++) {
            if(i == n - 1) {
                diff[n - 1] = total;
            }
            else {
                diff[i] = in[i + 1] - in[i];
                total -= diff[i];
            }
            if ((diff[i] == diff[c])&&(i != 0)) {
                c++;
            } else {
                c = 0;
            }
            nxtIfFail[i] = c;
        }
        c = 0;
        int[] newDiff = new int[n];
        int i = 0;
        total = 360000;
        while((i < n) || (c > 0)) {
            if(i < n - 1) {
                newDiff[i] = in2[i + 1] - in2[i];
                total -= newDiff[i];
            } else if (i == n - 1){
                newDiff[i] = total;
            }
            if(newDiff[i % n] == diff[c]) {
                c++;
            } else {
                c = nxtIfFail[c];
            }
            if(c >= n) {
                System.out.print("possible");
                return;
            }
            i++;
        }
        System.out.print("impossible");
    }
}
