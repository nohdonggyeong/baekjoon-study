package 알고리즘.세그먼트트리.boj_2243_사탕상자;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static SegmentTree sgtree = new SegmentTree(4 * 1_000_000);
	
	static class SegmentTree {
		long[] tree;
		
		SegmentTree(int n) {
			tree = new long[n];
		}
		
		long init(int start, int end, int node) {
			if (start == end) {
				return tree[node] = 0;
			}
			
			int mid = (start + end) / 2;
			return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
		}
		
		void update(int start, int end, int node, int index, int diff) {
			if (index < start || index > end) {
				return;
			}
			
			tree[node] += diff;
			
			if (start == end) {
				return;
			}
			
			int mid = (start + end) / 2;
			update(start, mid, node * 2, index, diff);
			update(mid + 1, end, node * 2 + 1, index, diff);
		}
		
		long sum(int start, int end, int node, int left, int right) {
			if (left > end || right < start) {
				return 0;
			}
			
			if (left <= start && right >= end) {
				return tree[node];
			}
			
			int mid = (start + end) / 2;
			return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
		}
	}
	
	static int binarySearch(int left, int right, long n) {
		int pl = left;
		int pr = right;
		
		do {
			int pc = (pl + pr) / 2;
			long sum = sgtree.sum(1, 1_000_000, 1, 1, pc);
			
			if (sum < n) {
				pl = pc + 1;
			} else if (sum >= n) {
				pr = pc - 1;
			}
		} while (pl <= pr);
		
		return pl;
	}
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			sgtree.init(1, 1_000_000, 1);

			n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				if (A == 1) {
					long B = Long.parseLong(st.nextToken());
					int res = binarySearch(1, 1_000_000, B);
					sb.append(res).append("\n");
					sgtree.update(1, 1_000_000, 1, res, -1);
				} else if (A == 2) {
					int B = Integer.parseInt(st.nextToken());
					int C = Integer.parseInt(st.nextToken());
					sgtree.update(1, 1_000_000, 1, B, C);
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
