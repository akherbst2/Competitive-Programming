import java.util.*;

/**
 * Created by Alyssa on 7/3/2016.
 */
public class BirthdayParty {

    static boolean[] safeLinks;
    static int numSafeLinks;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            int p = sc.nextInt();
            int c = sc.nextInt();
            if (p == 0 && c == 0) {
                break;
            }
            safeLinks = new boolean[c];
            numSafeLinks = 0;
            List<Node> nodes = new ArrayList<>();
            List<Link> links = new ArrayList<>();
            for (int i = 0; i < p; i++) {
                nodes.add(new Node(i));
            }
            for(int i = 0; i < c; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                Link link = new Link(i, nodes.get(a), nodes.get(b));
                nodes.get(a).links.add(link);
                nodes.get(b).links.add(link);
                links.add(link);
            }
            System.out.println(inviteEveryone(nodes, links)? "No":"Yes");
        }
    }

    static boolean allSafeLinks(int size) {
        return numSafeLinks == size;
    }

    public static class Link
    {
        int idx;
        Node n1, n2;
        List<Node> chain;

        public Link(int i, Node n1, Node n2) {
            this.idx = i;
            this.n1 = n1;
            this.n2 = n2;
            chain = new ArrayList<>();
        }

        public Node getNext() {
            Node lastNode = chain.get(chain.size() - 1);
            return lastNode == n1? n2:n1;
        }

        //returns the starting index of the loop
        public int containsLoop() {
            HashMap<Node, Integer> seen = new HashMap<>();
            for (int k = 0; k < chain.size(); k++) {
                if (seen.containsKey(chain.get(k))) {
                    return seen.get(chain.get(k));
                }
                seen.put(chain.get(k), k);
            }
            return -1;
        }

        // Starts at starting idx
        public void markLinksSafe(int idx) {

            //TODO check last one too
            for (int i = idx; i < chain.size() - 1; i++) {
                Link nextLink = chain.get(i).getLinkTo(chain.get(i + 1));
                safeLinks[nextLink.idx] = true;
                numSafeLinks++;
            }
        }
        @Override
        public boolean equals(Object other) {
            Link otherLink = (Link) other;
            return this.idx == otherLink.idx;
        }
    }

    public static class Node {
        int idx;
        List<Link> links;  //links leading away
        public Node(int idx) {
            this.idx = idx;
            links = new ArrayList<>();
        }

        public Link getLinkTo(Node n) {
            for(Link link: links) {
                Node other = link.n1 == this? link.n2:link.n1;
                if (other == n) {
                    return link;
                }
            }
            return null;
        }
    }

    public static boolean inviteEveryone(List<Node> nodes, List<Link> linkList) {
        Stack<Link> stack = new Stack<>();
        List<Link> firstLinks = nodes.get(0).links;
        for (Link link:firstLinks) {
            link.chain.add(nodes.get(0));
            stack.add(link);
        }
        int numLinks = linkList.size();
        while(!stack.isEmpty() && (numSafeLinks != numLinks)) {
            Link currLink = stack.pop();
            Node nextNode = currLink.getNext();
            for(Link link:nextNode.links) {
                if (!link.equals(currLink)) {
                    List<Node> nextChain = new ArrayList<>(currLink.chain);
                    nextChain.add(nextNode);
                    link.chain = nextChain;
                    int loopIdx = link.containsLoop();
                    if (loopIdx != -1) {
                        link.markLinksSafe(loopIdx);
                    }
                    else {
                        stack.add(link);
                    }
                }
            }
        }
        return allSafeLinks(numLinks);
    }
}
