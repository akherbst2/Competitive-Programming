/**
 * Created by Alyssa on 8/6/2016.
 */
import java.util.*;
public class Typos {

    public static void main(String[] args) {
        int n = 1000000;
        String domain = "asdf.bvc";
        System.out.print(typosquatting(n, domain));
    }

    static int typosquatting(int n, String domain) {
        Set<String> typos = new HashSet<>();

        int k = 1;
        List<String> listOfLastK = new ArrayList<>();
        listOfLastK.add(domain);
        int numAdded = 0;
        while(typos.size() <= n) {
            List<String> listOfNewK = new ArrayList<>();
            for(String s : listOfLastK) {
                for (int i = 0; i < s.length() - 1; i++) {
                    char c1 = s.charAt(i);
                    char c2 = s.charAt(i + 1);
                    StringBuilder sb = new StringBuilder();
                    if ((c1 != '.') && (c2 != '.')) {
                        sb.append(s.substring(0, i));
                        sb.append(c2);
                        sb.append(c1);
                        if (i < s.length() - 2) {
                            sb.append(s.substring(i + 2));
                        }
                    }
                    if(!sb.toString().equals(domain)&&(!sb.toString().equals(""))) {
                        if(!typos.contains(sb.toString())) {
                            numAdded++;
                        }
                        typos.add(sb.toString());
                        listOfNewK.add(sb.toString());
                    }

                }
            }
            //if no more typos found
            if(numAdded == 0) {
                return -1;
            }
            else {
                numAdded = 0;
            }
            listOfLastK = listOfNewK;
            k++;
        }
        return k - 2;
    }


}
