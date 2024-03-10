package backjoon.알고리즘_분류.다익스트라.boj_1446_지름길;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, D;
	static List<Node>[]	shortcuts;
	static int[] dist;
	static final int INF = 10001;
	
	static class Node {
		int end;
		int weight;
		
		Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			shortcuts = new ArrayList[D + 1];
			for (int n = 0; n <= D; n++) {
				shortcuts[n] = new ArrayList<Node>();
			}
			
			int start, end, weight;
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				weight = Integer.parseInt(st.nextToken());
				
				if (end <= D) {
					shortcuts[start].add(new Node(end, weight));	
				}
			}
			
			dist = new int[D + 1];
			Arrays.fill(dist, INF);
			dist[0] = 0;
			
			for (int cur = 0; cur <= D ; cur++) {
				if (cur > 0 && dist[cur] > dist[cur - 1] + 1) {
					dist[cur] = dist[cur - 1] + 1;
				}
				
				for (Node shortcut : shortcuts[cur]) {
					if (dist[shortcut.end] > dist[cur] + shortcut.weight) {
						dist[shortcut.end] = dist[cur] + shortcut.weight;
					}
				}
			}
			
			bw.write(String.valueOf(dist[D]));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
