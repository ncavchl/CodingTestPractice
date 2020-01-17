import java.util.*;
import java.io.*;

/**
 * BOJ 2342 Dance Dance Revolution
 * �������� ���
 */
public class Main {
	static int[] list = new int[100001]; // �ִ� 100000�� �ʸ���
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
        
        
        d = new int[n+1][5][5]; // (1)�� ���� ���� / (2)(3)�޹߿����� ���� ���� 
        
        int MAX = 10000000;
        //�ִ밪���� �ʱ�ȭ
        for(int i=0; i<=n; i++)
        	for(int j=0; j<=4; j++)
        		for(int k=0; k<=4; k++)
        			d[i][j][k] = MAX;
        
        //�������� �̵� 2 / �������� �̵� 3 / �ݴ�� �̵� 4 / �����߷� �ݺ� 1
        
        //ù��° �̵�
        // �޹� �Ǵ� ������ 
        d[1][0][list[1]] = 2;
        d[1][list[1]][0] = 2;
        d[0][0][0] = 0;
        
        
        
        for(int i=2; i<=n; i++) {
        	//������ ���� 2����
        	for ( int j=0; j<=4; j++ ) {
                for ( int k=0; k<=4; k++ ) {
                	//��� ���� ��ġ�� �� ����.
                	if(j==k) continue;
                	//������ ���� ���ؼ��� �����ϱ� 
                	if(j != list[i] && k != list[i]) continue;
                	
                	//�� �޹� ������ 0~4 ��ġ���� i��°�� �̵��ϴ� �� �����ϱ� 
                	for(int z=0; z<=4; z++) { //���� z��ġ���� i��°������� �̵��� ��� �� ���� ���� ��
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
    	//��������� dp �� �ٸ� �� 5���� ����� �� ����ϱ� 
    	for(int i=0; i<=4; i++) {
    		//������ ��ġ�� ���ʹ� �� �� ��ġ ���� 
    		if(i == list[n]) continue;
    		ans = Math.min(ans, d[n][list[n]][i]);
    		ans = Math.min(ans, d[n][i][list[n]]);
    	}
    	
    	if(ans >= MAX) System.out.println(0);
    	else System.out.println(ans);
        
    }
    //�̵� �ɸ��µ� ��� �� ��� �Լ�
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
