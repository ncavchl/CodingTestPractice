import java.util.*;
import java.io.*;


public class Main {
	static int V, E; // ���� V 1~20000 / ���� 1~300000
	static int startV;
	
	static int[] visited;
	static int[] dist;
	static HashMap<Integer, LinkedList<edge>> maps;
	
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        startV = Integer.parseInt(st.nextToken());
        
        maps = new HashMap<>();
        visited = new int[V+1];
        dist = new int[V+1];
        
        //�Ÿ��� �ִ� ������ ä���α�
        for(int i=1; i<V+1; i++) {
        	dist[i] = Integer.MAX_VALUE-1;
        }
        
        for(int i=0; i<E; i++) {
        	//���� �Է� �ޱ�
        	st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	
        	if(!maps.containsKey(start)) {
        		maps.put(start, new LinkedList<>()); // �ؽ��߰�
        	}
        	maps.get(start).add(new edge(start, end, cost));
        }
        
        dist[startV] = 0; // ������
        
        
        for(int i=0; i<V; i++) {
        	//�ּҰ� ������ ���� Ȯ��
        	int mincost = Integer.MAX_VALUE;
        	int from = -1;
        	//ó�� �ʱ�ȭ
        	for(int k=1; k<dist.length; k++) {
        		System.out.println(i + " " + dist[k] + " " + mincost);
        		if(visited[k] == 0 && mincost > dist[k]) {
        			from = k;
        			mincost = dist[k];
        			System.out.println(i + " - " + k);
        		}
        	}
        	
        	visited[from] = 1;
        	
        	//from���� ����ϴ� ��ΰ� ���� �� ó��
        	if(maps.containsKey(from)) {
        		for(edge next : maps.get(from)) {
        			int to = next.to;
        			int cost = next.cost;
        			//from���� �� ���� ���Ǿ� �ְ� ���� �� ���� ��� 
        			if(dist[from] != Integer.MAX_VALUE-1 && dist[to] > dist[from] + cost) {
        				dist[to] = dist[from] + cost;
        			}
        			
        		}
        	}
        	

        }
        for(int i=1; i<dist.length; i++) {
        	if(dist[i] != Integer.MAX_VALUE - 1) {
        		System.out.println(dist[i]);
        	}
        	else
        		System.out.println("INF");
        }
        
    }
}
class edge {
	public int from;
	public int to;
	public int cost;
	
	public edge(int a, int b, int c) {
		
		this.from = a;
		this.to = b;
		this.cost = c;
	}
}
