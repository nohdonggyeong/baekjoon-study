package PRO검정.배달;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N, M, D;
	static long[] tree;
	static long deliverableHomeCount, deliverablePairCount;
	
	static final int NEGATIVE_COMPENSATION = 1_000_000_001; // 음식점 좌표 -1,000,000,000 ~ 1,000,000,000 범위를 1 ~ 2,000,000,001로 보정하기 위한 값
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());

			tree = new long[N * 4];
			st = new StringTokenizer(br.readLine());
			long index;
			for (int n = 1; n <= N; n++) {
				index = Long.parseLong(st.nextToken()) + NEGATIVE_COMPENSATION;
				update(1, N, 1, index, 1);
			}
			
			deliverableHomeCount = 0;
			deliverablePairCount = 0;
			int x, y, overD;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				overD = Math.abs(y) - D;
				if (overD >= 0) {
					deliverableHomeCount++;
					deliverablePairCount += query(1, N, 1, restaurantLocationOrder.get(x - overD), restaurantLocationOrder.get(x + overD));
				}
			}
			
			sb.append(deliverableHomeCount).append(" ").append(deliverablePairCount);
			bw.write(sb.toString());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void update(int start, int end, int node, int index, int diff) {
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
	
	static int query(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}
		
		if (left <= start && right >= end) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		return query(start, mid, node * 2, left, right) + query(mid + 1, end, node * 2 + 1, left, right);
	}
}
