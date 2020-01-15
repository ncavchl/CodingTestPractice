import java.util.*;
import java.io.*;

//import java.util.*;


/**
 * BOJ 1516 게임개발
 * 위상정렬
 */
public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(in.readLine());
        ArrayList vertex[] = new ArrayList[n + 1];
        int dist[] = new int[n + 1];// 거리 저장
        int degree[] = new int[n + 1]; // 차수? 저장 
        int ans[] = new int[n + 1];
        Queue q = new LinkedList<>();

        for (int i = 1; i <= n; i++)
            vertex[i] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int d = Integer.parseInt(st.nextToken());
            dist[i] = ans[i] = d; // 걸리는 시간 

            int v;
            while ((v = Integer.parseInt(st.nextToken())) != -1) {
                vertex[v].add(i); // 미리 지어야 하는 건물 
              //  System.out.println(v + " " + i);
                degree[i]++; // 미리 지어야하는 건물 개수?
            }
        }

        for (int i = 1; i <= n; i++)
            if (degree[i] == 0) q.add(i); // 선행건물 없는거부터 지음 

        while (!q.isEmpty()) {
            int here = (int)q.poll();
          //  System.out.println("hrer   " + here);
            for (int i=0; i<vertex[here].size(); i++) {
            	int k = (int) vertex[here].get(i);
            //	System.out.println("k "+ k + " here " + here + " " + ans[here]);
                ans[k] = Math.max(ans[k], ans[here] + dist[k]);
                if (--degree[k] == 0)
                    q.add(k);
            }

        }

        for (int i = 1; i <= n; i++) {
            out.write(ans[i] + "\n");
        }
        out.close();
    }
}