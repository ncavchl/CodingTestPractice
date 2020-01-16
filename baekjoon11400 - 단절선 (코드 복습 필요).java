import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * BOJ 11400 ������
 */
public class Main {
    static class Edge{
        int fr, to;
        public Edge(int to) {
            super();
            this.to = to;
        }
        public Edge(int fr, int to) {
            super();
            this.fr = fr;
            this.to = to;
        }
    }  

    static int count = 1;
    static int[] discovered;
    static boolean[] isCutVertax;
    static ArrayList<Edge>[] aLists;
    static ArrayList<Edge> res;

    public static void main(String[] args) throws Exception {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        res = new ArrayList();
        aLists = new ArrayList[N+1];

        discovered = new int[N+1];
        isCutVertax = new boolean[N+1];

        // ��������Ʈ �ʱ�ȭ
        for(int i=1; i<=N; i++) {
            aLists[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            aLists[a].add(new Edge(b));
            aLists[b].add(new Edge(a));
        }

        // Ž�� ����
        for (int i=1; i<=N; i++) {
            if(discovered[i] == 0) {
                dfs(i, 0);
            }
        }

        Comparator<Edge> nodeComparator = new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o2.fr < o1.fr) {
                    return 1;
                } else if (o2.fr == o1.fr){
                    return o1.to - o2.to;
                } else {
                    return -1;
                }
            }
        };
        Collections.sort(res, nodeComparator);
        System.out.println(res.size());
        for (int i=0; i < res.size(); i++) {
            bw.write(res.get(i).fr + " " + res.get(i).to +"\n");
        }
        bw.flush();
    }

    // A�� A�� �ڽ� ��尡 A���� parent���� ���� ������ ������� �ʰ� ������ �� �ִ� ���� �� ���� ���� dfs�Լ��� �湮�� ������ ��ȯ
    private static int dfs(int node, int parent) {
        discovered[node] = count++;
        int ret = discovered[node];
        // �ڱ�� ������� �߿��� ���� ���� �湮�Ǵ� ����� ������ �����ϴ� ����

        Edge edge;
        for (int i=0; i<aLists[node].size(); i++) {
            edge = aLists[node].get(i);
            if (edge.to == parent) continue;    // �ڽ��� �θ�� üũ���� ����

            if (discovered[edge.to] == 0) {
                // �ڽ� ��尡 ���� �ִ� ��� �� ���� ���� �湮�� ����� �湮����
                int low = dfs(edge.to, node);

                // low�� �ڱ� �湮 �������� �ʴ� ���, �ڱ⺸�� �տ� �ִ� ��δ� �ڱ⸦ ���ؼ� �ۿ� ���� -> ������
                if(low > discovered[node]) {
                    res.add(new Edge(Math.min(node, edge.to), Math.max(node, edge.to)));
                }

                ret = Math.min(ret, low);
            } else {
                // �̹� �湮�� ������ ret�� �� �ּҰ� ����
                ret = Math.min(ret, discovered[edge.to]);
            }
        }        
        return ret;
    }
} 