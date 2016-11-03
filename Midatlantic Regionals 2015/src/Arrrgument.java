import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Arrrgument {
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);

        int numKinds = scan.nextInt();
        int numPicks = 2 * scan.nextInt();
        scan.nextLine();
        
        ArrayList<ArrayList> worth = new ArrayList<ArrayList>();
        HashSet<Character> gems = new HashSet<Character>();
        
        int maxValue = 0;
        while(numKinds != 0) {
            ArrayList<Integer> worths = new ArrayList<Integer>();
            String str = scan.nextLine();
            char gem = str.charAt(0);
            String[] line = str.split(" ");
            for (int i = 1; i < line.length -1; i++) {
                int value = Integer.parseInt(line[i]);
                worths.add(value);
            }
            
            

            numKinds = scan.nextInt();
        }

       


    }

}
