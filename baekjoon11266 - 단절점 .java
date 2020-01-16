import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ 11266 단절점
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
    static int[] lows;  // low 확인을 위한 부분
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

        // 인접리스트 초기화
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

        // 탐색 순번
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
        // discovered, low 확인을 위한 부분
        /*
        System.out.println("");
        System.out.println("dis / low");
        for (int i = 1; i <= N ; i++) {
            System.out.printf("%d / %d\n", discovered[i], lows[i]);
        }
        */
    }

    // A의 자식 노드가 A를 거치지 않고 도달할 수 있는 정점 중 가장 먼저 dfs함수가 방문한 정점을 반환
    private static int dfs(int node, boolean isRoot) {
        /* 자기보다 앞에 탐색할수 있는 경우가 있으면 단절점이 되지 않는다. */
        /* DFS스패닝트리를 만들면서 기존 트리는 그대로 사용됨 없어지는 것이 아님*/
        /* DFS스패닝 트리의 역할은 순서를 지정해 주는 것과 
         * DFS스패닝 트리에서 루트가 자식을 2개 가지는지 체크 */

        discovered[node] = count++;
        int ret = discovered[node];

        int child = 0; // 루트 노드일 경우 스패닝트리에서 자식수

        Edge edge;
        for (int i=0; i<aLists[node].size(); i++) {
            edge = aLists[node].get(i);

            if (discovered[edge.to] == 0) {
                child++;

                // 자식 노드가 갈수 있는 노드 중 가장 일찍 방문한 노드의 방문순번
                int low = dfs(edge.to, false);
                lows[edge.to] = low;

                // low가 자기 방문 순서보다 늦거나 같은 경우, 자기보다 앞에 있는 경로는 자기를 통해서 밖에 못감 -> 단절점
                if(!isRoot && low >= discovered[node]) {
                    isCutVertax[node] = true;
                }

                ret = Math.min(ret, low);
            } else {
                // 이미 방문한 정점과 ret값 비교 최소값 저장
                ret = Math.min(ret, discovered[edge.to]);
            }
        }

        // 루트인 경우, 스패닝 트리에서 자식이 두개 있는 경우 단절점임
        if (isRoot && child >= 2) {
            isCutVertax[node] = true;
        }
        return ret;
    }
}  