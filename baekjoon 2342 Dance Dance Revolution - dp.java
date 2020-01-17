import java.util.*;
import java.io.*;

/**
 * BOJ 2342 Dance Dance Revolution
 * 경찰차랑 비슷
 */
public class Main {
	static int[] list = new int[100001]; // 최대 100000개 십만개
	static int[][][] d;
    public static void main(String[] args) throws IOException {
    	
        Scanner sc = new Scanner(System.in);
        
        int num;
        int ii=1;
        int n=0;
        while(true) {
        	num = sc.nextInt();
        	if(num==0) break;
        	list[ii] = num;
        	ii++;
        	n++;
        }
        
        
        d = new int[n+1][5][5]; // (1)일 행한 순번 / (2)(3)왼발오른발 방향 저장 
        
        int MAX = 10000000;
        //최대값으로 초기화
        for(int i=0; i<=n; i++)
        	for(int j=0; j<=4; j++)
        		for(int k=0; k<=4; k++)
        			d[i][j][k] = MAX;
        
        //원점에서 이동 2 / 근접으로 이동 3 / 반대로 이동 4 / 같은발로 반복 1
        
        //첫번째 이동
        // 왼발 또는 오른발 
        d[1][0][list[1]] = 2;
        d[1][list[1]][0] = 2;
        d[0][0][0] = 0;
        
        
        
        for(int i=2; i<=n; i++) {
        	//누르는 순서 2부터
        	for ( int j=0; j<=4; j++ ) {
                for ( int k=0; k<=4; k++ ) {
                	//양발 같은 위치에 못 있음.
                	if(j==k) continue;
                	//누르는 값에 대해서만 저장하기 
                	if(j != list[i] && k != list[i]) continue;
                	
                	//각 왼발 오른발 0~4 위치에서 i번째로 이동하는 값 저장하기 
                	for(int z=0; z<=4; z++) { //전에 z위치에서 i번째사건으로 이동한 경우 중 가장 작은 값
                		if(j==list[i])
                			d[i][j][k] = Math.min(d[i][j][k], d[i-1][z][k] + calc(z, j));
                		if(k==list[i])
                			d[i][j][k] = Math.min(d[i][j][k], d[i-1][j][z] + calc(z, k));
                	}
                	//System.out.println(d[i][j][k]);
                }    
            }
        	
        	
        }
        
        int ans = Integer.MAX_VALUE;
    	//마지막사건 dp 중 다른 발 5가지 경우의 수 계산하기 
    	for(int i=0; i<=4; i++) {
    		//마지막 위치에 양쪽발 둘 다 위치 못함 
    		if(i == list[n]) continue;
    		ans = Math.min(ans, d[n][list[n]][i]);
    		ans = Math.min(ans, d[n][i][list[n]]);
    	}
    	
    	if(ans >= MAX) System.out.println(0);
    	else System.out.println(ans);
        
    }
    //이동 걸리는데 드는 힘 계산 함수
    static int calc(int a, int b) {
        if ( a == b ) return 1;
        if ( a == 0 || b == 0 ) return 2;
        if ( (a == 1 && b == 4) || (a == 4 && b == 1) ) {
            return 3;
        } else {
            return Math.abs(a - b) + 2;
        }
    }
}
