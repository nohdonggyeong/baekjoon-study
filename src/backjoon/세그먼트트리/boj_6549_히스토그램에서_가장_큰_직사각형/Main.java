package backjoon.세그먼트트리.boj_6549_히스토그램에서_가장_큰_직사각형;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] nums;
	static long[] tree;
	static long area, maxArea;
	static final int INF = 1_000_000_000;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			
			StringTokenizer st;
			while (true) {
				st = new StringTokenizer(br.readLine());
				n = Integer.parseInt(st.nextToken());
				if (n == 0) {
					break;
				}
				
				nums = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					nums[i] = Integer.parseInt(st.nextToken());
				}
				
				tree = new long[n * 4];
				init(1, n, 1);
				
				maxArea = 0;
				for (int i = 1; i <= n; i++) {
					for (int j = i; j <= n; j++) {
						area = (j - i + 1) * query(1, n, 1, i, j);
						maxArea = Math.max(maxArea, area);
					}
				}
				sb.append(maxArea).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static long init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = nums[start];
		}
		
		int mid = (start + end) / 2;
		return tree[node] = Math.min(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
	}
	
	static long query(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return INF;
		}
		
		if (left <= start && right >= end) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		return Math.min(query(start, mid, node * 2, left, right), query(mid + 1, end, node * 2 + 1, left, right));
	}
}
