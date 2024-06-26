package backjoon.알고리즘_분류.세그먼트트리.boj_13544_수열과_쿼리_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int[] A;
	static ArrayList<Integer>[] tree;
	
	static void init(int start, int end, int node) {
		if (start == end) {
			tree[node] = new ArrayList<>();
			tree[node].add(A[start]);
			return;
		}
		
		int mid = (start + end) / 2;
		init(start, mid, node * 2);
		init(mid + 1, end, node * 2 + 1);
		
		tree[node] = new ArrayList<>();
		tree[node].addAll(tree[node * 2]);
		tree[node].addAll(tree[node * 2 + 1]);
		Collections.sort(tree[node]);
		return;
	}
	
	static int query(int start, int end, int node, int left, int right, int number) {
		if (right < start || left > end) {
			return 0;
		}
		
		if (left <= start && end <= right) {
			if (tree[node].get(0) > number) {
				return tree[node].size();
			} else if (tree[node].get(tree[node].size() - 1) <= number) {
				return 0;
			} else {
				int index = Collections.binarySearch(tree[node], number);
				if (index >= 0) {
					return tree[node].size() - index - 1;
				} else {
					return tree[node].size() + index + 1;
				}
			}
		}
		
		int mid = (start + end) / 2;
		int leftChild = query(start, mid, node * 2, left, right, number);
		int rightChild = query(mid + 1, end, node * 2 + 1, left, right, number);
		return leftChild + rightChild;
	}
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			int N = Integer.parseInt(br.readLine());
			A = new int[N + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			tree = new ArrayList[N * 4];
			init(1, N, 1);
			
			int M = Integer.parseInt(br.readLine());
			int a, b, c, i, j, k;
			int last_ans = 0;
			StringBuilder sb = new StringBuilder();
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				i = a ^ last_ans;
				j = b ^ last_ans;
				k = c ^ last_ans;
				
				last_ans = query(1, N, 1, i, j, k);
				sb.append(last_ans).append("\n");
			}
			
			bw.write(sb.deleteCharAt(sb.length() - 1).toString());
			bw.flush();
		}
	}
}

