package baekjoon1713;
//import java.util.*;
//import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections; //sort �� ���� Ŭ���� 

/*
20200106 ����1713 �ĺ���õ / ����ø 
����
*/


public class Main {
	static class Student implements Comparable<Student>{
		//sort ������ ���� ���ϴ� �Լ� �������̵��Ϸ��� Comparable
		int num; //���� ����...?
		int recommend; //��õ��
		
		Student(int num, int recommend){
			this.num = num;
			this.recommend = recommend;
		}
		
		@Override
		public int compareTo(Student s) {
			if(this.num > s.num)
				return 1; // ��������
			return -1;
		}
		
	}
	
	public static void main(String args[]) throws Exception{
		Scanner sc = new Scanner(System.in); 
		int frame; // ���ڼ�
		int rec; // ��õǥ ��
		
		frame = sc.nextInt();
		ArrayList<Student> list = new ArrayList<Student>();
		for(int i=0; i<frame; i++) list.add(new Student(0,0)); // ���ڵ� �л���������
		
		rec = sc.nextInt();
		while(rec-- > 0) {
			int recommendnum = sc.nextInt();
			boolean state = false;
			
			for(int i=0; i<list.size(); i++) { // ���ڻ�����
				if(list.get(i).num == recommendnum) { //�갡 ��õ������ ��õǥ�� ����
					list.get(i).recommend++;
					state = true;
				}		
			}
			
			if(state) continue; // �ִ� ��ȣ�� ���� while��
			
			list.add(new Student(recommendnum, 1)); // ù �߰���
			int min = 1001; // �ִ� ��õ�� 1000
			for(int i=0; i<list.size() - 1; i++) {
				//�ϴ� �ְ� �ְ� ���� ������õ���� ã��
				if(list.get(i).recommend < min)
					min = list.get(i).recommend;
			}
			for(int i=0; i<list.size() - 1; i++) {
				//�ְ� ���� �����ΰ� �����
				if(list.get(i).recommend == min) {
					list.remove(i);
					break; // for�� break
				}
			}
			
		}//while
		Collections.sort(list); // ���� ����Ʈ �������� ����
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).num == 0) continue; // ���� 3���� �л� 2���� ��������������
			else System.out.print(list.get(i).num + " ");
		}
		
		
	}
	
}