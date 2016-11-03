import java.util.*;

/**
 * Created by Alyssa on 8/31/2016.
 */
public class SignalStrength {

    static HashMap<Integer, Double> mult;
    static HashMap<Integer, List<Edge>> edges;
    static HashSet<Integer> visited;
    static Integer end;
    static HashMap<Integer, Double> recieving;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mult = new HashMap<>();
        edges = new HashMap<>();
        int N = sc.nextInt();
        end = N - 1;
        double output = 0;
        if(N != 0) {


            for (int i = 0; i < N; i++) {
                mult.put(i, sc.nextDouble());
                List<Edge> currList = new ArrayList<>();
                int k = sc.nextInt();
                for (int j = 0; j < k; j++) {
                    Integer to = sc.nextInt();
                    Double str = sc.nextDouble();
                    Edge edge = new Edge(to, str);
                    currList.add(edge);
                }
                edges.put(i, currList);
            }

            recieving = new HashMap<>();
            for (int i = 0; i < N; i++) {
                recieving.put(i, 0.0);
            }

            ArrayDeque<Signal> q = new ArrayDeque<>();
            q.offer(new Signal(0, mult.get(0)));
            while (!q.isEmpty()) {
                Signal curr = q.poll();
                recieving.put(curr.on, Math.max(recieving.get(curr.on), curr.st));
                Double currStrength = curr.st * mult.get(curr.on);

                List<Edge> currEdges = edges.get(curr.on);
                for (Edge e : currEdges) {
                    Double newStr = currStrength * e.st;
                    if (newStr > (recieving.get(e.to))) {
                        q.offer(new Signal(e.to, currStrength * e.st));
                    }
                }
            }


            visited = new HashSet<>();

            q = new ArrayDeque<>();
            q.offer(new Signal(0, recieving.get(0)));
            output = 0;
            while (!q.isEmpty()) {
                Signal curr = q.poll();
                Double currStrength = recieving.get(curr.on) * mult.get(curr.on);
                visited.add(curr.on);

                if (curr.on.equals(end)) {
                    output = Math.max(output, currStrength);
                } else {
                    List<Edge> currEdges = edges.get(curr.on);
                    for (Edge e : currEdges) {
                        if (!visited.contains(e.to)) {
                            q.offer(new Signal(e.to, currStrength));
                        }
                    }
                }

            }
        }
        if(N == 1) {
            output = mult.get(0);
        }
        System.out.printf("%.2f\n", output);
    }
    static class Signal {
        Integer on;
        Double st;

        public Signal(Integer on, Double st) {
            this.on=on;
            this.st=st;
        }
    }


    static class Edge {
        Integer to;
        Double st;
        public Edge(Integer to, Double st) {
            this.to = to;
            this.st = st;
        }

    }
}
