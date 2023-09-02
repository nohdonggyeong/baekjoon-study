package 단계별로_풀어보기.정렬.boj_1181_단어_정렬_문자열_compareTo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static class Node implements Comparable<Node> {
		private String word;
		
		public Node(String word) {
			this.word = word;
		}

		@Override
		public int compareTo(Node o) {
			if (word.length() > o.word.length()) {
				return 1;
			} else if (word.length() == o.word.length()) {
				return word.compareTo(o.word);
			}
			return -1;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		List<Node> nodeList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			
			boolean flag = true;
			for (int j = 0; j < nodeList.size(); j++) {
				if (str.equals(nodeList.get(j).word)) {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				nodeList.add(new Node(str));
			}
		}		
		
		Collections.sort(nodeList);
		
		for (int i = 0; i < nodeList.size(); i++) {
			sb.append(nodeList.get(i).word).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
