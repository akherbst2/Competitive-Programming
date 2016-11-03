import jdk.nashorn.internal.ir.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alyssa on 10/3/2016.
 */
public class WordClouds {
    static int[] w, h;
    static int c;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        c = sc.nextInt();
        w = new int[n];
        h = new int[n];

        for(int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
            h[i] = sc.nextInt();
        }
        State s = recur(0);
        System.out.print(s.totalHeight);

    }
    static class State {
        List<Block> cloud;
        int colWidth;
        int totalHeight;

        public State(List<Block> cloud, int colWidth, int totalHeight) {
            this.cloud = cloud;
            this.colWidth = colWidth;
            this.totalHeight = totalHeight;
        }
    }


    static State recur(int idx) {
        if(idx >= n) {
            List<Block> list = new ArrayList<>();
            list.add(new Block(0, 0));
            return new State(list, 0, 0);
        }
        else {
            State nextBest = recur(idx + 1);
            if((nextBest.colWidth + w[idx])< c) {
                Block lastCloud = nextBest.cloud.get(nextBest.cloud.size() - 1);
                lastCloud.w = nextBest.colWidth + w[idx];
                int prevColH = lastCloud.h;
                lastCloud.h = Math.max(lastCloud.h, h[idx]);
                return new State(nextBest.cloud, lastCloud.w, nextBest.totalHeight - prevColH + lastCloud.h);
            }
            else {
                nextBest.cloud.add(new Block(w[idx], h[idx]));
                nextBest.colWidth = w[idx];
                nextBest.totalHeight = nextBest.totalHeight + h[idx];
                return nextBest;
            }
        }
    }

    static class Block {
        int w, h;

        public Block(int w, int h) {
            this.w = w;
            this.h = h;
        }
    }
}
