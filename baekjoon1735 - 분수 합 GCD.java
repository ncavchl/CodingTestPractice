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
 ����  1991 - Ʈ����ȸ
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


class SegmentTree2{
	long[] tree;
	long[] nums;
	
	public void prints(){
		int size = (int) Math.pow(2, Math.ceil(Math.log(nums.length)/Math.log(2))+1); // �ݿø�
		//for(int i=0 ; i<size; i++)
		//	System.out.println(i + "����� �� " + tree[i] );
	}
	
	public SegmentTree2(long[] nums) {
		this.nums = nums;
		int size = (int) Math.pow(2, Math.ceil(Math.log(nums.length)/Math.log(2))+1);
		tree = new long[size];
	}
	
	public long makeTree(int left, int right, int node) {
		//System.out.println("make " + left + " " + right + " " + node + " " );
		if(left == right) { // ���� �ٴڿ� �������� ��
			return tree[node] = nums[left];
		}
		
		int mid = (left + right) / 2;
		tree[node] += makeTree(left, mid, node*2);
		tree[node] += makeTree(mid+1, right, node*2 + 1);
		
		//System.out.println("make " + left + " " + right + " " + node + " " + tree[node]);
		return tree[node];
  	}
	//��û�ϴ� �Լ� 
	public long query(int node, int left, int right, int targetLeft, int targetRight) {
		if(targetRight < left || right < targetLeft) {
			return 0;
		}else if(targetLeft <= left && targetRight >= right){
		//	System.out.println("    " + tree[node] + " " + node);
			return tree[node];
		}else {
			int mid = (left + right) / 2;
			return query(node*2, left, mid, targetLeft, targetRight) + query(node*2+1, mid+1, right, targetLeft, targetRight);
		}
	}
	
	public void update(int node, int left, int right, int targetIndex, long diff) {
		//System.out.println("----------" + diff);
		//ex)1,8,1,2 // ����������
		if(targetIndex < left || right < targetIndex) {
			return;
		}
		else {
		//	System.out.println(node + "�ٲ����    " + tree[node]);
			
			tree[node] += diff;
			if(left==right) {
			//	System.out.println(tree[node]+ "�ٲ� - " + left + " " + right + " dif" + diff);
				return ;
			}
			else {
				//tree[node] += diff;
			//	System.out.println("update ---" + tree[node] + " " + "" + diff);
				int mid = (left+right) /2;
				update(node*2, left, mid, targetIndex, diff);
				update(node*2+1, mid+1, right, targetIndex, diff);
			}
			
		}
	
	}	
}	

class Minheap{
	List<Integer> heap;
	
	public Minheap() { // ����
		heap = new ArrayList<> ();
		heap.add((int)Math.pow(2,  32));
	}
	
	public int top() {
		int h = heap.get(0);
		return h;
	}
	
	public void insert(int val) {
		heap.add(val);
		int current = heap.size() -1;
		//int parent = current / 2;
		
		heap.set(0, heap.get(current) * 1);
		
		while(current > 1 && heap.get(current/2) < heap.get(current)) { // �θ𺸴� �׻� �ڽ��� ũ�� ��.
			int tmp = heap.get(current/2);
			heap.set(current/2, heap.get(current));
			heap.set(current,  tmp);
			
			current = current / 2;
			
			heap.set(0, heap.get(current) * 1);
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
		
		//���� ����
		int temp = heap.get(heap.size() - 1);
		heap.set(1, temp);
		heap.remove(heap.size() - 1);
		/*
		int s = 1;
		while(s * 2 < heap.size() - 1  ) {
			//�¿� ��� �� �� ���� �� - �� �߿� �����Ŷ� ��ȯ 
			//�� �� ��尡 ���� �� 
			if(s*2 > heap.size() - 1) break;
			
			
			//�������� ���� ��
			if(s*2+1 > heap.size()) {
				int min;
				if(heap.get(s*2) < heap.get(s)) {
					min = heap.get(s*2);
					heap.set(s*2, heap.get(s));
					heap.set(1, min);
				}
			}
			
			// �¿� ��� �Ѵ� ���� ��
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
			int max = heap.get(pos*2);
			int maxPos = pos*2;
			
			//�¿� �ִ��� �Ǻ� �� �� ������ ���� 
			if(((pos*2+1) < heap.size()) && max < heap.get(pos*2+1)) { // ������ �ְ� �������� ������ Ŭ�� 
				max=heap.get(pos*2+1);
				maxPos = pos*2 + 1;
			}
			if(heap.get(pos) < max)
				break;
			
			int tempp = heap.get(pos);
			heap.set(pos,  heap.get(maxPos));
			heap.set(maxPos,  tempp);
			pos = maxPos;
		}
		
		
		
		return delete;
	}
	
}	

	
