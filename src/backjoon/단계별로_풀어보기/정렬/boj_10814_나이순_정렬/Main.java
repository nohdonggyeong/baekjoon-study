package backjoon.단계별로_풀어보기.정렬.boj_10814_나이순_정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		private int age;
		private String name;
		
		public Node (int age, String name) {
			this.age = age;
			this.name = name;
		}

		@Override
		public int compareTo(Node o) {
			return age - o.age;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		List<Node> nodeList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			nodeList.add(new Node(Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		Collections.sort(nodeList);
		
		for (int i = 0; i < n; i++) {
			sb.append(nodeList.get(i).age).append(" ").append(nodeList.get(i).name).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
