import java.util.*;
import java.io.*;

/**
 * BOJ 1753 최단경로
 * 다익스트라
 */
public class Main {
    static int N,M,K;

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(br.readLine());

        ArrayList<Edge> node[] = new ArrayList[N+1];
        for(int i=0;i<=N;i++) {
            node[i]=new ArrayList<Edge>();
        }

        int from,to,cost;
        for(int i=0;i<M;i++) {
            stk = new StringTokenizer(br.readLine());
            from = Integer.parseInt(stk.nextToken());
            to = Integer.parseInt(stk.nextToken());
            cost = Integer.parseInt(stk.nextToken());
            node[from].add(new Edge(to,cost));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(10, new Comparator<Edge>() {
            public int compare(Edge a, Edge b) {
                return a.cost-b.cost;
            }
        });

        int crr[] = new int[N+1];
        Arrays.fill(crr, Integer.MAX_VALUE);
        crr[K]=0;

        pq.add(new Edge(K,0));
        while(!pq.isEmpty()) {  
            int next=pq.peek().to;
            int dist=pq.peek().cost;
            pq.poll();

            if(crr[next]<dist) {                    // 이미 더 좋은경로를 찾았으면 건너뜀
                continue;
            }

            for(int i=0;i<node[next].size();i++ ) {
                int next2=node[next].get(i).to;
                int dist2=node[next].get(i).cost;

                if(crr[next2] > dist+dist2) {
                    crr[next2] = dist+dist2;
                    pq.add(new Edge(next2,dist+dist2));
                }
            }

        }

        for(int i=1;i<crr.length;i++) {
            if(K == i) {
                System.out.println("0");
            } else if (crr[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(crr[i]);
            }
        }
    }

    static class Edge {
        int to;
        int cost;
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}