package 알고리즘.세그먼트트리.boj_2042_구간_합_구하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2 {
	static int N, M, K;
	static long[] arr, tree;
	
	// start: 시작 인덱스, end: 끝 인덱스
	static long init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = arr[start];
		}
		
		int mid = (start + end) / 2;
		
		// 재귀로 두 부분으로 나누고, 그 합을 현재 노드의 값으로 함
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}
	
	// start: 시작 인덱스, end: 끝 인덱스
	// index: 구간 합을 수정하고자 하는 노드
	// diff: 업데이트 보정 값
	static void update(int start, int end, int node, int index, long diff) {
		// 범위 밖에 있는 경우
		if (index < start || index > end) {
			return;
		}
		
		// 범위 안에 있으면 올라가며 다른 원소도 갱신
		tree[node] += diff;
		if (start == end) {
			return;
		}
		
		int mid = (start + end) / 2;
		update(start, mid, node * 2, index, diff);
		update(mid + 1, end, node * 2 + 1, index, diff);
	}
	
	// start: 시작 인덱스, end: 끝 인덱스
	// left, right: 구간 합을 구하고자 하는 범위
	static long sum(int start, int end, int node, int left, int right) {
		// 범위 밖에 있는 경우
		if (left > end || right < start) {
			return 0;
		}
		
		// 범위 안에 있는 경우
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		// 그렇지 않다면, 두 부분으로 나눠 합을 구하기
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
	
	public static void main(String[] args) {
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new long[N + 1]; // arr 배열은 N개의 수 집합
			for (int i = 1; i <= N; i++) { // i는 1부터 시작
				arr[i] = Long.parseLong(br.readLine());
			}
			
			// 2^k >= N을 만족하는 k의 최솟값을 구한 후
			// 2^k * 2를 tree 배열의 크기로 정의
			int k = (int) Math.ceil(Math.log(N) / Math.log(2));
			int size = (int) Math.pow(2, k + 1);
			tree = new long[size];
			
			// 사이즈를 구하는 과정이 귀찮으면, 단순히 N * 4 사이즈 사용
			// tree = new long[N * 4];
			
			// tree 배열 값 채우기
			init(1, N, 1); // i는 1부터 시작
			
			for (int i = 0; i < M + K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				
				if (a == 1) {
					long diff = c - arr[b];
					arr[b] = c;
					update(1, N, 1, b, diff);
				} else if (a == 2) {
					sb.append(sum(1, N, 1, b, (int) c)).append("\n");
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
