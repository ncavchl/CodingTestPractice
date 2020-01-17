import java.util.*;
import java.io.*;

/**
 * BOJ 2666 벽장문의 이동
 * 경찰차랑 비슷
 */
public class Main {
	 public static int S;
	 public static int D1, D2;
	 public static int N;
	 public static int[] ns;
	 public static int[][] d; // DP 배열
	
    public static void main(String[] args) throws IOException {
    	// TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();
        D1 = sc.nextInt();
        D2 = sc.nextInt();
        N = sc.nextInt();
        d = new int[N+2][N+2];
        ns = new int[N+2];
        ns[0] = D1;
        ns[1] = D2;
        for (int n=2; n<N+2; n++) {
            ns[n] = sc.nextInt();
        }
        //사건처리
        System.out.println(mem(0,1));   
    }
    
    public static int mem(int f, int s) {
    	int ret;

    	if(f == N+1 || s == N+1) return 0; // 끝까지 벽장이동했으므로
    	
    	ret = d[f][s];
    	if(ret != 0) return ret;
    	
    	int next = Math.max(f, s) + 1; // 사용할 벽장 순서대로 이동 / 2에서 이동할건지 5에서 이동할건지
    	//최소 이동으로 저장하기
    	int p1 = mem(next, s) + Math.abs(ns[next] - ns[f]);
    	int p2 = mem(f, next) + Math.abs(ns[next] - ns[s]);
    	ret = Math.min(p1,  p2);
    	
    	return d[f][s] = ret;
    	
    	
    }
}
