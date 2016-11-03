import java.util.*;

/**
 * Created by Alyssa on 9/21/2016.
 */
public class TexasSummers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Spot> spots = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            spots.add(new Spot(sc.nextInt(), sc.nextInt(), i));
        }
        Spot start = new Spot(sc.nextInt(), sc.nextInt(), -1);
        Spot end = new Spot(sc.nextInt(), sc.nextInt(), -2);

    }

//    public List<Spot> dijkstras(Spot s, Spot e, List<Spot> shades) {
//        Queue<Spot> q = new PriorityQueue<>();
//        //  spot, min sweat
//        Map<Spot, Sweat> minSweats = new HashMap<>();
//        q.offer(s);
//        minSweats.put(s, new Sweat());
//        while(!q.isEmpty()) {
//            Spot curr = q.poll();
//            if(curr.goal()) {
//                return minSweats.get(curr).path;
//            }
//            if(minSweats.get(curr).ammount < curr.ammount) {
//                continue;
//            }
//
//            for(Spot adj: shades) {
//                if(!minSweats.containsKey(adj) || minSweats.get(adj).ammount > minSweats.get(curr).add(adj).ammount) {
//                    q.offer(adj);
//                    minSweats.put(adj, sweatPath.add(adj));
//                }
//
//
//            }
//
//        }
//
//    }



    static class State {
        Sweat sweat;
        Spot spot;
        public State(Sweat sw, Spot sp) {
            this.sweat = sw;
            this.spot = sp;
        }
    }

    static class Sweat {
        List<Spot> path;
        Double ammount;
        public Sweat() {
            this.path = new ArrayList<>();
            this.ammount = 0.0;
        }

        public Sweat(List<Spot> path, Double ammount) {
            this.path = path;
            this.ammount = ammount;
        }

        public Sweat add(Spot spot) {
           List<Spot> newPath = new ArrayList<>(path);
            newPath.add(spot);
            return new Sweat(newPath, this.ammount + Math.pow(sweatCreated(newPath.get(newPath.size()), spot), 2));
        }
    }


    static double sweatCreated(Spot a, Spot b) {
        return Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2)  ;
    }

    static final class Spot {
        int x, y, idx;
        public Spot(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }

        public boolean goal() {
            return idx == -2;
        }

        @Override
        public int hashCode() {
            return idx;
        }

        @Override
        public boolean equals(Object obj) {
            Spot spot = (Spot) obj;
            return this.idx == spot.idx;
        }
    }
}
