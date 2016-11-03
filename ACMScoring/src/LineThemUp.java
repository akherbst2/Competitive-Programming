/**
 * Created by Alyssa on 10/1/2016.
 */
import java.util.*;
public class LineThemUp {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        ArrayList<String> list = new ArrayList<String>(0);
        TreeSet<String> tree = new TreeSet<String>();
        for(int i = 0; i < N; i++){
            String str = scan.next();
            list.add(str);
            tree.add(str);
        }

        //check if it's increasing
        int index = 0;
        boolean increasing = true;
        for(String str: tree){
            if(list.get(index).equals(str)){
                index++;
            }
            else{
                increasing = false;
                break;
            }
        }

        if(increasing == true){
            System.out.println("INCREASING");
        }
        else {
            boolean decreasing = true;
            index = 0;
            for (String str : tree.descendingSet()) {
                if (list.get(index).equals(str)) {
                    index++;
                } else {
                    decreasing = false;
                    break;
                }
            }

            if (decreasing == true) {
                System.out.println("DECREASING");
            } else {
                System.out.println("NEITHER");
            }

        }
    }
}
