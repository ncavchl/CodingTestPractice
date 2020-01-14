import java.util.*;
import java.io.*;

/**
 * BOJ 11404 �÷��̵�
 * ����-����
 */
public class Main {
    static class Edge {
        int from, to, cost;

        public Edge(int a, int b, int c) {
            super();
            from = a;
            to = b;
            cost = c;
        }

    }

    static int N, M;
    static int[][] dist; // �Ÿ� ��� 
    static Edge[] e; // �뼱�� ���� 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N+1][N+1];
        
        e = new Edge[M];
                
        //���� ���
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // �Ÿ��� �ּҰ����� �Է� 
            if(dist[from][to] == 0) {
            	dist[from][to] = cost;
            } else {
            	dist[from][to] = Math.min(cost, dist[from][to]);
            }
        }
        
        for (int mid = 1; mid <= N; mid++){ 
            for (int start = 1; start <= N; start++){ 
                if (dist[start][mid] > 0) {
                    for(int end = 1; end <= N; end++){ 
                        if(dist[mid][end] == 0 || start == end)
                            continue;
                        // �÷��̵� ����
                        if(dist[start][end] == 0 || dist[start][end] > dist[start][mid] + dist[mid][end]) {
                            dist[start][end] = dist[start][mid] + dist[mid][end];
                        }
                    }
                }
            }
        }

        for(int x = 1; x <= N; x++){ 
            for(int y = 1; y <= N; y++) {
                System.out.printf("%d ", dist[x][y]);
            }

            System.out.println();
        }
        br.close();
        bw.close();
        
    }
}