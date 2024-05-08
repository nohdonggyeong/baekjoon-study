package backjoon.알고리즘_분류.다이나믹프로그래밍.boj_2098_외판원_순회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, statusFullBit;
	static final int INF = 987654321;
	static int[][] W, dp;
	
	static int tsp(int now, int statusVisitedBit) {
		if (statusVisitedBit == statusFullBit) { // 모든 도시 방문 완료
			return W[now][0] == 0
					? INF // 경로 없으면 INF로 탐색 무효화 (Math.min)
					: W[now][0]; // 경로가 존재하면 w[now][0]
		}
		
		if (dp[now][statusVisitedBit] != -1) { // 이미 방문한 도시 
			return dp[now][statusVisitedBit];
		}
		
		dp[now][statusVisitedBit] = INF; // 해당 도시에 출석 표시
		
		for (int i = 0; i < N; i++) { // 방문하지 않은 도시 탐색 
			int next = statusVisitedBit | (1 << i); // next : i 도시 방문
			if (W[now][i] == 0 || (statusVisitedBit & (1 << i)) != 0) {
				continue; // 경로가 없거나 i 도시를 이미 방문했을 경우
			}
			dp[now][statusVisitedBit] = Math.min(dp[now][statusVisitedBit], tsp(i, next) + W[now][i]);
		}
		
		return dp[now][statusVisitedBit];
	}
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			N = Integer.parseInt(br.readLine()); // 도시 최대 번호
			statusFullBit = (1 << N) - 1; // 모든 도시 방문 상태 (e.g. 2진수로 N개의 1 구성 1111..1 -> 10진수)

			W = new int[N][N]; // 이동 비용
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					W[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp = new int[N][statusFullBit]; // 각 도시에서 지점 방문한 상태의 이동 비용 합계 저장
			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], -1);
			}
			
			bw.write(String.valueOf(tsp(0, 1))); // 0번도시 부터 탐색 시작 (check: 0001)
			bw.flush();
		}
	}

}
