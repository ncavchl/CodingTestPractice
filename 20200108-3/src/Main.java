import java.util.*;
import java.io.*;

/**
 * BOJ 2098 외판원 순회 
 * MST같지만 MST 아님
 * DP 문제 + bitmask 연산
 */
public class Main {
	static int N;
	static int[][] list;
	static int[][] d;
	static final int INF = (int) (1e9 + 1); // 최대값 세팅
	
    public static void main(String[] args) throws IOException {
    	N = 4;
    	d = new int[N][1 << N]; // 비트마스트 
        
    	
    	int a = (1<<N) - 1; // N까지 1로채우기
    	int b = (1<<N); // N+1자리만 1 나머지는 0
    	
    	System.out.println(a);
    	System.out.println(b);
    	
    	
    	for(int j=0; j<(1<<N); j++)
    		System.out.println(j);
    	
    	System.out.println("-" + (b|1<<0));
    	
    }
}
