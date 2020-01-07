import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

/*백준2805 나무자르기 - 바이너리서치*/

public class Main {
	public static long max = 0;

	public static void main(String args[]) throws Exception{
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		
		long target = sc.nextInt(); // 나무 가져갈거
		
		int maxHeight = 0;
		int[] trees = new int[count];
		for (int i=0; i<count; i++) {
			trees[i] = sc.nextInt();
			if(trees[i] > maxHeight) maxHeight = trees[i];
		}
		binarySearch(0, maxHeight, trees, target);
		System.out.println(max);
		
	}
	//start end
	public static void binarySearch(int s, int e, int[] arr, long target) {
		if(s>e) return;
		int mid = (s+e) / 2;
		long result = cutTree(arr, mid);
		if(result >= target) {
			if(max < mid) max = mid;
			binarySearch(mid + 1, e, arr, target);
		}
		else {
			binarySearch(s, mid-1, arr, target);
		}
	}
	
	public static long cutTree(int[] trees, int height) {
		long result = 0;
		for(int i = 0; i<trees.length; i++) {
			if(height < trees[i]) result +=(trees[i] - height);
		}
		return result;
	}
}