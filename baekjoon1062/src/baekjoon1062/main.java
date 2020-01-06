package baekjoon1062;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class main {
	public static char charSet[]; //�ĺ��� �迭
	public static boolean visit[]; //��Ʈ��ŷ �湮 ���� üũ
	public static int n, k, wordSetSize, max = 0, candidateSize;
	public static String wordSet[]; //�ܾ������
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		String line[] = in.readLine().split(" "), word;
		int i, j, w = 0, wordSize, preCount = 0;
		n = Integer.parseInt(line[0]); 
		k = Integer.parseInt(line[1]);
		wordSet = new String[n]; //���� Ž���� ���� �ܾ������
		//�ĺ��� �ؽü�(�ߺ����� ����)
		HashSet<Character> candidate = new HashSet<>();
		
		for(i=0;i<n;i++){
			word = in.readLine();
			/** 1�� ���ؾ� ���ڵ�(a,n,t,i,c)�� ��� ���� **/
			word = word.replaceAll("[antic]", "");
			wordSize = word.length();
			/** 1-1�� ���� �� ���� �ܾ��� ���̰� 0�̸� preCount+1 **/
			if(wordSize==0) preCount++;
			else{
				/** 1-2�� ���̰� 0���� ũ�ٸ� ���� Ž���� ���� �ܾ�����ҿ� ���� **/
				wordSet[w++] = word;
				/** 1-3�� ���� �ܾ���� Ž�� �ĺ����� ���� **/
				for(j=0;j<wordSize;j++) candidate.add(word.charAt(j));
			}
		}
		/** 2�� ���� k < 5��� 0�� ����ϰ� ���� **/
		if(k<5) preCount = max = 0;
		else{
			k-=5; //3�� k-=5. ������ ���ؾ� ���ڸ�ŭ ����.
			wordSetSize = w;
			candidateSize = candidate.size();
			charSet = new char[candidateSize];
			visit = new boolean[candidateSize];
			i = 0;
			/** 4�� �ĺ��� �ܾ���� �ε��� ������ ���� �ű� **/
			for(char ch : candidate) charSet[i++] = ch;
			/** 5�� �ĺ����� ����(candidateSize) < ������ ����(k) **/
			if(candidateSize<k) k = candidateSize;
			/** 6�� �ĺ����� �ִ� �ܾ���� �ϳ��� �����ذ��� ��͸� �̿��� ��Ʈ��ŷ **/
			dfs(new HashSet<>(), 0, 0);
		}
		out.write(String.valueOf(preCount+max));//8�� �ִ밪+preCount ���
		out.close();
		in.close();
	}
	
	private static void dfs(HashSet<Character> picked, int idx, int pick){
		int i, j, count = 0, wordSize;
		/** 7��
        "���±��� ������ ������ ���� == k"�� �����ϸ�
        �ܾ�����ҿ� ����� �ܾ��� �񱳸� �ؼ�
        ��� �� �ִ� �ܾ��� �ִ밪 ���� **/
		if(pick==k){
			boolean impossible;
			for(i=0;i<wordSetSize;i++){
				impossible = false;
				wordSize = wordSet[i].length();
				for(j=0;j<wordSize;j++)
					if(!picked.contains(wordSet[i].charAt(j))){
						impossible = true;
						break;
					}
				if(!impossible) count++;
			}
			if(max < count) max = count;
			return;
		}
		for(i=idx;i<candidateSize;i++)
			if(!visit[i]){
				visit[i] = true;
                picked.add(charSet[i]);			
               
                dfs(picked, i, pick+1);
				
                visit[i] = false;
				picked.remove(charSet[i]);
			}
	}
}

/*
������ ��Ÿ�� ����
import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
20200106 ����1062 ����ħ
DFS
1.�湮
2.üũ��
3. �����ߴ°�
4.���� �̵� ��� �湮
5.üũ�ƿ�
*/
/*
public class main{
	public static char charSet[]; //������� �ܾ� �迭
	public static boolean visit[]; //�湮 ���� üũ 
	public static int n, k, wordSetSize, max = 0, candidateSize; // �Է¹޴� �ܾ� ����, ��� ���� ����
	public static String wordSet[]; // �ܾ� ����
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		//System.out.println(n + "f" + k);
		
		String word;
		int i, j, w = 0, wordSize, preCount = 0; // �Է¹޴� �ܾ� ����, antic�θ� �̷���� �ܾ�
		wordSet = new String[n];
		//�ߺ����� �ؽ�!!
		HashSet<Character> candidate = new HashSet<> ();
		
		for(i=0; i<n; i++) {
			//�ܾ�鿡 ���ؼ� antic ����ó��, �ܾ�����ҿ� �ؽÿ� �Է�
			word = br.readLine();
			word = word.replaceAll("[antic]", "");
			wordSize = word.length();
			if(wordSize == 0) preCount++; // antic �θ� �̷�����ܾ�
			else {
				//1. �ܾ� ����ҿ� ����
				wordSet[w++] = word;
				for(j=0; j<wordSize; j++) {
					candidate.add(word.charAt(j));
				}
			}
		}
		
		//���� ���� �ܾ� ������ 5�� �̸��̸� �ܾ� �ϳ��� �����Ƿ� ����
		if(k<5) preCount = max = 0;
		else {
			k = k - 5;
			wordSetSize = w;
			candidateSize = candidate.size();
			charSet = new char[candidateSize]; // ���ܾ� dfs�� ���� char �迭
			visit = new boolean[candidateSize]; // ���ܾ� �ε��� �湮 üũ�� ���� �迭
			i = 0;
			for(char ch : candidate) charSet[i++] = ch;
			if(candidateSize < k) k = candidateSize; // ���ܾ� ���� ������� ���� ������ ���� �� �����ϰ� ����
			
			dfs(new HashSet<>(), 0, 0); // ���ο� �ؽ������� �������
		}
		
		System.out.println(preCount+max);
		br.close();
		
	}
	
	private static void dfs(HashSet<Character> picked, int idx, int pick) {
		int i, j, count=0, wordSize;
		if(pick == k) {
			boolean impossible; // �� �ܾ�� ����� ����
			for(i=0; i<wordSetSize; i++) {
				impossible = false; // �����ϴ�ħ
				wordSize = wordSet[i].length();
				for(j=0; j<wordSize; j++) {
					if(!picked.contains(wordSet[i].charAt(j))) {
						//�ȵ��������
						impossible = true;
						break; //���°� �߰ߵǸ� �ٷ� Ž�� ����
					}
				}
				if(!impossible) count++; // ���������� ����
			}
			if(max < count) max = count;
			return ;
		}
		for(i = idx; i<candidateSize; i++) {
			if(!visit[i]) { // ���� �湮 ����
				visit[i] = true;
				picked.add(charSet[i]); // ������� �ܾ�� ���� ����ٰ� ħ
				
				dfs(picked, i, pick+1); // i������ �尬�� (�������Ⱥ�)
				
				visit[i] = false;
				picked.remove(charSet[i]);
			}
		}
		
		
	}
}
*/
