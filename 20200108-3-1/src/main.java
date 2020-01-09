import java.io.BufferedReader;
import java.util.HashMap;
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
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;


//import java.util.*;

/*
 백준  1991 - 트리순회
 https://hugssy.tistory.com/136
 https://am003507.tistory.com/142
 
 */
public class main {
	
	//static String[] tree;
	static ArrayList<String> tree;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		int N = Integer.parseInt(st.nextToken());

		//tree = new String[N*2 + 1];
		tree = new ArrayList<String> ();
		tree.add("");
		
		
		st = new StringTokenizer(br.readLine()); 
		
		String f = st.nextToken();
		String l = st.nextToken();
		String r = st.nextToken();
		
		
		tree.add(f);
		tree.add(l);
		tree.add(r);
	
		for(int i=2; i<=N; i++) {
			
			st = new StringTokenizer(br.readLine()); 
			
			String mid = st.nextToken();
			String left = st.nextToken();
			String right = st.nextToken();
			for(int j=2; j<= tree.size(); j++) {
			//	if(tree.get(j).equals(mid)) {
			//		tree.set(j*2, left);
			//		tree.set(j*2+1, right);
			//	}
			}
			
		}		
		
		int le = tree.size();
		for(int k=1; k< le; k++) 
			System.out.print(tree.get(k));
		
	}
	
	public void addTree() {
		
	}
}

class Trie{
	TrieNode root = new TrieNode();
	
	void insert(String word) {
		TrieNode current = root;
		for(int i=0; i<word.length(); i++) {
			int wordIndex = word.charAt(i) - 'A';
			if(current.children[wordIndex] == null) {
				current.children[wordIndex]current = new TrieNode();
			}
			current = current.children[wordIndex];
		}
		current.isEnd = true;
	}
	
	boolean containsNode(String word) {
		TrieNode current = root;
		for(int i=0; i<word.length(); i++;) {
			int wordIndex = word.charAt(i) - 'A';
			if(current.children[wordIndex] == null) {
				return false;
			}
			current = current.children[wordIndex];
		}
		return current.isEnd;
	}
	
}
	
