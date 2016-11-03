import java.util.*;
/**
 * Created by Alyssa on 7/31/2016.
 */
public class Prob1 {
    //static //String[] sharps = {"A", "A#", "B", "B#", "C#", "D", "D#", "E", "E#", "F#", "G", "G#"};
    //static //String[] flats  = {"A", "Bb", "Cb", "C", "Db", "D", "Eb", "E", "Fb", "F", "Gb", "G", "Ab"};
    static String[] top = {"B#", "Db", "D", "Eb", "Fb", "E#", Gb""}
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String base = sc.next();
        String third = sc.next();
        int idxBase = -1;

        Comparator<String> comp = new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                if (o1.equals(o2))
                {
                    return 0;
                }
                if( o1.charAt(0)==(o2.charAt(0)))
                {
                    int idx1 = -10;
                    if (o1.length() == 1)
                    {
                        if(o2.charAt(1) == 'b')
                        {
                            return 1;
                        }
                        return -1;
                    }
                    if(o1.charAt(1) == 'b')
                    {
                        return -1;
                    }
                    else if (o1.charAt(1) == '#')
                    {
                        return 1;
                    }

                }
                return o1.compareTo(o2);
            }
        };

        if (base.contains("b"))
        {
            idxBase = Arrays.binarySearch(flats, base, comp);
        }
        else
        {
            idxBase = Arrays.binarySearch(sharps, base, comp);
        }
        int idxThird = -1;
        if(third.contains("b"))
        {
            idxThird = Arrays.binarySearch(flats, third, comp);
        }
        else
        {
            idxThird = Arrays.binarySearch(sharps, third, comp);
        }

        if((Math.abs(idxThird- idxBase) % 14) == 3)
        {
            System.out.println("minor");
        }
        else
        {
            System.out.print("major");
        }



    }
}
