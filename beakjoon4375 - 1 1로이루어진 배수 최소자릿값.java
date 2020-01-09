import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//import java.util.HashSet;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.lang.*;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.math.BigInteger;
//import java.util.*;

/*
백준 4375 1

 
 */

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// int n = sc.nextInt();

		while (sc.hasNextInt()) {

			int a = sc.nextInt();
			//System.out.println("-" + a);

			int index = 2;
			int on = 11;

			if (a== 1 || a == 1111 || a == 111 || a == 11) {
				if (a == 1) index = 1;
				if (a == 1111)
					index = 4;
				if (a == 111)
					index = 3;
				if (a == 11)
					index = 2;
				System.out.println(index);
			}

			else {

				// 자리수체크
				if (a > 1000) {
					on = 11111;
					index = 5;
				} else if (a > 100) {
					on = 1111;
					index = 4;
				} else if (a > 10) {
					on = 111;
					index = 3;
				}

				//System.out.println(index);

				int mods = 1; // 나머지 찾기
				while (true) {
					
					if (on % a == 0 && on >= a)
						
						break; // 나머지가 0 이면
					else {
						mods = on % a;
						if (mods > a) {
							on = mods;

						} else { // 나머지가 on보다 작으면 키우기
							on = mods * 10 + 1;
							index++;
						}
					}
					//System.out.println("나머지" + on);
				}

				int ans = index;
				System.out.println(ans);
			}
		}

	}

}