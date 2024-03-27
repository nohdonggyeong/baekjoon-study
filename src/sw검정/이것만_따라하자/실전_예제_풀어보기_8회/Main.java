package sw검정.이것만_따라하자.실전_예제_풀어보기_8회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {	
	static int T;
	static int Ri, Rj;
	static char[][] map;
	static boolean[][] shot;
	
	static void shootUp(int i, int j) {
		int ni = i - 1;
		int nj = j;
		if (ni < 0 || ni >= Ri || nj < 0 || nj >= Rj) {
			return;
		}
		if (map[ni][nj] == 'T') {
			shot[ni][nj] = true;
			return;
		}
		if (map[ni][nj] == 'G' || map[ni][nj] == 'W') {
			return;
		}
		shootUp(ni, nj);
	}
	
	static void shootDown(int i, int j) {
		int ni = i + 1;
		int nj = j;
		if (ni < 0 || ni >= Ri || nj < 0 || nj >= Rj) {
			return;
		}
		if (map[ni][nj] == 'T') {
			shot[ni][nj] = true;
			return;
		}
		if (map[ni][nj] == 'G' || map[ni][nj] == 'W') {
			return;
		}
		shootDown(ni, nj);
	}
	
	static void shootLeft(int i, int j) {
		int ni = i;
		int nj = j - 1;
		if (ni < 0 || ni >= Ri || nj < 0 || nj >= Rj) {
			return;
		}
		if (map[ni][nj] == 'T') {
			shot[ni][nj] = true;
			return;
		}
		if (map[ni][nj] == 'G' || map[ni][nj] == 'W') {
			return;
		}
		shootLeft(ni, nj);
	}
	
	static void shootRight(int i, int j) {
		int ni = i;
		int nj = j + 1;
		if (ni < 0 || ni >= Ri || nj < 0 || nj >= Rj) {
			return;
		}
		if (map[ni][nj] == 'T') {
			shot[ni][nj] = true;
			return;
		}
		if (map[ni][nj] == 'G' || map[ni][nj] == 'W') {
			return;
		}
		shootRight(ni, nj);
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			Ri = Integer.parseInt(st.nextToken());
			Rj = Integer.parseInt(st.nextToken());
			
			map = new char[Ri][Rj];
			shot = new boolean[Ri][Rj];
			for (int i = 0; i < Ri; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < Rj; j++) {
					map[i][j] = st.nextToken().charAt(0);
				}
			}
			
			for (int i = 0; i < Ri; i++) {
				for (int j = 0; j < Rj; j++) {
					if (map[i][j] == 'G') {
						shootUp(i, j);
						shootDown(i, j);
						shootLeft(i, j);
						shootRight(i, j);
					}
				}
			}

			int count = 0;
			for (int i = 0; i < Ri; i++) {
				for (int j = 0; j < Rj; j++) {
					if (shot[i][j]) {
						count++;
					}
				}
			}
			sb.append("#").append(count).append(" ").append(count).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
