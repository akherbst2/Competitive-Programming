/**
 * Created by Alyssa on 10/26/2016.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class PlaneBoarding {
    static HashMap<String, List<String>> lazies = new HashMap<>();
    static int W;
    static int L;
    static List<String> orderedLazies = new ArrayList<>();
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);

        W = Integer.parseInt(sc.next());
        L = Integer.parseInt(sc.next());

        String[][] arr = new String[W][L];
        String lin = sc.next();
        String[] vals = lin.split(",");
        StringBuilder sb = new StringBuilder();

        for(String val:vals) {
//            int myL = Character.valueOf(val.charAt(1)) - 49;
//            int myW = Character.valueOf(val.charAt(0)) - 65;
            if((val.length() > 2)&&(val.charAt(val.length() - 1)== '*')) {
                int myL = Integer.parseInt(val.substring(1, val.length() - 1)) - 1;
                int myW = Character.valueOf(val.charAt(0)) - 65;
                String key = val.substring(0, val.length() - 1);
                loop:
                for(int l = 0; l < L; l++) {
                    for(int w = 0; w < W; w++) {
                        if((arr[w][l] == null)) {

                            arr[w][l] = key;
                            if(!lazies.containsKey(key)) {
                                lazies.put(key, new ArrayList<>());
                                orderedLazies.add(key);
                            }
                            lazies.get(key).add(((char)(65 + w)) + Integer.toString(l));
                            break loop;
                        }
                        else if ((l == myL) &&(w == myW)) {
                            bump(w, l, arr);
                            arr[w][l] = key;
                            if(!lazies.containsKey(key)) {
                                lazies.put(key, new ArrayList<>());
                                orderedLazies.add(key);
                            }
                            lazies.get(key).add(((char)(65 + w)) + Integer.toString(l));
                            break loop;
                        }

                    }
                }


            }
            //if NOT A LAZY
            else {
                int myL = Integer.parseInt(val.substring(1)) - 1;
                int myW = Character.valueOf(val.charAt(0)) - 65;
                if(arr[myW][myL] != null) {
                    bump(myW, myL, arr);
                }
                arr[myW][myL] = val;
            }

        }

        for(String val: orderedLazies) {
            List<String> path = lazies.get(val);
            for(int k = 0; k < path.size(); k++) {
                sb.append(path.get(k).substring(0, 1) + (Integer.parseInt(path.get(k).substring(1)) + 1));
                if(k != path.size() - 1) {
                    sb.append(',');
                }

            }
            sb.append('\n');
        }
        System.out.print(sb);
        //TODO Iterate through each key in map, adding commas, no comma at the end.

    }

    //key is the thing that should go in the array.
    //we DO NOT know if key is a lazy.
    static void bump(int inW, int inL, String[][] arr) {
        String toMove = arr[inW][inL];
        int tmL = Integer.parseInt(toMove.substring(1)) - 1;
        int tmW = Character.valueOf(toMove.charAt(0)) - 65;
        loop:
        for(int l = 0; l < L; l++) {
            for(int w = 0; w < W; w++) {

                if((arr[w][l] == null)) {

                    arr[w][l] = toMove;
//                    if(!lazies.containsKey(toMove)) {
//                        lazies.put(toMove, new ArrayList<>());
//                        orderedLazies.add(key);
//                    }
                    lazies.get(toMove).add(((char)(65 + w)) + Integer.toString(l));
                    break loop;
                }
                if ((l == tmL) &&(w == tmW)) {
                    bump(w, l, arr);
                    arr[w][l] = toMove;
//                    if(!lazies.containsKey(key)) {
//                        lazies.put(key, new ArrayList<>());
//                        orderedLazies.add(key);
//                    }
                    lazies.get(toMove).add(((char)(65 + w)) + Integer.toString(l));
                    break loop;
                }
            }
        }

    }

    public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(Reader in) {
            br = new BufferedReader(in);
        }

        public FastScanner() {
            this(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String readNextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int[] readIntArray(int n) {
            int[] a = new int[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextInt();
            }
            return a;
        }

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextLong();
            }
            return a;
        }
    }
}