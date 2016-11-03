import java.util.Scanner;

/**
 * Created by Alyssa on 10/5/2016.
 */
public class CantoringAlong {
    static boolean[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int n = Integer.parseInt(sc.next());
            int len = (int)Math.pow(3, n);
            arr = new boolean[len];
            split(0, len, len);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < arr.length; i++) {
                if(arr[i]) sb.append(' ');
                else sb.append('-');
            }
            sb.append("\n");
            System.out.print(sb);
        }
    }

    static void split(int s, int e, int len) {
        if(len != 1) {

            int third = (len / 3);
            split(s, s + third, third);
            for(int i = third + s; i < (third * 2) + s; i++) {
                arr[i] = true;
            }
            split(2*third + s, len + s, third);
        }

    }
}
