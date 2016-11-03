import java.util.Scanner;

/**
 * Created by Alyssa on 7/31/2016.
 */
public class ProbC
{

    public static void main(String[] args)
    {
        Scanner sc =new  Scanner(System.in);
        int N = sc.nextInt();
        int[] expected = new int[N];
        int[] beats = new int[N];
        int total = sc.nextInt();
        for(int i = 1; i < N; i++) {
            int newBeat = total - (sc.nextInt() + expected[i]);
            total -= newBeat;
            beats[i] = newBeat;
            for (int j = i; j < N; j += i) {
                expected[j] += newBeat;
            }
        }
        if(total < 0){
            System.out.print("bad beats");
        }
        else{
            for (int i = 1; i < N; i++)
            {
                if(beats[i] != 0) {
                    System.out.println("1/" + i + " " + beats[i]);
                }
            }
        }
    }
}
