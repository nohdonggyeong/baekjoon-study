package backjoon.유형별로_풀어보기.DFS.boj_2644_촌수계산;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, person1, person2, m, count;
	static boolean[] visit;
	static List<List<Integer>> relationship;
	
	static void dfs(int person, int depth) {
		if (person == person2) {
			count = depth;
			return;
		}
		
		List<Integer> list = relationship.get(person);
		for (int el : list) {
			if (!visit[el]) {
				visit[el] = true;
				dfs(el, depth + 1);
				visit[el] = false;
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
//		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		relationship = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			relationship.add(new ArrayList<>());
		}
		visit = new boolean[n + 1];
		
		st = new StringTokenizer(br.readLine());
		person1 = Integer.parseInt(st.nextToken());
		person2 = Integer.parseInt(st.nextToken());
		
		m = Integer.parseInt(br.readLine());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			relationship.get(parent).add(child);
			relationship.get(child).add(parent);
		}
		
		count = -1;
		dfs(person1, 0);
		
		bw.write(String.valueOf(count));
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println();
//		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
