package 알고리즘.DFS.boj_1303_전쟁_전투;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M, white, blue, count;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	static void getLump(int r, int c, char ch) {
		map[r][c] = 'O';
		count += 1;
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nc >= 0 && nr < M && nc < N && map[nr][nc] == ch) {
				getLump(nr, nc, ch);
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[M][N];
		
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		white = 0;
		blue = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'W') {
					count = 0;
					getLump(i, j, 'W');
					white += Math.pow(count, 2);
				} else if (map[i][j] == 'B') {
					count = 0;
					getLump(i, j, 'B');
					blue += Math.pow(count, 2);
				}
			}
		}
		
		bw.write(String.valueOf(white));
		bw.write(" ");
		bw.write(String.valueOf(blue));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
