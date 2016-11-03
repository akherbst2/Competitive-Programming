/**
 * Created by Alyssa on 10/29/2016.
 */

import java.util.*;
import java.io.*;

public class Kinfolk {
    static List<Integer> levels;
    static HashMap<R, String> map= new HashMap();
    static {
        levels = new ArrayList<>();
        int max = 0;
        int idx = 1;
        while(max <= 32770) {
            levels.add(max);
            max += Math.pow(2, idx);
            idx++;
        }
        levels.add(max);


        //Filling in relationships
        map.put(new R(true, 0, -4), "great-great-grandparent");
        map.put(new R(true, 0, -3), "great-grandparent");
        map.put(new R(true, 0, -2), "grandparent");
        map.put(new R(true, 0, -1), "parent");

        map.put(new R(true, 0, 1), "child");
        map.put(new R(true, 0, 2), "grandchild");
        map.put(new R(true, 0, 3), "great-grandchild");
        map.put(new R(true, 0, 4), "great-great-grandchild");

        map.put(new R(true, 1, -4), "great-great-granduncle");
        map.put(new R(true, 1, -3), "great-granduncle");
        map.put(new R(true, 1, -2), "granduncle");
        map.put(new R(true, 1, -1), "uncle");

        map.put(new R(true, 1, 0), "kin");

        map.put(new R(true, 1, 1), "nephew");
        map.put(new R(true, 1, 2), "grandnephew");
        map.put(new R(true, 1, 3), "great-grandnephew");
        map.put(new R(true, 1, 4), "great-great-grandnephew");

        //females

        map.put(new R(false, 0, -4), "great-great-grandparent");
        map.put(new R(false, 0, -3), "great-grandparent");
        map.put(new R(false, 0, -2), "grandparent");
        map.put(new R(false, 0, -1), "parent");

        map.put(new R(false, 0, 1), "child");
        map.put(new R(false, 0, 2), "grandchild");
        map.put(new R(false, 0, 3), "great-grandchild");
        map.put(new R(false, 0, 4), "great-great-grandchild");

        map.put(new R(false, 1, -4), "great-great-grandaunt");
        map.put(new R(false, 1, -3), "great-grandaunt");
        map.put(new R(false, 1, -2), "grandaunt");
        map.put(new R(false, 1, -1), "aunt");

        map.put(new R(false, 1, 0), "kin");

        map.put(new R(false, 1, 1), "niece");
        map.put(new R(false, 1, 2), "grandniece");
        map.put(new R(false, 1, 3), "great-grandniece");
        map.put(new R(false, 1, 4), "great-great-grandniece");


        //x cousin k removed

        //males
        map.put(new R(true, 2, 0), "1st cousin");
        map.put(new R(true, 2, 1), "1st cousin once removed");
        map.put(new R(true, 2, 2), "1st cousin twice removed");
        map.put(new R(true, 2, 3), "1st cousin thrice removed");
        map.put(new R(true, 2, -1), "1st cousin once removed");
        map.put(new R(true, 2, -2), "1st cousin twice removed");
        map.put(new R(true, 2, -3), "1st cousin thrice removed");

        map.put(new R(true, 3, 0), "2nd cousin");
        map.put(new R(true, 3, 1), "2nd cousin once removed");
        map.put(new R(true, 3, 2), "2nd cousin twice removed");
        map.put(new R(true, 3, 3), "2nd cousin thrice removed");
        map.put(new R(true, 3, -1), "2nd cousin once removed");
        map.put(new R(true, 3, -2), "2nd cousin twice removed");
        map.put(new R(true, 3, -3), "2nd cousin thrice removed");

        map.put(new R(true, 4, 0), "3rd cousin");
        map.put(new R(true, 4, 1), "3rd cousin once removed");
        map.put(new R(true, 4, 2), "3rd cousin twice removed");
        map.put(new R(true, 4, 3), "3rd cousin thrice removed");
        map.put(new R(true, 4, -1), "3rd cousin once removed");
        map.put(new R(true, 4, -2), "3rd cousin twice removed");
        map.put(new R(true, 4, -3), "3rd cousin thrice removed");

        //females
        map.put(new R(false, 2, 0), "1st cousin");
        map.put(new R(false, 2, 1), "1st cousin once removed");
        map.put(new R(false, 2, 2), "1st cousin twice removed");
        map.put(new R(false, 2, 3), "1st cousin thrice removed");
        map.put(new R(false, 2, -1), "1st cousin once removed");
        map.put(new R(false, 2, -2), "1st cousin twice removed");
        map.put(new R(false, 2, -3), "1st cousin thrice removed");

        map.put(new R(false, 3, 0), "2nd cousin");
        map.put(new R(false, 3, 1), "2nd cousin once removed");
        map.put(new R(false, 3, 2), "2nd cousin twice removed");
        map.put(new R(false, 3, 3), "2nd cousin thrice removed");
        map.put(new R(false, 3, -1), "2nd cousin once removed");
        map.put(new R(false, 3, -2), "2nd cousin twice removed");
        map.put(new R(false, 3, -3), "2nd cousin thrice removed");

        map.put(new R(false, 4, 0), "3rd cousin");
        map.put(new R(false, 4, 1), "3rd cousin once removed");
        map.put(new R(false, 4, 2), "3rd cousin twice removed");
        map.put(new R(false, 4, 3), "3rd cousin thrice removed");
        map.put(new R(false, 4, -1), "3rd cousin once removed");
        map.put(new R(false, 4, -2), "3rd cousin twice removed");
        map.put(new R(false, 4, -3), "3rd cousin thrice removed");


    }
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        BinaryTree bt = new BinaryTree(32767);
        //printTree(bt);

        int i1 = sc.nextInt();
        while(i1 != -1) {

            int i2 = sc.nextInt();
            boolean isMale = (sc.next().equals("M"));

            R r = new R(isMale, bt.findLen(i1, i2), bt.sep(i1, i2));
            if(map.containsKey(r)) {
                sb.append(map.get(r));
                sb.append('\n');
            }
            else {
                sb.append("kin");
                sb.append('\n');
            }

            i1 = sc.nextInt();
        }

        System.out.print(sb);

    }

    static void printTree(BinaryTree bt) {
        Queue<Node> toPrint = new ArrayDeque<>();
        Node start = bt.root;
        toPrint.add(start);
        while(!toPrint.isEmpty()) {
            Node curr = toPrint.poll();
            System.out.print(curr.idx + "\t");
            if (curr.left != null) {
                System.out.print(curr.left.idx + "\t");
                toPrint.add(curr.left);
            }
            if(curr.right != null) {
                System.out.print(curr.right.idx);
                toPrint.add(curr.right);
            }
            System.out.print("\n");
        }

    }

    public static class BinaryTree {
        Node root;
        int maxIdx;
        Node[] parentOf;
        Node[] nodes;

        public BinaryTree(int maxIdx) {
            this.maxIdx = maxIdx;
            this.parentOf = new Node[maxIdx + 1];
            this.root = new Node(0);
            nodes = new Node[maxIdx + 1];
            nodes[0] = root;
            int currIdx = 1;
            Queue<Node> q = new ArrayDeque<>();
            q.add(root);
            while(currIdx <= maxIdx) {
                Node parent = q.poll();
                parent.left = new Node(currIdx);
                nodes[currIdx] = parent.left;
                q.add(parent.left);
                parentOf[currIdx]  = parent;
                currIdx++;
                if(currIdx <= maxIdx) {
                    parent.right = new Node(currIdx);
                    nodes[currIdx] = parent.right;
                    q.add(parent.right);
                    parentOf[currIdx] = parent;
                    currIdx++;
                }
            }
        }


        public int level(int n) {

            int idx = 0;
            while((idx + 1 < levels.size())&&n > levels.get(idx)) {
                idx++;
            }
            return idx;
        }

        public int sep(int i1, int i2) {
            int level1 = level(i1);
            int level2 = level(i2);

            return level2 - level1;
        }


        //min length to common parent
        public int findLen(int a, int b) {
            Node pA = nodes[a];
            Node pB = nodes[b];
            int distA = 0;
            int distB = 0;

            while(!pA.equals(pB)&&
                    (pA != null)&&
                    (pB != null)) {
                if(pA.idx > pB.idx) {
                    pA = parentOf[pA.idx];
                    distA++;
                }
                else {
                    pB = parentOf[pB.idx];
                    distB++;
                }
            }
            if((pA == null)||(pB == null)) {
                return -1;
            }

            return Math.min(distA, distB);
        }
    }

    public static class R {
        boolean isM;
        int minLen, sep;

        public R(boolean isM, int minLen, int sep) {
            this.isM = isM;
            this.minLen = minLen;
            this.sep = sep;
        }

        @Override
        public int hashCode() {
            int start = 1000000; //1,000,000
            start -= (minLen * 10000);
            start += (sep * 10);
            if(isM) {
                start++;
            }
            return start;
        }

        @Override
        public boolean equals(Object obj) {
            return this.hashCode() == obj.hashCode();
        }
    }

    public static class Node{
        int idx;
        Node left, right;

        public Node(int idx) {
            this.idx = idx;
        }

        public Node(int idx, Node left, Node right) {
            this.idx = idx;
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object obj) {
            return this.idx == ((Node) obj).idx;
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