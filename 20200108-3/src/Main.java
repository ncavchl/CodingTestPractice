import java.util.*;
import java.io.*;
import java.util.StringTokenizer;
//import java.util.HashSet;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//import java.util.*;

/*
���� 3176 ���γ�Ʈ��ũ

 */

public class Main {
	static final int NMAX = 100001;
    static final int KMAX = 17; // 2^17 = 131072
    static int N, M;
    static ArrayList<int[]> adj[] = new ArrayList[NMAX];
    static int depth[] = new int[NMAX];
    static int par[][] = new int[KMAX + 1][NMAX];
    static int DMIN[][] = new int[KMAX + 1][NMAX];
    static int DMAX[][] = new int[KMAX + 1][NMAX];

    static int min(int a, int b) {
        return (a < b) ? a : b;
    }

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
            depth[i] = -1;
            for (int k = 0; k <= KMAX; k++) {
                DMIN[k][i] = 1000001;
                DMAX[k][i] = 0;
                par[k][i] = 0;
            }
        }
        for (int i = 0, f, t, l; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            f = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            adj[f].add(new int[] { t, l });
            adj[t].add(new int[] { f, l });
        }

        Queue<Integer> que = new LinkedList<Integer>();
        que.add(1);
        depth[1] = 0;
        par[0][1] = 1;
        while (!que.isEmpty()) {
            int node = que.poll();

            int sz = adj[node].size();
            for (int i = 0; i < sz; i++) {
                int cur[] = adj[node].get(i);
                int nnode = cur[0];
                int len = cur[1];
                if (depth[nnode] == -1) // ���� �湮���� ���� ����̸�
                {
                    depth[nnode] = depth[node] + 1; // depth ���� ����
                    par[0][nnode] = node; // �Ѵܰ� �� �θ� ���� ����
                    DMAX[0][nnode] = len; // ���� �� ���� ���� ����
                    DMIN[0][nnode] = len; // ���� ª�� ���� ���� ����
                    que.add(nnode);
                }
            }
        }

        for (int k = 1; k <= KMAX; k++) {
            for (int n = 1; n <= N; n++) {
                par[k][n] = par[k - 1][par[k - 1][n]];

                DMAX[k][n] = max(DMAX[k - 1][n], DMAX[k - 1][par[k - 1][n]]);
                DMIN[k][n] = min(DMIN[k - 1][n], DMIN[k - 1][par[k - 1][n]]);
            }

        }
        // M���� ������ ���� LCA�� ã���� ���Ѵ�.
        M = Integer.parseInt(br.readLine());
        for (int i = 0, a, b; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            int minv = 1000001, maxv = -1;

            // �� ������ depth�� ���� �ٸ� ��� depth�� ���� �ش�.
            // ���߸鼭 ������ ������ ���� �� ���ο� ���� ª�� ���� ����
            if (depth[a] > depth[b]) {
                for (int k = KMAX; k >= 0; k--) {
                    while (a != b && depth[par[k][a]] >= depth[b]) {
                        minv = min(minv, DMIN[k][a]);
                        maxv = max(maxv, DMAX[k][a]);
                        a = par[k][a];
                    }
                }
            } else if (depth[a] < depth[b]) {
                for (int k = KMAX; k >= 0; k--) {
                    while (a != b && depth[par[k][b]] >= depth[a]) {
                        minv = min(minv, DMIN[k][b]);
                        maxv = max(maxv, DMAX[k][b]);
                        b = par[k][b];
                    }
                }
            }

            for (int k = KMAX; k >= 0 && a != b; k--) {
                while (a != b && par[k][a] != par[k][b]) {

                    minv = min(minv, min(DMIN[k][a], DMIN[k][b]));
                    maxv = max(maxv, max(DMAX[k][a], DMAX[k][b]));
                    a = par[k][a];
                    b = par[k][b];
                }
            }

            if (a != b) { // �� ���ð� �������� �ʴٸ� LCA�� �� ������ �θ� ����
                minv = min(minv, min(DMIN[0][a], DMIN[0][b]));
                maxv = max(maxv, max(DMAX[0][a], DMAX[0][b]));
            }
            bw.write(minv + " " + maxv + "\n");
        }
        bw.flush();
    }
}