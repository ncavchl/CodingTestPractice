import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * BOJ 11400 단절선
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

        // 인접리스트 초기화
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

        // 탐색 순번
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

    private static int dfs(int node, int parent) {
        /* 자기보다 앞에 탐색할수 있는 경우가 있으면 단절점이 되지 않는다. */
        /* DFS스패닝트리를 만들면서 기존 트리는 그대로 사용됨 없어지는 것이 아님*/
        /* DFS스패닝 트리의 역할은 순서를 지정해 주는 것과 
         * DFS스패닝 트리에서 루트가 자식을 2개 가지는지 체크 */

        discovered[node] = count++;
        int ret = discovered[node];
        // 자기랑 인접노드 중에서 가장 빨리 방문되는 노드의 순서를 저장하는 변수

        Edge edge;
        for (int i=0; i<aLists[node].size(); i++) {
            edge = aLists[node].get(i);
            if (edge.to == parent) continue;    // 자신의 부모는 체크하지 않음

            if (discovered[edge.to] == 0) {
                // 자식 노드가 갈수 있는 노드 중 가장 일찍 방문한 노드의 방문순번
                int low = dfs(edge.to, node);

                // low가 자기 방문 순서보다 늦는 경우, 자기보다 앞에 있는 경로는 자기를 통해서 밖에 못감 -> 단절선
                if(low > discovered[node]) {
                	//System.out.println("low" + low + " " + node + "!! " + edge.to  + " ? " + discovered[node]);
                    res.add(new Edge(Math.min(node, edge.to), Math.max(node, edge.to)));
                }

                ret = Math.min(ret, low);
            } else {
                // 이미 방문한 정점과 ret값 비교 최소값 저장
                ret = Math.min(ret, discovered[edge.to]);
            }
        }        
        return ret;
    }
} 