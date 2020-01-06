package baekjoon3055;

//import java.util.*;
//import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;
/*
20200106 백준3055 탈출
BFS
1. 큐에 시작점을 넣는다
2. 큐에서하는 빼옴
3. 목적지인가?
4. 가능항목 모두 넣으면서 체크인하기 
*/
class map1{ // 좌표용 클래스
	int x, y; 
	map1(int y, int x){
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int R, C; // 지도 사이즈
	static char[][] map;
	static Queue<map1> q1;
	static Queue<map1> q2;
	
	static int[] dx = {-1, 1, 0, 0}; // nx ny 반대라서 좌 우 하 상
	static int[] dy = {0, 0, -1, 1};
	static int cnt;
	static int min; // ?
	static String fail = "KAKTUS";
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		q1 = new LinkedList<>(); // 고슴도치
		q2 = new LinkedList<>(); // 물
		
		map = new char[R][C];
		
		String[] str;
		for(int i=0; i<R; i++) {
			str = br.readLine().split(""); //한줄받음
			for(int j=0; j<C; j++) {
				map[i][j] = str[j].charAt(0); // j번재 문자 하나
				if(str[j].charAt(0) == 'S') q1.add(new map1(i,j));
				if(str[j].charAt(0) == '*') q2.add(new map1(i,j));
			}
		}
		
		cnt = 0; // 이동거리
		
		bfs(map); // 지도가 인자
		
		System.out.println(cnt);
		
	}
	
	public static void bfs(char[][] map) {
		while(true) {
			cnt++; // 한타임 지남
			
			//물부터 퍼트리기
			int size2 = q2.size();
			for(int s = 0; s<size2; s++) {
				map1 m1 = q2.poll(); // 큐 처음꺼 뽑아서 x,y 좌표에 넣기
				
				for(int i=0; i<4; i++) {// 상하좌우 가보기
					int ny = m1.y + dy[i];
					int nx = m1.x + dx[i];
					//테두리 판단
					if(nx >=0 && ny >= 0 && nx <C && ny<R) {
						if(map[ny][nx] == '.') {
							//이동가능
							map[ny][nx] = '*';
							q2.add(new map1(ny, nx));
						}
					}
				}
			}
			
			//물부터 넣고  이동 다했는데도 return 안되면 끝내기
			if(q1.size() == 0) {
				System.out.println(fail);
				System.exit(0);
			}	
			
			int size1 = q1.size();
			for(int s = 0; s < size1; s++) {
				map1 m1 = q1.poll();
				
				for(int i=0; i<4; i++) {// 상하좌우 가보기
					int ny = m1.y + dy[i];
					int nx = m1.x + dx[i];
					//테두리 판단
					if(nx >=0 && ny >= 0 && nx <C && ny<R) {
						if(map[ny][nx] == 'D') {
							return ; // 도착
						}
						if(map[ny][nx] == '.') {
							//이동가능
							map[ny][nx] = 'S';
							q1.add(new map1(ny, nx));
						}
					}
				}
				
			}
		}
	}
}
