package baekjoon1062;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class main {
	public static char charSet[]; //후보군 배열
	public static boolean visit[]; //백트래킹 방문 여부 체크
	public static int n, k, wordSetSize, max = 0, candidateSize;
	public static String wordSet[]; //단어저장소
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		String line[] = in.readLine().split(" "), word;
		int i, j, w = 0, wordSize, preCount = 0;
		n = Integer.parseInt(line[0]); 
		k = Integer.parseInt(line[1]);
		wordSet = new String[n]; //추후 탐색을 위한 단어저장소
		//후보군 해시셋(중복방지 보장)
		HashSet<Character> candidate = new HashSet<>();
		
		for(i=0;i<n;i++){
			word = in.readLine();
			/** 1번 남극어 글자들(a,n,t,i,c)을 모두 제거 **/
			word = word.replaceAll("[antic]", "");
			wordSize = word.length();
			/** 1-1번 제거 후 남은 단어의 길이가 0이면 preCount+1 **/
			if(wordSize==0) preCount++;
			else{
				/** 1-2번 길이가 0보다 크다면 추후 탐색을 위한 단어저장소에 저장 **/
				wordSet[w++] = word;
				/** 1-3번 남은 단어들을 탐색 후보군에 저장 **/
				for(j=0;j<wordSize;j++) candidate.add(word.charAt(j));
			}
		}
		/** 2번 만약 k < 5라면 0을 출력하고 종료 **/
		if(k<5) preCount = max = 0;
		else{
			k-=5; //3번 k-=5. 제거한 남극어 글자만큼 가감.
			wordSetSize = w;
			candidateSize = candidate.size();
			charSet = new char[candidateSize];
			visit = new boolean[candidateSize];
			i = 0;
			/** 4번 후보군 단어들의 인덱스 접근을 위해 옮김 **/
			for(char ch : candidate) charSet[i++] = ch;
			/** 5번 후보군의 개수(candidateSize) < 글자의 개수(k) **/
			if(candidateSize<k) k = candidateSize;
			/** 6번 후보군에 있는 단어들을 하나씩 선택해가며 재귀를 이용한 백트래킹 **/
			dfs(new HashSet<>(), 0, 0);
		}
		out.write(String.valueOf(preCount+max));//8번 최대값+preCount 출력
		out.close();
		in.close();
	}
	
	private static void dfs(HashSet<Character> picked, int idx, int pick){
		int i, j, count = 0, wordSize;
		/** 7번
        "여태까지 선택한 글자의 개수 == k"를 만족하면
        단어저장소에 저장된 단어들과 비교를 해서
        배울 수 있는 단어의 최대값 구함 **/
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
왜인지 런타임 에러
import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
20200106 백준1062 가르침
DFS
1.방문
2.체크인
3. 도착했는가
4.다음 이동 장소 방문
5.체크아웃
*/
/*
public class main{
	public static char charSet[]; //배워야할 단어 배열
	public static boolean visit[]; //방문 여브 체크 
	public static int n, k, wordSetSize, max = 0, candidateSize; // 입력받는 단어 개수, 배울 문자 개수
	public static String wordSet[]; // 단어 저장
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		//System.out.println(n + "f" + k);
		
		String word;
		int i, j, w = 0, wordSize, preCount = 0; // 입력받는 단어 길이, antic로만 이루어진 단어
		wordSet = new String[n];
		//중복방지 해시!!
		HashSet<Character> candidate = new HashSet<> ();
		
		for(i=0; i<n; i++) {
			//단어들에 대해서 antic 제외처리, 단어저장소와 해시에 입력
			word = br.readLine();
			word = word.replaceAll("[antic]", "");
			wordSize = word.length();
			if(wordSize == 0) preCount++; // antic 로만 이루어진단어
			else {
				//1. 단어 저장소에 저장
				wordSet[w++] = word;
				for(j=0; j<wordSize; j++) {
					candidate.add(word.charAt(j));
				}
			}
		}
		
		//새로 배우는 단어 개수가 5개 미만이면 단어 하나도 못배우므로 종료
		if(k<5) preCount = max = 0;
		else {
			k = k - 5;
			wordSetSize = w;
			candidateSize = candidate.size();
			charSet = new char[candidateSize]; // 배울단어 dfs를 위한 char 배열
			visit = new boolean[candidateSize]; // 배울단어 인덱스 방문 체크를 위한 배열
			i = 0;
			for(char ch : candidate) charSet[i++] = ch;
			if(candidateSize < k) k = candidateSize; // 배울단어 수가 배워야할 문자 수보다 많을 때 동일하게 맞춤
			
			dfs(new HashSet<>(), 0, 0); // 새로운 해쉬셋으로 집어넣음
		}
		
		System.out.println(preCount+max);
		br.close();
		
	}
	
	private static void dfs(HashSet<Character> picked, int idx, int pick) {
		int i, j, count=0, wordSize;
		if(pick == k) {
			boolean impossible; // 이 단어는 못배움 여부
			for(i=0; i<wordSetSize; i++) {
				impossible = false; // 가능하다침
				wordSize = wordSet[i].length();
				for(j=0; j<wordSize; j++) {
					if(!picked.contains(wordSet[i].charAt(j))) {
						//안들어있으면
						impossible = true;
						break; //없는거 발견되면 바로 탐색 나감
					}
				}
				if(!impossible) count++; // 셀수있으면 증가
			}
			if(max < count) max = count;
			return ;
		}
		for(i = idx; i<candidateSize; i++) {
			if(!visit[i]) { // 아직 방문 안함
				visit[i] = true;
				picked.add(charSet[i]); // 배워야할 단어들 꺼로 배웠다고 침
				
				dfs(picked, i, pick+1); // i번부터 드갔다 (전에껀안봄)
				
				visit[i] = false;
				picked.remove(charSet[i]);
			}
		}
		
		
	}
}
*/

