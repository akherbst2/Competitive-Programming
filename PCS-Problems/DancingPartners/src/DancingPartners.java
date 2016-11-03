import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Alyssa on 4/21/2016.
 */
public class DancingPartners {
    static int M, F, P;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        F = sc.nextInt();
        P = sc.nextInt();

        graph = new ArrayList<ArrayList<Integer>>();
        //F starts at M
        for (int k = 0; k < (M + F); k++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int k = 0; k < P; k++) {
            int m = sc.nextInt();
            int f = sc.nextInt();
            graph.get(m).add(f + M);
            graph.get(f + M).add(m);
        }
        int matches = findMaxMatching(graph);
        System.out.println(M + F - matches*2);
    }

    /**
     * Pick node on 1 side
     * for every red node
     * for every nbr
     * pair if we can, mark as matched
     * if we can't, we go back through a red and a blue
     * retrace our steps
     *
     * @param graph
     * @return
     */
    static int findMaxMatching(ArrayList<ArrayList<Integer>> graph) {
        int N = graph.size();
        int matches = 0;
        int[] matchedTo = new int[N];
        Arrays.fill(matchedTo, -1);
        for(int node = 0; node < M; node++) {
            boolean[] visited = new boolean[N];
            if (findMatchingNode(node, graph, matchedTo, visited)) {
                matches++;
            }
        }
        return matches;
    }

    static boolean findMatchingNode(int node, ArrayList<ArrayList<Integer>> graph, int[] matchedTo, boolean[] visited) {
        if (visited[node]) {
            return false;
        }
        visited[node] = true;
        ArrayList<Integer> nbrs = graph.get(node);
        for (Integer n: nbrs) {
            if (matchedTo[n] == -1 || findMatchingNode(matchedTo[n], graph, matchedTo, visited)) {
                matchedTo[n] = node;
                matchedTo[node] = n;
                return true;
            }
        }
        return false;
    }

}
