package AD검정.ad_230421_와트의_카드게임;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int T, K, Q;
	static int[] points;
	static Scores[] checkScores;
	static boolean[] checkResult;
	
	static class Scores {
		private int watt;
		private int hans;
		private int snow;
		
		Scores(int watt, int hans, int snow) {
			this.watt = watt;
			this.hans = hans;
			this.snow = snow;
		}
	}
	
	public static void check(Scores scores) {
		for (int i = 0; i < checkScores.length; i++) {
			if (checkScores[i].watt == scores.watt
					&& checkScores[i].hans == scores.hans
					&& checkScores[i].snow == scores.snow) {
				checkResult[i] = true;
			}
		}
	}
	
	public static void dfs(Scores scores, int depth) {
		if (depth == K) {
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			// 0: watt win, 1: hans win, 2: snow win
			switch (i) {
			case 0:
				if (scores.hans - points[depth] < 0) {
					scores.watt += scores.hans;
					scores.hans = 0;
				} else {
					scores.watt += points[depth];
					scores.hans -= points[depth];
				}

				if (scores.snow - points[depth] < 0) {
					scores.watt += scores.snow;
					scores.snow = 0;
				} else {
					scores.watt += points[depth];
					scores.snow -= points[depth];
				}
				break;
			case 1:
				if (scores.watt - points[depth] < 0) {
					scores.hans += scores.watt;
					scores.watt = 0;
				} else {
					scores.hans += points[depth];
					scores.watt -= points[depth];
				}

				if (scores.snow - points[depth] < 0) {
					scores.hans += scores.snow;
					scores.snow = 0;
				} else {
					scores.hans += points[depth];
					scores.snow -= points[depth];
				}
				break;
			case 2:
				if (scores.watt - points[depth] < 0) {
					scores.snow += scores.watt;
					scores.watt = 0;
				} else {
					scores.snow += points[depth];
					scores.watt -= points[depth];
				}

				if (scores.hans - points[depth] < 0) {
					scores.snow += scores.hans;
					scores.hans = 0;
				} else {
					scores.snow += points[depth];
					scores.hans -= points[depth];
				}
				break;
			default:
				break;
			}
			check(scores);
			dfs(scores, depth + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			
			st = new StringTokenizer(br.readLine());
			Scores scores = new Scores(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			K = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			points = new int[K];
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < K; k++) {
				points[k] = Integer.parseInt(st.nextToken());
			}

			checkScores = new Scores[Q];
			for (int q = 0; q < Q; q++) {
				st = new StringTokenizer(br.readLine());
				checkScores[q] = new Scores(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			checkResult = new boolean[Q];
			dfs(scores, 0);
			
			sb.append("#").append(t + 1);
			for (boolean r : checkResult) {
				sb.append(" ").append(r ? "1" : "0");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
