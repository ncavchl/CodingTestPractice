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
���� 10610 30

 
 */

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// int n = sc.nextInt();
		String str = sc.nextLine();
		int[] numcnt;
		long total = 0;
		
		numcnt = new int[10]; // �� ���� �ڸ��� ���� ����
		int len = str.length();
		for(int i=0; i<len; i++) {
			int num = Integer.parseInt(str.substring(i, i+1)); // �Ѱ��� �ڸ��� 
			numcnt[num] += 1;
			total += num;
			//System.out.println(num + " = " + numcnt[num] + " - ");
		}
			
		
		//���ڿ� 0 �־�ߵǰ�
		//�� �ڸ��� ���� 3�� ���
		if(total % 3 != 0 || !str.contains("0")) {
			System.out.println(-1);
			return ;
		}
		//�� 1~9 ���� ����  ��Ʈ���� 9���� ���̱� 
		
		//StringBuilder s = new StringBuilder();
		String ans = "";
		for(int i=9; i>=0; i--) {
			while(numcnt[i] > 0) {
				//s.append(i);
				ans = ans + Integer.toString(i);
				numcnt[i]-- ;
				//System.out.println(numcnt[i]);
			}
		}
		
		System.out.println(ans);
		
	}

}