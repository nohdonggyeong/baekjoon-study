package backjoon.알고리즘_분류.세그먼트트리.boj_13544_수열과_쿼리_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MainBak {
	static int[] A;
	static ArrayList<Integer>[] tree;
	
	static void build(int start, int end, int node) {
		if (start == end) {
			tree[node] = new ArrayList<>();
			tree[node].add(A[start]);
			return;
		}
		
		int mid = (start + end) / 2;
		build(start, mid, node * 2);
		build(mid + 1, end, node * 2 + 1);
		
		tree[node] = new ArrayList<>();
		tree[node].addAll(tree[node * 2]);
		tree[node].addAll(tree[node * 2 + 1]);
		Collections.sort(tree[node]);
		return;
	}
	
	
	static int query(int start, int end, int node, int left, int right, int number) {
		if (right < start || end < left) {
			return 0;
		}
		
		if (left <= start && end <= right) {
			if (tree[node].get(0) > number) {
				return tree[node].size();
			} else if (tree[node].get(tree[node].size() - 1) < number) {
				return 0;
			} else {
				int index = Collections.binarySearch(tree[node], number);
				if (index < 0) {
					return tree[node].size() + 1 + index;
				}
				return tree[node].size() - 1 - index;
			}
		}
		
		int mid = (start + end) / 2;
		return query(start, mid, node * 2, left, right, number) + query(mid + 1, end, node * 2 + 1, left, right, number);
	}
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			int N = Integer.parseInt(br.readLine());
			
			A = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				A[n] = Integer.parseInt(st.nextToken());
			}
			
			tree = new ArrayList[N * 4];
			build(1, N, 1);
			
			int M = Integer.parseInt(br.readLine());
			int last_ans = 0;
			int i, j, k;
			StringBuilder sb = new StringBuilder();
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				i = Integer.parseInt(st.nextToken()) ^ last_ans;
				j = Integer.parseInt(st.nextToken()) ^ last_ans;
				k = Integer.parseInt(st.nextToken()) ^ last_ans;
				
				last_ans = query(1, N, 1, i, j, k);
				sb.append(last_ans).append("\n");
			}
			
			bw.write(sb.deleteCharAt(sb.length() - 1).toString());
			bw.flush();
		}
	}
}

