package backjoon.알고리즘_분류.DFS.boj_3584_가장_가까운_공통_조상;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int T, N;
	static int[] tree;
	static int num1, num2;
	static List<Integer> ancestorList1, ancestorList2;
	
	static void dfs1(int cur) {
		int parent = tree[cur];
		if (parent == 0) {
			return;
		}
		ancestorList1.add(parent);
		dfs1(parent);
	}
	
	static void dfs2(int cur) {
		int parent = tree[cur];
		if (parent == 0) {
			return;
		}
		ancestorList2.add(parent);
		dfs2(parent);
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			
			T = Integer.parseInt(br.readLine());
			for (int t = 1; t <= T; t++) {
				N = Integer.parseInt(br.readLine());
				tree = new int[N + 1];
				
				int parent, child;
				for (int n = 1; n < N; n++) {
					st = new StringTokenizer(br.readLine());
					parent = Integer.parseInt(st.nextToken());
					child = Integer.parseInt(st.nextToken());
					tree[child] = parent;
				}
				
				st = new StringTokenizer(br.readLine());
				num1 = Integer.parseInt(st.nextToken());
				num2 = Integer.parseInt(st.nextToken());
				
				ancestorList1 = new ArrayList<Integer>();
				ancestorList2 = new ArrayList<Integer>();
				ancestorList1.add(num1);
				ancestorList2.add(num2);
				dfs1(num1);
				dfs2(num2);
				
				for (int el : ancestorList1) {
					if (ancestorList2.contains(el)) {
						bw.write(String.valueOf(el));
						bw.newLine();
						break;
					}
				}
			}
			
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
