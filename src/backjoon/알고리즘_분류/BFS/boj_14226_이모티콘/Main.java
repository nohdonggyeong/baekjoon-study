package backjoon.알고리즘_분류.BFS.boj_14226_이모티콘;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	private static int S;
	private static boolean[][] visited = new boolean [1001][1001]; // visited[count][clipboard];
	
	private static int bfs() {
		Queue<Node> cmdQ = new LinkedList<>();
		cmdQ.add(new Node(1, 0, 0));
		visited[1][0] = true;
		
		while (!cmdQ.isEmpty()) {
			Node node = cmdQ.remove();
			
			// return time
			if (node.count == S) {
				return node.time;
			}
			
			// copy
			cmdQ.add(new Node(node.count, node.count, node.time + 1));
			
			// paste
			if (node.clipboard > 0
					&& node.count + node.clipboard <= 1000
					&& !visited[node.count + node.clipboard][node.clipboard]) {
				visited[node.count + node.clipboard][node.clipboard] = true;
				cmdQ.add(new Node(node.count + node.clipboard, node.clipboard, node.time + 1));
			}
			
			// delete
			if (node.count > 0
					&& !visited[node.count - 1][node.clipboard]) {
				visited[node.count - 1][node.clipboard] = true;
				cmdQ.add(new Node(node.count - 1, node.clipboard, node.time + 1));
			}
		}
		
		return -1;
	}
	
	private static class Node {
		private int count;
		private int clipboard;
		private int time;
		
		Node(int count, int clipboard, int time) {
			this.count = count;
			this.clipboard = clipboard;
			this.time = time;
		}
		
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		S = Integer.parseInt(br.readLine());
		bw.write(String.valueOf(bfs()));
		bw.flush();
		bw.close();
		br.close();
	}
}
