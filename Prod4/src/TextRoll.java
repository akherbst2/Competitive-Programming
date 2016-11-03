import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alyssa on 9/3/2016.
 */
public class TextRoll {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();
        List<String> lns = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            lns.add(sc.nextLine());
        }

        int c = 0;

        int j = 0;

        while(j < lns.size()) {

            String line = lns.get(j);

            while(c < line.length()) {
                if(line.charAt(c) == ' ') {
                    break;
                } else {
                    c++;
                }
            }
            j++;
        }

        System.out.print(c + 1);
    }
}
