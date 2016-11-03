/**
 * Created by Alyssa on 4/1/2016.
 */
public class BinaryTree<E> {
    
    public class Node<E> implements Comparable<Node<E>>{
        E val;
        Node l, r;

        public Node(E val) {
            this.val = val;
        }

        public Node(E val, Node l, Node r) {
            this.val = val;
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Node<E> node) {
            if (node == null) {
                throw new NullPointerException();
            }
            else {
                return val.compareTo(node.val);
            }
        }

        public boolean equals(Node<E> node) {

        }
    }
    }
}
