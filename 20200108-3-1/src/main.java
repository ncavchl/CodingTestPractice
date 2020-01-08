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
 백준  1927 최소 힙
 https://kim6394.tistory.com/222
 */

public class main {
	
	static int n;

	public static void main(String args[]) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());
		//BufferedReader br = new BufferedReader(new FileReader(fileName));

		// int n, k;

		// n = st.nextToken();
//		String fileName = "test.txt";
//		Scanner scanner = new Scanner(new File(fileName));
//		int n = scanner.nextInt();
//		
//		for(int i=0; i<n; i++)
//		{
//			int v = scanner.nextInt();
//			System.out.println(v);
//			
//		}
			

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		Minheap hip = new Minheap();
		
		for(int i=0; i<n; i++) {
			int c = sc.nextInt();
			if(c == 0) {

				System.out.println( hip.delete());
			}
			else {
				hip.insert(c);
			}
		}

		
		
	}

}

class Minheap{
	List<Integer> heap;
	
	public Minheap() { // 생성
		heap = new ArrayList<> ();
		heap.add(0);
	}
	
	public int top() {
		int h = heap.get(0);
		return h;
	}
	
	public void insert(int val) {
		heap.add(val);
		int current = heap.size() -1;
		//int parent = current / 2;
		
		while(current > 1 && heap.get(current/2) > heap.get(current)) { // 부모보다 항상 자식이 크면 됨.
			int tmp = heap.get(current/2);
			heap.set(current/2, heap.get(current));
			heap.set(current,  tmp);
			
			current = current / 2;
			
			//System.out.println(" - " + current + "ff");
		}

		
		//for(int i=0; i<heap.size(); i++) {
		//	System.out.print((i) + " - " + heap.get(i) + " ;");
		//}
		//System.out.println(" ");
	}
	
	public int delete() {
		if(heap.size() < 2) return 0;
		
		int delete = heap.get(1);
		
		//삭제 구현
		int temp = heap.get(heap.size() - 1);
		heap.set(1, temp);
		heap.remove(heap.size() - 1);
		/*
		int s = 1;
		while(s * 2 < heap.size() - 1  ) {
			//좌우 노드 둘 다 있을 때 - 둘 중에 작은거랑 교환 
			//그 뒤 노드가 없을 때 
			if(s*2 > heap.size() - 1) break;
			
			
			//우측노드는 없을 때
			if(s*2+1 > heap.size()) {
				int min;
				if(heap.get(s*2) < heap.get(s)) {
					min = heap.get(s*2);
					heap.set(s*2, heap.get(s));
					heap.set(1, min);
				}
			}
			
			// 좌우 노드 둘다 있을 때
			else if(heap.get(s) > heap.get(s*2) || heap.get(s) > heap.get(s*2+1)) {
				int min;
				if(heap.get(s*2) > heap.get(s*2+1)) {
					min = heap.get(s*2+1);
					heap.set(s*2+1, heap.get(s));
					heap.set(s, min);
				}
				else {
					min = heap.get(s*2);
					heap.set(s*2+1, heap.get(s));
					heap.set(s, min);
				}				
			}
	
			s++;
		}
		*/
		
		int pos = 1;
		while((pos * 2) < heap.size()) {
			int min = heap.get(pos*2);
			int minPos = pos*2;
			
			//좌우 있는지 판별 및 더 작은거 저장 
			if(((pos*2+1) < heap.size()) && min > heap.get(pos*2+1)) { // 우측이 있고 우측보다 좌측이 클때 
				min=heap.get(pos*2+1);
				minPos = pos*2 + 1;
			}
			if(heap.get(pos) < min)
				break;
			
			int tempp = heap.get(pos);
			heap.set(pos,  heap.get(minPos));
			heap.set(minPos,  tempp);
			pos = minPos;
		}
		
		
		
		return delete;
	}
	
}	
	
