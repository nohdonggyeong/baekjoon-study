package backjoon.최소신장트리.boj_1647_도시_분할_계획.prim_solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Next implements Comparable<Next> {
	int end;
	int weight;
	
	Next(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Next o) {
		return Integer.compare(this.weight, o.weight);
	}
}

public class Main {
	static int N, M;
	static List<Next>[] adjList;
	static int total, max;
	
	static void prim() {
		Queue<Next> queue = new PriorityQueue<Next>();
		queue.offer(new Next(1, 0));
		
		boolean[] visited = new boolean[N + 1];
		
		while (!queue.isEmpty()) {
			Next curNext = queue.poll();
			int curEnd = curNext.end;
			
			if (visited[curEnd]) {
				continue;
			}
			
			visited[curEnd] = true;
			int curWeight = curNext.weight;
			total += curWeight;
			max = Math.max(max, curWeight);
			
			for (Next next : adjList[curEnd]) {
				if (!visited[next.end]) {
					queue.offer(next);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
	
			adjList = new ArrayList[N + 1];
			for (int n = 1; n < N + 1; n++) {
				adjList[n] = new ArrayList<>();
			}
			
			int A, B, C;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				
				adjList[A].add(new Next(B, C));
				adjList[B].add(new Next(A, C));
			}
			
			total = 0;
			max = 0;
			prim();
			
			bw.write(String.valueOf(total - max));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
