package AD검정.ad_230421_와트의_카드게임;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_bak1 {
	static int T, K, Q, answer;
	static Score nowScore;
	static int[] deals;
	
	static class Score {
		private int watt;
		private int hans;
		private int snow;
		private int cnt;
		
		public Score(int watt, int hans, int snow, int cnt) {
			this.watt = watt;
			this.hans = hans;
			this.snow = snow;
			this.cnt = cnt;
		}
	}
	
	static void bfs(Score checkScore) {
		Queue<Score> queue = new LinkedList<>();
		queue.offer(nowScore);
		
		while (!queue.isEmpty()) {
			Score s = queue.poll();
			
			if (s.cnt != 0
					&& checkScore.watt == s.watt
					&& checkScore.hans == s.hans
					&& checkScore.snow == s.snow) {
						answer = 1;
						return;
			}

			if (s.cnt >= checkScore.cnt) {
				continue;
			}
			
			for (int i = 0; i < 3; i++) {
				switch (i) {
				case 0:
					queue.offer(new Score(s.watt + deals[s.cnt] * 2,
							s.hans - deals[s.cnt] < 0 ? 0 : s.hans - deals[s.cnt],
							s.snow - deals[s.cnt] < 0 ? 0 : s.snow - deals[s.cnt],
							s.cnt + 1));
					break;
				case 1:
					queue.offer(new Score(s.watt - deals[s.cnt] < 0 ? 0 : s.watt - deals[s.cnt],
							s.hans + deals[s.cnt] * 2,
							s.snow - deals[s.cnt] < 0 ? 0 : s.snow - deals[s.cnt],
							s.cnt + 1));
					break;
				case 2:
					queue.offer(new Score(s.watt - deals[s.cnt] < 0 ? 0 : s.watt - deals[s.cnt], 
							s.hans - deals[s.cnt] < 0 ? 0 : s.hans - deals[s.cnt], 
							s.snow + deals[s.cnt] * 2, 
							s.cnt + 1));
					break;
				default:
					break;
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			nowScore = new Score(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			K = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());

			deals = new int[K];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++) {
				deals[j] = Integer.parseInt(st.nextToken());
			}

			sb.append("#");
			sb.append(String.valueOf(i + 1));
			for (int j = 0; j < Q; j++) {
				answer = 0;
				st = new StringTokenizer(br.readLine());
				bfs(new Score(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), K));
				sb.append(" ");
				sb.append(String.valueOf(answer));
			}
			sb.append("\n");
			
		}
		
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}
}
