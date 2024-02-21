package PRO검정.도서관;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {	
	static int T, N, Q;
	static List<Integer> history; // 계속해서 반납하는 책 번호를 추가하는 history 리스트
	static int[] tree; // 리프 노드에서 history에 대해 책이 현재 존재하면 1, 없으면 0으로 개수 저장
	static int total;
	
	static final int MAX_SIZE = 150_000; // N(100_000) + Q/2(50_000)
	
	static int init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = 1;
		}
		
		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
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
	
	static int sum(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}
		
		if (left <= start && right >= end) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		return sum(start, mid , node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
	
	static int binarySearch(int start, int end, int order) {
		do {
			int mid = (start + end) / 2;
			int count = sum(1, MAX_SIZE, 1, 1, mid);
			
			if (count < order) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		} while (start <= end);
		
		return start;
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			T = Integer.parseInt(br.readLine());
			for (int t = 1; t <= T; t++) {
				st = new StringTokenizer(br.readLine());
				N = Integer.parseInt(st.nextToken());
				Q = Integer.parseInt(st.nextToken());
				
				history = new ArrayList<Integer>();
				for (int n = 0; n <= N; n++) {
					history.add(n);
				}
				
				tree = new int[MAX_SIZE * 4];
				init(1, MAX_SIZE, 1);
				
				total = 0;
				for (int q = 0; q < Q; q++) {
					st = new StringTokenizer(br.readLine());
					int op = Integer.parseInt(st.nextToken());					
					if (op == 1) {
						int order = Integer.parseInt(st.nextToken());
						int realOrder = binarySearch(1, MAX_SIZE, order);
						int number = history.get(realOrder);
						total += number;
						update(1, MAX_SIZE, 1, realOrder, -1);
					} else if (op == 2) {
						int number = Integer.parseInt(st.nextToken());
						update(1, MAX_SIZE, 1, MAX_SIZE + 1, 1);
						history.add(number);
					}
				}
				
				sb.append("#").append(t).append(" ").append(total).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}