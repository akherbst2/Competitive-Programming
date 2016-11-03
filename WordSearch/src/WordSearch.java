import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alyssa on 6/5/2016.
 */
public class WordSearch {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        char[][] list = new char[N][N];
        for (int i = 0; i < N; i++) {
            String next = sc.next();
            for ( int k = 0; k < N; k++) {
                list[i][k] = next.charAt(k);
            }
        }

        for (int j = 0; j < K; j++) {

        }
    }
}
