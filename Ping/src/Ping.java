import java.util.*;

/**
 * Created by Alyssa on 9/3/2016.
 */
public class Ping {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            List<Integer> pings = new ArrayList<>();
            String line = sc.nextLine();
            if(line.equals("0")) {
                System.exit(0);
            }
            int size = line.length();
            BitSet bits = new BitSet(line.length());
            for (int i = 0; i < size; i++) {
                if (line.charAt(i) == '1') {
                    bits.set(i);
                }
            }
            BitSet exp = new BitSet(size);
            for (int i = 1; i < size; i++) {
                if (bits.get(i) != exp.get(i)) {
                    pings.add(i);
                    for (int j = i; j < size; j += i) {
                        exp.flip(j);
                    }
                }
            }
            StringBuilder bob = new StringBuilder();
            for (int i = 0; i < pings.size(); i++) {
                bob.append(pings.get(i));
                if (i != pings.size() - 1) {
                    bob.append(' ');
                }
            }
            System.out.println(bob);
        }
    }
}
