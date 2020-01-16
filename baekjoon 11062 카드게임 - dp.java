import java.util.*;
import java.io.*;

/**
 * BOJ 11062 ī�� ���� 
 * �κ��� - �������̹Ƿ� ���� sum ���ϴ� ������� 
 */
public class Main {
	static int[][] d;
	static int[] num;
	static int[] sum;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
       
        
        while(n>0) {
	        StringTokenizer st = new StringTokenizer(br.readLine()); 
	        int m = Integer.parseInt(st.nextToken());
	        num = new int[m+1];
	        st = new StringTokenizer(br.readLine()); 
	        sum  = new int[m+1];
	        for ( int i=1; i<=m; i++ ) {
	        	
	        	num[i] = Integer.parseInt(st.nextToken());
	        	sum[i] = num[i] + sum[i-1];
	        }
	        //System.out.println("sum "+ sum);
	        
	        //�Լ�
	        int ans=0;
        
	        //�ٿ찡 ��ԵǴ� �ְ�����dp
	        d = new int[m+1][m+1];
	        //1. �迭 
	        //2. �ʱⰪ - i==j num[i]
	        //3. ��ȭ��    --   ����or ������ ���� ��ü �������� ��밡 ������������ �ְ����� 
	        //D(1,4) -> max(12-D(2,4), 12-D(1,3))
	        
	        //D(i,j) -> max( sum - D(i+1, j) , sum - D(i, j-1))
	        
	        ans = find(1, m);
	        
	        
        
        	System.out.println(ans);
        	
        	n--;
        }
        
    }
    //���� �� �̿��ϱ� 
    static int find(int s, int e) {
    	//�̹� �ѹ� ����� ��
    	if(d[s][e] != 0) return d[s][e];
    	if(s==e) {
    		//d[s][e] = num[s];
    		return num[s];
    	}else {
    		int summ = sum[e] - sum[s-1];
    		d[s][e] = Math.max(summ - find(s+1, e), summ - find(s, e-1)); // ���� ������ ������ ����� �� ū��
    	}
    	return d[s][e];
    }
}
