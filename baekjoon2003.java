import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/*백준2003 수들의 합*/

public class Main {
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//Scanner sc = new Scanner(System.in);
		
		int N; //1~10000
		int M; // 1~300000000
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		//시간복잡도 2N이 나오도록
		int cnt = 0;
		int window; // 꼭 포함하게 되는 수 
		int sum=0;
		boolean[] visit = new boolean[N];
		/*for(int i=0; i<N; i++) {
			sum = 0;
			for(int j=i; j<N; j++) {
				sum += arr[j];
				if(sum == M) {
					cnt++;
					break;
				}
			}
		}*/
		
		int start = 0; // 나아가면서 더하는 수 
		int end = 0; // 뒤에 남은 수 
		
		
		while(start<N) {
			sum += arr[start];
			if(sum == M) {
				cnt++;
				sum -= arr[end];
				end++; // 합한 경우 맞으면 처음값 더한거 빼고 그 다음으로 이동
			}
			else if(sum>M) {
				while(sum > M) {
					//end값 앞당기면서 빼기
					sum -= arr[end];
					end ++;
					
					if(sum == M) cnt++;
				}
			}

			//합히 작으면 start 는 계속 앞으로 나아가서 누적으로 더함.
			start++;
		}
		
		System.out.println(cnt); // 답출력
		
	}

}
