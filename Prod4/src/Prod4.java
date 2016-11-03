import java.util.Arrays;
import java.util.Scanner;

public class Prod4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] B = new int[N];
        for (int i = 0; i < N; i++) {
            B[i] = sc.nextInt();
        }
        System.out.print(solution(B, M));
    }


    public static int solution(int[] B, int M) {
        long prod = 1;
        int N = B.length;
        A[] a = new A[N];
        Arrays.fill(a, new A(1, M));
        for(int i = 1; i < N; i++) {
            if((i==1) || B[i] > B[i-1]) {
                    a[i-1].max = B[i];
                    a[i-1].min = B[i];
            }
            else {
                a[i].min = 1 + B[i];
            }
            int poss = (a[i - 1].max - a[i - 1].min) + 1;
            prod *= poss;
        }
        int poss = (a[N - 1].max - a[N - 1].min) + 1;
        prod *= poss;
        return (int) prod % ((int)(Math.pow(10, 9) + 9));
    }

    static class A {
        int min, max;

        A() {
        }

        A(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
