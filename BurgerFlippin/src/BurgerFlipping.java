import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Alyssa on 4/8/2016.
 */
public class BurgerFlipping {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> orders = new ArrayList<Integer>();
        double sum = 0;
        for (int i = 0; i < n; i++) {
            orders.add(sc.nextInt());


        }
        Collections.sort(orders);
        double time = 0;
        for(Integer o :orders){
            time = time + o;
            sum = sum + time;
            //System.out.println(sum);

        }

        //System.out.println(sum);
        //DecimalFormat df = new DecimalFormat("#.###");
        System.out.println(sum / n);
    }
}
