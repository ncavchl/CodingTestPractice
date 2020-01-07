import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

/*
백준 1806 부분합
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N; // 사람수 3~500000
		int S;

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			// System.out.println(arr[i]);
		}

		int left = 0;
		int right = 0;
		int size = N;
		int sum = arr[left];
		int min = N + 1;
		int length = right - left + 1;
		while (left <= right && right < size) {
			if (sum >= S) {
				length = right - left + 1;
				if (min > length)
					min = length;
				if (left + 1 < size)
					sum -= arr[left];
				left++;
				continue;
			}
			if (sum < S) {
				if (right + 1 < size)
					sum += arr[right + 1];
				right++;
				continue;
			}
		}
		if (min == N + 1)
			min = 0;
		System.out.println(min);

	}
}