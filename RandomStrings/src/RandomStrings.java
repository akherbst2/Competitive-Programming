import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alyssa on 8/21/2016.
 */
public class RandomStrings {
    static int N;
    static int total;
    static List<String> words;
    static HashMap<String, Integer[]> onFail;
    static HashMap<String, Integer> counters;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        total = 0;
        words = new ArrayList<>();
        onFail = new HashMap<>();
        counters = new HashMap<>();
        for(int i = 0; i < N; i++) {
            String nxt = sc.next();
            createOnFail(nxt);
            words.add(nxt);
            counters.put(nxt, 0);
        }
        String big = sc.next();
        for(int i = 0; i < big.length(); i++) {
            char ch = big.charAt(i);
            for(String word:words) {
                int counter = counters.get(word);
                if(ch == word.charAt(counter)) {
                    counter = counter + 1;
                    if(counter == word.length()) {
                        counter = onFail.get(word)[word.length()];
                        if (counter == word.length()) {
                            counter --;
                        }
                        counters.put(word, counter);
                        total++;
                    }
                    else {
                        counters.put(word, counter);
                    }
                }
                else {
                    counter = onFail.get(word)[counter];
                    counters.put(word, onFail.get(word)[counter]);
                }
            }
        }
        System.out.print(total);
    }

    static void createOnFail(String word) {
        Integer[] arr = new Integer[word.length() + 1];
        arr[0] = 0;
        if (word.length() > 1) {
            arr[1] = 0;
        }
        int c = 0;
        for(int i = 2; i < word.length(); i++) {
            if(word.charAt(i) == word.charAt(c)) {
                c++;
                arr[i] = c;
            }
            else {
                c = 0;
                arr[i] = c;
            }
        }
        if(word.charAt(0) == word.charAt(c)) {
            c++;
            arr[word.length()] = c;
        }
        else {
            c = 0;
            arr[word.length()] = c;
        }
        onFail.put(word, arr);
    }
}
