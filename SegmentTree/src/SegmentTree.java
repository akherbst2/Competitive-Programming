/**
 * Created by Alyssa on 4/19/2016.
 */
public class SegmentTree {
    public static final int IDENTITY = Integer.MAX_VALUE;
    SegmentTree left, right;
    int val;
    Interval range;

    public SegmentTree(int l, int r) {
        this(new Interval(l, r));
    }

    public SegmentTree(Interval i) {
        range = i;
        if (range.length() > 1) {
            left = new SegmentTree(range.leftInterval());
            right = new SegmentTree(range.rightInterval());
        }
    }

    public static int combine(int x, int y) {
        return Math.min(x, y);
    }

    public int rangeQuery(int l, int r) {
        Interval range = new Interval(l, r);
        return rangeQuery(range);
    }

    public int rangeQuery(Interval i) {
        if (!this.range.intersects(i)) {
            return IDENTITY;
        } else if (this.range.contained(i)) {
            return this.val;
        } else {
            return combine(this.left.rangeQuery(i), this.right.rangeQuery(i));
        }
    }

    public int update(int i, int val) {
        if (range.length() == 1) {
            this.val = val;
        } else {
            if (this.range.leftInterval().containsPoint()) {
                this.left.update(i, val);
            } else {
                //@TODO SOMETHING
            }
        }
    }

    private static class Interval {
        final int left, right;

        public Interval(int l, int r) {
            left = l;
            right = r;
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

        public boolean intersect(Interval i) {
            return !(i.right < this.left || this.right < i.left);
        }
    }
}

