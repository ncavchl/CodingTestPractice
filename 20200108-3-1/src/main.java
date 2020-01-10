import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

//import java.util.HashSet;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

//import java.util.*;

/*
 백준  1837 골드바흐의 추측
 */
public class main {
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final int N = 100;
        boolean [] b = new boolean[N+1];
        List<Integer> list = new ArrayList<>();
 
        Arrays.fill(b, true);
 
        for(int i=3; i*i <= N; i+=2) {
            if(b[i]) //똑같은 배수를 돌 필요는 없다. i=4일 때 4의 배수는 2의 배수
                for(int j=i*i; j<=N; j+=i) {
                    b[j] = false;
                    System.out.println(j);
                    if(j==6) System.out.println(6 + " - ");
                }    
        }
        for(int i=3; i<=N; i+=2)
            if(b[i])
                list.add(i);
 
        while(true) {
            int n = scan.nextInt();
            boolean isWrong = true;
            if(n==0)
                break;
            for(int i=0; i<list.size(); i++)
                if(b[n-list.get(i)]) {
                    System.out.println(n+ " = " + list.get(i) +" + " + (n-list.get(i)));
                    isWrong = false;
                    break;
                }
            if(isWrong) //입력만 정상적이라면 틀릴 경우는 사실 없다.
                System.out.println("Goldbach's conjecture is wrong.");
        }
    }

}