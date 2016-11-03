import java.util.*;

/**
 * Created by Alyssa on 9/24/2016.
 */
public class first {
    static String[] arr = {"@", "8", "(", "|)", "3", "#", "6", "[-]", "|", "_|",
            "|<", "1", "[]\\/[]", "[]\\[]", "0", "|D", "(,)", "|Z", "$",
            "']['", "|_|", "\\/", "\\/\\/", "}{", "`/", "2" };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        char[] ch = line.toCharArray();

        StringBuilder out = new StringBuilder();
        for(int i = 0; i < ch.length; i++) {
            int chIdx = (int) ch[i];
            if ((chIdx >= 65)&&(chIdx <= 90)) {
                chIdx = chIdx % 65;
                out.append(arr[chIdx]);
            }
            else if ((chIdx >= 97)&&(chIdx <= 122)) {
                chIdx = chIdx % 97;
                out.append(arr[chIdx]);
            }
            else {
                out.append(ch[i]);
            }
        }
        System.out.print(out);
    }
}
