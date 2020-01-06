package baekjoon3055;

//import java.util.*;
//import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;
/*
20200106 ����3055 Ż��
BFS
1. ť�� �������� �ִ´�
2. ť�����ϴ� ����
3. �������ΰ�?
4. �����׸� ��� �����鼭 üũ���ϱ� 
*/
class map1{ // ��ǥ�� Ŭ����
	int x, y; 
	map1(int y, int x){
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int R, C; // ���� ������
	static char[][] map;
	static Queue<map1> q1;
	static Queue<map1> q2;
	
	static int[] dx = {-1, 1, 0, 0}; // nx ny �ݴ�� �� �� �� ��
	static int[] dy = {0, 0, -1, 1};
	static int cnt;
	static int min; // ?
	static String fail = "KAKTUS";
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		q1 = new LinkedList<>(); // ����ġ
		q2 = new LinkedList<>(); // ��
		
		map = new char[R][C];
		
		String[] str;
		for(int i=0; i<R; i++) {
			str = br.readLine().split(""); //���ٹ���
			for(int j=0; j<C; j++) {
				map[i][j] = str[j].charAt(0); // j���� ���� �ϳ�
				if(str[j].charAt(0) == 'S') q1.add(new map1(i,j));
				if(str[j].charAt(0) == '*') q2.add(new map1(i,j));
			}
		}
		
		cnt = 0; // �̵��Ÿ�
		
		bfs(map); // ������ ����
		
		System.out.println(cnt);
		
	}
	
	public static void bfs(char[][] map) {
		while(true) {
			cnt++; // ��Ÿ�� ����
			
			//������ ��Ʈ����
			int size2 = q2.size();
			for(int s = 0; s<size2; s++) {
				map1 m1 = q2.poll(); // ť ó���� �̾Ƽ� x,y ��ǥ�� �ֱ�
				
				for(int i=0; i<4; i++) {// �����¿� ������
					int ny = m1.y + dy[i];
					int nx = m1.x + dx[i];
					//�׵θ� �Ǵ�
					if(nx >=0 && ny >= 0 && nx <C && ny<R) {
						if(map[ny][nx] == '.') {
							//�̵�����
							map[ny][nx] = '*';
							q2.add(new map1(ny, nx));
						}
					}
				}
			}
			
			//������ �ְ�  �̵� ���ߴµ��� return �ȵǸ� ������
			if(q1.size() == 0) {
				System.out.println(fail);
				System.exit(0);
			}	
			
			int size1 = q1.size();
			for(int s = 0; s < size1; s++) {
				map1 m1 = q1.poll();
				
				for(int i=0; i<4; i++) {// �����¿� ������
					int ny = m1.y + dy[i];
					int nx = m1.x + dx[i];
					//�׵θ� �Ǵ�
					if(nx >=0 && ny >= 0 && nx <C && ny<R) {
						if(map[ny][nx] == 'D') {
							return ; // ����
						}
						if(map[ny][nx] == '.') {
							//�̵�����
							map[ny][nx] = 'S';
							q1.add(new map1(ny, nx));
						}
					}
				}
				
			}
		}
	}
}
