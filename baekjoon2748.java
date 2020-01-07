import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

/*
백준 2748 피보나치 수 2
 */

public class Main {
	public static void main(String[] args) {
		// 피보나치
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		long[] arr = new long[n + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = -1;
		}
		System.out.println(fi(n, arr));
		// for(int a : arr) {
		// System.out.print(a+" ");
		// }
		scan.close();
	}

	static long fi(int num, long[] arr) {
		if (arr[num] != -1) {
			return arr[num];
		}
		if (num == 0 || num == 1) {
			arr[num] = num;
			return num;
		} else {
			long result = fi(num - 1, arr) + fi(num - 2, arr);
			arr[num] = result;
			return result;
		}
	}
}