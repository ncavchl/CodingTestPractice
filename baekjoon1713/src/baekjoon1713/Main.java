package baekjoon1713;
//import java.util.*;
//import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections; //sort 를 위한 클래스 

/*
20200106 백준1713 후보추천 / 사진첩 
정렬
*/


public class Main {
	static class Student implements Comparable<Student>{
		//sort 기준을 위해 비교하는 함수 오버라이딩하려고 Comparable
		int num; //들어온 순서...?
		int recommend; //추천수
		
		Student(int num, int recommend){
			this.num = num;
			this.recommend = recommend;
		}
		
		@Override
		public int compareTo(Student s) {
			if(this.num > s.num)
				return 1; // 오름차순
			return -1;
		}
		
	}
	
	public static void main(String args[]) throws Exception{
		Scanner sc = new Scanner(System.in); 
		int frame; // 액자수
		int rec; // 추천표 수
		
		frame = sc.nextInt();
		ArrayList<Student> list = new ArrayList<Student>();
		for(int i=0; i<frame; i++) list.add(new Student(0,0)); // 액자들어갈 학생정보세팅
		
		rec = sc.nextInt();
		while(rec-- > 0) {
			int recommendnum = sc.nextInt();
			boolean state = false;
			
			for(int i=0; i<list.size(); i++) { // 액자사이즈
				if(list.get(i).num == recommendnum) { //얘가 추천받으면 추천표수 증가
					list.get(i).recommend++;
					state = true;
				}		
			}
			
			if(state) continue; // 있는 번호면 다음 while문
			
			list.add(new Student(recommendnum, 1)); // 첫 추가됨
			int min = 1001; // 최대 추천수 1000
			for(int i=0; i<list.size() - 1; i++) {
				//일단 넣고 최고 빼고 최저추천수값 찾기
				if(list.get(i).recommend < min)
					min = list.get(i).recommend;
			}
			for(int i=0; i<list.size() - 1; i++) {
				//최고 빼고 최저인거 지우기
				if(list.get(i).recommend == min) {
					list.remove(i);
					break; // for문 break
				}
			}
			
		}//while
		Collections.sort(list); // 액자 리스트 내림차순 정렬
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).num == 0) continue; // 액자 3갠데 학생 2명꺼만 나왔을수도있음
			else System.out.print(list.get(i).num + " ");
		}
		
		
	}
	
}