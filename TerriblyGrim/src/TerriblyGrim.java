import java.util.*;

/**
 * Created by Alyssa on 9/14/2016.
 */
public class TerriblyGrim {
    static List<Integer> primes;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lower = sc.nextInt();
        int upper = sc.nextInt();

        primes = primes(upper, lower);
        HashMap<Integer, List<Integer>> div = new HashMap<>();


        //setup.  adds a list of primes per number in set.
        for(int i = lower; i <= upper; i++) {
            List<Integer> pfd = new ArrayList<>();
            for(int j = 0; j < primes.size(); j++) {
                double divided = (i* 1.0) / primes.get(j);
                if ((divided % 1) == 0) {
                    pfd.add(primes.get(j));
                }
            }
            div.put(i, pfd);
        }

    }

    static int[] method(int[] currPerNum, int idx, List<Integer> availPrimes) {
        if (currPerNum == null) {
            return null;
        }
        if((availPrimes.size() > 0 )||(availPrimes != null)) {
            currPerNum[idx]
        }

    }

    static List<Integer> primes(int upper, int lower) {
        List<Integer> output = new ArrayList<Integer>();
        boolean[] arr = new boolean[(upper / 2) + 1];
        Arrays.fill(arr, true);
        for(int i = 2; i < arr.length; i++) {
            if (arr[i]) {
                output.add(i);
            }
            for(int j = i; j < arr.length; j+= i) {
                arr[j] = false;
            }
        }
        return output;
    }


}
