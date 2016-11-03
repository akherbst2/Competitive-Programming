import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alyssa on 10/5/2016.
 */
public class DiskGame {
    static HashMap<Character, List<Integer>> map;
    static StringBuilder sb;
    static final int INIT = 65;
    public static void main(String[] args) {
        sb = new StringBuilder();
        map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> aInit = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            aInit.add(i);
        }
        map.put('A', aInit);
        map.put('B', new ArrayList<>());
        map.put('C', new ArrayList<>());

        recur('A', 'C', 'B', n - 1);
        System.out.print(sb);
    }

    static void recur(char from, char to, char temp, int idx) {
        if(map.get(from).size()) {
            move(from, to);
        }
        else if (idx > 0){
            recur(from, temp, to, idx - 1);
            move(from, to);
            recur(temp, to, from, map.get(temp).size() - 1);
        }
    }

    static void move(char from, char to) {
        int top = map.get(from).remove(0);
        map.get(to).add(0, top);
        sb.append(from);
        sb.append(" -> ");
        sb.append(to);
        sb.append("\n");
    }
}
