import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Alyssa on 10/1/2016.
 */
public class HiddenPassword {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char[] in = sc.next().toCharArray();
        char[] pass = sc.next().toCharArray();

        boolean isVal = isValidPassword(in, pass);
        if(isVal) {
            System.out.print("PASS");
        }
        else {
            System.out.print("FAIL");
        }
    }

    static boolean isValidPassword(char[] in, char[] pass) {
        int k = 0;
        HashMap<Character, Integer> chs = new HashMap<>();
        for(int i = 0; i < in.length; i++) {
            if(chs.containsKey(in[i])){
                chs.put(in[i], 1 + chs.get(in[i]));
            }
            else {
                chs.put(in[i], 1);
            }
        }

        for(int i = 0; i < pass.length; i++) {
            char curr = pass[i];
            if((chs.get(curr) != null) &&(chs.get(curr) > 0)) {
                if(curr == in[k]) {
                    chs.put(in[k], chs.get(curr) - 1);
                    k++;
                    if(k == in.length) {
                        return true;
                    }
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }
}
