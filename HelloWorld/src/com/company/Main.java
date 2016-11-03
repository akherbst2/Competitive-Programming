import java.util.*;
public class Main {
    /**
     *  Start out with a priority Queue based off of the shortest path of
     *  unvisited nodes, plus a list of "shortest paths" to each node that
     *  begin at infinity.
     *
     *  Visit start, add neighbors to queue if their distance is less
     *  than current distance.
     *
     6 8
     0 1 1
     1 0 3
     1 2 1
     2 3 2
     3 4 1
     4 5 4
     5 3 2
     4 0 3
     2
     1 2



     *  Goal is ElogV
     *  E = edges, V = Vertices
     *
     *  Use static inner classes so you can submit in one file :3
     *
     *  https://pcs.spruett.me/problems/21
     */

    static Queue<Integer> queue = new PriorityQueue<Integer>();
    //static Map<State, Double> distances = new HashMap<State, Double>();
    static int[][] path;
    //static HashMap<Integer, List<Integer>> path
    static int P;
    static int W;
    static int home = 0;
    static int output = 0;
    static List<Base> targets;

    public static void main( String[] args) {

        readInput();
        for (Base b: targets) {
            System.out.println(b.toString());
        }
        // for(Base b:targets) {
        // dhikstras(b);
        //    // for (Base endpoint:targets) {
        //       // if (b.start != endpoint.start) {
        //          // output += b.dest[endpoint.start];
        //       // }
        //    // }
        // }

        System.out.println(output);
    }

    static class Base {
        Integer start;
        Integer[] dest;
        Queue<Integer> pq;

        public Base(int start, int destSize ) {
            dest = new Integer[destSize];
            Arrays.fill(dest, (int) Double.POSITIVE_INFINITY);
            dest[start] = 0;
            pq = new PriorityQueue<Integer>();
        }
        @Override
        public String toString() {
            StringBuilder bob = new StringBuilder();
            bob.append(start);
            bob.append("\t");
            bob.append(Arrays.toString(dest));
            return bob.toString();
        }
    }

    public static void dhikstras(Base base) {
        Queue<Integer> pq = base.pq;
        boolean[] visited = new boolean[P];
        int visitCount = 0;
        pq.add(base.start);

        while((!pq.isEmpty())&&(visitCount < P)){
            Integer idx = pq.remove();
            if (visited[idx] == false) {
                //for each neighbor
                for(int k = 0; k < P; k++) {
                    //if new path is shorter than old path
                    if((path[idx][k] != -1)&&(base.dest[idx] + path[idx][k] < base.dest[k])) {
                        //the new destination to k will be rerouted through idx
                        base.dest[k] = base.dest[idx] + path[idx][k];
                        //adds the neighbor to pq
                        pq.add(k);
                        visited[idx] = true;
                        visitCount++;
                    }
                }
            }
        }

    }



    public static void readInput() {

        Scanner sc = new Scanner(System.in);
        P = sc.nextInt();
        W = sc.nextInt();

        path = new int[P][P];

        for (int k = 0; k < W; k++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();
            path[start][end] = cost;
        }
        targets  = new ArrayList<Base>();

        //adds the Home Base as a target
        targets.add(new Base(home, P));

        //Reads in the target Bases and creates them
        int numTargets = sc.nextInt();
        targets = new ArrayList<Base>();

        for (int k = 0; k < numTargets; k++) {
            targets.add(new Base(sc.nextInt(), P));
        }

        //Finds shortest paths for each target
        for(Base base:targets) {
            dhikstras(base);
        }

    }
}