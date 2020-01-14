import java.util.*;
import java.io.*;
import java.util.StringTokenizer;
//import java.util.HashSet;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//import java.util.*;

/**
 * BOJ 3830 교수님은 기다리지 않는다 union find
 */

public class Main {
	static int N, M; //

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] map;
		int MAX = 1000000;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0)
				break;
			map = new int[N + 1][N + 1];
			// Arrays.fill(map, 1000000); //MAX 로 채워두기
			for (int i = 1; i < N + 1; i++)
				for (int j = 1; j < N + 1; j++)
					map[i][j] = MAX;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				String menu = st.nextToken();
				if (menu.equals("!")) {
					// st = new StringTokenizer(br.readLine());
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					int w = Integer.parseInt(st.nextToken());
					map[a][b] = w; // b가 a보다 w만큼 무겁다.
					map[b][a] = -w;
				} else {
					// st = new StringTokenizer(br.readLine());
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());

					// map 갱신
					for (int k = 1; k < N + 1; k++) {

						if (map[a][k] != MAX && map[k][b] != MAX) {
							map[a][b] = map[a][k] + map[k][b];

						}
					}

					if (map[a][b] == MAX)
						System.out.println("UNKNOWN");
					else {
						System.out.println(map[a][b]);
					}
				}
			}

		}

	}
}
