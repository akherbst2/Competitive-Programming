import java.util.Scanner;

/**
 * Created by Alyssa on 9/24/2016.
 */
public class QuickEstimates {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        StringBuilder bob = new StringBuilder();
        for(int i = 0; i < n; i++) {
            String s = sc.nextLine();
            bob.append(s.length());
            if(i != n - 1) bob.append('\n');
        }
        System.out.print(bob);
    }

}
