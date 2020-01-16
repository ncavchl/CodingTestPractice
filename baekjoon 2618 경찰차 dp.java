import java.util.*;
import java.io.*;

/**
 * BOJ 2618 경찰차 dp
 */
public class Main {
	public static final int INF = Integer.MAX_VALUE;
	public static int N, W;
	public static Point[] es; // 사건 및 경찰차 장소 저장
	public static int[][] d; // dp배열
	public static ArrayList ps; // 사건담당한 경찰차 저장

	public static void main(String[] args) throws IOException {

		// D(f,s) f 는 1번차가 맡은 사건 / s는 2번차가 맡은 사건
		// ex) 3개 사건 D(*,4) D(4, *) 이면 사건은 끝난 것임
		// 0 - 1번경찰차 초기 위치 / 1 - 2번경찰차 초기 위치 // 2부터 사건 개수 위치 포지션
		// D(0,1) 처음 -> min(1번차가 사건1가거나 , 2번차가 사건1가거나)
		// (1,1) -> (1사건위치)/이동거리 + D(2,1) or (6,6) -> (1사건위치) + D(0,2)

		// 다음사건 번호는 D(i, j) 라면 Max(i,j) + 1 번임.
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		W = sc.nextInt();
		es = new Point[W + 2];
		d = new int[W + 2][W + 2];
		ps = new ArrayList();

		es[0] = new Point(1, 1, 1); // 경찰차1
		es[1] = new Point(N, N, 2); // 경찰차2

		for (int i = 2; i < W + 2; i++) {
			es[i] = new Point(sc.nextInt(), sc.nextInt()); // 사건 등록
		}

		int ans = mem(0, 1); // 두 경찰차가 이동한 총 거리
		System.out.println(ans);
		path(0, 1); // 사건맡은순서 출력
		
/*		for(int i=0; i<W+2; i++) {
			for(int j=0; j<W+2;  j++) {
				System.out.print(d[i][j] + " ");
			}
			System.out.println("");
			
		}
*/
	}

	// 점화식
	public static int mem(int f, int s) {
	//	System.out.println("mem " + f + " " + s);
		int ret;
		// 초기값
		if (f == W + 1 || s == W + 1)
			return 0; // 사건개수에 도달하면 사건은 끝난 것임.
		ret = d[f][s]; // 기본이 0
		if (ret != 0)
			return ret; // 이미 값 설정 되있으면 효율을 위해 바로 return

		// 점화식
		int next = Math.max(f, s) + 1; // 다음으로 갈 사건 번호
		int p1 = mem(next, s) + dist(es[f], es[next]); // 경찰차 1이 갈 경우
		int p2 = mem(f, next) + dist(es[next], es[s]);// 경찰차 2가 갈 경우
		ret = Math.min(p1, p2);
		return d[f][s] = ret;
	}

	// 누가 사건맡았는지
	public static void path(int f, int s) { // 1번차 2번차 각각 무슨 사건 맡았나
		if (f == W + 1 || s == W + 1) {
			for (int i = 0; i < ps.size(); i++) {
				System.out.println(ps.get(i));
			}
			return;
		}
		int next = Math.max(f, s) + 1;
		int p1 = d[next][s] + dist(es[f], es[next]);
		int p2 = d[f][next] + dist(es[s], es[next]);
		if (p1 < p2) {
			ps.add(1);
			path(next, s);
		} else {
			ps.add(2);
			path(f, next);
		}
		return;
	}

	public static int dist(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

}

class Point {
	public int x;
	public int y;
	public int pc;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, int pc) {
		this.x = x;
		this.y = y;
		this.pc = pc;
	}
}
