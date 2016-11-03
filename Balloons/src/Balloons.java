import java.util.*;

/**
 * Created by Alyssa on 9/11/2016.
 */
public class Balloons {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();

        List<Team> teams = new ArrayList<>();
        int balloonsNeeded = 0;

        for (int i = 0; i < n; i++) {
            Team t = new Team(sc.nextInt(), sc.nextInt(), sc.nextInt());
            balloonsNeeded += t.k;
            teams.add(t);
        }
        sc.nextInt();
        sc.nextInt();
        sc.nextInt();
        //Greatest delta goes first
        Collections.sort(teams);
        int dist = 0;
        int idx = 0;
        while(balloonsNeeded > 0) {
            Team t = teams.get(idx);
            if (t.da < t.db) {
                if (a > 0) {
                    a--;
                    dist+= t.da;
                }
                else {
                    b--;
                    dist += t.db;
                }
            } else {
               if (b > 0) {
                   b--;
                   dist += t.db;
               }
                else {
                   a--;
                   dist += t.da;
               }
            }
            if(t.k == 0) {
                idx++;
            }
            balloonsNeeded--;
        }

        System.out.println(dist);

    }

    static class Team implements Comparable{
        int k;
        int da;
        int db;
        int delta;
        public Team(int k, int da, int db) {
            this.da = da;
            this.k = k;
            this.db = db;
            this.delta = Math.abs(da - db);
        }

        @Override
        public int compareTo(Object o) {
            Team t = (Team) o;
            return this.delta - t.delta;
        }
    }
}
