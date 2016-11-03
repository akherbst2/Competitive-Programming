import java.util.Scanner;

/**
 * Created by Alyssa on 4/26/2016.
 */
public class KMP {
    static String search;
    static String target;
    public static void main(String[] args) {
        //String search = "ABAEABBABACABACABAE";
        //String target = "ABACABAD";
        Scanner sc = new Scanner(System.in);

        search = sc.next();
        target = sc.next();
        System.out.println(KMP(search, target) >= 0 ? "yes":"no");
    }



    public static int[] failureTable(String target) {
        int[] table = new int[target.length() + 1];
        table[0] = -1;
        table[1] = 0;

        int left = 0;
        int right = 2;

        while(right < table.length) {
            if (target.charAt(right-1) == target.charAt(left)) {
                left++;
                table[right] = left;
                right++;
            }
            else if(left > 0)
            {
                left = table[left];
            }
            else {
                table[right] = left;
                right++;
            }
        }
        return table;
    }

    public static int KMP(String search, String target) {
        int[] failTable = failureTable(target);

        int tP = 0; //Pointer into Target
        int sP = 0; //Pointer into Search
        while (sP < search.length()) {
            if (search.charAt(sP) == target.charAt(tP)) {
                tP++;
                //Returns the index of the start of the string
                if (tP == target.length()) {
                    return sP + 1 - target.length();
                }
                sP++;
            }
            else if (tP > 0){
                tP = failTable[tP];
            }
            else {
                sP++;
            }
        }
        return -1;
    }
}
