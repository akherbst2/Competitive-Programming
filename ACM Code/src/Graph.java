import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Alyssa on 4/5/2016.
 */
public class Graph {





    public class Node<E> implements Comparable<Node<E>>
    {
        ArrayList<E> nbrs;
        E val;
        //TODO Insert an Identity for E
        public Node(E val) {
            this.val = val;
        }
        public Node(E val, ArrayList<E> nbrs) {
            this.val = val;
            this.nbrs = nbrs;
        }

        public int compareTo(Node<E> other) {
            return val.compareTo(other.val);
        }



    }
}
