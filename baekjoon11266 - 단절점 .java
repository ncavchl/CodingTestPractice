import java.util.*;
import java.io.*;

//import java.util.*;



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

    static int count = 1; // 확인을 위해 
    static int[] discovered; // 탐색하는 순서 
    static int[] lows;  // low 확인을 위한 부분
    static boolean[] isCutVertax; // 단절점 여부
    static ArrayList<Edge>[] aLists; // 정점마다 연결정보 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        aLists = new ArrayList[N+1];

        discovered = new int[N+1];
        lows = new int[N+1];
        isCutVertax = new boolean[N+1];
        
        for(int i=1; i<N+1; i++)
        	aLists[i] = new ArrayList<>();
        
        for(int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            aLists[a].add(new Edge(b));
            aLists[b].add(new Edge(a));
        }

        
        //dfs 탐색
        for(int i=1; i<=N; i++) {
        	if(discovered[i] == 0) {
        		dfs(i, true); // 노드, root여부
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
    
    private static int dfs(int node, boolean isRoot){
    	 /* 자기보다 앞에 탐색할수 있는 경우가 있으면 단절점이 되지 않는다. */
        /* DFS스패닝트리를 만들면서 기존 트리는 그대로 사용됨 없어지는 것이 아님*/
        /* DFS스패닝 트리의 역할은 순서를 지정해 주는 것과 
         * DFS스패닝 트리에서 루트가 자식을 2개 가지는지 체크 */
    	
    	discovered[node] = count++;
    	int ret = discovered[node]; // 순서저장
    	
    	int child = 0;//루트 노드일 경우 스패닝 트리에서 자식 수 
    	
    	Edge edge ;
    	for(int i=0; i<aLists[node].size(); i++) {
    		edge = aLists[node].get(i);
    		
    		if(discovered[edge.to] == 0) {
    			//아직 dfs안들린 곳이면
    			child++; // 자식 수 늘림
    			//자식노드 들 자신 순서 저장? // 자식도느가 갈 수 있는 노드 중 가장 일찍 방문한 노드의 방문순번
                // 자식 노드가 갈수 있는 노드 중 가장 일찍 방문한 노드의 방문순번
                int low = dfs(edge.to, false);
                lows[edge.to] = low;

                // low가 자기 방문 순서보다 늦거나 같은 경우, 자기보다 앞에 있는 경로는 자기를 통해서 밖에 못감 -> 단절점
                if(!isRoot && low >= discovered[node]) {
                    isCutVertax[node] = true;
                }

                ret = Math.min(ret, low);
            } else {
                //ex)5번노드인 경우(처음 1과 연결된 노드/ 1은 시작점  / 연결된 노드인데 1의 방문순서가 더빠르면 
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