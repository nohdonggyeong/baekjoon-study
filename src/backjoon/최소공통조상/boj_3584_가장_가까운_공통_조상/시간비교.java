package backjoon.최소공통조상.boj_3584_가장_가까운_공통_조상;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 시간비교 {
	static int T, N;
	static int[] childToParent;
	static List<Integer> parentListA;
	static int result;
	
	static void dfsA(int a) {
		parentListA.add(a);
		
		int parent = childToParent[a];
		if (parent != 0) {
			dfsA(parent);
		}
	}
	
	static void backTrackingB(int b) {
		if (parentListA.contains(b)) {
			result = b;
			return;
		}
		
		int parent = childToParent[b];
		if (parent != 0) {
			backTrackingB(parent);
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			T = Integer.parseInt(br.readLine());
			for (int t = 1; t <= T; t++) {
				N = Integer.parseInt(br.readLine());
				
				childToParent = new int[N + 1];
				
				int parent, child;
				for (int n = 1; n < N; n++) {
					st = new StringTokenizer(br.readLine());
					parent = Integer.parseInt(st.nextToken());
					child = Integer.parseInt(st.nextToken());
					childToParent[child] = parent;
				}
				
				int a, b;
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				parentListA = new ArrayList<Integer>();
				dfsA(a);
				
				backTrackingB(b);
				sb.append(result).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
