package backjoon.유형별로_풀어보기.BFS.boj_9205_맥주_마시면서_걸어가기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		private int x;
		private int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static String bfs(Node home, Node[] stores, Node pentaport) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[stores.length];
		
		q.offer(home);
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			
			if (Math.abs(pentaport.x - node.x) + Math.abs(pentaport.y - node.y) <= 1000) {
				return "happy";
			}
			
			for (int i = 0; i < stores.length; i++) {
				if (!visited[i]) {
					int nx = stores[i].x;
					int ny = stores[i].y;
					
					if (Math.abs(nx - node.x) + Math.abs(ny - node.y) <= 1000) {
						visited[i] = true;
						q.offer(new Node(nx, ny));
					}
				}
			}
		}
		
		return "sad";
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
	
		int t = Integer.parseInt(br.readLine());
		int inputX, inputY;
		for (int i = 0; i < t; i++) { // for: 테스트 케이스
			int n = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine()); // home node
			inputX = Integer.parseInt(st.nextToken());
			inputY = Integer.parseInt(st.nextToken());
			Node home = new Node(inputX, inputY);
			
			Node[] stores = new Node[n]; // stores node[]
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				inputX = Integer.parseInt(st.nextToken());
				inputY = Integer.parseInt(st.nextToken());
				stores[j] = new Node(inputX, inputY);
			}
			
			st = new StringTokenizer(br.readLine()); // pentaport node
			inputX = Integer.parseInt(st.nextToken());
			inputY = Integer.parseInt(st.nextToken());
			Node pentaport = new Node(inputX, inputY);
			
			bw.write(bfs(home, stores, pentaport));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
