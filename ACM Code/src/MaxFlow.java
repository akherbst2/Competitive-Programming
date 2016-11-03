import java.util.ArrayList;

/**
 * We want a weighted graph with edges
 * Created by Alyssa on 4/28/2016.
 */
public class MaxFlow {




    static class Node<E> {
        //How do we distinguish each node?
        E val;
        ArrayList<Edge> edges;

        public Node(E val) {
            this.val = val;
            edges = new ArrayList<Edge>();
        }
        @Override
        public int hashCode() {
            return val.hashCode();
        }



    }


    static class Edge {
        double cost;
        Node node;
        public Edge(double cost, Node node) {
            this.cost = cost;
            this.node = node;
        }

    }



}
