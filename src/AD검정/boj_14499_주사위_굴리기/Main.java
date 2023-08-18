package AD검정.boj_14499_주사위_굴리기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M, x, y, K;
	static int[][] map;
	static int[] dr = {0, 0, -1, 1}; // 동, 서, 북, 남
	static int[] dc = {1, -1, 0, 0};
	static int[] dice = {0, 0, 0, 0, 0, 0}; // 바닥면, 천장면, 윗면, 오른쪽면, 아랫면, 왼쪽면
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < K; i++) {
			// 방향 입력
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int nx = x + dr[dir];
			int ny = y + dc[dir];
			
			// 벗어나지 못함
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				continue;
			} else {
				x = nx;
				y = ny;
			}
			
			// 회전
			int temp = 0;
			switch (dir) {
			case 0:
				temp = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[1];
				dice[1] = dice[5];
				dice[5] = temp;
				break;
			case 1:
				temp = dice[0];
				dice[0] = dice[5];
				dice[5] = dice[1];
				dice[1] = dice[3];
				dice[3] = temp;
				break;
			case 2:
				temp = dice[0];
				dice[0] = dice[2];
				dice[2] = dice[1];
				dice[1] = dice[4];
				dice[4] = temp;
				break;
			case 3:
				temp = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[1];
				dice[1] = dice[2];
				dice[2] = temp;
			default:
				break;
			}
			
			// 상단 출력
			bw.write(String.valueOf(dice[1]));
			bw.newLine();
			
			// 바닥 수 작업
			if (map[x][y] == 0) {
				map[x][y] = dice[0];
			} else {
				dice[0] = map[x][y];
				map[x][y] = 0;
			}
			

		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
