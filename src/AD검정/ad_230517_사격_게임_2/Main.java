package AD검정.ad_230517_사격_게임_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int H, W;
	static int[][] D;
	static boolean[][] visited;
	
	static int maxPoint;
	static void solution() {
		// 가로 세로 선택지 list
		// 그 중 3개 선택
		// dfs로 maxPoint 탐색
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			D = new int[H][W];
			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					D[h][w] = Integer.parseInt(st.nextToken());
				}
			}

			visited = new boolean[H][W];

//			System.out.println();
//			for (int h = 0; h < H; h++) {
//				for (int w = 0; w < W; w++) {
//					System.out.print(String.valueOf(D[h][w]) + " ");
//				}
//				System.out.println();
//			}
			
			maxPoint = 0;
			solution();
			
			sb.append("#").append(t + 1).append(" ").append(maxPoint).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
