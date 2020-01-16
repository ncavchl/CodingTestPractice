import java.util.*;
import java.io.*;

/**
 * BOJ 2618 ������ dp
 */
public class Main {
	public static final int INF = Integer.MAX_VALUE;
	public static int N, W;
	public static Point[] es; // ��� �� ������ ��� ����
	public static int[][] d; // dp�迭
	public static ArrayList ps; // ��Ǵ���� ������ ����

	public static void main(String[] args) throws IOException {

		// D(f,s) f �� 1������ ���� ��� / s�� 2������ ���� ���
		// ex) 3�� ��� D(*,4) D(4, *) �̸� ����� ���� ����
		// 0 - 1�������� �ʱ� ��ġ / 1 - 2�������� �ʱ� ��ġ // 2���� ��� ���� ��ġ ������
		// D(0,1) ó�� -> min(1������ ���1���ų� , 2������ ���1���ų�)
		// (1,1) -> (1�����ġ)/�̵��Ÿ� + D(2,1) or (6,6) -> (1�����ġ) + D(0,2)

		// ������� ��ȣ�� D(i, j) ��� Max(i,j) + 1 ����.
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		W = sc.nextInt();
		es = new Point[W + 2];
		d = new int[W + 2][W + 2];
		ps = new ArrayList();

		es[0] = new Point(1, 1, 1); // ������1
		es[1] = new Point(N, N, 2); // ������2

		for (int i = 2; i < W + 2; i++) {
			es[i] = new Point(sc.nextInt(), sc.nextInt()); // ��� ���
		}

		int ans = mem(0, 1); // �� �������� �̵��� �� �Ÿ�
		System.out.println(ans);
		path(0, 1); // ��Ǹ������� ���
		
/*		for(int i=0; i<W+2; i++) {
			for(int j=0; j<W+2;  j++) {
				System.out.print(d[i][j] + " ");
			}
			System.out.println("");
			
		}
*/
	}

	// ��ȭ��
	public static int mem(int f, int s) {
	//	System.out.println("mem " + f + " " + s);
		int ret;
		// �ʱⰪ
		if (f == W + 1 || s == W + 1)
			return 0; // ��ǰ����� �����ϸ� ����� ���� ����.
		ret = d[f][s]; // �⺻�� 0
		if (ret != 0)
			return ret; // �̹� �� ���� �������� ȿ���� ���� �ٷ� return

		// ��ȭ��
		int next = Math.max(f, s) + 1; // �������� �� ��� ��ȣ
		int p1 = mem(next, s) + dist(es[f], es[next]); // ������ 1�� �� ���
		int p2 = mem(f, next) + dist(es[next], es[s]);// ������ 2�� �� ���
		ret = Math.min(p1, p2);
		return d[f][s] = ret;
	}

	// ���� ��ǸþҴ���
	public static void path(int f, int s) { // 1���� 2���� ���� ���� ��� �þҳ�
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
