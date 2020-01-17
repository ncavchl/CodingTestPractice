import java.util.*;
import java.io.*;

/**
 * BOJ 2098 ���ǿ� ��ȸ 
 * MST������ MST �ƴ�
 * DP ���� + bitmask ����
 */
public class Main {
	static int N;
	static int[][] list;
	static int[][] d;
	static final int INF = (int) (1e9 + 1); // �ִ밪 ����
	
    public static void main(String[] args) throws IOException {
    	N = 4;
    	d = new int[N][1 << N]; // ��Ʈ����Ʈ 
        
    	
    	int a = (1<<N) - 1; // N���� 1��ä���
    	int b = (1<<N); // N+1�ڸ��� 1 �������� 0
    	
    	System.out.println(a);
    	System.out.println(b);
    	
    	
    	for(int j=0; j<(1<<N); j++)
    		System.out.println(j);
    	
    	System.out.println("-" + (b|1<<0));
    	
    }
}
