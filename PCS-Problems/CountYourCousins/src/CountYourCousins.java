import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alyssa on 9/3/2016.
 */
public class CountYourCousins {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        whileloop:
        while(true) {
            List<Node> nodes = new ArrayList<>();
            int n = sc.nextInt();
            int k = sc.nextInt();
            if((n == 0) &&(k == 0)) {
                System.exit(0);
            }
            Node keyNode = null;

            //links a child node to a parent node.
            HashMap<Node, Node> parents = new HashMap<>();//chlid, parent

            Node root = new Node(0, new ArrayList<>(), sc.nextInt());
            nodes.add(root);
            if(root.idx == k) {
                System.out.println(0);
                continue;
            }
            int parentIdx = -1;
            for(int i = 1; i < n; i++) {
                int currIdx = sc.nextInt();
                if((currIdx - nodes.get(nodes.size() - 1).idx) > 1) {
                    parentIdx++;
                }
                Node node = new Node(nodes.get(parentIdx).depth + 1, new ArrayList<>(), currIdx);
                parents.put(node, nodes.get(parentIdx));
                nodes.get(parentIdx).children.add(node);
                nodes.add(node);
                if(node.idx == k) {
                    keyNode = node;
                }
            }
            Node parent = null;
            Node grandParent = null;
            if(parents.containsKey(keyNode)) {
                parent = parents.get(keyNode);
                if(parents.containsKey(parent)) {
                    grandParent = parents.get(parent);
                }
                else {
                    System.out.println(0);
                    continue whileloop;
                }
            }

            else {
                System.out.println(0);
                continue whileloop;
            }

            int total = 0;
            for(Node gen2 : grandParent.children) {
                if(!gen2.equals(parent)) {
                    total += gen2.children.size();
                }
            }
            System.out.println(total);
        }
    }

    static class Node {
        int depth;
        List<Node> children;
        int idx;
        public Node(int d, List<Node> c, int i) {
            this.depth = d;
            this.children = c;
            this.idx = i;
        }

        @Override
        public int hashCode() {
            return this.idx;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof  Node) {
                Node n = (Node) obj;
                return (n.depth == depth)&&(n.children.equals(children))&&(n.idx == idx);
            }
            return false;
        }
    }
}
