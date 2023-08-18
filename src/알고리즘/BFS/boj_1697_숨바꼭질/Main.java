package 알고리즘.BFS.boj_1697_숨바꼭질;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	private static int N, K;
	private static boolean[] visited = new boolean[100001];
	
	private static int bfs() {
		Queue<Node> cmdQ = new LinkedList<>();
		
		cmdQ.add(new Node(N, 0));
		visited[N] = true;
		
		while (!cmdQ.isEmpty()) {
			Node node = cmdQ.remove();
			
			// return time
			if (node.location == K) {
				return node.time;
			}
			
			// go to X - 1
			if (node.location - 1 >= 0
					&& !visited[node.location - 1]) {
				visited[node.location - 1] = true;
				cmdQ.add(new Node(node.location - 1, node.time + 1));
			}
			
			// go to X + 1
			if (node.location + 1 <= 100000
					&& !visited[node.location + 1]) {
				visited[node.location + 1] = true;
				cmdQ.add(new Node(node.location + 1, node.time + 1));
			}
			
			// go to 2 * X
			if (2 * node.location <= 100000
					&& !visited[2 * node.location]) {
				visited[2 * node.location] = true;
				cmdQ.add(new Node(2 * node.location, node.time + 1));
			}
		}
		
		return -1;
	}
	
	private static class Node {
		int location;
		int time;
		
		Node(int location, int time) {
			this.location = location;
			this.time = time;
		}
	}
	
	public static void main(String arvs[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] inputStrArr = br.readLine().split(" ");
		N = Integer.parseInt(inputStrArr[0]);
		K = Integer.parseInt(inputStrArr[1]);
		
		bw.write(String.valueOf(bfs()));
		bw.flush();
		
		bw.close();
		br.close();
	}

}
