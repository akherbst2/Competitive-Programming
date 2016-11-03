import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Alyssa on 10/1/2016.
 */
public class ACMScoring {


    public static void main(String[] args) {
        int probs = 0;
        int time = 0;

        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> numFails = new HashMap<>();
        int t = Integer.parseInt(sc.next());

        while(t != -1) {
            String p = sc.next();
            String rw = sc.next();

            if(rw.equals("wrong")) {
                if(numFails.containsKey(p)) {
                    numFails.put(p, numFails.get(p) + 1);
                }
                else {
                    numFails.put(p, 1);
                }
            }
            else {  //rw = "right"
                if(numFails.containsKey(p)) {
                    time += (20 * numFails.get(p));
                }
                time += t;
                probs++;
            }
            t = Integer.parseInt(sc.next());
        }
        System.out.print(probs + " " + time);


    }
}
