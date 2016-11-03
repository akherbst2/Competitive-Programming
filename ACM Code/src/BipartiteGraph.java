import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Alyssa on 4/21/2016.
 */
public class BipartiteGraph {

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
    public int findMaxMatching(ArrayList<ArrayList<Integer>> graph) {
        int N = graph.size();
        int matches = 0;
        int[] matchedTo = new int[N];
        Arrays.fill(matchedTo, -1);
        for(int node = 0; node < N; node++) {
            boolean[] visited = new boolean[N];
            if (findMatchingNode(node, graph, matchedTo, visited)) {
                matches++;
            }
        }
        return matches;
    }

    public boolean findMatchingNode(int node, ArrayList<ArrayList<Integer>> graph, int[] matchedTo, boolean[] visited) {
        if (visited[node]) {
            return false;
        }
        visited[node] = true;
        ArrayList<Integer> nbrs = graph.get(node);
        for (Integer n: nbrs) {
            if (matchedTo[n] == -1 || findMatchingNode(n, graph, matchedTo, visited)) {
                matchedTo[n] = node;
                return true;
            }
        }
        return false;
    }



}
