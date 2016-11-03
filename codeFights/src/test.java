/**
 * Created by Alyssa on 8/6/2016.
 */
import java.util.*;
public class test {
    public static void main(String[] args) {
        //String[][] arr = {{"A", "B"}, {"C", "D"}, {"B", "B"}, {"E", "A"}};
        String[][] arr =  {{"godaddy.net","godaddy.com"},
                {"godaddy.org","godaddycares.com"},
                {"godady.com","godaddy.com"},
                {"godaddy.ne","godaddy.net"}};
        System.out.print(Arrays.deepToString(domainForwarding(arr)));
    }

    static String[][] domainForwarding(String[][] redirects) {
        HashMap<String, String> sites = new HashMap<>();
        for(int i = 0; i < redirects.length; i++) {
            sites.put(redirects[i][0], redirects[i][1]);
        }
        Set<String> seen = new HashSet<>();
        HashMap<String, Set<String>> output = new HashMap<>();
        for(String end:sites.keySet()) {
            while(!seen.contains(end)) {
                seen.add(end);
                end = sites.get(end);
                if (!sites.containsKey(end)) {
                    break;
                }
            }
            if(output.containsKey(end)) {
                Set<String> val = output.get(end);
                val.addAll(seen);
                output.put(end, val);
            }
            else {
                output.put(end, seen);
            }
            seen = new HashSet<>();
        }
        List<String[]> step1 = new ArrayList<>();
        Set<Map.Entry<String, Set<String>>> entries = output.entrySet();
        for(Map.Entry<String, Set<String>> entry: entries) {
            List<String> newList = new ArrayList<>(entry.getValue());
            newList.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            newList.add(entry.getKey());
            step1.add(newList.toArray(new String[newList.size()]));
        }
        step1.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                String s1 = o1[o1.length - 1];
                String s2 = o2[o2.length - 1];
                return s1.compareTo(s2);
            }
        });

        return step1.toArray(new String[step1.size()][]);
    }

}
