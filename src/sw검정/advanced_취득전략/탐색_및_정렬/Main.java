package sw검정.advanced_취득전략.탐색_및_정렬;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main {
	static int T, N;
	static List<Node> inputList;
	static List<Integer> outputList;
	
	static class Node implements Comparable<Node> {
		private int x;
		private int y;
		private int n;
		
		public Node (int x, int y, int n) {
			this.x = x;
			this.y = y;
			this.n = n;
		}
		
		@Override
		public int compareTo(Node o) {
			if (this.x == o.x) {
				return y - o.y; // 오름차순
			}
			return x - o.x; // 오름차순
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			inputList = new ArrayList<>();
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				inputList.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), n + 1));
			}
			Collections.sort(inputList);
			
//			System.out.println();
//			for (Node node : inputList) {
//				System.out.println(String.valueOf(node.x) + ", " + String.valueOf(node.y));
//			}
//			System.out.println();
			
			// x를 증가시키면서 최소 y 값 1개씩을 저장할 것이며
			// x y 모두 오름차순이기 때문에 x가 같은 경우는 최초만을 저장하고 y는 이전보다 작아져야 한다.
			outputList = new ArrayList<>();
			outputList.add(inputList.get(0).n);			
			int x = inputList.get(0).x;
			int y = inputList.get(0).y;
			inputList.remove(0);
			
			for (Node node : inputList) {
				if (node.x > x && node.y < y) {
					outputList.add(node.n);
					x = node.x;
					y = node.y;
				}
			}
			
			sb.append("#").append(t);
			for (Integer el : outputList) {
				sb.append(" ").append(el);
			}
			sb.append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
