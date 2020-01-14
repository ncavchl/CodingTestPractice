import java.util.*;
import java.io.*;

/**
 * BOJ 11657 Ÿ�Ӹӽ�
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
    static int dist[];
    static Edge[] e;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        dist[1] = 0; // ������ 1 ��
        // �ʱ�ȭ
        for (int i = 2; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        boolean isCycle = false;
        e = new Edge[M];//�����뼱

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            e[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i <= N; i++) { // ���� ����Ŭ ������ ���� N��°�� ����
            for (int j = 0; j < M; j++) {
                if (dist[e[j].from] != Integer.MAX_VALUE
                        && dist[e[j].to] > dist[e[j].from] + e[j].cost) {
                    dist[e[j].to] = dist[e[j].from] + e[j].cost;
                    if (i == N) {//�پ��°����� ���ư��� �����ִٸ� ����Ŭ����.
                        isCycle = true;
                    }
                }
            }
        }

        if (isCycle) {
            bw.write("-1");
        } else {
            for (int i = 2; i < dist.length; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    bw.write("-1" + "\n");
                } else {
                    bw.write(dist[i] + "\n");
                }
            }
        }
        br.close();
        bw.close();
    }
}