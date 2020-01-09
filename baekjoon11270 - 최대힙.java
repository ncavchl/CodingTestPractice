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

//import java.util.*;

/*
 πÈ¡ÿ  11270 √÷¥Î»¸
 https://kim6394.tistory.com/222
 
 heap.add((int)Math.pow(2,  32));
 */
public class main{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		maxheap heap = new maxheap();
		
		for(int i=0; i<N; i++) {
			int val = Integer.parseInt(br.readLine());
			if(val==0) {
				System.out.println(heap.delete());
			}
			else {
				heap.insert(val);
			}
		}
	}
}

class maxheap{

		private ArrayList<Integer> heap;
		
		public maxheap() {
			heap = new ArrayList<>();
			heap.add((int)Math.pow(2,  32));
		}
		
		public void insert(int val) {
			heap.add(val);
			int p = heap.size() - 1;
			while(p>1 && heap.get(p/2) < heap.get(p)) {
				int t = heap.get(p/2);
				heap.set(p/2,  heap.get(p));
				heap.set(p, t);
				p = p/2;
			}
		}
		
		public int delete() {
			if(heap.size() - 1 < 1) {
				return 0;
			}
			int di = heap.get(1);
			
			heap.set(1,  heap.get(heap.size()-1));
			heap.remove(heap.size()-1);
			
			int p = 1;
			while((p*2) < heap.size()) {
				int max = heap.get(p*2);
				int mp = p*2;
				if(((p*2+1) < heap.size()) && max < heap.get(p*2+1)) {
					max = heap.get(p*2+1);
					mp = p * 2 + 1;
					
				}
				if(heap.get(p) > max) {
					break;
				}
				int t= heap.get(p);
				heap.set(p,  heap.get(mp));
				heap.set(mp,  t);
				p = mp;
			}
			
			return di;
		}
	
}