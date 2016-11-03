import java.util.Scanner;

/**
 * Created by Alyssa on 4/19/2016.
 */
public class AccountMaximumQueries {
    static int N;
    static int M;
    static SegmentTree root;

    public static void main(String[ ]args) {
        readInput();
    }

    public static void readInput() {
        Scanner reedr = new Scanner(System.in);
        N = reedr.nextInt();
        M = reedr.nextInt();
        root = new SegmentTree(0, N-1);
        StringBuilder bob = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String command = reedr.next();
            if (command.equals("ADD")) {
                root.update(reedr.nextInt(), reedr.nextInt());
            }
            else if (command.equals("MAX")) {
                bob.append(root.rangeQuery(reedr.nextInt(), reedr.nextInt()));
                bob.append("\n");
            }
            else {
                System.out.println("You didn't enter a correct command");
            }
        }
        System.out.print(bob);
    }


    public static class SegmentTree {
        public static final int IDENTITY = Integer.MIN_VALUE;
        SegmentTree left, right;
        int val, del;
        Interval range;


        public SegmentTree(int l, int r) {
            this(new Interval(l, r));
        }

        //        public void propagate() {
        //            if (this.range.length() > 1) {
        //                this.left.del += this.del;
        //                this.right.del += this.del;
        //                this.del = 0;
        //                this.val = combine(this.left.getVal(), this.right.getVal());
        //            }
        //        }

        public int getVal() {
            return this.val + this.del*this.range.length();
        }

        public SegmentTree(Interval i) {
            range = i;
            del = 0;
            if (range.length() > 1) {
                left = new SegmentTree(range.leftInterval());
                right = new SegmentTree(range.rightInterval());
            }
        }

        public int combine(int x, int y) {
            return Math.max(x, y);
        }

        public int rangeQuery(int l, int r) {
            Interval range = new Interval(l, r);
            return rangeQuery(range);
        }

        public int rangeQuery(Interval i) {
            //propagate();
            if (!this.range.intersects(i)) {
                return IDENTITY;
            }
            else if (this.range.contained(i)) {
                return this.val;
            }
            else {
                return combine(this.left.rangeQuery(i), this.right.rangeQuery(i));
            }
        }

        //        public void increment(int delta, Interval i) {
        //            //propagate();
        //            if(!this.range.intersects(i)) {
        //                return;
        //            }
        //            else if (this.range.contained(i)) {
        //                this.del += delta;
        //            }
        //            else {
        //                this.left.increment(delta, i);
        //                this.right.increment(delta, i);
        //                combine(this.left.getVal(), this.right.getVal());
        //            }
        //        }

        public void update(int i, int val) {
            if (range.length() == 1) {
                this.val += val;
            }
            else {
                if (this.range.leftInterval().containsPoint(i)) {
                    this.left.update(i, val);

                }
                else {
                    this.right.update(i, val);
                }
                this.val = combine(this.combine(left.val, right.val), this.val);

            }
        }


        private static class Interval {
            final int left, right;

            public Interval(int l, int r) {
                left = l;
                right = r;
            }

            public boolean containsPoint(int i) {
                return (i <= right && i >= left);
            }

            public int length() {
                return right - left + 1;
            }

            public int midPoint() {
                return (left + right) / 2;
            }

            public Interval leftInterval() {
                return new Interval(left, midPoint());
            }

            public Interval rightInterval() {
                return new Interval(midPoint() + 1, right);
            }

            public boolean contained(Interval i) {
                return i.left <= this.left && this.right <= i.right;
            }

            public boolean intersects(Interval i) {
                return !(i.right < this.left || this.right < i.left);
            }
        }
    }}


