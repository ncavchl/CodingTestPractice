import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int KMAX = 17;
    static final int MAX = 1000001;
    static ArrayList<Integer> adj[] = new ArrayList[MAX];
    static Queue<Integer> queue = new LinkedList<Integer>();
    static int parent[][] = new int[KMAX + 1][MAX];
    static int depth[] = new int[MAX];
    static int N, M = 0;
    static int a, b = 0;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < MAX; i++) {
            depth[i] = -1;
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        depth[1] = 0;
        parent[0][1] = 0;
        queue.offer(1);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int node : adj[current]) {
                if (depth[node] == -1) {
                    depth[node] = depth[current] + 1;
                    parent[0][node] = current;
                    queue.offer(node);
                }
            }
        }

        for (int k = 1; k <= KMAX; k++) {
            for (int n = 1; n <= N; n++) {
                parent[k][n] = parent[k - 1][parent[k - 1][n]];
            }
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            // System.out.println("#"+depth[a]+" "+depth[b]);

            if (depth[a] > depth[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            for (int k = KMAX; k >= 0; k--) {
                // System.out.println(depth[a]+" "+depth[parent[k][b]]);
                if (depth[a] <= depth[parent[k][b]]) {
                    b = parent[k][b];
                }
            }

            for (int k = KMAX; k >= 0 && a != b; k--) {
                if (parent[k][a] != parent[k][b]) {
                    a = parent[k][a];
                    b = parent[k][b];
                }
            }
            int lca = a;
            if(a!=b) lca = parent[0][lca];
            bw.write(lca+"\n");         

            // bw.write(lca(a, b)+"\n");
        }
        br.close(); 
        bw.flush(); 
        bw.close();
    }

    static int lca(int a, int b) {
        // depth 가 a가 더 낮으면 더 깊은것으로 swap
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        } // 높이 차이 계산
        int diff = depth[a] - depth[b];
        // ex) diff = 11 // 11 = 2^3 + 2^1 + 2^0
        int k = 0;
        while (diff >= 1) {
            if (diff % 2 == 1) {
                a = parent[k][a];
            }
            diff /= 2;
            k++;
        }
        // 위로 올라가 b와 동일한 값이 나오면 a는 LCA 임
        if (a == b) {
            return a;
        }
        // 남은 부분은 남은 값으로 점프
        for (k = KMAX - 1; k > -1; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }
        return parent[0][a];
    }

} // eof Class