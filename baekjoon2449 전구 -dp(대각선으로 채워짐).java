import java.util.*;
import java.io.*;

/**
 * BOJ 2449 ����
 * �������� ���� �ϴµ� ��� Ƚ�� 
 * D[i][N]
 Dij = Di~k + Dk+1~j + Ai�� Ak+1�� ������ ? 0 : 1
 D11 = 0 // Dii 0
 
 */
public class Main {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n, k;
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        int[] num = new int[n+1];
        int[][] d = new int[n+1][n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<n+1; i++) {
        	num[i] = Integer.parseInt(st.nextToken());
        }
        //���ʿ� 0���� ���õ�
        int max = Integer.MAX_VALUE;
        //��ü �Ⱦ����.
        //��������
        for(int l=1; l<=n; l++) {
        	for(int i=1; i<=n-l; i++) {
        		int j = i+l; // ����
        		d[i][j] = max;
        		for(int p=i; p<j; p++) {
        			int c = d[i][p] + d[p+1][j] + (num[i] == num[p+1]? 0:1); // �����ſ��� �� ù ���� ���� Ʋ������ ���� ���� ��� �߻�
        			d[i][j] = Math.min(c,  d[i][j]);
        		}
        	}
        }
        
        
        
        System.out.println(d[1][n]);
        
    }
}
