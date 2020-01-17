import java.util.*;
import java.io.*;

/**
 * BOJ 7578 공장
 * 교차 횟수 
 * 세그먼트 트리 
 */
public class Main {
    static int [] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));           
        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N+1];
        HashMap map = new HashMap();

        tree = new int[N*4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        st =  new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            //b.add(new Integer(Integer.parseInt(st.nextToken())));
            map.put (st.nextToken(), String.valueOf(i) );
        }

        long ans =0;
        int idx =0;

        for(int i = 1; i <= N; i++){
            idx = Integer.parseInt((String) map.get(a[i]+"") );
            ans += idx - i + find(1, idx, N, 1, N);
            update(1, 1, N, idx, 1);
        }       
        System.out.println(ans+"");
    }

    private static int find(int idx, int l, int r, int s, int e) {
        if (r < s || e < l) return 0;
        if (l <= s && e <= r) return tree[idx];
        return find(idx*2, l, r, s, (s+e)/2) + find(idx*2+1, l, r, (s+e)/2+1, e);
    }

    private static void update(int node, int s, int e, int index, int diff) {
        if (s > index || index > e) return;
        tree[node] += diff;
        if (s != e) {
            update(node*2, s, (s+e)/2, index, diff);
            update(node*2+1, (s+e)/2+1, e, index, diff);
        }
    }
}