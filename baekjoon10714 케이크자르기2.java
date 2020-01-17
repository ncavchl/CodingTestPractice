import java.util.*;
import java.io.*;

/**
 * BOJ 10714 케이크 자르기 2
 *
 */
public class Main {
    static int N;
    static long[] a;
    static long[][] d;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        a = new long[N+1];
        d = new long[N+1][N+1];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                d[i][j] = -1;
            }
        }

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        long ans = 0;
        for (int i=0; i<N; i++) {
            ans = Math.max(ans, (long)a[i]+ioi(minus(i),plus(i),0));
        }
        System.out.println(ans);
    }

    static long ioi(int l, int r, int depth) {
        if (depth >= N) return 0;
        if (a[l] < a[r]) {
            return joi(l, plus(r), depth+1);
        } else { 
            return joi(minus(l), r, depth+1);
        }
    }

    static long joi(int l, int r, int depth) {
        if (depth >= N-1) return 0;
        long ret = d[l][r];
        if (ret != -1) return ret;
        long t1 = (long)a[l]+ioi(minus(l), r, depth+1);
        long t2 = (long)a[r]+ioi(l, plus(r), depth+1);
        return d[l][r] = Math.max(t1, t2);
    }

    static int plus(int x) {
        return (x+1)%N;
    }

    static int minus(int x) {
        return (x+N-1)%N;
    }
}
