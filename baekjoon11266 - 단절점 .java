import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ 11266 ������
 */
public class Main {
    static class Edge{
        int to;
        public Edge(int to) {
            super();
            this.to = to;
        }
    }  

    static int count = 1;
    static int[] discovered;
    static int[] lows;  // low Ȯ���� ���� �κ�
    static boolean[] isCutVertax;
    static ArrayList<Edge>[] aLists;

    public static void main(String[] args) throws Exception {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        aLists = new ArrayList[N+1];

        discovered = new int[N+1];
        lows = new int[N+1];
        isCutVertax = new boolean[N+1];

        // ��������Ʈ �ʱ�ȭ
        for(int i = 1; i <= N; i ++) {
            aLists[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            aLists[a].add(new Edge(b));
            aLists[b].add(new Edge(a));
        }

        // Ž�� ����
        for (int i = 1; i <= N ;i ++) {
            if(discovered[i] == 0) {
                dfs(i, true);
            }
        }
        int cnt = 0;
        for (int i = 1; i <= N ; i++) {
            if(isCutVertax[i]) {
                cnt++;
            }
        }
        System.out.println(cnt);
        for (int i = 1; i <= N ; i++) {
            if(isCutVertax[i]) {
                System.out.print(i +" ");
            }
        }
        // discovered, low Ȯ���� ���� �κ�
        /*
        System.out.println("");
        System.out.println("dis / low");
        for (int i = 1; i <= N ; i++) {
            System.out.printf("%d / %d\n", discovered[i], lows[i]);
        }
        */
    }

    // A�� �ڽ� ��尡 A�� ��ġ�� �ʰ� ������ �� �ִ� ���� �� ���� ���� dfs�Լ��� �湮�� ������ ��ȯ
    private static int dfs(int node, boolean isRoot) {
        /* �ڱ⺸�� �տ� Ž���Ҽ� �ִ� ��찡 ������ �������� ���� �ʴ´�. */
        /* DFS���д�Ʈ���� ����鼭 ���� Ʈ���� �״�� ���� �������� ���� �ƴ�*/
        /* DFS���д� Ʈ���� ������ ������ ������ �ִ� �Ͱ� 
         * DFS���д� Ʈ������ ��Ʈ�� �ڽ��� 2�� �������� üũ */

        discovered[node] = count++;
        int ret = discovered[node];

        int child = 0; // ��Ʈ ����� ��� ���д�Ʈ������ �ڽļ�

        Edge edge;
        for (int i=0; i<aLists[node].size(); i++) {
            edge = aLists[node].get(i);

            if (discovered[edge.to] == 0) {
                child++;

                // �ڽ� ��尡 ���� �ִ� ��� �� ���� ���� �湮�� ����� �湮����
                int low = dfs(edge.to, false);
                lows[edge.to] = low;

                // low�� �ڱ� �湮 �������� �ʰų� ���� ���, �ڱ⺸�� �տ� �ִ� ��δ� �ڱ⸦ ���ؼ� �ۿ� ���� -> ������
                if(!isRoot && low >= discovered[node]) {
                    isCutVertax[node] = true;
                }

                ret = Math.min(ret, low);
            } else {
                // �̹� �湮�� ������ ret�� �� �ּҰ� ����
                ret = Math.min(ret, discovered[edge.to]);
            }
        }

        // ��Ʈ�� ���, ���д� Ʈ������ �ڽ��� �ΰ� �ִ� ��� ��������
        if (isRoot && child >= 2) {
            isCutVertax[node] = true;
        }
        return ret;
    }
}  