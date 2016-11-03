import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class TargetPractice {
    static int N;
    static ArrayList<Double> probs;
    static ArrayList<Integer> indicesNoNullProb;
    static int MAX_ITERATIONS = 30;
    static HashMap<HashMap<Integer, Integer>, Double> dp;
    static int size;
    static double[] probs = new double[MAX_ITERATIONS];
//    static HashMap<Integer[], Integer> dp;


    public static void main(String[] args) {
        dp = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        probs = new ArrayList<>();
        N = sc.nextInt();
        double px = 1;
        HashMap<Integer, Integer> state = new HashMap<>();
        indicesNoNullProb = new ArrayList<>();
        double nullProb = 1;
        for(int i = 0; i < N; i++) {
            Double newProb = sc.nextDouble();
            probs.add(newProb);
            nullProb -= newProb;
            indicesNoNullProb.add(i);
//            px *= newProb;
        }
        probs.add(nullProb);
        final long startTime = System.currentTimeMillis();
        size = probs.size();
        state = new HashMap<>();
        double expVal = recur(state, px, 0, 0);
        System.out.println(expVal);
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) / 1000.0 );
    }

    static double recur(HashMap<Integer, Integer> state, double px, int x, int iter) {
        if (iter < MAX_ITERATIONS) {
            if(hasHitAll(state)) {
                return px * x;
            }
            else {
                double output = 0;
                if(dp.containsKey(state)) {
                    return dp.get(state);
                }
                else {
                    for (int i = 0; i < size; i++) {
                        HashMap<Integer, Integer> newState = new HashMap<>(state);
                        if (!newState.containsKey(i)) {
                            newState.put(i, 0);
                        }
                        newState.put(i, newState.get(i) + 1);
                        output += recur(newState, px * probs.get(i), x + 1, iter + 1);
                    }
                    dp.put(state, output);
                    return output;
                }
            }
        }
        else return 0;
    }

    static boolean hasHitAll(HashMap<Integer, Integer> state)
    {
        return state.keySet().containsAll(indicesNoNullProb);
    }

}

